package com.example.fhict_companion_app_group1.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fhict_companion_app_group1.R
import com.example.fhict_companion_app_group1.activities.MainActivity
import com.example.fhict_companion_app_group1.services.FHICTService
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var fhictService: FHICTService
    private lateinit var profileFragmentView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fhictService = (activity as MainActivity).getFhictService()
        profileFragmentView = inflater.inflate(R.layout.fragment_profile, container, false)
        getInfo()
        return profileFragmentView
    }

    private fun getInfo()
    {
        doAsync {
            val me = fhictService.getMyInfo()
            Log.d("TAG", me.displayName)
            uiThread{
                profileFragmentView.findViewById<TextView>(R.id.myProfileName).text = me.displayName
                profileFragmentView.findViewById<TextView>(R.id.myProfileId).text = "I number: " + me.id
                profileFragmentView.findViewById<TextView>(R.id.myProfileEmail).text = "Email: " + me.mail
                profileFragmentView.findViewById<TextView>(R.id.myProfileDepartment).text = "Stream: " + me.department
                Log.d("TAG", me.displayName)
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
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}