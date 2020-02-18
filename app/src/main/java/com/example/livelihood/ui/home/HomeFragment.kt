package com.example.livelihood.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.livelihood.*
import com.example.livelihood.ui.location.LocationFragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.json.JSONArray

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })

        root.foodButton.setOnClickListener {
            val intent : Intent = Intent(getActivity(), FoodActivity::class.java)
            startActivity(intent)
        }

        root.crimeButton.setOnClickListener {
            getAPI("https://4cd27cd9.ngrok.io/get/crimes") { result ->
                val array = JSONArray(result)

                val intent = Intent(getActivity(), MapsActivity::class.java)
                for (index in 0..(array.length() - 1)) {
                    val info = array.getJSONObject(index)
                    intent.putExtra("lan$index",  info.getString("lan").toDouble())
                    intent.putExtra("lat$index",  info.getString("lat").toDouble())
                    val title = info.getString("city") + ": " + info.getString("percent") + "%"
                    intent.putExtra("name$index", title)
                }
                startActivity(intent)
            }
        }


        root.realButton.setOnClickListener {
            getAPI("https://4cd27cd9.ngrok.io/get/mortage") { result ->
                val array = JSONArray(result)

                val intent = Intent(getActivity(), MapsActivity::class.java)
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

        return root
    }

}