package nekono.inno.stepikapi.search

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson
import android.content.Context
import android.preference.PreferenceManager
import android.net.ConnectivityManager
import com.google.gson.reflect.TypeToken
import nekono.inno.stepikapi.R
import nekono.inno.stepikapi.util.Course


class SearchCourseView : Activity(), SearchCourse.View {

    val presenter = SearchCoursePresenter(this)
    lateinit var prevButton: Button
    lateinit var nextButton: Button
    lateinit var searchEditText: EditText
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_search)
        prevButton = findViewById(R.id.previous_button)
        nextButton = findViewById(R.id.next_button)

        searchEditText = findViewById(R.id.course_search_field)

        recyclerView = findViewById<RecyclerView>(R.id.search_result)

        searchEditText.setOnKeyListener({ v, keyCode, event ->
            presenter.keyClicked(v, keyCode, event)
        })

        presenter.start()

    }

    override fun showCourses() {

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CourseResultAdapter(presenter)
    }

    public fun onPrevious(view: View) {
        presenter.previousPage()
    }

    public fun onNext(view: View) {
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

    override fun onPause() {
        super.onPause()
        presenter.activityPaused()
    }

    override fun saveCourses(courses: ArrayList<Course>) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPrefs.edit()
        val gson = Gson()

        val json = gson.toJson(courses)

        editor.putString("MarkedCourses", json)
        editor.commit()
    }

    override fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun readCourses(): ArrayList<Course>? {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
        val gson = Gson()
        val json = sharedPrefs.getString("MarkedCourses", null)
        val type = object : TypeToken<ArrayList<Course>>() {

        }.type
        val arrayList = gson.fromJson<ArrayList<Course>>(json, type)
        return arrayList
    }



    override fun disableEditText() {
        searchEditText.isEnabled = false
    }
}