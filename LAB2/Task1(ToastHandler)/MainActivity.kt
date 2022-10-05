package com.example.toasthandler

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { //onCreate вызывается, когда приложение создаёт и отображает разметку активности
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button_ok) //объект button является ссылкой на кнопку

        button.setOnClickListener {
         //Toast.LENGTH_SHORT показывает текстовое уведомление на короткое время (2 сек)
            val toast = Toast.makeText(applicationContext, "Кнопка ОК", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}
