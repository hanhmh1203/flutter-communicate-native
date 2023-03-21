import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/scan_code/scan_screen_page.dart';

import 'package:flutter_structure_app/ui/connect_native_get_info/tracking_location/tracking_location_screen_page.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/sensor/sensor_page.dart';

import 'package:get/get.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';

void main() async {
  runApp(GetMaterialApp(
    debugShowCheckedModeBanner: false,
    home: const HomeScreen(),
    builder: EasyLoading.init(),
  ));
}


class HomeScreen extends GetView {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        const SizedBox(height: 20),
        ElevatedButton(
          onPressed: () async {
            SensorScreen.open();
            // add your logic for button 2 here
          },
          child: const Text('Sensor Reading'),
        ),
        const SizedBox(height: 20),
        ElevatedButton(
          onPressed: () async {
            // add your logic for button 3 here
            TrackingLocationScreen.open();
          },
          child: const Text('Tracking Location'),
        ),
        const SizedBox(height: 20),
        ElevatedButton(
          onPressed: () async {
            // add your logic for button 3 here
            ScanScreen.open();
          },
          child: const Text('Scan QR Code/ BarCode'),
        ),
        const SizedBox(height: 20),
      ],
    );
  }
}
