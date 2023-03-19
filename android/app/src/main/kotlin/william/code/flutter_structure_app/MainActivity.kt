package william.code.flutter_structure_app

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.Sensor
import android.hardware.SensorManager
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import william.code.flutter_structure_app.scan.ConstantEnvironmentActivity
import william.code.flutter_structure_app.scan.ScanActivity
import william.code.flutter_structure_app.service.EnvironmentChannelServiceLocation
import william.code.flutter_structure_app.service.LocationService

class MainActivity : FlutterActivity(), EventChannel.StreamHandler {
    private val REQUEST_CODE_SCAN_ACTIVITY = 1
    private var sensorMethodChannel: MethodChannel? = null;
    private lateinit var sensorManager: SensorManager;
    private var pressureChannel: EventChannel? = null
    private var ambientTemperatureChannel: EventChannel? = null
    private var humidityChannel: EventChannel? = null
    private var lightChannel: EventChannel? = null


    private var activityMethodChannel: MethodChannel? = null;


    private var locationBroadcastReceiver: FlutterLocationReceiver = FlutterLocationReceiver()
    private var eventSink: EventChannel.EventSink? = null
    private  lateinit var environmentChannelServiceLocation: EnvironmentChannelServiceLocation
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        Log.i("hanhmh1203", "configureFlutterEngine")
        super.configureFlutterEngine(flutterEngine)
        environmentChannelServiceLocation = EnvironmentChannelServiceLocation(flutterEngine)
        setupChannel(flutterEngine.dartExecutor.binaryMessenger)

    }

    private fun setupChannel(messenger: BinaryMessenger) {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorMethodChannel = MethodChannel(messenger, ConstantEnvironmentSensor.methodChannelName)
        sensorMethodChannel?.let {
            it.setMethodCallHandler { methodCall: MethodCall, result: MethodChannel.Result ->
                if (methodCall.method == ConstantEnvironmentSensor.methodName) {
                    setupForSensor(messenger, methodCall, result)
                } else {
                    result.notImplemented()
                }
            }
        }
        activityMethodChannel =
            MethodChannel(messenger, ConstantEnvironmentActivity.methodChannelName)
        activityMethodChannel!!.let {
            it.setMethodCallHandler { methodCall: MethodCall, result: MethodChannel.Result ->
                if (methodCall.method == ConstantEnvironmentActivity.openNativeViewMethod) {
                    startActivityForResult(
                        Intent(this, ScanActivity::class.java),
                        REQUEST_CODE_SCAN_ACTIVITY
                    )
                    result.success(
                        true
                    )
                } else {
                    result.notImplemented()
                }
            }
        }

        environmentChannelServiceLocation.serviceTrackingMC.let {
            it.setMethodCallHandler { methodCall: MethodCall, result: MethodChannel.Result ->
                when (methodCall.method) {
                    EnvironmentChannelServiceLocation.methodStartService -> {
                        val intent = Intent(this, LocationService::class.java)
                        startService(intent)
                        result.success(true)
                    }
                    EnvironmentChannelServiceLocation.methodStopService -> {
                        val intent = Intent(this, LocationService::class.java)
                        stopService(intent)
                        result.success(true)
                    }
                    else -> {
                        result.notImplemented()
                    }
                }
            }
        }

        environmentChannelServiceLocation.sendLocationEC.setStreamHandler(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_SCAN_ACTIVITY) {
                var result: String? = data?.getStringExtra("my_result")
                if (result == null) result = "hanhmh1203"
                returnResultToFlutter(result)
            }
        }
    }

    private fun returnResultToFlutter(result: String) {
        val methodChannel = MethodChannel(
            flutterEngine!!.dartExecutor.binaryMessenger,
            ConstantEnvironmentActivity.methodChannelName
        )
        val resultData: MutableMap<String, Any> = HashMap()
        resultData[ConstantEnvironmentActivity.argumentName] = result
        methodChannel.invokeMethod(ConstantEnvironmentActivity.activityResultMethod, resultData)
    }

    private fun setupForSensor(
        messenger: BinaryMessenger,
        methodCall: MethodCall,
        result: MethodChannel.Result
    ) {
        when (methodCall.arguments) {
            Sensor.TYPE_PRESSURE -> {//6
                setupStreamForPressure(messenger)
                result.success(
                    sensorManager.getSensorList(Sensor.TYPE_PRESSURE).isNotEmpty()
                )
            }
            Sensor.TYPE_AMBIENT_TEMPERATURE -> {
                setupStreamForAmbientTemperature(messenger)
                result.success(
                    sensorManager.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE)
                        .isNotEmpty()
                )
            }
            Sensor.TYPE_RELATIVE_HUMIDITY -> {
                setupStreamForHumidity(messenger)
                result.success(
                    sensorManager.getSensorList(Sensor.TYPE_RELATIVE_HUMIDITY)
                        .isNotEmpty()
                )
            }
            Sensor.TYPE_LIGHT -> {
                setupStreamForLight(messenger)
                result.success(
                    sensorManager.getSensorList(Sensor.TYPE_LIGHT).isNotEmpty()
                )
            }
            null -> {
                result.success(true)
            }
            else -> {
                result.notImplemented()
            }
        }
    }

    private fun setupStreamForPressure(messenger: BinaryMessenger) {
        pressureChannel = EventChannel(messenger, ConstantEnvironmentSensor.pressureChannelName)
        val streamChannel = SensorStreamHandler(sensorManager, Sensor.TYPE_PRESSURE)
        pressureChannel!!.setStreamHandler(streamChannel)
    }

    private fun setupStreamForAmbientTemperature(messenger: BinaryMessenger) {
        ambientTemperatureChannel =
            EventChannel(messenger, ConstantEnvironmentSensor.ambientTemperatureChannelName)
        val streamChannel = SensorStreamHandler(sensorManager, Sensor.TYPE_AMBIENT_TEMPERATURE)
        ambientTemperatureChannel!!.setStreamHandler(streamChannel)
    }

    private fun setupStreamForHumidity(messenger: BinaryMessenger) {
        humidityChannel = EventChannel(messenger, ConstantEnvironmentSensor.humidityChannelName)
        val streamChannel = SensorStreamHandler(sensorManager, Sensor.TYPE_RELATIVE_HUMIDITY)
        humidityChannel!!.setStreamHandler(streamChannel)
    }

    private fun setupStreamForLight(messenger: BinaryMessenger) {
        lightChannel = EventChannel(messenger, ConstantEnvironmentSensor.lightChannelName)
        val streamChannel = SensorStreamHandler(sensorManager, Sensor.TYPE_LIGHT)
        lightChannel!!.setStreamHandler(streamChannel)
    }

    private fun tearDownChannel() {
        sensorMethodChannel?.setMethodCallHandler(null)
        pressureChannel?.setStreamHandler(null)
        lightChannel?.setStreamHandler(null)
        humidityChannel?.setStreamHandler(null)
        environmentChannelServiceLocation.tearDown()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("hanhmh1203", "onDestroy")
        tearDownChannel()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(locationBroadcastReceiver)
    }

    inner class FlutterLocationReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            val latitude = intent?.getDoubleExtra("latitude", 0.0)
            val longitude = intent?.getDoubleExtra("longitude", 0.0)
            Log.i("hanhmh1203", "FlutterLocationReceiver latitude${latitude} longitude:$longitude")
            eventSink?.success("${String.format("%.6f", latitude)}:${String.format("%.6f", longitude)}")
        }

    }

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        eventSink = events
        val filter = IntentFilter().apply {
            addAction("LOCATION_UPDATE")
        }
        LocalBroadcastManager.getInstance(this).registerReceiver(
            locationBroadcastReceiver,
            filter
        )
    }

    override fun onCancel(arguments: Any?) {
    }
}
