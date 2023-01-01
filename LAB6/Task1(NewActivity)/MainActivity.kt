package com.example.newactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn_show_pic)

        button.setOnClickListener {
            intent = Intent(this, PicActivity::class.java)
            intent.putExtra("picLink", "https://www.rosphoto.com/images/u/articles/1510/4_8.jpg")
            startActivity(intent)
        }
    }
}
