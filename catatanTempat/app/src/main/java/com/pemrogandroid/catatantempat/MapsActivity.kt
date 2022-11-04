package com.pemrogandroid.catatantempat

import android.annotation.SuppressLint
import android.content.ContentProviderClient
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PackageManagerCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.Task
import com.pemrogandroid.catatantempat.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var map: GoogleMap
    private lateinit var btnMyLoc : Button
    private lateinit var btnKampus : Button
    private lateinit var btnRumah : Button
    private lateinit var binding: ActivityMapsBinding
    private val PERMISSION_LOC = 1




    @SuppressLint("MissingPermission")
    private fun getLocation(){
        if(ContextCompat.checkSelfPermission(this,
            android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            map.isMyLocationEnabled = true
        }else
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),PERMISSION_LOC)
    }

    @SuppressLint("MissingPermission", "MissingSuperCall")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == PERMISSION_LOC){
            if(grantResults.contains(PackageManager.PERMISSION_GRANTED)){
                map.isMyLocationEnabled = true
            }
            else{
                Toast.makeText(this,"akses batal",Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun fetchLocation(){
        val task = fusedLocationProviderClient.lastLocation

        if(ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            map.isMyLocationEnabled = true
        }else
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),PERMISSION_LOC)
        task.addOnSuccessListener {
            if(it!=null){
                val zoom = 20f
                val myLoc =  LatLng(it.latitude,it.longitude)
                map.addMarker(MarkerOptions().position(myLoc).title("apa hayo"))
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc, zoom))
            }
        }
    }
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val zoom = 20f
        val locDefault = LatLng(-7.7925968,110.3657317)
        map.addMarker(MarkerOptions().position(locDefault).title("apa hayo"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(locDefault, zoom))
        btnMyLoc = findViewById(R.id.btnMyLoc)
        btnMyLoc.setOnClickListener {
            fetchLocation()
        }

        val kampus = LatLng(-7.7860846,110.3783007)
        btnKampus = findViewById(R.id.btnKampus)
        btnKampus.setOnClickListener {
            map.addMarker(MarkerOptions().position(kampus).title("Kampus"))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(kampus, zoom))
        }

        val rumah = LatLng(-7.8445749,110.3751202)
        btnRumah = findViewById(R.id.btnRumah)
        btnRumah.setOnClickListener {
            map.addMarker(MarkerOptions().position(rumah).title("Rumah"))
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(rumah, zoom))

        }

    }
}