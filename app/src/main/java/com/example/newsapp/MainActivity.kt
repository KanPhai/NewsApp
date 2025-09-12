package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.newsapp.Activity.LoginActivity
import com.example.newsapp.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    // ViewBinding for accessing UI elements
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        // Show the splash screen before the main UI
        installSplashScreen()

        enableEdgeToEdge()
        setContentView(binding.root)
        // Handle button click: go to LoginActivity and close MainActivity
        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}