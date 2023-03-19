import 'package:flutter_structure_app/data_layer/remote/response/astros_response.dart';
import 'package:get/get.dart';
import 'package:get/get_connect/http/src/response/response.dart';

import '../../domain_layer/error_handling/AppException.dart';
import 'api_service.dart';

class RemoteDataSource {
  final ApiService _apiService;

  RemoteDataSource(this._apiService);
  Future<AstrosResponse> getAstros() async {
    Response response = _returnResponse(await _apiService.getAstros());
    return AstrosResponse.fromResponse(response);
  }
  Response _returnResponse(Response response) {
    if (response.bodyString != null) {
      return response;
    }

    if (response.statusCode == null) {
      throw FetchDataException('No internet connection: ${response.statusText}');
      // throw Exception();
    }

    throw FetchDataException('${response.statusText}');
    // throw Exception();
  }
  /// handle error when parse data with error response
  dynamic errorHandler(Response response) {
    switch (response.statusCode) {
      case 200:
      case 201:
      case 202:
      case 400:
      case 403:
        var responseJson = response.bodyString;
        return responseJson;
    // case 403:
    // throw UnauthorisedException(response.body.toString());
    // case 400:
    //   throw BadRequestException(response.body.toString());
      case 500:
        throw Exception();
        // throw FetchDataException(
        //     'Error occured while Communication with Server with statusText : ${response.statusText}');
    // default:
    //   throw 'Error occurred retry';
    }
    if (response.statusCode == null) {
      throw Exception();
      // throw FetchDataException(
      //     'No internet connection: ${response.statusText}');
    }
  }
}
