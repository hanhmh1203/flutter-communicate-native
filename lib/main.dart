import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/basic/basic_page.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/flatform_view/platform_view_scan_page.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/scan_code/scan_page.dart';

import 'package:flutter_structure_app/ui/connect_native_get_info/tracking_location/tracking_location_screen_page.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/sensor/sensor_page.dart';

import 'package:get/get.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';

void main() async {
  runApp(const GetMaterialApp(
    debugShowCheckedModeBanner: false,
    home: HomeScreen(),
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
            BasicScreen.open();
            // add your logic for button 2 here
          },
          child: const Text(' Basic Method'),
        ),
        const SizedBox(height: 20),
        ElevatedButton(
          onPressed: () async {
            SensorScreen.open();
            // add your logic for button 2 here
          },
          child: const Text('Sensor Reading (Stream)'),
        ),
        const SizedBox(height: 20),
        ElevatedButton(
          onPressed: () async {
            // add your logic for button 3 here
            TrackingLocationScreen.open();
          },
          child: const Text('Tracking Location (Stream)'),
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

        ElevatedButton(
          onPressed: () async {
            // add your logic for button 3 here
            PlatFormViewScanScreen.open();
          },
          child: const Text('Platform View'),
        ),
        const SizedBox(height: 20),
      ],
    );
  }
}
