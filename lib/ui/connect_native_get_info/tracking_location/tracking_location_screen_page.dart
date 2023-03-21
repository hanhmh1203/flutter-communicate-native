import 'package:flutter/material.dart';
import 'package:get/get.dart';

import 'tracking_location_controller.dart';

class TrackingLocationScreen extends StatelessWidget {
  const TrackingLocationScreen({super.key});

  static open() {
    Get.to(const TrackingLocationScreen());
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return GetBuilder<TrackingLocationController>(
      init: TrackingLocationController(),
      builder: (controller) => Scaffold(
        appBar: AppBar(
          leading: IconButton(
            icon: const Icon(Icons.arrow_back),
            onPressed: () {
              Get.back();
            },
          ),
          title: const Text('Tracking Location (Android Only)'),
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

              // Add some space between the button and the text
            ],
          ),
        ),
      ),
    );
  }
}
