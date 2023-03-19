package william.code.flutter_structure_app;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Build;
import androidx.annotation.RequiresApi;
import io.flutter.plugin.common.EventChannel;

@androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.KITKAT)
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\bJ\u001a\u0010\u000e\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0016J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u000f2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\u0015\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lwilliam/code/flutter_structure_app/SensorStreamHandler;", "Lio/flutter/plugin/common/EventChannel$StreamHandler;", "Landroid/hardware/SensorEventListener2;", "sensorManager", "Landroid/hardware/SensorManager;", "sensorType", "", "interval", "(Landroid/hardware/SensorManager;II)V", "eventSink", "Lio/flutter/plugin/common/EventChannel$EventSink;", "sensor", "Landroid/hardware/Sensor;", "kotlin.jvm.PlatformType", "onAccuracyChanged", "", "accuracy", "onCancel", "arguments", "", "onFlushCompleted", "onListen", "events", "onSensorChanged", "event", "Landroid/hardware/SensorEvent;", "app_debug"})
public final class SensorStreamHandler implements io.flutter.plugin.common.EventChannel.StreamHandler, android.hardware.SensorEventListener2 {
    private final android.hardware.SensorManager sensorManager = null;
    private final int interval = 0;
    private final android.hardware.Sensor sensor = null;
    private io.flutter.plugin.common.EventChannel.EventSink eventSink;
    
    public SensorStreamHandler(@org.jetbrains.annotations.NotNull()
    android.hardware.SensorManager sensorManager, int sensorType, int interval) {
        super();
    }
    
    @java.lang.Override()
    public void onListen(@org.jetbrains.annotations.Nullable()
    java.lang.Object arguments, @org.jetbrains.annotations.Nullable()
    io.flutter.plugin.common.EventChannel.EventSink events) {
    }
    
    @java.lang.Override()
    public void onCancel(@org.jetbrains.annotations.Nullable()
    java.lang.Object arguments) {
    }
    
    @java.lang.Override()
    public void onSensorChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.SensorEvent event) {
    }
    
    @java.lang.Override()
    public void onAccuracyChanged(@org.jetbrains.annotations.Nullable()
    android.hardware.Sensor sensor, int accuracy) {
    }
    
    @java.lang.Override()
    public void onFlushCompleted(@org.jetbrains.annotations.Nullable()
    android.hardware.Sensor sensor) {
    }
}