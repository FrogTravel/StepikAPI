package nekono.inno.stepikapi

import android.content.Context

interface SearchCourse {
    interface View {
        fun getContextActivity() : Context
    }

    interface Presenter {
        fun getCourses(): ArrayList<Course>
        fun getContext(): Context
    }
}