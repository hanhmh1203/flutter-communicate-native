
import '../error_handling/AppError.dart';

abstract class DataRequest {
  Map<String, String>? headers = {};
  Map<String, dynamic> parameters = {};
  AppError? errorData;

  bool isDataCorrect();

  /// call api do not automatic convert int to string in query
  Map<String, String> getParameterAsString() {
    Map<String, String> stringQueryParameters = parameters.map((key, value) => MapEntry(key, value.toString()));
    return stringQueryParameters;
  }

  @override
  String toString() {
    return 'DataRequest{parameters: $parameters}';
  }
}