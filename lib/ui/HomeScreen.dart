import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter_structure_app/ui/HomeBinding.dart';
import 'package:flutter_structure_app/ui/HomeController.dart';
import 'package:get/get.dart';

class HomeScreen extends GetView<HomeController> {
  static openHome() {
    Get.off(const HomeScreen(), binding: HomeBinding());
  }

  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
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
