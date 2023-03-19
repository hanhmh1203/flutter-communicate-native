import 'package:get/get.dart';
import 'package:get/get_connect/http/src/request/request.dart';
import 'package:logger/logger.dart';

import '../util/constant.dart';

class ApiService extends GetConnect {
  final String apiKey = weatherApiKey;
  static const String appBaseUrl = "http://api.open-notify.org";
  Logger logger = Logger();

  @override
  void onInit() {
    httpClient.baseUrl = appBaseUrl;
    httpClient.defaultContentType = "application/json";
    httpClient.timeout = const Duration(seconds: 60);

    httpClient.addResponseModifier((Request request, Response response) async {
      print(" addResponseModifier ${httpClient.baseUrl}");
      logger.d(request.url);
      logger.d(response.bodyString);
      logger.d(response.status.code);
      return response;
    });

    httpClient.addRequestModifier((Request request) async {
      print(" addRequestModifier ${httpClient.baseUrl}");
      print(" addRequestModifier ${request.url}");
      print(" addRequestModifier ${request.toString()}");
      return request;
    });
    super.onInit();
  }

  Future<Response> getAstros() async {
    String url = "/astros.json";
    final response = get(url);
    return response;
  }
}
