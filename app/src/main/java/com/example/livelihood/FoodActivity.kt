package com.example.livelihood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import org.json.JSONArray

class FoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_food)

        getAPI("https://4cd27cd9.ngrok.io/get/foods") { result ->
            val array = JSONArray(result)

            val btn1 = findViewById<Button>(R.id.foodButton1)
            btn1.setOnClickListener {
                val intent = Intent(this, MapsActivity::class.java)
                val info = array.getJSONObject(0)

                intent.putExtra("lan", info.getString("lan").toDouble())
                intent.putExtra("lat", info.getString("lat").toDouble())
                intent.putExtra("name",  info.getString("name"))
                startActivity(intent)
            }

            val btn2 = findViewById<Button>(R.id.foodButton2)
            btn2.setOnClickListener {
                val intent = Intent(this, MapsActivity::class.java)
                val info = array.getJSONObject(1)

                intent.putExtra("lan",  info.getString("lan").toDouble())
                intent.putExtra("lat",  info.getString("lat").toDouble())
                intent.putExtra("name", info.getString("name"))
                startActivity(intent)
            }

            val btn3 = findViewById<Button>(R.id.foodButton3)
            btn3.setOnClickListener {
                val intent = Intent(this, MapsActivity::class.java)
                val info = array.getJSONObject(2)

                intent.putExtra("lan",  info.getString("lan").toDouble())
                intent.putExtra("lat",  info.getString("lat").toDouble())
                intent.putExtra("name", info.getString("name"))
                startActivity(intent)
            }
        }
    }

}
