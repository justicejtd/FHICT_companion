package com.example.fhict_companion_app_group1.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fhict_companion_app_group1.R
import com.example.fhict_companion_app_group1.TOKEN_INTENT_KEY
import com.example.fhict_companion_app_group1.models.Token
import com.example.fhict_companion_app_group1.services.FHICTService
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var fhictService: FHICTService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val token = intent.getStringExtra(TOKEN_INTENT_KEY)
        fhictService = FHICTService(Token(token.toString()))
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerHost) as NavHostFragment? ?: return
        val navController = host.navController

        setupBottomNavMenu(navController)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationMain)
        bottomNav?.setupWithNavController(navController)
    }

    fun getFhictService(): FHICTService {
        return fhictService
    }
}