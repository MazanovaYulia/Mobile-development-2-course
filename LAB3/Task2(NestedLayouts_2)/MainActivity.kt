package com.example.nestedlayouts

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var clickCounter = 0
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

        if (clickCounter % 3 == 2) {
            text11.text = ""
            text12.text = clickCounter.toString()
            text13.text = ""
            text21.text = ""
            text22.text = clickCounter.toString()
            text23.text = ""
            text31.text = ""
            text32.text = clickCounter.toString()
            text33.text = ""
        }
        if (clickCounter % 3 == 0) {
            text11.text = ""
            text12.text = ""
            text13.text = clickCounter.toString()
            text21.text = ""
            text22.text = ""
            text23.text = clickCounter.toString()
            text31.text = ""
            text32.text = ""
            text33.text = clickCounter.toString()
        }
        if (clickCounter % 3 == 1) {
            text11.text = clickCounter.toString()
            text12.text = ""
            text13.text = ""
            text21.text = clickCounter.toString()
            text22.text = ""
            text23.text = ""
            text31.text = clickCounter.toString()
            text32.text = ""
            text33.text = ""
        }
    }
}
