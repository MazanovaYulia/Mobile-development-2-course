package com.example.mydialer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ContactItemDiffCallback : DiffUtil.ItemCallback<Contact>() {
    override fun areItemsTheSame(oldItem: Contact, newItem: Contact) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Contact, newItem: Contact) = oldItem == newItem
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val textName: TextView = view.findViewById(R.id.textName)
    private val textPhone: TextView = view.findViewById(R.id.textPhone)
    private val textType: TextView = view.findViewById(R.id.textType)

    fun bindTo(contact: Contact) {
        textName.text = contact.name
        textPhone.text = contact.phone
        textType.text = contact.type
    }
}

class Adapter : ListAdapter<Contact, RecyclerView.ViewHolder>(ContactItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.r_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = currentList[position]
        val viewHolder: ViewHolder = holder as ViewHolder
        viewHolder.bindTo(data)
    }
}
