package nekono.inno.stepikapi

import android.view.KeyEvent

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
        fun showProgressBar()
        fun hideProgressBar()
        fun disableEditText()
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