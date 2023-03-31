import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:flutter/services.dart';
import 'package:flutter_structure_app/ui/connect_native_get_info/scan_code/scan_controller.dart';
import 'package:get/get.dart';

import 'platform_view_scan_controller.dart';

class PlatFormViewScanScreen extends StatelessWidget {
  const PlatFormViewScanScreen({super.key});

  static open() {
    Get.to(const PlatFormViewScanScreen());
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return GetBuilder<PlatFormViewScanController>(
      init: PlatFormViewScanController(),
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
          body: SafeArea(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                const SizedBox(height: 16),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                  SizedBox(
                  width: 300,
                  height: 300,
                  child: Stack(
                    children: [
                      PlatformViewLink(
                        viewType: PlatFormViewScanController.viewType,
                        surfaceFactory: (context, controller) {
                          return AndroidViewSurface(
                            controller: controller as AndroidViewController,
                            gestureRecognizers: const <
                                Factory<OneSequenceGestureRecognizer>>{},
                            hitTestBehavior: PlatformViewHitTestBehavior.opaque,
                          );
                        },
                        onCreatePlatformView: (params) {
                          return PlatformViewsService.initSurfaceAndroidView(
                            id: params.id,
                            viewType: PlatFormViewScanController.viewType,
                            layoutDirection: TextDirection.ltr,
                            creationParams:
                            PlatFormViewScanController.creationParams,
                            creationParamsCodec: const StandardMessageCodec(),
                            onFocus: () {
                              params.onFocusChanged(true);
                            },
                          )
                            ..addOnPlatformViewCreatedListener(
                                params.onPlatformViewCreated)
                            ..create();
                        },
                      ),
                      Container(
                        color: Colors.black, // set your desired background color here
                        child: const Visibility(
                          visible: false, // set visibility to false when platform view is attached
                          child: SizedBox(
                            width: 300,
                            height: 300,
                          ),
                        ),
                      ),
                    ],
                  ),
                )

            ],
                ),
                const SizedBox(height: 16),
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
              ],
            ),
          )

          // Add some space between the button and the text
          ),
    );
  }
}
