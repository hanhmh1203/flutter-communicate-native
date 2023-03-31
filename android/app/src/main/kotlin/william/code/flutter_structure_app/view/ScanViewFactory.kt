package william.code.flutter_structure_app.view

import android.content.Context
import android.view.View
import androidx.camera.view.PreviewView
import io.flutter.embedding.android.FlutterActivity
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory


class ScanViewFactory(
    private val cameraWidget: View,
    private val previewView: PreviewView,
    private val activity: FlutterActivity,
) : PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    override fun create(context: Context, viewId: Int, args: Any?): PlatformView {
        val creationParams = args as Map<String?, Any?>?
        return ScanView(
            activity, cameraView = cameraWidget,
            previewView = previewView, id = viewId, creationParams = creationParams
        )
    }
}