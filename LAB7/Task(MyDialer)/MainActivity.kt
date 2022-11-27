import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import timber.log.Timber.Forest.plant
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        plant(Timber.DebugTree())

        var json: String?

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        val button: Button = findViewById(R.id.btn_search)
        val txt: EditText = findViewById(R.id.et_search)

        val inp: InputStream = assets.open("phones.json")
        json = inp.bufferedReader().use { it.readText() }.replace("\r\n", "")

        val phones = Gson().fromJson(json, Array<Contact>::class.java).toList()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(this, phones as ArrayList<Contact>)


        button.setOnClickListener {
            if (txt.text.toString() != "") {
                val newArray = ArrayList<Contact>()
                for (i in phones.indices) {
                    if (txt.text.toString() in phones[i].name || txt.text.toString() in phones[i].phone || txt.text.toString() in phones[i].type) {
                        newArray.add(phones[i])
                    }
                }
                recyclerView.adapter = Adapter(this, newArray)
            }
            else {
                recyclerView.adapter = Adapter(this, phones)
            }
        }
    }

    data class Contact(
        var name: String,
        var phone: String,
        var type: String,
    )


    class Adapter(
        private val context: Context,
        private val list: ArrayList<Contact>
    ) : RecyclerView.Adapter<Adapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val name: TextView = view.findViewById(R.id.textName)
            val phone: TextView = view.findViewById(R.id.textPhone)
            val type: TextView = view.findViewById(R.id.textType)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.rview_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = list[position]
            holder.name.text = data.name
            holder.phone.text = data.phone
            holder.type.text = data.type
        }

        override fun getItemCount(): Int {
            return list.count()
        }
    }
}
