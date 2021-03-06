package nekono.inno.stepikapi.search

import android.support.v7.content.res.AppCompatResources.getDrawable
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import nekono.inno.stepikapi.R

class CourseResultAdapter(val presenter : SearchCourse.Presenter) : RecyclerView.Adapter<CourseResultAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.result_card, parent, false)

        return CourseViewHolder(itemView, presenter)
    }

    override fun getItemCount() = presenter.getCourses().size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = presenter.getCourses()[position]

        if(course.target_type == "lesson"){
            holder.courseName.text = "Урок: " + course.lesson_title
        }else {

            holder.courseName.text = course.course_title

            Picasso.get().load(course.course_cover)
                    .error(getDrawable(holder.itemView.context, R.drawable.default_pic)!!)
                    .placeholder(getDrawable(holder.itemView.context, R.drawable.default_pic)!!)
                    .into(holder.courseCover)


            holder.starImageView.visibility = if (!course.marked) View.INVISIBLE else View.VISIBLE
        }

    }

    class CourseViewHolder(itemView: View, presenter: SearchCourse.Presenter) : RecyclerView.ViewHolder(itemView){
        var courseName = itemView.findViewById<TextView>(R.id.course_name)
        var courseCover = itemView.findViewById<ImageView>(R.id.course_cover)
        var cardView  = itemView.findViewById<CardView>(R.id.card_view)
        var starImageView = itemView.findViewById<ImageView>(R.id.star_image_view)

        init{
            cardView.setOnClickListener({ presenter.courseClicked(presenter.getCourses()[adapterPosition]) })
        }
    }


}