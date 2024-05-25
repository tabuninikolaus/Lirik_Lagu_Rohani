package com.nikolaus.liriklagurohani

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.nikolaus.liriklagurohani.databinding.ActivityLoginBinding
import kotlinx.coroutines.handleCoroutineException

class activity_login : AppCompatActivity() {
    //    Dec Intent Pindah dari Login to Home
        private lateinit var btn1: Button
        private lateinit var txt1: TextView
        private lateinit var txt2: TextView
        private lateinit var binding: ActivityLoginBinding
        private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        //    Kodingan Intent Pindah dari Login to Home
        btn1 = findViewById(R.id.BtnLogin)
        btn1.setOnClickListener {
            val intenlogin = Intent(this@activity_login,activity_home::class.java)
            startActivity(intenlogin)
        }
        txt1 = findViewById(R.id.TxtForgetPass)
        txt1.setOnClickListener {
            val intentforgetpass = Intent(this@activity_login,activivity_forgetpassword::class.java)
            startActivity(intentforgetpass)
        }
        txt2 = findViewById(R.id.TxtRegister)
        txt2.setOnClickListener {
            val intentbuttonmasuk = Intent(this@activity_login,activity_register::class.java)
            startActivity(intentbuttonmasuk)
        }
        //    Kodingan Intent Pindah dari Login to Home

        //    Kodingan Binding & Firebaseauth
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.BtnLogin.setOnClickListener {
            val email : String = binding.loginEmail.text.toString().trim()
            val password : String = binding.loginEmail.text.toString().trim()

            if (email.isEmpty()){
                binding.loginEmail.error = "Input Email"
                binding.loginEmail.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.loginEmail.error = "Invalid Email"
                binding.loginEmail.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6){
                binding.loginPassword.error = "Password more than 6 characters"
                binding.loginPassword.requestFocus()
                return@setOnClickListener
            }
            loginUser(email,password)
        }
        binding.TxtRegister.setOnClickListener {
            startActivity(Intent(this,activity_register::class.java))
        }
        binding.TxtForgetPass.setOnClickListener {
            startActivity(Intent(this,activivity_forgetpassword::class.java))
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun loginUser(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Intent(this,activity_home::class.java).also {
                    it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            } else{
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            Intent(this, activity_home::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

}