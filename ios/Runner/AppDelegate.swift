import UIKit
import Flutter
import CoreMotion


@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate, ScanViewControllerDelegate {
    func didScanBarcode(_ barcodeValue: String) {
          print("Scanned barcode: \(barcodeValue)")
        // map type
        var resultData = [String: Any]()
        resultData["argumentName_my_result"] = barcodeValue
        methodChannelScan?.invokeMethod("result_method", arguments: resultData)
      }
    var methodChannelScan : FlutterMethodChannel?
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
  ) -> Bool {
      let methodChannelNameScan = "environment_activity_method_channel"
      let methodChannelNameSensor = "environment_sensors/method"
      let methodChannelNameBasic = "basic_page"
      
      let pressureChannelName = "environment_sensors/pressure"

      let controller: FlutterViewController  = window?.rootViewController as! FlutterViewController
      
      let basic = FlutterMethodChannel(name: methodChannelNameBasic, binaryMessenger: controller.binaryMessenger)
      
      
      basic.setMethodCallHandler({
          (call: FlutterMethodCall, result: @escaping FlutterResult) -> Void in
          switch call.method {
          case "getDeviceInfo":
              let argument = call.arguments
              let version = UIDevice.current.systemVersion
              result("\(version)\n argument: \(argument)")
    
          default:
              result(FlutterMethodNotImplemented)
          }
      })
      
      

      let sensor = FlutterMethodChannel(name: methodChannelNameSensor, binaryMessenger: controller.binaryMessenger)
      
      let pressureStream = PressureStreamHandler()
      
      sensor.setMethodCallHandler({
          (call: FlutterMethodCall, result: @escaping FlutterResult) -> Void in
          switch call.method {
          case "isSensorAvailable":
              result(CMAltimeter.isRelativeAltitudeAvailable())
    
          default:
              result(FlutterMethodNotImplemented)
          }
      })
      let pressureEventChannel = FlutterEventChannel(name: pressureChannelName, binaryMessenger: controller.binaryMessenger)
      pressureEventChannel.setStreamHandler(pressureStream)
      
      methodChannelScan = FlutterMethodChannel(name: methodChannelNameScan, binaryMessenger: controller.binaryMessenger)
      
      methodChannelScan?.setMethodCallHandler({
          (call: FlutterMethodCall, result: @escaping FlutterResult) -> Void in
          switch call.method {
          case "open_new_activity":
                 // Create a new view controller and present it
                 let storyboard = UIStoryboard(name: "Main", bundle: nil)
                 let viewController = storyboard.instantiateViewController(withIdentifier: "ScanViewController") as! ScanViewController
                 viewController.delegate = self
                 controller.present(viewController, animated: true, completion: nil)
                 result(true) // Return nil to indicate success
          default:
              result(FlutterMethodNotImplemented)
          }
      })
      
      GeneratedPluginRegistrant.register(with: self)
    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }
    
}
