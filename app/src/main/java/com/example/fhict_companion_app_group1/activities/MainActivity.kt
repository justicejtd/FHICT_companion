package com.example.fhict_companion_app_group1.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fhict_companion_app_group1.R
import com.example.fhict_companion_app_group1.TOKEN_INTENT_KEY
import com.example.fhict_companion_app_group1.models.Token
import com.example.fhict_companion_app_group1.services.FHICTService
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.view.Window

import androidx.core.content.ContextCompat

import android.view.WindowManager


class MainActivity : AppCompatActivity() {
    private lateinit var FHICTService: FHICTService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = intent.getStringExtra(TOKEN_INTENT_KEY)
        FHICTService = FHICTService(Token(token.toString()))
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerHost) as NavHostFragment? ?: return
        val navController = host.navController

        setupBottomNavMenu(navController)
        Log.d("Debug", "onCreate")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Debug", "onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Debug", "onStart")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationMain)
        bottomNav?.setupWithNavController(navController)
    }

    fun getFhictService(): FHICTService {
        return FHICTService
    }

//    fun btnOnClickOpenMyProfileActivity(view: View) {
//        val intent = Intent(this, MyProfileActivity::class.java)
//        startActivity(intent)
//    }
//
//    fun btnOnClickOpenPeopleActivity(view: View) {
//        val intent = Intent(this, AllPeopleActivity::class.java)
//        startActivity(intent)
//    }
}