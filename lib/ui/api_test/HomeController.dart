import 'package:flutter_structure_app/BaseController.dart';
import 'package:flutter_structure_app/domain_layer/usecase/load_astros_usecase.dart';
import 'package:get/get.dart';

import '../../domain_layer/data/DataResult.dart';

class HomeController extends BaseController {
  final String TAG = "HomeController";
  LoadAstrosUseCase astrosUseCase = Get.find();
  loadDataAstros() async {
    // EasyLoading.show();
    var result = handleIfError(await astrosUseCase.loadApi());
    if(result is ResultSuccess){
      logger.i("$TAG success ${result.data}");
    }else{
      logger.i("$TAG fail");
    }
  }
}
