package com.nikolaus.liriklagurohani

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class activity_home : AppCompatActivity() {
    private lateinit var laguRecyclerView: RecyclerView
    private lateinit var judul : Array<String>
    private lateinit var pengarang : Array<String>
    private lateinit var gambar : Array<Int>
    private lateinit var liriklagu : Array<String>
    private lateinit var listlagu : ArrayList<ItemData>



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        gambar = arrayOf(
            R.drawable.lyrics,
            R.drawable.lyrics,
            R.drawable.lyrics,
            R.drawable.lyrics,
            R.drawable.lyrics,
            R.drawable.lyrics,
            R.drawable.lyrics,
            R.drawable.lyrics,
            R.drawable.lyrics,)
         judul = arrayOf("Tetap Cinta Yesus","Kasih Allahku", "Mujizat Terjadi",
             "Janjimu Sperti Fajar","Kasih SetiaMu","Sentuh Hatiku","Sejauh Timur Dari Barat"
             ,"Sperti Rusa Rindu","Sbab Kau Besar")
        pengarang = arrayOf(
            "Cpt. Citra Scholastika","Cpt. Various Artist", "Cpt. Gloria Tio",
            "Cpt. Nikita","Cpt. Maria Shandi","Cpt. Maria Shandi","Cpt. Natashia Nikita"
            ,"Cpt. Hosana Singer","Cpt. Jacqlien Celosse","Cpt. Gloria Trio"
        )

        liriklagu = arrayOf(
            getString(R.string.desc_Allah_nogoba1),
            getString(R.string.desc_Allah_nogoba2),
            getString(R.string.desc_Allah_nogoba3),
            getString(R.string.desc_Allah_nogoba4),
            getString(R.string.desc_Allah_nogoba5),
            getString(R.string.desc_Allah_nogoba6),
            getString(R.string.desc_Allah_nogoba7),
            getString(R.string.desc_Allah_nogoba8),
            getString(R.string.desc_Allah_nogoba9),
            getString(R.string.desc_Allah_nogoba10),
        )
        laguRecyclerView = findViewById(R.id.LirikLaguRV)
        laguRecyclerView.layoutManager = LinearLayoutManager(this)
        laguRecyclerView.setHasFixedSize(true)

        listlagu = arrayListOf<ItemData>()
        getDataUser()




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }


    }

    private fun  getDataUser(){
        for ( i in gambar.indices){
            val dataLagu = ItemData(gambar[i],judul[i],pengarang[i])
            listlagu.add(dataLagu)
        }
     var adapter = MyAdapter(listlagu)
     laguRecyclerView.adapter = adapter
     adapter.setOnItemClickListener(object:MyAdapter.onItemClickListener
     {
         override fun onItemClick(position: Int) {
             intent= Intent(this@activity_home,activity_detail::class.java)
             intent.putExtra("idgambar",listlagu[position].gambar)
             intent.putExtra("idjudul",listlagu[position].judul)
             intent.putExtra("idpengarang",listlagu[position].pengarang)
             intent.putExtra("idliriklagu",liriklagu[position])

             startActivity(intent)

         }

     })
    }





}