import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_structure_app/BaseController.dart';
import '../environment/environment_sensors.dart';

class BasicController extends BaseController {
  final String TAG = "BasicController";
  String deviceInfo = "";
  final MethodChannel _methodChannel = const MethodChannel('basic_page');
  int argument = 0;

  void getInfo() async {
    deviceInfo = await _methodChannel.invokeMethod("getDeviceInfo", argument);
    print("$TAG deviceInfo: $deviceInfo");
    argument++;
    update();
  }
  @override
  void onClose() {
    // TODO: implement onClose
    super.onClose();
  }
}
