package william.code.flutter_structure_app.service

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel

class EnvironmentChannelServiceLocation(flutterEngine: FlutterEngine) {
    companion object{
        const val methodStartService = "start_tracking_location"
        const val methodStopService = "stop_tracking_location"
    }
    var serviceTrackingMC: MethodChannel =
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "start_service")
    var sendLocationEC: EventChannel =
        EventChannel(flutterEngine.dartExecutor.binaryMessenger, "receive_tracking_location")

    fun tearDown(){
        sendLocationEC.setStreamHandler(null)
    }
}