import 'package:flutter/material.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/basic/basic_controller.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/sensor/sensor_controller.dart';
import 'package:get/get.dart';

class BasicScreen extends StatelessWidget {
  static open() {
    Get.to(const BasicScreen());
  }

  const BasicScreen({super.key});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return GetBuilder<BasicController>(
      init: BasicController(),
      builder: (controller) => Scaffold(
        appBar: AppBar(
          leading: IconButton(
            icon: const Icon(Icons.arrow_back),
            onPressed: () {
              Get.back();
            },
          ),
          title: const Text('Basic Method'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(
                onPressed: () async {
                  controller.getInfo();
                },
                child: const Text('Get Device Info'),
              ),
              const SizedBox(height: 16),
              Text(
                'Device info: ${controller.deviceInfo}',
                style: const TextStyle(fontSize: 16),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
