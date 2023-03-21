import 'dart:async';

import 'package:flutter_structure_app/BaseController.dart';
import '../environment/environment_sensors.dart';

class SensorController extends BaseController {
  final String TAG = "SensorController";
  bool pressureAvailable = false;
  final environmentSensors = EnvironmentSensors();
  double pressureReading = 0;

  StreamSubscription? _pressureSubscription;

  void startReading() async {
    pressureAvailable =
        await environmentSensors.getSensorAvailable(SensorType.Pressure);
    update();
    if (pressureAvailable) {
      _pressureSubscription = environmentSensors.pressure.listen((pressure) {
        pressureReading = pressure;
        update();
      });
    }
  }

  void stopReading() {
    _pressureSubscription?.cancel();
  }
}
