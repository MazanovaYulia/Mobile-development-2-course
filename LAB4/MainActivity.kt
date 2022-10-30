package com.example.rview

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(fetchList())
    }

    class ColorData(val colorName: String, val colorHex: Int) {
        fun getMyColorName() : String {return this.colorName}
        fun getMyColorHex() : Int {return this.colorHex}
    }

    private fun fetchList(): ArrayList<ColorData> {
        val data = arrayListOf<ColorData>()

        for (index in 0..4) {
            val color = ColorData(Colors()[index].getMyColorName(),
                                  Colors()[index].getMyColorHex())
            data.add(color)
        }
        return data
    }

    private fun Colors(): ArrayList<ColorData> {
        val list = arrayListOf<ColorData>()

        list.add(ColorData("BLACK", Color.BLACK))
        list.add(ColorData("WHITE", Color.WHITE))
        list.add(ColorData("MAGENTA", Color.MAGENTA))
        list.add(ColorData("RED", Color.RED))
        list.add(ColorData("BLUE", Color.BLUE))

        return list
    }
}
