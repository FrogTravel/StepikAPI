package nekono.inno.stepikapi

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class SearchCourseView : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_search)

        var recyclerView = findViewById<RecyclerView>(R.id.search_result)

        var courseArray = arrayListOf<Course>(Course("1", 0.0, ""),
                Course("2", 0.0, ""))

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = CourseResultAdapter(courseArray)
    }


}