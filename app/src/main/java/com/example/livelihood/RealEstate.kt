package com.example.livelihood

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import org.json.JSONArray

class RealEstate : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_realestate)

        getAPI("https://4cd27cd9.ngrok.io/get/mortage") { result ->
            val array = JSONArray(result)

            val intent = Intent(this, MapsActivity::class.java)

            for (index in 0..(array.length() - 1)) {
                val info = array.getJSONObject(index)
                intent.putExtra("key", "real")
                intent.putExtra("lan$index",  info.getString("lan").toDouble())
                intent.putExtra("lat$index",  info.getString("lat").toDouble())
                val title = info.getString("city") + ": " + info.getString("percent") + "%"
                intent.putExtra("name$index", title)
            }
            startActivity(intent)

        }
    }
}
