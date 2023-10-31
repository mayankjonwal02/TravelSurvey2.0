package com.jonwal.travel_survey_4

import android.Manifest
import android.app.Notification
import android.app.Notification.FOREGROUND_SERVICE_DEFAULT
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.FOREGROUND_SERVICE_DEFERRED
import androidx.core.app.NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE
import androidx.core.app.ServiceCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.google.android.gms.location.*
import com.google.firebase.firestore.FirebaseFirestore
import com.jonwal.travel_survey_4.screens.getsharedpref
import getAndroidID
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Date

class locationService : Service() {
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private lateinit var notificationManager:NotificationManager
    private lateinit var notification:NotificationCompat.Builder
    lateinit var locationCallback : LocationCallback
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Check for location permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Request location updates
            requestLocationUpdates()
        } else {
            Log.e("LocationService", "Location permission not granted")
        }

        var mynotification =  generateNotification()

        startForeground(1001,mynotification.build())


        return START_STICKY
    }

    private fun requestLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 5000
            fastestInterval = 2000
            maxWaitTime = 2
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

         locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    var date = Date()
                    var timestamp = date.time
                    var sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                    var formattedtimestamp = sdf.format(timestamp)
//                    GlobalScope.launch(Dispatchers.Main){
//                        Toast.makeText(
//                            this@locationService,
//                            "$latitude, $longitude",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                    }
                    // Create a reference to the "UserDeviceID" document
                    val userDeviceID = getAndroidID(this@locationService)!!
                    val userDeviceIDDocRef = FirebaseFirestore.getInstance().collection("UserDeviceID").document(userDeviceID)

                    val coordinatesCollectionRef = userDeviceIDDocRef.collection("coordinates")

                    coordinatesCollectionRef.add(LatLng1(location.latitude, location.longitude,formattedtimestamp))
                    var currenttime  = LocalTime.now()
                    var targettime = LocalTime.of(18,0)
                    if(currenttime == targettime  || currenttime == LocalTime.of(12,0))
                    {
                        formfillingreminder()
                    }

                }
            }
        }

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
        CoroutineScope(SupervisorJob() + Dispatchers.IO).launch{ mFusedLocationClient?.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper()) }
    }

    override fun onDestroy() {
        super.onDestroy()
        getsharedpref(this@locationService).edit().putBoolean("isservicerunning",false).apply()
        mFusedLocationClient?.removeLocationUpdates(locationCallback)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }



    fun generateNotification(): NotificationCompat.Builder {
        var channel = NotificationChannel(
            "tracking_location",
            "tracking_location",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

        notification = NotificationCompat.Builder(this@locationService , "tracking_location")
            .setContentTitle("Tracking....")
            .setContentText("App is tracking your Live Location")
            .setSmallIcon(R.drawable.location)
            .setOngoing(true)
            .setColor(ContextCompat.getColor(this, R.color.purple_200))
            .setColorized(true)
            .setForegroundServiceBehavior(FOREGROUND_SERVICE_IMMEDIATE)

            .setAutoCancel(false)


        return notification
    }

    fun formfillingreminder()
    {
        var channel = NotificationChannel(
            "form_filling_reminder",
            "form_filling_reminder",
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        var intent = Intent(this , SecondaryActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this,0, intent , PendingIntent.FLAG_IMMUTABLE)

        notification = NotificationCompat.Builder(this@locationService , "form_filling_reminder")
            .setContentTitle("Trip Experience Form")
            .setContentText("Kindly share your Trip Experience")
            .setSmallIcon(R.drawable.location)
            .setContentIntent(pendingIntent)
            .setColor(ContextCompat.getColor(this, R.color.teal_700))
            .setColorized(true)
            .setForegroundServiceBehavior(FOREGROUND_SERVICE_IMMEDIATE)

        notificationManager.notify(1002, notification.build())


    }

}
data class LatLng1(val latitude: Double, val longitude: Double,var timestamp : String)
