package com.example.mydialer

import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

const val url = "https://drive.google.com/u/0/uc?id=1-KO-9GA3NzSgIc1dkAsNm8Dqw0fuPxcR&export=download"

class ContentFragment : Fragment() {
    private var adapter = Adapter()
    private val contactList = contactsRequest()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        adapter.submitList(contactList)
        val recyclerView: RecyclerView = view.findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
    }

    private fun contactsRequest(): ArrayList<Contact> {
        val myService: ExecutorService = Executors.newFixedThreadPool(2)
        val contactList = myService.submit(Callable {
            val connection = URL(url).openConnection() as HttpURLConnection
            val jsonData = connection.inputStream.bufferedReader().readText()
            connection.disconnect()
            Gson().fromJson(jsonData, Array<Contact>::class.java).toList() as ArrayList<Contact>
        })
        return contactList.get()
    }

    fun search(request: CharSequence)
    {
        val searchList = arrayListOf<Contact>()
        if(request.isNotBlank()){
            for(contact in contactList){
                if ((contact.name.contains(request)) or
                    (contact.phone.contains(request)) or
                    (contact.type.contains(request))){
                    searchList.add(contact)
                }
            }
        }
        else {
            searchList.addAll(contactList)
        }
        adapter.submitList(searchList)
    }
}
