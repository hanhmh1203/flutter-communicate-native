
import 'DataRequest.dart';

class LoadWeatherRequest extends DataRequest {
  @override
  bool isDataCorrect() {
    // TODO: implement isDataCorrect
    return true;
  }

  final double lat;
  final double lon;

  LoadWeatherRequest(this.lat, this.lon);
}
