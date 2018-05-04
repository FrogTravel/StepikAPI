package nekono.inno.stepikapi

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CourseResultAdapter(val presenter : SearchCourse.Presenter) : RecyclerView.Adapter<CourseResultAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_element, parent, false)

        return CourseViewHolder(itemView)
    }

    override fun getItemCount() = presenter.getCourses().size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = presenter.getCourses()[position]
        holder.courseName.text = course.course_title

        Picasso.get().load(course.course_cover)
                .into(holder.courseCover)

    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var courseName = itemView.findViewById<TextView>(R.id.couse_name)
        var courseCover = itemView.findViewById<ImageView>(R.id.course_cover)
    }
}