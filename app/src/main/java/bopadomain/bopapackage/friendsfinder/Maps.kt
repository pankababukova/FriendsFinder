package bopadomain.bopapackage.friendsfinder
/*
package bopadomain.bopapackage.friendsfinder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_maps.*


abstract class Maps : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)

        //Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady (googleMap: GoogleMap) {
        mMap = googleMap

        val graz = LatLng(47.1, 15.4)
        mMap.addMarker(MarkerOptions().position(graz).title("Marker in Graz"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(graz))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)



        var auth= FirebaseAuth.getInstance()
        sign_in_email.text= "Hello " + auth.currentUser?.email


        signout_button.setOnClickListener{
            auth.signOut()
            startActivity(Intent (this, MainActivity::class.java))
        }


    signout_button.setOnClickListener{
        AuthUI.getInstance().signOut(this)
        var i=Intent(this, MainActivity::class.java)
        startActivity(i)
    }
    }
}
*/