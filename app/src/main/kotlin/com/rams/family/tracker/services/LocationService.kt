package com.rams.family.tracker.services

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.IBinder
import android.os.Looper
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.rams.family.tracker.model.Supporter
import com.rams.family.tracker.user
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LocationService: Service() {

    private val supporter: Supporter by inject()
    private val fusedLocationProviderClient by lazy{
        LocationServices.getFusedLocationProviderClient(this)
    }
    private val callBack:LocationCallback by lazy {
        object: LocationCallback(){
            override fun onLocationResult(result: LocationResult) {
                super.onLocationResult(result)
                val location = result.locations[0]
                processLocation(location)
            }
        }
    }
    private val locationRequest: LocationRequest get() =
        LocationRequest.Builder(Priority.PRIORITY_BALANCED_POWER_ACCURACY,10*1000).build()

    override fun onBind(p0: Intent?): IBinder? {
        
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return showNotification()
    }

    private fun showNotification(): Int {
        return START_STICKY
    }

    private fun startLocationUpdates(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,callBack, Looper.getMainLooper())
    }

    private fun processLocation(location: Location){
        CoroutineScope(Dispatchers.IO).launch {
            user?.uid?.let{

                /*supporter.userMap[it]?.let { user ->
                    user.lat = location.latitude
                    user.lng = location.longitude
                    user.lastOnline = System.currentTimeMillis()
                    db.collection("users").document(it).set(user)
                        .addOnFailureListener { ex ->
                            ex.printStackTrace()
                        }
                    userData = user
                    supporter._userState.emit(user)
                }*/
            }
            //supporter.locFlow.emit(LatLng(location.latitude, location.longitude))
        }
    }

}