import 'package:flutter/material.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/scan_code/scan_controller.dart';
import 'package:get/get.dart';

class ScanScreen extends StatelessWidget {
  const ScanScreen({super.key});

  static open() {
    Get.to(const ScanScreen());
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return GetBuilder<ScanController>(
      init: ScanController(),
      builder: (controller) => Scaffold(
        appBar: AppBar(
          leading: IconButton(
            icon: const Icon(Icons.arrow_back),
            onPressed: () {
              Get.back();
            },
          ),
          title: const Text('Scan QR Code/BarCode'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ElevatedButton(
                onPressed: () async {
                  controller.startReading();
                },
                child: const Text('Start Scan QR Code/ BarCode'),
              ),
              const SizedBox(height: 16),
              Text(
                'Text from Native: ${controller.resultReading}',
                style: const TextStyle(fontSize: 16),
              ),

              // Add some space between the button and the text
            ],
          ),
        ),
      ),
    );
  }
}
