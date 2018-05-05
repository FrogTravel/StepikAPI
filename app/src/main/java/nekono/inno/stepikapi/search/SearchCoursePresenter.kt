package nekono.inno.stepikapi.search

import android.util.Log
import android.view.KeyEvent
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import nekono.inno.stepikapi.util.API
import nekono.inno.stepikapi.util.Course
import nekono.inno.stepikapi.util.Meta
import nekono.inno.stepikapi.util.Reply

class SearchCoursePresenter(val view: SearchCourse.View) : SearchCourse.Presenter {
    var coursesList = arrayListOf<Course>()
    var pageNumber = 1
    var request = "Java"
    val api = API.create()

    var markedCourses = arrayListOf<Course>()

    override fun getCourses() = coursesList

    override fun start() {
        view.disableNextButton()
        view.disablePreviousButton()

        val temp = view.readCourses()
        if (temp != null) {
            markedCourses = temp
        }

        if (view.isNetworkAvailable()) {
            requestCourses()
        } else {
            coursesList = markedCourses
            view.showCourses()
        }

        view.setHintWord(request)

    }

    private fun requestCourses() {

        api.getSearchResults(request, pageNumber)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError)

    }

    private fun onNext(reply: Reply) {
        coursesList = reply.search_results

        coursesList.filter {
            it.marked = true
            val res = markedCourses.contains(it);
            it.marked = false
            res
        }
                .forEach { it.marked = true }
        Log.d("debugggg", "added: " + coursesList.toString())

        view.showCourses()

        updateButtons(reply.meta)
    }

    private fun onError(error: Throwable) {
        Log.e("error", error.toString())

        error.printStackTrace()
        requestCourses()

    }

    override fun previousPage() {
        pageNumber--
        requestCourses()
        Log.d("debugggg", "Previous Page")
    }

    override fun nextPage() {
        pageNumber++
        requestCourses()
        Log.d("debugggg", "Next Page")
    }

    private fun updateButtons(meta: Meta) {
        if (meta.has_next) view.activateNextButton() else view.disableNextButton()
        if (meta.has_previous) view.activatePreviousButton() else view.disablePreviousButton()
    }

    override fun keyClicked(v: View, keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            // сохраняем текст, введенный до нажатия Enter в переменную
            request = view.getSearchWord()

            pageNumber = 1
            requestCourses()

            return true
        }
        return false
    }

    override fun courseClicked(course: Course) {
        if (!course.marked) {
            course.marked = true
            markedCourses.add(course)
        } else {
            course.marked = false
            markedCourses.remove(course)
        }
        view.showCourses()
    }

    override fun activityPaused() {
        view.saveCourses(markedCourses)
    }


}