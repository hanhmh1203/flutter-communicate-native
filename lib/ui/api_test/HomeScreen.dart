import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_structure_app/ui/api_test/HomeBinding.dart';
import 'package:flutter_structure_app/ui/api_test/HomeController.dart';
import 'package:get/get.dart';

class HomeScreen extends GetView<HomeController> {
  static openHome() {
    Get.to(const HomeScreen(), binding: HomeBinding());
  }

  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          icon: const Icon(Icons.arrow_back),
          onPressed: () {
            Get.back();
          },
        ),
        title: Text('My Screen'),
      ),
      body: Center(
        child: Container(
          child: ElevatedButton(
            onPressed: () async {
              controller.loadDataAstros();
              Get.snackbar("snackbar", "show");
            },
            child: Text('Button'),
          ),
        ),
      ),
    );
  }
}
