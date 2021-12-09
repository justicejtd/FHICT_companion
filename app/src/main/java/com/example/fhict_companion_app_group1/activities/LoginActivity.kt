package com.example.fhict_companion_app_group1.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fhict_companion_app_group1.R
import com.example.fhict_companion_app_group1.TOKEN_INTENT_KEY
import com.example.fhict_companion_app_group1.fragments.TokenFragment.TokenFragment

class LoginActivity : AppCompatActivity(), TokenFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onFragmentInteraction(token: String?) {
        if (token != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TOKEN_INTENT_KEY, token)
            startActivity(intent)
        }
    }
}