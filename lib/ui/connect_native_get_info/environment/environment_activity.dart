import 'dart:async';

import 'package:flutter/services.dart';

class EnvironmentActivity {
  ///Main channel for method calls
  ///environment_activity_my_result
  static const argumentName = "argumentName_my_result";
  static const MethodChannel _methodChannel =
  MethodChannel('environment_activity_method_channel');

  static const resultMethod = "result_method";

  Future<bool> openNativeView() async {
    return await _methodChannel.invokeMethod('open_new_activity');
  }

  MethodChannel getMethodChannel() {
    return _methodChannel;
  }
  void tearDown(){
    _methodChannel.setMethodCallHandler(null);
  }
}