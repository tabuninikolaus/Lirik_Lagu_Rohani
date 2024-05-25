package com.nikolaus.liriklagurohani

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

     private lateinit var btn2: Button
     private lateinit var txtGoogle: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btn2 = findViewById(R.id.BtnMain)
        btn2.setOnClickListener {
            val intentsaya = Intent(this@MainActivity,activity_login::class.java)
            startActivity(intentsaya)
        }
        txtGoogle = findViewById<TextView>(R.id.TxtGoogle)
        txtGoogle.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse(("https://www.google.com/")))
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}