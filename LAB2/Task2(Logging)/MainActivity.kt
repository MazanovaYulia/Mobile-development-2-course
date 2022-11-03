package pr.com.logging

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edit_text)
        val buttonLog = findViewById<Button>(R.id.button_log)
        val buttonTimber = findViewById<Button>(R.id.button_timber)

        buttonLog.setOnClickListener{
            val tag = "From EditText"
            Log.v(tag, editText.text.toString())
        }
        Timber.plant(Timber.DebugTree())
        buttonTimber.setOnClickListener{
            Timber.v(editText.text.toString())
        }
    }
}
