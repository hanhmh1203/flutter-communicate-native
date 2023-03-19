import 'dart:async';

import 'package:flutter/services.dart';

class EnvironmentChannelServiceLocation {
  static const _methodStartService = "start_tracking_location";
  static const _methodStopService = "stop_tracking_location";

  final MethodChannel _serviceTrackingMC = const MethodChannel('start_service');
  final EventChannel _receiveLocationEC =
      const EventChannel('receive_tracking_location');

  Future startTracking() async {
    if (resultSubscription != null && resultSubscription!.isPaused) {
      resultSubscription?.resume();
    }
    return await _serviceTrackingMC.invokeMethod(_methodStartService);
  }

  Future stopTracking() async {
    if (resultSubscription != null && !resultSubscription!.isPaused) {
      resultSubscription?.pause();
    }
    return await _serviceTrackingMC.invokeMethod(_methodStopService);
  }

  StreamSubscription? resultSubscription;

  Stream<String>? _resultEvent;

  Stream<String> get result {
    _resultEvent ??= _receiveLocationEC
        .receiveBroadcastStream()
        .map((event) => event.toString());
    return _resultEvent!;
  }

  void tearDown() {
    resultSubscription?.cancel();
  }
}
