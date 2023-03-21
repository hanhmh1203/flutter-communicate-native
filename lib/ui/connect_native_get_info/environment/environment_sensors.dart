import 'dart:async';

import 'package:flutter/services.dart';


class EnvironmentSensors {
  ///Main channel for method calls
  final MethodChannel _methodChannel =
  const MethodChannel('environment_sensors/method');


  ///Event channel for pressure readings
  final EventChannel _pressureEventChannel =
  const EventChannel('environment_sensors/pressure');


  ///Stream of pressure readings
  Stream<double>? _pressureEvents;
  ///Check for the availabilitity of device sensor by sensor type.
  Future<bool> getSensorAvailable(SensorType sensorType) async {
    if (sensorType == SensorType.Pressure) {
      return await _methodChannel.invokeMethod('isSensorAvailable', 6);//TYPE_PRESSURE = 6;
    }
    return false;
  }

  ///Gets the pressure reading from device sensor, if present
  Stream<double> get pressure {
    _pressureEvents ??= _pressureEventChannel
          .receiveBroadcastStream()
          .map((event) => double.parse(event.toString()));
    return _pressureEvents!;
  }
}

///An enum for defining device types when checking for sensor availability
enum SensorType { Pressure }