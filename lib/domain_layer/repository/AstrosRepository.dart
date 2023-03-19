
import 'package:flutter_structure_app/domain_layer/response_model/people.dart';

import '../data/DataResult.dart';
import '../data/LoadWeatherRequest.dart';

abstract class IAstrosRepository {
  Future<DataResult<List<People>>> requestApi();
}
