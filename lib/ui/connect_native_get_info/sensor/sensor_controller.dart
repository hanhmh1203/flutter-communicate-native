import 'dart:async';

import 'package:flutter_structure_app/BaseController.dart';
import 'package:flutter_structure_app/domain_layer/usecase/load_astros_usecase.dart';
import 'package:get/get.dart';

import '../../../domain_layer/data/DataResult.dart';
import '../environment/environment_sensors.dart';

class SensorController extends BaseController {
  final String TAG = "SensorController";
  bool tempAvailable = false;
  bool humidityAvailable = false;
  bool lightAvailable = false;
  bool pressureAvailable = false;
  bool isAvailable = false;
  final environmentSensors = EnvironmentSensors();
  double pressureReading = 0;
  String temReading = "";
  String humidityReading = "";
  String lightReading = "";

  StreamSubscription? _pressureSubscription;
  StreamSubscription? _tempSubscription;
  StreamSubscription? _humiditySubscription;
  StreamSubscription? _lightSubscription;

  void startReadingAndroid() async {
    // isAvailable = await environmentSensors.isAvailable();
    // update();
    pressureAvailable =
        await environmentSensors.getSensorAvailable(SensorType.Pressure);
    print("pressureAvailable ${pressureAvailable}");
    if (pressureAvailable) {
      _pressureSubscription = environmentSensors.pressure.listen((pressure) {
        pressureReading = pressure;
        update();
      });
    }

    tempAvailable = await environmentSensors
        .getSensorAvailable(SensorType.AmbientTemperature);
    print("tempAvailable ${tempAvailable}");
    if (tempAvailable) {
      _tempSubscription = environmentSensors.temperature.listen((temperature) {
        temReading = temperature.toString();
        print("_tempSubscription:$temperature");
        update();
      });
    }
    humidityAvailable =
        await environmentSensors.getSensorAvailable(SensorType.Humidity);
    print("humidityAvailable ${humidityAvailable}");
    if (humidityAvailable) {
      _humiditySubscription = environmentSensors.humidity.listen((humidity) {
        print("_humiditySubscription:$humidity");
        humidityReading = humidity.toString();
        update();
      });
    }
    lightAvailable =
        await environmentSensors.getSensorAvailable(SensorType.Light);
    print("lightAvailable ${lightAvailable}");
    if (lightAvailable) {
      _lightSubscription = environmentSensors.light.listen((light) {
        print("_lightSubscription:$light");
        lightReading = light.toString();
        update();
      });
    }
  }

  void startReadingIos() async {
    print("hanhmh1203 startReadingIos");
    isAvailable = await environmentSensors.isAvailable();
    print("hanhmh1203 startReadingIos: $isAvailable");
    update();
    pressureAvailable =
        await environmentSensors.getSensorAvailable(SensorType.Pressure);
    print("pressureAvailable ${pressureAvailable}");
    if (pressureAvailable) {
      _pressureSubscription = environmentSensors.pressure.listen((pressure) {
        pressureReading = pressure;
        update();
      });
    }
  }

  void stopReading() {
    _pressureSubscription?.cancel();
    _tempSubscription?.cancel();
    _humiditySubscription?.cancel();
    _lightSubscription?.cancel();
  }
}
