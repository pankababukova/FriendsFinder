package bopadomain.bopapackage.friendsfinder

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var map: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
    private lateinit var lastLocation: Location


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val chatButton = findViewById(R.id.my_chats_button) as Button
        my_chats_button.setOnClickListener {
            val intent = Intent(this, ChatActivity :: class.java)
            startActivity(intent)
        }

        my_profile_btn.setOnClickListener{
            val intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as? SupportMapFragment
        if (mapFragment != null) {
            mapFragment.getMapAsync(this)
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        /*
        // Add a marker in Graz and move the camera
        val graz = LatLng(47.1, 15.4)
        map.addMarker(MarkerOptions().position(graz).title("Marker in Graz"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(graz, 12.0f))

         */

        map.uiSettings.isZoomControlsEnabled = true
        map.setOnMarkerClickListener(this)
        setUpMap()

        //draws a dot on users location
        //authomaticallz adds a zoom button to tap and zoom
        map.isMyLocationEnabled = true
        //gives the most recent available location
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            //move the camera is able to retrieve the mosdt recent location
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }

    }




    override fun onMarkerClick(p0: Marker?)= false

    //create a method to check if app has been granted access to fine location
    //if not granted, will request it from the user
    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
    }


}

/*
View rootView = inflater.inflate(R.layout.activity_maps, container, false);
Button chatButton = (Button)rootView.findViewById(R.id.chat_button);
chat_button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        onButtonClick((Button) view);
    }
*/