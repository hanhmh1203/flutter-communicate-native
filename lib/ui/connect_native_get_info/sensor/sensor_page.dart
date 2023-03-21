import 'package:flutter/material.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/sensor/sensor_controller.dart';
import 'package:get/get.dart';

class SensorScreen extends StatelessWidget {
  static open() {
    Get.to(const SensorScreen());
  }

  const SensorScreen({super.key});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return GetBuilder<SensorController>(
      init: SensorController(),
      builder: (controller) => Scaffold(
        appBar: AppBar(
          leading: IconButton(
            icon: const Icon(Icons.arrow_back),
            onPressed: () {
              Get.back();
            },
          ),
          title: const Text('Sensor Reading'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                'Sensor isAvailable: ${controller.pressureAvailable}',
                style: const TextStyle(fontSize: 16),
              ),
              const SizedBox(height: 16),

              ElevatedButton(
                onPressed: () async {
                  controller.startReading();
                },
                child: const Text('start'),
              ),
              const SizedBox(height: 16),
              Text(
                'Sensor pressure: ${controller.pressureReading}',
                style: const TextStyle(fontSize: 16),
              ),
              const SizedBox(height: 16),
              ElevatedButton(
                onPressed: () async {
                  controller.stopReading();
                },
                child: const Text('stop'),
              ),
              // Add some space between the button and the text
            ],
          ),
        ),
      ),
    );
  }
}
