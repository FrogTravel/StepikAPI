package nekono.inno.stepikapi

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

class SearchCourseView : Activity(), SearchCourse.View {

    val presenter = SearchCoursePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_search)

        var recyclerView = findViewById<RecyclerView>(R.id.search_result)


        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = CourseResultAdapter(presenter)
    }


    //TODO maybe change name
    override fun getContextActivity(): Context = this

}