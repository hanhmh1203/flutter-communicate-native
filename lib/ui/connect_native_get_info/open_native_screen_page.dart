import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_structure_app/ui/api_test/HomeBinding.dart';
import 'package:flutter_structure_app/ui/api_test/HomeController.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/sensor/sensor_controller.dart';
import 'package:get/get.dart';

import 'open_native_controller.dart';

class OpenOtherScreen extends StatelessWidget {
  const OpenOtherScreen({super.key});

  static open() {
    Get.to(const OpenOtherScreen());
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return GetBuilder<OpenNativeController>(
      init: OpenNativeController(),
      builder: (controller) => Scaffold(
        appBar: AppBar(
          leading: IconButton(
            icon: const Icon(Icons.arrow_back),
            onPressed: () {
              Get.back();
            },
          ),
          title: const Text('Communicate with Native'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                child: ElevatedButton(
                  onPressed: () async {
                    if (GetPlatform.isAndroid) {
                      controller.startTracking();
                    }
                  },
                  child: Text('start tracking'.toUpperCase()),
                ),
              ),
              SizedBox(height: 16),
              Text(
                'Tracking location: ${controller.resultLocation}',
                style: TextStyle(fontSize: 16),
              ),
              SizedBox(height: 16),
              Container(
                child: ElevatedButton(
                  onPressed: () async {
                    controller.stopTracking();
                  },
                  child: const Text('STOP TRACKING'),
                ),
              ),
              SizedBox(height: 16),

              Text(
                'Can open Native Screen: ${controller.isAvailable}',
                style: TextStyle(fontSize: 16),
              ),
              SizedBox(height: 16),

              Container(
                child: ElevatedButton(
                  onPressed: () async {
                    // if (GetPlatform.isAndroid) {
                    //   controller.startReading();
                    // }
                    controller.startReading();
                  },
                  child: Text('Start Scan'),
                ),
              ),
              SizedBox(height: 16),
              Text(
                'Text from Native: ${controller.resultReading}',
                style: TextStyle(fontSize: 16),
              ),

              // Add some space between the button and the text
            ],
          ),
        ),
      ),
    );
  }
}
