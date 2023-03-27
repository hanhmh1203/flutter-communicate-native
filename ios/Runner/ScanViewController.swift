//
//  ScanViewController.swift
//  Runner
//
//  Created by William Mai on 10/03/2023.
//

import UIKit
import AVFoundation
import MLKitBarcodeScanning

protocol ScanViewControllerDelegate: class {
    func didScanBarcode(_ barcodeValue: String)
}

class ScanViewController: UIViewController, AVCaptureMetadataOutputObjectsDelegate {
    weak var delegate: ScanViewControllerDelegate?
    func didScanBarcode(_ barcodeValue: String) {
            print("ScanViewController Scanned barcode: \(barcodeValue)")
          delegate?.didScanBarcode(barcodeValue)
          dismiss(animated: true, completion: nil)
      }
  
    
    func metadataOutput(_ output: AVCaptureMetadataOutput, didOutput metadataObjects: [AVMetadataObject], from connection: AVCaptureConnection) {
           // Check if we have any metadata objects
           guard let metadataObject = metadataObjects.first else {
               return
           }
           // Check if the metadata object is a barcode object
           guard let barcodeObject = metadataObject as? AVMetadataMachineReadableCodeObject else {
               return
           }
           // Get the barcode value
           let barcodeValue = barcodeObject.stringValue ?? ""
           print("Scanned barcode: \(barcodeValue)")
        didScanBarcode(barcodeValue)
       }
    var captureSession: AVCaptureSession!
    var previewLayer: AVCaptureVideoPreviewLayer!

    // Declare a variable to hold the barcode scanner
    var barcodeScanner: BarcodeScanner?


    
    override func viewDidLoad() {
        super.viewDidLoad()
        print("viewDidLoad")
        // Check camera authorization status
           let status = AVCaptureDevice.authorizationStatus(for: .video)
           switch status {
           case .authorized:
               // The user has previously granted permission to use the camera.
               // Set up the capture session and preview layer.
               setUpCaptureSession()
           case .notDetermined:
               // The user has not yet been asked for camera access.
               // Request camera access.
               AVCaptureDevice.requestAccess(for: .video) { granted in
                   if granted {
                       // The user has granted permission to use the camera.
                       // Set up the capture session and preview layer.
                       self.setUpCaptureSession()
                   }
               }
           case .denied, .restricted:
               // The user has previously denied or restricted camera access.
               // Display an alert asking the user to grant camera access in Settings.
               let alert = UIAlertController(title: "Camera Access Denied", message: "Please grant permission to use the camera in Settings.", preferredStyle: .alert)
               alert.addAction(UIAlertAction(title: "OK", style: .default, handler: nil))
               present(alert, animated: true, completion: nil)
           @unknown default:
               fatalError("Unknown AVCaptureDevice authorization status.")
           }
        
      
    }
    func setUpCaptureSession() {
        // Set up the capture session and preview layer.
        // ...
        captureSession = AVCaptureSession()

        guard let videoCaptureDevice = AVCaptureDevice.default(for: .video) else {
            return
        }

        let videoInput: AVCaptureDeviceInput

        do {
            videoInput = try AVCaptureDeviceInput(device: videoCaptureDevice)
        } catch {
            return
        }

        if captureSession.canAddInput(videoInput) {
            captureSession.addInput(videoInput)
        } else {
            return
        }

        let metadataOutput = AVCaptureMetadataOutput()

        if captureSession.canAddOutput(metadataOutput) {
            captureSession.addOutput(metadataOutput)

            metadataOutput.setMetadataObjectsDelegate(self, queue: DispatchQueue.main)
            metadataOutput.metadataObjectTypes = [.qr, .ean13, .ean8, .code39, .code93, .code128, .pdf417, .aztec, .interleaved2of5, .itf14, .dataMatrix]
        } else {
            return
        }

        previewLayer = AVCaptureVideoPreviewLayer(session: captureSession)
        previewLayer.frame = view.layer.bounds
        previewLayer.videoGravity = .resizeAspectFill
        view.layer.addSublayer(previewLayer)

        captureSession.startRunning()
    }

    //    override func viewDidLoad() {
    //        super.viewDidLoad()
    //
    ////        // Initialize the barcode scanner
    //         barcodeScanner = BarcodeScanner.barcodeScanner()
    ////
    ////         // Configure the scanner to scan QR codes and barcodes
    //         let barcodeOptions = BarcodeScannerOptions(formats: .all)
    ////         barcodeScanner?.options = options
    ////        barcodeScanner.
    //        // Do any additional setup after loading the view.
    //    }
}
