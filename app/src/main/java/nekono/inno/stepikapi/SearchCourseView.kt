package nekono.inno.stepikapi

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText

class SearchCourseView : Activity(), SearchCourse.View {
    val presenter = SearchCoursePresenter(this)
    lateinit var prevButton : Button
    lateinit var nextButton : Button
    lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_search)
        prevButton = findViewById(R.id.previous_button)
        nextButton = findViewById(R.id.next_button)

        searchEditText = findViewById(R.id.course_search_field)

        searchEditText.setOnKeyListener({ v, keyCode, event ->
            presenter.keyClicked(v, keyCode, event)
        })

        presenter.start()

    }

    override fun showCourses() {
        var recyclerView = findViewById<RecyclerView>(R.id.search_result)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CourseResultAdapter(presenter)
    }

    public fun onPrevious(view: View){
        presenter.previousPage()
    }

    public fun onNext(view: View){
        presenter.nextPage()
    }

    override fun disablePreviousButton() {
        prevButton.isEnabled = false
    }

    override fun activatePreviousButton() {
        prevButton.isEnabled = true
    }

    override fun disableNextButton() {
        nextButton.isEnabled = false
    }

    override fun activateNextButton() {
        nextButton.isEnabled = true
    }

    override fun setHintWord(request: String) {
        searchEditText.hint = request
    }

    override fun getSearchWord() = searchEditText.text.toString()
}