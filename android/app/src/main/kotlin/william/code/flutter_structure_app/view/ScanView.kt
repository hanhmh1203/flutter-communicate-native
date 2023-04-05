package william.code.flutter_structure_app.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.platform.PlatformView
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class ScanView(
    private val context: Context,
    private val previewView: PreviewView,
    private val cameraView: View,
    id: Int,
    creationParams: Map<String?, Any?>?
) : PlatformView, MethodChannel.MethodCallHandler {
    val tag  = "ScanView"
    private var methodChannel: MethodChannel? = null
    private var flutterEngine: FlutterEngine? = null
    override fun onMethodCall(methodCall: MethodCall, result: MethodChannel.Result) {
        if (methodCall.method.equals("data_from_flutter_to_native")) {
            val data: String = methodCall.arguments as String
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
            startCamera()
            // Use the data as needed
            result.success(true)
        } else {
            result.notImplemented()
        }
    }

    private lateinit var camera: Camera
    private lateinit var preview: Preview
    private var imageAnalyzer: ImageAnalysis? = null
    private var cameraExecutor: ExecutorService
    private var scanner: BarcodeScanner

    init {
        flutterEngine = FlutterEngineCache.getInstance()["my_engine_id"]
        flutterEngine?.let {
            methodChannel = MethodChannel(it.dartExecutor.binaryMessenger, "PlatformView_ScanView")
            methodChannel!!.setMethodCallHandler(this)
        }

        val options = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(
                Barcode.FORMAT_ALL_FORMATS
            )
            .build()
        scanner = BarcodeScanning.getClient(options)

        cameraExecutor = Executors.newSingleThreadExecutor()

        // Set up the image analyzer to scan for barcodes
        imageAnalyzer = ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also {
                it.setAnalyzer(cameraExecutor, BarcodeAnalyzer())
            }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            preview = Preview.Builder()
                .build()

            try {
                cameraProvider.unbindAll()
                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()
                val preview: Preview = Preview.Builder().build()
                preview.setSurfaceProvider(previewView.surfaceProvider)

                camera = cameraProvider.bindToLifecycle(
                    context as LifecycleOwner, cameraSelector, preview, imageAnalyzer
                )

            } catch (exc: Exception) {
                Log.e(tag, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(context))
    }

    private inner class BarcodeAnalyzer : ImageAnalysis.Analyzer {
        override fun analyze(imageProxy: ImageProxy) {
            @Suppress("UnsafeExperimentalUsageError")
            @androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
            val mediaImage = imageProxy.image
            if (mediaImage != null) {
                val image =
                    InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
                scanner.process(image)
                    .addOnSuccessListener { barcodes ->
                        for (barcode in barcodes) {
                            val rawValue = barcode.rawValue
                            val valueType = barcode.valueType
                            Log.d(tag, "Barcode detected: $rawValue")

                            // send value to Flutter code
                            methodChannel?.invokeMethod(
                                "data_from_native_to_flutter",
                                rawValue.toString()
                            )
                            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
                            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                            cameraProvider.unbindAll()
                        }
                    }
                    .addOnFailureListener {
                        Log.e(tag, "Barcode detection failed: ${it.message}")
                    }
                    .addOnCompleteListener {
                        imageProxy.close()
                    }
            }
        }
    }

    override fun onFlutterViewDetached() {
        super.onFlutterViewDetached()
        if (::camera.isInitialized) {
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            cameraProvider.unbindAll()
        }
    }


    override fun getView(): View {
        return cameraView
    }

    override fun dispose() {

    }

}
