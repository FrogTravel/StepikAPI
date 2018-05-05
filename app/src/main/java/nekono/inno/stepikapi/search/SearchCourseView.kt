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
import android.widget.Toast
import android.R.attr.data
import android.app.PendingIntent.getActivity


/**
 * Создание-инициализация всех вьюх и всякий элементов, логики здесь нет, все пасится в presenter
 */
class SearchCourseView : Activity(), SearchCourse.View {
    val presenter = SearchCoursePresenter(this)
    lateinit var prevButton: Button
    lateinit var nextButton: Button
    lateinit var searchEditText: EditText
    lateinit var recyclerView: RecyclerView
    val SAVETAG = "MarkedCourses"

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

    fun onPrevious(view: View) {
        presenter.previousPage()
    }

    fun onNext(view: View) {
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

        editor.putString(SAVETAG, json)
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
        val json = sharedPrefs.getString(SAVETAG, null)
        val type = object : TypeToken<ArrayList<Course>>() {

        }.type
        val arrayList = gson.fromJson<ArrayList<Course>>(json, type)
        return arrayList
    }

    override fun disableEditText() {
        searchEditText.isEnabled = false
    }

    override fun showAddedToMarkedToast() {
        Toast.makeText(this,"Добавлено в избранные!",
                Toast.LENGTH_SHORT).show()
    }

    override fun showRemovedFromMarkedToast() {
        Toast.makeText(this,"Удалено из избранных!",
                Toast.LENGTH_SHORT).show()
    }
}