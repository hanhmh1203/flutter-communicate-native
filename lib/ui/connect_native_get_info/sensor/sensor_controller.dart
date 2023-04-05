import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_structure_app/BaseController.dart';
import '../environment/environment_sensors.dart';

class SensorController extends BaseController {
  final String TAG = "SensorController";

  double pressureReading = 0;
  void stopReading() {
    _pressureSubscription?.cancel();
  }

  final MethodChannel _methodChannel =
  const MethodChannel('environment_sensors/method');
  bool pressureAvailable = false;

  StreamSubscription? _pressureSubscription;

  void startReading() async {
    pressureAvailable =
    await getSensorAvailable(SensorType.Pressure);
    update();
    if (pressureAvailable) {
      _pressureSubscription = pressure.listen((pressure) {
        pressureReading = pressure;
        update();
      });
    }
  }

  Future<bool> getSensorAvailable(SensorType sensorType) async {
    if (sensorType == SensorType.Pressure) {
      return await _methodChannel.invokeMethod(
          'isSensorAvailable', 6); //TYPE_PRESSURE = 6;
    }
    return false;
  }

  final EventChannel _pressureEventChannel =
  const EventChannel('environment_sensors/pressure');
  Stream<double>? _pressureEvents;

  Stream<double> get pressure {
    _pressureEvents ??= _pressureEventChannel
        .receiveBroadcastStream()
        .map((event) => double.parse(event.toString()));
    return _pressureEvents!;
  }
}