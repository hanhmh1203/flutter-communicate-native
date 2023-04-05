package william.code.flutter_structure_app

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener2
import android.hardware.SensorManager
import android.os.Build
import androidx.annotation.RequiresApi
import io.flutter.plugin.common.EventChannel

@RequiresApi(Build.VERSION_CODES.KITKAT)
class SensorStreamHandler(
    private val sensorManager: SensorManager,
    sensorType: Int,
    private val interval: Int = SensorManager.SENSOR_DELAY_NORMAL
) : EventChannel.StreamHandler, SensorEventListener2 {
    private val sensor = sensorManager.getDefaultSensor(sensorType)
    private var eventSink: EventChannel.EventSink? =null
    override fun onSensorChanged(event: SensorEvent?) {
        val sensorValue = event!!.values[0]
        eventSink?.success(sensorValue)
    }
    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        if(sensor!=null){
            eventSink = events
            sensorManager.registerListener(this, sensor, interval)
        }
    }

    override fun onCancel(arguments: Any?) {
        sensorManager.unregisterListener(this)
        eventSink = null
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onFlushCompleted(sensor: Sensor?) {
    }
}