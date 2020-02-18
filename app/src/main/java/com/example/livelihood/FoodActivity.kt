package com.example.livelihood

import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_food.view.*
import org.json.JSONArray
import java.lang.Exception

class FoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_food)

        val info:Array<Any>

        getAPI("https://d743cac9.ngrok.io/get/foods") { result ->
            println(result)
            val array = JSONArray(result)
            print(array)

            val btn1 = findViewById<Button>(R.id.foodButton1)
            btn1.setOnClickListener {
                val intent = Intent(this, MapsActivity::class.java)
                val info1 = array.getJSONObject(0)

                intent.putExtra("lan", info1.getString("lan").toDouble())
                intent.putExtra("lat", info1.getString("lat").toDouble())
                intent.putExtra("name", "Coop's Aurora Bistro")
                startActivity(intent)
            }

            val btn2 = findViewById<Button>(R.id.foodButton2)
            btn2.setOnClickListener {
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("lan", 44.006864)
                intent.putExtra("lat", -79.468816)
                intent.putExtra("name", "Mei Ling Chinese Food")
                startActivity(intent)
            }

            val btn3 = findViewById<Button>(R.id.foodButton3)
            btn3.setOnClickListener {
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("lan", 44.007233)
                intent.putExtra("lat", -79.468547)
                intent.putExtra("name", "Shawarma Land")
                startActivity(intent)
            }
        }
    }

}
