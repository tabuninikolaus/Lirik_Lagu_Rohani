package com.nikolaus.liriklagurohani

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.nikolaus.liriklagurohani.databinding.ActivityActivivityForgetpasswordBinding

class activivity_forgetpassword : AppCompatActivity() {
    private lateinit var binding: ActivityActivivityForgetpasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_activivity_forgetpassword)
        binding = ActivityActivivityForgetpasswordBinding.inflate(layoutInflater)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.BtnResetPass.setOnClickListener {
            val email : String = binding.emailForgetPass.text.toString().trim()
            if (email.isEmpty()){
                binding.emailForgetPass.error = "Input Email"
                binding.emailForgetPass.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.emailForgetPass.error = "Invalid Email"
                binding.emailForgetPass.requestFocus()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"cek Email for reset your password", Toast.LENGTH_SHORT).show()
                    Intent(this,activity_login::class.java).also {
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(it)
                    }
                } else{
                    Toast.makeText(this,"${it.exception?.message}",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

}