package com.example.mydialer2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val callback: (String) -> Unit) : ListAdapter<Contact, MyViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rview_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindTo(getItem(position), callback)
    }
}

class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindTo(item: Contact, callback: (String) -> Unit) {
        val textViewName = itemView.findViewById<TextView>(R.id.textName)
        val textViewType = itemView.findViewById<TextView>(R.id.textType)
        val textViewPhone = itemView.findViewById<TextView>(R.id.textPhone)

        textViewName.text = item.name
        textViewType.text = item.type
        textViewPhone.text = item.phone

        itemView.setOnClickListener {
            callback(item.phone)
        }
    }
}
