enum ErrorType {
  unknown,
  dataResponse,
  dataRequest,
  notFound
}

abstract class AppError {
  abstract ErrorType errorType;
  String? errorCode;
  abstract String? errorMessage;

  @override
  String toString() {
    return 'ErrorData {errorType: $errorType, errorCode: $errorCode, errorMessage: $errorMessage}';
  }
}

class UnknownError extends AppError {
  @override
  ErrorType errorType = ErrorType.unknown;

  @override
  String? errorMessage;

  UnknownError(this.errorMessage);
}

class RequestError extends AppError {
  @override
  ErrorType errorType = ErrorType.dataRequest;

  @override
  String? errorMessage;

  RequestError(this.errorMessage);
}

class ResponseError extends AppError {
  @override
  ErrorType errorType = ErrorType.dataResponse;

  @override
  String? errorCode;

  @override
  String? errorMessage;

  ResponseError(this.errorCode, this.errorMessage);
}