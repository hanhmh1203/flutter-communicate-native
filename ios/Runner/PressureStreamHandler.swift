//
//  PressureStreamHandler.swift
//  Runner
//
//  Created by William Mai on 26/02/2023.
//

import Foundation
import CoreMotion
import Flutter
class PressureStreamHandler : NSObject, FlutterStreamHandler{
    let altimeter = CMAltimeter()
    private let queue = OperationQueue()
    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        if(CMAltimeter.isRelativeAltitudeAvailable()){
            altimeter.startRelativeAltitudeUpdates(to:  queue){(data, error) in if (data != nil){
                let pressurePascal = data?.pressure
                events(pressurePascal!.doubleValue * 10)
            }}
        }
        return nil
    }
    
    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        altimeter.stopRelativeAltitudeUpdates()
        return nil
    }
    

    
}
