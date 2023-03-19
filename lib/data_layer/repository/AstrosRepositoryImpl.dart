import 'package:flutter_structure_app/data_layer/remote/response/astros_response.dart';
import 'package:flutter_structure_app/domain_layer/repository/AstrosRepository.dart';
import 'package:flutter_structure_app/domain_layer/response_model/people.dart';

import '../../domain_layer/data/DataResult.dart';
import '../../domain_layer/error_handling/AppError.dart';
import '../remote/remote_data_source.dart';

class AstrosRepositoryImpl extends IAstrosRepository {
  final RemoteDataSource _remote;

  AstrosRepositoryImpl(this._remote);


  @override
  Future<DataResult<List<People>>> requestApi()  async {
    try{
      var result = await _remote.getAstros();
      print("hanhmh1203 requestApi result $result");
      return ResultSuccess(result.data);
    }catch(e){
      print("requestApi $e");
    }
    return ResultError(UnknownError("call api error"));
  }
}
