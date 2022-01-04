package net.basicmodel

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
@SuppressLint("MissingPermission")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mapview.onCreate(savedInstanceState)
        mapview.onResume()
        MapsInitializer.initialize(this)
        mapview.getMapAsync {
            it.mapType = GoogleMap.MAP_TYPE_NORMAL
            it.uiSettings.isZoomControlsEnabled = true
            it.isMyLocationEnabled = true
            it.uiSettings.isMyLocationButtonEnabled = true
            it.setOnMyLocationButtonClickListener {
                val locationManager: LocationManager =
                    this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) as Location
                location.let { it1 ->
                    val position = CameraPosition.builder().target(LatLng(it1.latitude, it1.longitude))
                        .zoom(15.5f)
                        .bearing(0f)
                        .tilt(25f)
                        .build()
                    it.animateCamera(CameraUpdateFactory.newCameraPosition(position),1000, null)
                    it.addMarker(MarkerOptions()
                        .position(LatLng(it1.latitude, it1.longitude)).title("On Your Location"))
                }
                false
            }
            val locationManager: LocationManager =
                this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) as Location
            location.let { it1 ->
                val position = CameraPosition.builder().target(LatLng(it1.latitude, it1.longitude))
                    .zoom(15.5f)
                    .bearing(0f)
                    .tilt(25f)
                    .build()
                it.animateCamera(CameraUpdateFactory.newCameraPosition(position),1000, null)
                it.addMarker(MarkerOptions()
                    .position(LatLng(it1.latitude, it1.longitude)).title("On Your Location"))
            }

        }

    }
}