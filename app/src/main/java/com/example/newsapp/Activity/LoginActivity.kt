package com.example.newsapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        // Navigate to SigninActivity when "signup" button is clicked
        binding.signup.setOnClickListener{
            startActivity(Intent(this, SigninActivity::class.java))
            finish()
        }
        auth = FirebaseAuth.getInstance()

        // Handle login button click
       binding.login.setOnClickListener{
          val email = binding.emaillogin.text.toString().trim()
          val password = binding.pwlogin.text.toString().trim()

           // Check if email or password fields are empty
          if (email.isEmpty()||password.isEmpty()){
              Toast.makeText(this,"Enter field",Toast.LENGTH_SHORT).show()
              return@setOnClickListener
          }
          login(email,password)

       }


    }

    // Function to log in user using Firebase Authentication
    private fun login(email:String,password:String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                task ->if (task.isSuccessful){
                    Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(
                    this,
                    task.exception?.localizedMessage ?: "Login failed",
                    Toast.LENGTH_SHORT
                ).show()
            }

            }
    }
}