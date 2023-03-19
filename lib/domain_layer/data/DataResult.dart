import '../error_handling/AppError.dart';
import '../error_handling/AppException.dart';

enum ResultStatus {
  error,
  success,
  exception
}

abstract class DataResult<T> {
  abstract ResultStatus status;
  // AppError? errorData;
  T? data;
}

class ResultSuccess<T> extends DataResult<T> {
  @override
  ResultStatus status = ResultStatus.success;

  @override
  T? data;

  ResultSuccess(this.data);
}

class ResultError<T> extends DataResult<T> {
  @override
  ResultStatus status = ResultStatus.error;

  AppError? errorData;

  ResultError(this.errorData);
}

class ResultException<T> extends DataResult<T> {
  @override
  ResultStatus status = ResultStatus.exception;

  AppException? appException;

  ResultException(this.appException);
}