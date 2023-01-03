package com.example.gson_20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

class PicViewer : AppCompatActivity(){
    var link: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pic_viewer)

        val pic = intent.getStringExtra("picLink")
        link = pic.toString()

        val imageView = findViewById<View>(R.id.pic_view_image) as ImageView

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar);

        Glide
            .with(this)
            .load(link)
            .into(imageView)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("favlink", link)
        setResult(2,intent)
        finish()
        return true
    }
}
