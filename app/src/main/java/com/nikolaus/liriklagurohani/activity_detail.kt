package com.nikolaus.liriklagurohani

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nikolaus.liriklagurohani.R.id.Deskripsi
import com.nikolaus.liriklagurohani.R.id.Gambar
import com.nikolaus.liriklagurohani.R.id.Judul
import com.nikolaus.liriklagurohani.R.id.TxtLirikDetail

class activity_detail : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        val gambar : ImageView= findViewById(R.id.ImgDetail)
        val judul  : TextView= findViewById(R.id.TxtJudulDetail)
        val pengarang: TextView= findViewById(R.id.TXTPengarangDetail)
        val liriklagu: TextView= findViewById(R.id.TxtLirikDetail)// Ini nanti coba cek lagi karena sebelunya mrngunakan R.id

        val bundle: Bundle?= intent.extras
        val bJudul = bundle!!.getString("idjudul")
        val bGambar = bundle.getInt("idgambar")
        val bPengarang = bundle.getString("idpengarang")
        val bLiriklagu = bundle.getString("idliriklagu")

        gambar.setImageResource(bGambar)
        judul.text = bJudul
        pengarang.text = bPengarang
        liriklagu.text = bLiriklagu


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}





