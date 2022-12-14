package com.example.mydialer7

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private val phones = "[\n" +
            "  {\n" +
            "    \"name\": \"(Приёмная)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-80\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Бухгалтерия)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-64\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Бухгалтерия)\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-08\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Юридическое бюро)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-63\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Отдел правовой и кадровой работы)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-93\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Отдел материально-технического снабжения)\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-12\",\n" +
            "    \"type\": \"\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"\",\n" +
            "    \"phone\": \"+375 44 712 36 26\",\n" +
            "    \"type\": \"Сектор сбыта бумаги\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Реализация на внутренний рынок)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-79\",\n" +
            "    \"type\": \"Сектор сбыта бумаги\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Реализация на внешний рынок)\",\n" +
            "    \"phone\": \"+375 (2239) 4-11-77\",\n" +
            "    \"type\": \"Сектор сбыта бумаги\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Реализация на внутренний рынок)\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-25\",\n" +
            "    \"type\": \"Сектор сбыта бумаги\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"\",\n" +
            "    \"phone\": \"+375 44 580 09 70\",\n" +
            "    \"type\": \"Сектор сбыта продукции деревообработки\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Реализация продукции деревообработки)\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-42\",\n" +
            "    \"type\": \"Сектор сбыта продукции деревообработки\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(Реализация продукции деревообработки)\",\n" +
            "    \"phone\": \"+375 (2239) 3-64-71\",\n" +
            "    \"type\": \"Сектор сбыта продукции деревообработки\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"\",\n" +
            "    \"phone\": \"+375 29 352 25 20\",\n" +
            "    \"type\": \"Реализация домов, бань, беседок, клеёного бруса\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-43\",\n" +
            "    \"type\": \"Реализация домов, бань, беседок, клеёного бруса\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(приемная)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-80\",\n" +
            "    \"type\": \"Факс\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(отдел сбыта)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-79\",\n" +
            "    \"type\": \"Факс\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(отдел материально-технического снабжения)\",\n" +
            "    \"phone\": \"+375 (2239) 7-17-82\",\n" +
            "    \"type\": \"Факс\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"(служба главного энергетика)\",\n" +
            "    \"phone\": \"+375 (2239) 7-18-06\",\n" +
            "    \"type\": \"Факс\"\n" +
            "  }\n" +
            "]" +
            ""
    private val adapter: ContactAdapter = ContactAdapter { number ->
        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(callIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.r_view)
        val searchEditText = findViewById<EditText>(R.id.et_search)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter

        val phonesType = object : TypeToken<List<Contact>>() {}.type
        val phones = Gson().fromJson<List<Contact>>(phones, phonesType)
        adapter.submitList(phones)
        searchEditText.addTextChangedListener {
            val filteredPhones = phones.filter { item ->
                item.phone.contains(it.toString()) || item.name.contains(it.toString()) || item.type.contains(it.toString())
            }
            adapter.submitList(filteredPhones)
            val filterState = getSharedPreferences("app_preferences", MODE_PRIVATE).edit()
            filterState.putString("SEARCH_FILTER", it.toString()).apply()
        }

        val lastFilterText = getSharedPreferences("app_preferences", MODE_PRIVATE).getString("SEARCH_FILTER","")
        searchEditText.append(lastFilterText)
    }
}
