package com.example.mydialer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import timber.log.Timber
import java.net.HttpURLConnection
import java.net.URL

data class Contact (
    val name: String,
    val phone: String,
    val type: String
)

class Adapter (private val context: Context, private val list: List<Contact>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.textName)
        val phone: TextView = view.findViewById(R.id.textPhone)
        val type: TextView = view.findViewById(R.id.textType)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rview_item, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return list.count()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.name.text = data.name
        holder.phone.text = data.phone
        holder.type.text = data.type
    }
}

class MainActivity : AppCompatActivity() {
    var contactsJson = ArrayList<Contact>()
    var contacts = ArrayList<Contact>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        val recyclerView: RecyclerView = findViewById(R.id.rView)
        val data = "https://drive.google.com/u/0/uc?id=1-KO-9GA3NzSgIc1dkAsNm8Dqw0fuPxcR&export=download"
        Thread {
            val connection = URL(data).openConnection() as HttpURLConnection
            val jsonData = connection.inputStream.bufferedReader().readText()
            connection.disconnect()
            contactsJson = Gson().fromJson(jsonData, Array<Contact>::class.java).toList() as ArrayList<Contact>
            contacts = contactsJson.clone() as ArrayList<Contact>
            runOnUiThread() {
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = Adapter(this, contacts)
            }
        }.start()
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val editText = findViewById<EditText>(R.id.textView)
            contacts.clear()
            if (!editText.text.isNullOrBlank()) {
                for (qq in contactsJson) {
                    if ((qq.name.contains(editText.text)) or (qq.phone.contains(editText.text)) or (qq.type.contains(editText.text))) {
                        contacts.add(qq)
                    }
                }
            }
            else {
                contacts = contactsJson.clone() as ArrayList<Contact>
            }
            val recyclerView: RecyclerView = findViewById(R.id.rView)
            recyclerView.adapter = Adapter(this, contacts)
            recyclerView.invalidate()
        }
    }
}
