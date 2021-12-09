package com.example.fhict_companion_app_group1.fragments

import agency.tango.android.avatarview.views.AvatarView
import agency.tango.android.avatarviewglide.GlideLoader
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
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ProfileFragment : Fragment() {
    private lateinit var fhictService: FHICTService
    private lateinit var profileFragmentView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fhictService = (activity as MainActivity).getFhictService()
        profileFragmentView = inflater.inflate(R.layout.fragment_profile, container, false)
        getInfo()
        return profileFragmentView
    }

    private fun getInfo() {
        doAsync {
            val me = fhictService.getMyInfo()
            uiThread {
                profileFragmentView.findViewById<TextView>(R.id.myProfileName).text = me.displayName
                profileFragmentView.findViewById<TextView>(R.id.myProfileId).text =
                    getString(R.string.student_number).plus(me.id)
                profileFragmentView.findViewById<TextView>(R.id.myProfileEmail).text =
                    getString(R.string.email).plus(me.mail)
                profileFragmentView.findViewById<TextView>(R.id.myProfileDepartment).text =
                    getString(R.string.stream).plus(me.department)
                val avatarView = profileFragmentView.findViewById(R.id.avatarView) as AvatarView
                val imageLoader = GlideLoader()
                imageLoader.loadImage(avatarView, "http:/example.com/user/someUserAvatar.png", me.displayName)
            }
        }
    }
}