package william.code.flutter_structure_app.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.embedding.android.FlutterActivity
import william.code.flutter_structure_app.ConstantEnvironmentSensor
import william.code.flutter_structure_app.R
import william.code.flutter_structure_app.SensorStreamHandler
import william.code.flutter_structure_app.scan.ConstantEnvironmentActivity
import william.code.flutter_structure_app.scan.ScanActivity


class LocationService : Service() {
    private var mLocationManager: LocationManager? = null
    private val LOCATION_INTERVAL = 200L //10 seconds
    val TAG = "LocationService"
    private val LOCATION_DISTANCE = 0.1f //10 meters
    private val NOTIFICATION_ID = 123
    lateinit var broadcastManager: LocalBroadcastManager

    class LocationListener(
        private val provider: String,
        private val broadcastManager: LocalBroadcastManager
    ) : android.location.LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.i("LocationService", "onLocationChanged $location ")
            val intent = Intent("LOCATION_UPDATE").apply {
                action = "LOCATION_UPDATE"
                putExtra("latitude", location.latitude)
                putExtra("longitude", location.longitude)
            }
            broadcastManager.sendBroadcast(intent)
            //TODO: Update the location to the server or do anything else you want to do with the location here.
        }


        override fun onProviderEnabled(provider: String) {
        }

        override fun onProviderDisabled(provider: String) {
        }

    }

    private lateinit var mLocationListeners: Array<LocationListener>
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        // Create a notification for the foreground service
        broadcastManager = LocalBroadcastManager.getInstance(this)
        mLocationListeners = arrayOf(
            LocationListener(
                LocationManager.GPS_PROVIDER,
                broadcastManager
            ),
            LocationListener(LocationManager.NETWORK_PROVIDER, broadcastManager)
        )
        val channelId = "my_channel_id"
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "My Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Foreground Service")
            .setContentText("Service is running in foreground")
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()

        // Start the foreground service

        // Start the foreground service
        startForeground(NOTIFICATION_ID, notification)
        initializeLocationManager()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        try {
            mLocationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                LOCATION_INTERVAL,
                LOCATION_DISTANCE,
                mLocationListeners[0]
            )
        } catch (ex: SecurityException) {
            Log.e(TAG, "fail to request location update, ignore", ex)
        } catch (ex: IllegalArgumentException) {
            Log.e(TAG, "gps provider does not exist " + ex.message)
        }

//        try {
//            mLocationManager!!.requestLocationUpdates(
//                LocationManager.NETWORK_PROVIDER,
//                LOCATION_INTERVAL,
//                LOCATION_DISTANCE,
//                mLocationListeners[1]
//            )
//        } catch (ex: SecurityException) {
//            Log.e(TAG, "fail to request location update, ignore", ex)
//        } catch (ex: IllegalArgumentException) {
//            Log.e(TAG, "network provider does not exist " + ex.message)
//        }
        return START_STICKY
    }

    private fun initializeLocationManager() {
        if (mLocationManager == null) {
            mLocationManager =
                applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mLocationManager != null) {
            for (listener in mLocationListeners) {
                try {
                    mLocationManager!!.removeUpdates(listener)
                } catch (ex: Exception) {
                    Log.i("LocationService", "fail to remove location listeners", ex)
                }
            }
        }
    }

}