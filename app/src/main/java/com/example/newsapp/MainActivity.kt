package com.example.newsapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.newsapp.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        installSplashScreen()
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

    }
}