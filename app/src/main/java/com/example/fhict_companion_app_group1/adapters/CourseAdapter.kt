package com.example.fhict_companion_app_group1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fhict_companion_app_group1.models.Course
import com.example.fhict_companion_app_group1.R

class CourseAdapter(private val courses: ArrayList<Course>) :
    RecyclerView.Adapter<CourseAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCourseName: TextView = view.findViewById(R.id.textViewCourseName)
        val textViewCourseTime: TextView = view.findViewById(R.id.textViewCourseTime)
        val textViewCourseDate: TextView = view.findViewById(R.id.textViewCourseDate)
        val textViewCourseDetails: TextView = view.findViewById(R.id.textViewCourseDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listViewScheduleItem =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_courses, parent, false)
        return ViewHolder(listViewScheduleItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewCourseName.text = courses[position].subject
        holder.textViewCourseTime.text = courses[position].getTimeSlot()
        holder.textViewCourseDate.text = courses[position].getDate()
        holder.textViewCourseDetails.text = courses[position].getDetails()
    }
    
    override fun getItemCount() = courses.size
}
