
import 'package:flutter_structure_app/domain_layer/repository/AstrosRepository.dart';
import 'package:flutter_structure_app/domain_layer/response_model/people.dart';

import '../data/DataResult.dart';
import '../error_handling/AppException.dart';

class LoadAstrosUseCase {
  final IAstrosRepository _repository;


  LoadAstrosUseCase(this._repository);

  Future<DataResult<List<People>>> loadApi() async {
    try {
      var result = await _repository.requestApi();
      return result;
    } catch (ex) {
      if (ex is AppException) {
        return ResultException(ex);
      } else {
        return ResultException(UnknownException(ex.toString()));
      }
    }
  }
}
