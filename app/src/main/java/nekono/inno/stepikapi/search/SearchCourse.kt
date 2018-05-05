package nekono.inno.stepikapi.search

import android.view.KeyEvent
import nekono.inno.stepikapi.util.Course

/**
 * Главный интерфейс для MVP
 */
interface SearchCourse {
    interface View {
        fun showCourses()
        fun disablePreviousButton()
        fun activatePreviousButton()
        fun disableNextButton()
        fun activateNextButton()
        fun getSearchWord(): String
        fun setHintWord(request : String)
        fun saveCourses(courses: ArrayList<Course>)
        fun isNetworkAvailable(): Boolean
        fun readCourses() : ArrayList<Course>?
        fun disableEditText()
        fun showAddedToMarkedToast()
        fun showRemovedFromMarkedToast()
    }

    interface Presenter {
        fun getCourses(): ArrayList<Course>
        fun start()
        fun nextPage()
        fun previousPage()
        fun keyClicked(v: android.view.View, keyCode: Int, event: KeyEvent): Boolean
        fun courseClicked(course: Course)
        fun activityPaused()
    }
}