package com.example.mydialer

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contentFragment = ContentFragment()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container_view,
                    contentFragment, null)
                .commit()
        }

        val searchString = findViewById<EditText>(R.id.et_search)
        searchString.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(request: Editable) {}

            override fun beforeTextChanged(request: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(request: CharSequence, start: Int,
                                       before: Int, count: Int) {
                contentFragment.search(request)
            }
        })
    }
}
