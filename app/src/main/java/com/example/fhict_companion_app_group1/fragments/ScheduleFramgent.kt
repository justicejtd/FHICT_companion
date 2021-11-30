package com.example.fhict_companion_app_group1.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fhict_companion_app_group1.R
import com.example.fhict_companion_app_group1.activities.MainActivity
import com.example.fhict_companion_app_group1.adapters.CourseAdapter
import com.example.fhict_companion_app_group1.models.Schedule
import com.example.fhict_companion_app_group1.services.FHICTService
import kotlinx.coroutines.delay
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.Thread.sleep

class ScheduleFragment : Fragment() {
    private val schedule = Schedule()
    private lateinit var fhictService: FHICTService
    private lateinit var scheduleFragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fhictService = (activity as MainActivity).getFhictService()
        // Inflate the layout for this fragment
        scheduleFragmentView = inflater.inflate(R.layout.fragment_schedule, container, false)
        setCourses()
        return scheduleFragmentView
    }

    private fun setCourses() {
        doAsync {
            schedule.courses = fhictService.getCourses()
            uiThread {
                val recyclerViewCourses =
                    scheduleFragmentView.findViewById<RecyclerView>(R.id.recyclerViewCourses)
                recyclerViewCourses.layoutManager = LinearLayoutManager(context)
                recyclerViewCourses.adapter = CourseAdapter(schedule.courses)
            }
        }
    }
}