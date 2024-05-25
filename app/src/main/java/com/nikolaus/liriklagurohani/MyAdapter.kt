package com.nikolaus.liriklagurohani

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent

class MyAdapter  (private val namaList: ArrayList<ItemData>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick (position: Int)
    }
    fun setOnItemClickListener(listener:onItemClickListener){
        mListener = listener
    }





    class MyViewHolder (itemData:View, listener: onItemClickListener):RecyclerView.ViewHolder(itemData) {
        val Gbr: ImageView = itemData.findViewById(R.id.Gambar)
        val Jdl: TextView = itemData.findViewById(R.id.Judul)
        val Desk: TextView = itemData.findViewById(R.id.Deskripsi)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
               }
             }

      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemData2=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return MyViewHolder(itemData2,mListener)
    }

    override fun getItemCount(): Int = namaList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = namaList[position]
        holder.Gbr.setImageResource(currentItem.gambar)
        holder.Jdl.text = currentItem.judul
        holder.Desk.text = currentItem.pengarang
    }
}



