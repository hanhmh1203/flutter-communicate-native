enum ExceptionType {
  unknownException,
  databaseException,
  jsonException,
  fetchDataException,
}

abstract class AppException implements Exception {
  abstract ExceptionType errorType;

  final message;
  final prefix;
  StackTrace? stackTrace;

  AppException(this.message, this.prefix, [this.stackTrace]);

  String getErrorMessage() {
    return '$prefix $message';
  }
}
///Lot of exception we can not define, throw in this exception
class UnknownException extends AppException {
  UnknownException(String message, [stackTrace])
      : super(message, "Unknown Error: ", stackTrace);

  @override
  ExceptionType errorType = ExceptionType.unknownException;
}

/// throw exception when parse json exception
class JsonResponseException extends AppException {
  JsonResponseException(String message, [stackTrace])
      : super(message, "FormatException Json Error: ", stackTrace);

  @override
  ExceptionType errorType = ExceptionType.jsonException;
}
/// throw exception when query db get exception
class DbException extends AppException {
  DbException(String message, [stackTrace])
      : super(message, "DatabaseException: ", stackTrace);

  @override
  ExceptionType errorType = ExceptionType.databaseException;
}

/// throw exception when we got network error, server error.
class FetchDataException extends AppException {
  FetchDataException(String message)
      : super(message, "Error During Communication: ");

  @override
  ExceptionType errorType = ExceptionType.fetchDataException;
}