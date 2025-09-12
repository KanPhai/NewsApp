package com.example.newsapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth

class SigninActivity : AppCompatActivity() {
    // Firebase Authentication instance
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySigninBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        // Navigate to LoginActivity when "login" button is clicked
        binding.login.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Handle "signup" button click
        binding.signup.setOnClickListener {
            val email = binding.usersignup.text.toString().trim()
            val password =binding.pwsigup.text.toString().trim()
            val confirmpassword= binding.confirmpw.text.toString().trim()

            // Check if any field is empty
            if (email.isEmpty()||password.isEmpty()||confirmpassword.isEmpty()){
                Toast.makeText(this,"Pleas Enter all Fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Check if password is at least 6 characters
            if (password.length<6){
                Toast.makeText(this,"Password must be at least 6 characters",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Check if passwords match
            if (password!=confirmpassword){
                Toast.makeText(this,"Passwords do not match",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Check if email ends with "@gmail.com"
            if (!email.endsWith("@gmail.com")){
                Toast.makeText(this,"Email should be end with @gmail.com",Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }


            // If all checks pass, call signup function
            signup(email,password)

        }


    }

    // Function to create a new user with Firebase Authentication
    private fun signup(email:String,password:String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                task -> if (task.isSuccessful){
                    auth.currentUser
                Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            }
                else{
                Toast.makeText(this,task.exception?.localizedMessage?:"not work",Toast.LENGTH_SHORT).show()

            }
            }
    }
}