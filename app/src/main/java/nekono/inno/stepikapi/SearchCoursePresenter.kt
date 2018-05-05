package nekono.inno.stepikapi

import android.util.Log
import android.view.KeyEvent
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchCoursePresenter(val view: SearchCourse.View) : SearchCourse.Presenter {
    var coursesList  = arrayListOf<Course>()
    var pageNumber = 1
    var request = "Kotlin"
    val api = API.create()

    val markedCourses = arrayListOf<Course>()

    //TODO temp for Debug then check for cached or make get
    override fun getCourses() = coursesList

    override fun start(){
        view.disableNextButton()
        view.disablePreviousButton()
        view.setHintWord(request)

        requestCourses(pageNumber)
    }

    private fun requestCourses(page: Int){

        api.getSearchResults(request, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onNext, this::onError)
    }

    private fun onNext(reply: Reply){
        coursesList = reply.search_results
        Log.d("debugggg", "added: " + coursesList.toString())

        view.showCourses()

        updateButtons(reply.meta)
    }

    private fun onError(error: Throwable){
        Log.e("error", error.toString())

        error.printStackTrace()
        requestCourses(pageNumber)

    }

    override fun previousPage() {
        requestCourses(--pageNumber)
        Log.d("debugggg", "Previous Page")

    }

    override fun nextPage() {
        requestCourses(++pageNumber)
        Log.d("debugggg", "Next Page")

    }

    private fun updateButtons(meta: Meta){
        if(meta.has_next) view.activateNextButton() else view.disableNextButton()
        if(meta.has_previous) view.activatePreviousButton() else view.disablePreviousButton()
    }

    override fun keyClicked(v: View, keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
            // сохраняем текст, введенный до нажатия Enter в переменную
            request = view.getSearchWord()

            pageNumber = 1
            requestCourses(pageNumber)

            return true
        }
        return false
    }

    override fun courseClicked(course: Course) {
    }
}