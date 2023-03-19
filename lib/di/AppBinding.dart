import 'package:flutter_structure_app/data_layer/repository/AstrosRepositoryImpl.dart';
import 'package:flutter_structure_app/domain_layer/repository/AstrosRepository.dart';
import 'package:flutter_structure_app/domain_layer/usecase/load_astros_usecase.dart';
import 'package:get/get.dart';

import '../data_layer/remote/api_service.dart';
import '../data_layer/remote/remote_data_source.dart';

/// ***************************************************************************
/// provider util, type, constant
/// => provide service, db instance
/// => provide repository
/// => provide use case
/// ***************************************************************************
class AppBinding extends Bindings {
  @override
  void dependencies() {
    ApiService api = Get.put(ApiService());
    var remote = Get.put(RemoteDataSource(api));

    IAstrosRepository astrosRepository =Get.put<IAstrosRepository>(AstrosRepositoryImpl(remote));
    Get.put(LoadAstrosUseCase(astrosRepository));
  }
}
