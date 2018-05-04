package nekono.inno.stepikapi

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class CourseResultAdapter(val courseList : ArrayList<Course>) : RecyclerView.Adapter<CourseResultAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.search_element, parent, false)

        return CourseViewHolder(itemView)
    }

    override fun getItemCount() = courseList.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.courseName.text = courseList[position].course_title
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var courseName = itemView.findViewById<TextView>(R.id.couse_name)
        var courseCover = itemView.findViewById<ImageView>(R.id.course_cover)
    }
}