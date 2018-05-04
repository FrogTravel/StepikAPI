package nekono.inno.stepikapi

import android.content.Context

class SearchCoursePresenter(val view: SearchCourse.View) : SearchCourse.Presenter {
    override fun getContext(): Context = view.getContextActivity()

    //TODO temp for Debug then check for cached or make get
    override fun getCourses() = arrayListOf(Course("1", 0.0, "https://stepik.org/media/cache/images/courses/187/cover/b5afd0216dbb45100bdcc6a607791cc2.png"),
                Course("2", 0.0, "https://stepik.org/media/cache/images/courses/187/cover/b5afd0216dbb45100bdcc6a607791cc2.png"),
                Course("3", 0.0, "https://stepik.org/media/cache/images/courses/187/cover/b5afd0216dbb45100bdcc6a607791cc2.png"))


}