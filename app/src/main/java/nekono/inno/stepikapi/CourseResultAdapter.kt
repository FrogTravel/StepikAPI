package nekono.inno.stepikapi

import android.support.v7.content.res.AppCompatResources.getDrawable
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class CourseResultAdapter(val presenter : SearchCourse.Presenter) : RecyclerView.Adapter<CourseResultAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.result_card, parent, false)

        return CourseViewHolder(itemView, presenter)
    }

    override fun getItemCount() = presenter.getCourses().size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = presenter.getCourses()[position]
        holder.courseName.text = course.course_title

        Picasso.get().load(course.course_cover)
                .error(getDrawable(holder.itemView.context, R.drawable.default_pic)!!)
                .placeholder(getDrawable(holder.itemView.context, R.drawable.default_pic)!!)
                .into(holder.courseCover)

        holder.courseRating.text = course.score.toString()
    }

    class CourseViewHolder(itemView: View, presenter: SearchCourse.Presenter) : RecyclerView.ViewHolder(itemView){
        var courseName = itemView.findViewById<TextView>(R.id.course_name)
        var courseCover = itemView.findViewById<ImageView>(R.id.course_cover)
        var courseRating = itemView.findViewById<TextView>(R.id.rating_text_view)
        var cardView  = itemView.findViewById<CardView>(R.id.card_view)

        init{
            cardView.setOnClickListener(View.OnClickListener { presenter.courseClicked(presenter.getCourses()[adapterPosition]) })
        }
    }
}