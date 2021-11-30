package com.example.fhict_companion_app_group1.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.fhict_companion_app_group1.R
import com.example.fhict_companion_app_group1.activities.MainActivity
import com.example.fhict_companion_app_group1.adapters.PeopleAdapter
import com.example.fhict_companion_app_group1.models.People
import com.example.fhict_companion_app_group1.services.FHICTService
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_people_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_people_details : Fragment() {
    // TODO: Rename and change types of parameters
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

    //        val view = inflater.inflate(R.layout.fragment_people_details, container, false)        val PeopleAdapter = PeopleAdapter(context, createMockPeople())
//        val listViewsAllPeople: ListView = view.findViewById(R.id.listViewAllPeople)
//        listViewsAllPeople.adapter = PeopleAdapter
//        return view
    }
    private fun setPeopleDetails() {
        doAsync {
            val people = fhictService.getTeachers()
            uiThread{
                val PeopleAdapter = PeopleAdapter(context, people)
                val listViewsAllPeople: ListView = peopleDetailsFragmentView.findViewById(R.id.listViewAllPeople)
                listViewsAllPeople.adapter = PeopleAdapter
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_people_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_people_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}