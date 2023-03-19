import 'dart:convert';

import 'package:flutter_structure_app/domain_layer/response_model/people.dart';
import 'package:get/get_connect/http/src/response/response.dart';

import '../../../domain_layer/error_handling/AppException.dart';

class AstrosResponse {
  String? message;
  int? number = 0;
  List<People> data;

  AstrosResponse(this.message, this.number, this.data);

  factory AstrosResponse.fromResponse(Response response) {
    try {
      var jsonObj = json.decode(response.bodyString!);
      var message = jsonObj['message'];
      var number = jsonObj['number'];
      var data = <People>[];
      if (message == "success" && number > 0) {
        if (jsonObj['people'] != null) {
          jsonObj['people'].forEach((v) {
            data.add(People.fromJson(v));
          });
        }
      }
      return AstrosResponse(message, number, data);
    } catch (ex, stackTrace) {
      throw JsonResponseException(ex.toString(), stackTrace);
    }
  }
}
