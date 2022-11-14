package com.example.complexevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonSave = (findViewById<Button>(R.id.button_save))
        val checkBox = (findViewById<CheckBox>(R.id.checkBox))
        val editText = (findViewById<EditText>(R.id.editText))
        val progressBar = (findViewById<ProgressBar>(R.id.progressBar))
        val textView = (findViewById<TextView>(R.id.textView))

        buttonSave.setOnClickListener{
            if (checkBox.isChecked) {
                textView.text = editText.text
                checkBox.isChecked = false
                if (progressBar.progress == 100)
                    progressBar.progress = 10
                else{
                    progressBar.progress =  progressBar.progress + 10
                }
            }
        }
    }
}
