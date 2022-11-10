package com.example.nestedlayouts

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var clickCounter : Int = 0
        setContentView(R.layout.activity_main)
        val buttonRoll = (findViewById<Button>(R.id.button_roll))
        buttonRoll.setOnClickListener {
            counter(clickCounter)
            clickCounter += 1
        }
    }

    private fun counter(clickCounter: Int) {
        val text11 = (findViewById<TextView>(R.id.text11))
        val text12 = (findViewById<TextView>(R.id.text12))
        val text13 = (findViewById<TextView>(R.id.text13))
        val text21 = (findViewById<TextView>(R.id.text21))
        val text22 = (findViewById<TextView>(R.id.text22))
        val text23 = (findViewById<TextView>(R.id.text23))
        val text31 = (findViewById<TextView>(R.id.text31))
        val text32 = (findViewById<TextView>(R.id.text32))
        val text33 = (findViewById<TextView>(R.id.text33))

        val intText11 = text11.text.toString().toIntOrNull()
        val intText12 = text12.text.toString().toIntOrNull()
        val intText13 = text13.text.toString().toIntOrNull()

        fun addOne(x: Int) = x + 1

        if (clickCounter == 1) {
            text12.text = clickCounter.toString()
            text11.text = ""
            text22.text = clickCounter.toString()
            text21.text = ""
            text32.text = clickCounter.toString()
            text31.text = ""
        }
        if (clickCounter == 2) {
            val count = addOne(intText12).toString()
            text13.text = count
            text12.text = ""
            text23.text = count
            text22.text = ""
            text33.text = count
            text32.text = ""
        }
        if (clickCounter == 3) {
            val count = addOne(intText13).toString()
            text11.text = count
            text13.text = ""
            text21.text = count
            text23.text = ""
            text31.text = count
            text33.text = ""
        }
    }
}
