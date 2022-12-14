package com.example.mydialer7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val callback: (String) -> Unit) : ListAdapter<Contact, ContactViewHolder>(ContactDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rview_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position), callback)
    }
}

class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Contact, callback: (String) -> Unit) {
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
