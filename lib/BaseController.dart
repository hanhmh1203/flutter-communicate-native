import 'package:get/get.dart';
import 'package:logger/logger.dart';

import 'domain_layer/data/DataResult.dart';

class BaseController extends GetxController{
  Logger logger = Logger();
  DataResult handleIfError(DataResult dataResultW) {
    if (dataResultW is ResultError) {
      logger.e('ResultError ${dataResultW.errorData?.errorMessage ?? 'str_error_title'.tr}');
    }
    if (dataResultW is ResultException) {
      logger.e('exception hanhmh1203 ${dataResultW.appException?.getErrorMessage() ?? 'str_error_title'.tr}');
      logger.e('StackTrace ${dataResultW.appException?.stackTrace.toString() ?? 'str_error_title'.tr}');
    }
    return dataResultW;
  }

}