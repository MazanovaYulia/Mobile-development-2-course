package pr.com.recyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    class ColorData(val colorName : String, val colorHex : Int) {}

    class Adapter(private val context : Context,
                   private val list: ArrayList<ColorData>) :
            RecyclerView.Adapter<Adapter.ViewHolder>() {
                class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
                    val viewIU: View = view.findViewById(R.id.view)
                    val textView : TextView = view.findViewById(R.id.textView)
                }
            }
}
