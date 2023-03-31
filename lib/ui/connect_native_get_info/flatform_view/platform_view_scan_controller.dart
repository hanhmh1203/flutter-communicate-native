import 'dart:async';
import 'dart:io';

import 'package:flutter_structure_app/BaseController.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/environment/environment_tracking_location.dart';
import '../environment/environment_activity.dart';

class PlatFormViewScanController extends BaseController {
  final String TAG = "ScanController";

  String resultReading = "";

  final environmentActivity = EnvironmentActivity();
  static const String viewType = 'ScanView';
  // Pass parameters to the platform side.
  static const Map<String, dynamic> creationParams = <String, dynamic>{};
  @override
  void onInit() {
    // TODO: implement onInit
    super.onInit();
    //receive by Method Channel
    // environmentActivity.getMethodChannel().setMethodCallHandler((call) {
    //   if (call.method == EnvironmentActivity.resultMethod) {
    //     print("$TAG ${call.arguments}");
    //     String result = call.arguments[EnvironmentActivity.argumentName];
    //     resultReading = result;
    //     update();
    //   }
    //   return Future.value(null);
    // });

  }

  void startReading() async {
    print("startReading");
    await environmentActivity.openNativeView();
    update();
  }

  @override
  void dispose() {
    // TODO: implement dispose
    environmentActivity.tearDown();
    super.dispose();
  }
}
