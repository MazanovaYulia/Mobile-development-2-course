package com.example.attributes

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.EditText
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = (findViewById<EditText>(R.id.editText))
        val buttonBlackText = (findViewById<Button>(R.id.button_blackText))
        val buttonSize8 = (findViewById<Button>(R.id.button_size8))
        val buttonWhiteBack = (findViewById<Button>(R.id.button_whiteBack))
        val buttonRedText = (findViewById<Button>(R.id.button_redText))
        val buttonSize24 = (findViewById<Button>(R.id.button_size24))
        val buttonYellowBack = (findViewById<Button>(R.id.button_yellowBack))

        buttonBlackText.setOnClickListener {
            editText.setTextColor(Color.BLACK)
        }
        buttonSize8.setOnClickListener {
            editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8F)
        }
        buttonWhiteBack.setOnClickListener {
            editText.setBackgroundColor(Color.WHITE)
        }
        buttonRedText.setOnClickListener {
            editText.setTextColor(Color.RED)
        }
        buttonSize24.setOnClickListener {
            editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
        }
        buttonYellowBack.setOnClickListener {
            editText.setBackgroundColor(Color.YELLOW)
        }
    }
}
