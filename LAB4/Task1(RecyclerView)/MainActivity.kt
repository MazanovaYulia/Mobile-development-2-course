package com.example.recyclerview

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(this, fetchList())
    }

    class ColorData(val colorName: String, val colorHex: Int)

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
