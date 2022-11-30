package com.example.recyclerview

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(this, fetchList())
        recyclerView.addItemDecoration(DividerItemDecoration(this,RecyclerView.VERTICAL))
    }

    class ColorData(val colorName: String, val colorHex: Int)

    class Adapter(
        private val context: Context,
        private val list: ArrayList<ColorData>):
        RecyclerView.Adapter<Adapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val viewIU: View = view.findViewById(R.id.view)
            val textView: TextView = view.findViewById(R.id.textView)
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.rview_item, parent, false)
            return ViewHolder(view)
        }
        override fun getItemCount(): Int {
            return list.count()
        }
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = list[position]
            holder.viewIU.setBackgroundColor(data.colorHex)
            holder.textView.text = data.colorName
        }
        }

    private fun fetchList(): ArrayList<ColorData> {
        return arrayListOf(
            ColorData("WHITE", Color.WHITE),
            ColorData("BLACK", Color.BLACK),
            ColorData("BLUE", Color.BLUE),
            ColorData("RED", Color.RED),
            ColorData("MAGENTA", Color.MAGENTA)
        )
    }
}
