package com.example.fhict_companion_app_group1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.fhict_companion_app_group1.R
import com.example.fhict_companion_app_group1.activities.MainActivity
import com.example.fhict_companion_app_group1.adapters.PeopleAdapter
import com.example.fhict_companion_app_group1.services.FHICTService
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PeopleDetailsFragment : Fragment() {
    private lateinit var fhictService: FHICTService
    private lateinit var peopleDetailsFragmentView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fhictService = (activity as MainActivity).getFhictService()
        peopleDetailsFragmentView = inflater.inflate(R.layout.fragment_people_details, container, false)
        setPeopleDetails()
        return peopleDetailsFragmentView
    }
    private fun setPeopleDetails() {
        doAsync {
            val people = fhictService.getTeachers()
            uiThread{
                val peopleAdapter = PeopleAdapter(context, people)
                val listViewsAllPeople: ListView = peopleDetailsFragmentView.findViewById(R.id.listViewAllPeople)
                listViewsAllPeople.adapter = peopleAdapter
            }
        }
    }
}