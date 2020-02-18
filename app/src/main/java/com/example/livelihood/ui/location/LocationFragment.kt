package com.example.livelihood.ui.location

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.livelihood.R
import com.example.livelihood.ui.notifications.NotificationsViewModel
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import android.text.method.TextKeyListener.clear
import android.widget.Button
import com.example.livelihood.MapsActivity
import com.example.livelihood.RealEstate
import com.example.livelihood.getAPI
import com.google.android.gms.maps.GoogleMap
import org.json.JSONArray

class LocationFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_location, container, false)
        getAPI("https://4cd27cd9.ngrok.io/get/crimes") { result ->
            val array = JSONArray(result)

            val btn1 = root.findViewById<Button>(R.id.crimebutton1)
            btn1.setOnClickListener {
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

        val btn1 = root.findViewById<Button>(R.id.realEstateButton)
        btn1.setOnClickListener {
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