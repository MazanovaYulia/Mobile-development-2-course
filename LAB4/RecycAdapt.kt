package com.example.rview

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//private val cellClickListener: CellClickListener

class RecyclerAdapter(private val colorsList: ArrayList<MainActivity.ColorData>) : RecyclerView
.Adapter<RecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.textViewColor)
        val hex: View = itemView.findViewById(R.id.viewColor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = colorsList[position].colorName
        holder.hex.setBackgroundColor(colorsList[position].colorHex)
       // holder.itemView.setOnClickListener(){
       //     cellClickListener.onCellClickListener(colorsList[position].color)
       // }
    }

    override fun getItemCount() = colorsList.size
}
