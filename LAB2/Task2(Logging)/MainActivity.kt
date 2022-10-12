package com.example.logging

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edit_text)
        val showText = Toast.makeText(this,editText.text, Toast.LENGTH_LONG).show()
        val buttonLog = findViewById<Button>(R.id.button_log)
        val buttonTimber = findViewById<Button>(R.id.button_timber)

        buttonLog.setOnClickListener{
            val TAG_LOG = "From EditText"
            Log.v(TAG_LOG, showText.toString())
        }
      //  buttonTimber.setOnClickListener()
    }
}
