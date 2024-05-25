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
import com.nikolaus.liriklagurohani.databinding.ActivityRegisterBinding

class activity_register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.buttonRegister.setOnClickListener {
            val email : String = binding.emailRegister.text.toString().trim()
            val password : String = binding.passwordRegister.text.toString().trim()
            val confirmpassword : String = binding.confirmPasswordRegister.text.toString().trim()
            //Conditional
            if (email.isEmpty()){
                binding.emailRegister.error = "Masukan Email"
                binding.emailRegister.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.emailRegister.error = "Invalid Email"
                binding.emailRegister.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6){
                binding.passwordRegister.error ="pasword must be more than 6 characters"
                binding.passwordRegister.requestFocus()
                return@setOnClickListener
            }
            if (password != confirmpassword){
                binding.confirmPasswordRegister.error ="pasword must be match"
                binding.passwordRegister.requestFocus()
                return@setOnClickListener
            }
            registerUser(email,password)
        }

        binding.SdhPnyAkunRegister.setOnClickListener {
            startActivity(Intent(this,activity_login::class.java))
        }



        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun registerUser(email: String, pasword: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,pasword).addOnCompleteListener {
            if (it.isSuccessful){
                Intent(this,activity_home::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            } else{
                Toast.makeText(this,it.exception?.message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null){
            Intent(this, activity_home::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

}