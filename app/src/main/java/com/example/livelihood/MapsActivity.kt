package com.example.livelihood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewParent
import com.example.livelihood.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONArray

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (intent.hasExtra("lan")) {
            var point: LatLng =
                LatLng(intent.getDoubleExtra("lan", 0.0), intent.getDoubleExtra("lat", 0.0))

            mMap.addMarker(MarkerOptions().position(point).title(intent.getStringExtra("name")))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(point))
        }
        if (intent.hasExtra("lan0")) {
            for (index in 0..5) {
                var point = LatLng(intent.getDoubleExtra("lan$index", 0.0), intent.getDoubleExtra("lat$index", 0.0))
                mMap.addMarker(MarkerOptions().position(point).title(intent.getStringExtra("name$index")))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(point))
            }
        }
        if (intent.hasExtra("key")) {
            for (index in 0..6) {
                var point = LatLng(intent.getDoubleExtra("lan$index", 0.0), intent.getDoubleExtra("lat$index", 0.0))
                mMap.addMarker(MarkerOptions().position(point).title(intent.getStringExtra("name$index")))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(point))
            }
        }
    }
}
