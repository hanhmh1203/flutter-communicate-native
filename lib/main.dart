import 'package:flutter/material.dart';
import 'package:flutter_easyloading/flutter_easyloading.dart';
import 'package:flutter_structure_app/di/AppBinding.dart';

import 'package:flutter_structure_app/ui/api_test/HomeScreen.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/open_native_screen_page.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/sensor/sensor_page.dart';

import 'package:get/get.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';

void main() async {

  runApp(GetMaterialApp(
      debugShowCheckedModeBanner: false,
      home: const LoginScreen(),

      builder: EasyLoading.init(),
      initialBinding: AppBinding()));

  configLoading();
}

void configLoading() {
  EasyLoading.instance
    // ..displayDuration = const Duration(milliseconds: 2000)
    ..indicatorType = EasyLoadingIndicatorType.fadingCircle
    ..loadingStyle = EasyLoadingStyle.custom
    ..indicatorSize = 45.0
    ..radius = 10.0
    ..progressColor = Colors.white
    ..backgroundColor = Colors.black26
    ..indicatorColor = Colors.white
    ..textColor = Colors.white
    ..maskColor = Colors.blue.withOpacity(0.5)
    ..userInteractions = true
    ..dismissOnTap = false;
  // ..customAnimation = CustomAnimation();
}

class LoginScreen extends GetView {
  const LoginScreen({super.key});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        ElevatedButton(
          onPressed: () async {
            HomeScreen.openHome();
          },
          child: const Text('To Test Api Call'),
        ),
        const SizedBox(height: 20),
        ElevatedButton(
          onPressed: () async {
            SensorScreen.open();
            // add your logic for button 2 here
          },
          child: const Text('Test Native Communicate'),
        ),
        SizedBox(height: 20),
        ElevatedButton(
          onPressed: () async {
            // add your logic for button 3 here
            OpenOtherScreen.open();
          },
          child: Text('Open Native Screen'),
        ),
        SizedBox(height: 20),
        ElevatedButton(
          onPressed: () async {
            // add your logic for button 4 here
          },
          child: Text('Button 4'),
        ),
        SizedBox(height: 20),
        ElevatedButton(
          onPressed: () async {
            // add your logic for button 5 here
          },
          child: Text('Button 5'),
        ),
      ],

    );
  }
}
