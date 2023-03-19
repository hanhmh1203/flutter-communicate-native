import 'package:flutter_structure_app/ui/api_test/HomeController.dart';
import 'package:get/get.dart';
import 'package:get/get_instance/src/bindings_interface.dart';

class HomeBinding extends Bindings{
  @override
  void dependencies() {
    Get.put(HomeController(), permanent: false);
  }

}