import 'dart:async';
import 'dart:ffi';
import 'dart:io';

import 'package:flutter/services.dart';
import 'package:flutter_structure_app/BaseController.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/environment/environment_tracking_location.dart';
import 'package:get/get.dart';
import '../environment/environment_activity.dart';

class PlatFormViewScanController extends BaseController {
  final String TAG = "PlatFormViewScanController";

  String resultReading = "";

  static const String viewType = 'ScanView';

  static const Map<String, dynamic> creationParams = <String, dynamic>{};
  MethodChannel channel = const MethodChannel("PlatformView_ScanView");
  RxBool isCameraReady = false.obs;
  @override
  void onInit() {
    // TODO: implement onInit
    super.onInit();
    //listen data send from Native
    channel.setMethodCallHandler((call) {
      if (call.method == "data_from_native_to_flutter") {
        resultReading = call.arguments;
        isCameraReady.value = false;
        update();
      }
      return Future.value(null);
    });
  }

  @override
  void onReady() {
    // TODO: implement onReady
    super.onReady();
    startReading();
  }
  //send and method to native with argument: "Hello PlatFormView"
  void startReading() async {
    bool result = await channel.invokeMethod(
        "data_from_flutter_to_native", "Hello PlatFormView");
    isCameraReady.value = result;
    update();
  }

  @override
  void dispose() {
    // TODO: implement dispose
    isCameraReady.close();
    super.dispose();
  }
}
