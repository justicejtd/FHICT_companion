package com.example.fhict_companion_app_group1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.fhict_companion_app_group1.models.People
import com.example.fhict_companion_app_group1.adapters.PeopleAdapter
import com.example.fhict_companion_app_group1.R

class AllPeopleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_people)
    }
}