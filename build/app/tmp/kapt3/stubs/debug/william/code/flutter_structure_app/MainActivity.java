package william.code.flutter_structure_app;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import william.code.flutter_structure_app.scan.ConstantEnvironmentActivity;
import william.code.flutter_structure_app.scan.ScanActivity;
import william.code.flutter_structure_app.service.EnvironmentChannelServiceLocation;
import william.code.flutter_structure_app.service.LocationService;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u00014B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\"\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00052\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0012\u0010\u001f\u001a\u00020\u00172\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\u0017H\u0014J\u001c\u0010#\u001a\u00020\u00172\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010$\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010%\u001a\u00020\u00172\u0006\u0010&\u001a\u00020\'H\u0002J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0002J \u0010+\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*2\u0006\u0010,\u001a\u00020-2\u0006\u0010&\u001a\u00020.H\u0002J\u0010\u0010/\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u00100\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u00101\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u00102\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0002J\b\u00103\u001a\u00020\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00060\u0011R\u00020\u0000X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lwilliam/code/flutter_structure_app/MainActivity;", "Lio/flutter/embedding/android/FlutterActivity;", "Lio/flutter/plugin/common/EventChannel$StreamHandler;", "()V", "REQUEST_CODE_SCAN_ACTIVITY", "", "activityMethodChannel", "Lio/flutter/plugin/common/MethodChannel;", "ambientTemperatureChannel", "Lio/flutter/plugin/common/EventChannel;", "environmentChannelServiceLocation", "Lwilliam/code/flutter_structure_app/service/EnvironmentChannelServiceLocation;", "eventSink", "Lio/flutter/plugin/common/EventChannel$EventSink;", "humidityChannel", "lightChannel", "locationBroadcastReceiver", "Lwilliam/code/flutter_structure_app/MainActivity$FlutterLocationReceiver;", "pressureChannel", "sensorManager", "Landroid/hardware/SensorManager;", "sensorMethodChannel", "configureFlutterEngine", "", "flutterEngine", "Lio/flutter/embedding/engine/FlutterEngine;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCancel", "arguments", "", "onDestroy", "onListen", "events", "returnResultToFlutter", "result", "", "setupChannel", "messenger", "Lio/flutter/plugin/common/BinaryMessenger;", "setupForSensor", "methodCall", "Lio/flutter/plugin/common/MethodCall;", "Lio/flutter/plugin/common/MethodChannel$Result;", "setupStreamForAmbientTemperature", "setupStreamForHumidity", "setupStreamForLight", "setupStreamForPressure", "tearDownChannel", "FlutterLocationReceiver", "app_debug"})
public final class MainActivity extends io.flutter.embedding.android.FlutterActivity implements io.flutter.plugin.common.EventChannel.StreamHandler {
    private final int REQUEST_CODE_SCAN_ACTIVITY = 1;
    private io.flutter.plugin.common.MethodChannel sensorMethodChannel;
    private android.hardware.SensorManager sensorManager;
    private io.flutter.plugin.common.EventChannel pressureChannel;
    private io.flutter.plugin.common.EventChannel ambientTemperatureChannel;
    private io.flutter.plugin.common.EventChannel humidityChannel;
    private io.flutter.plugin.common.EventChannel lightChannel;
    private io.flutter.plugin.common.MethodChannel activityMethodChannel;
    private william.code.flutter_structure_app.MainActivity.FlutterLocationReceiver locationBroadcastReceiver;
    private io.flutter.plugin.common.EventChannel.EventSink eventSink;
    private william.code.flutter_structure_app.service.EnvironmentChannelServiceLocation environmentChannelServiceLocation;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    public void configureFlutterEngine(@org.jetbrains.annotations.NotNull()
    io.flutter.embedding.engine.FlutterEngine flutterEngine) {
    }
    
    private final void setupChannel(io.flutter.plugin.common.BinaryMessenger messenger) {
    }
    
    @java.lang.Override()
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable()
    android.content.Intent data) {
    }
    
    private final void returnResultToFlutter(java.lang.String result) {
    }
    
    private final void setupForSensor(io.flutter.plugin.common.BinaryMessenger messenger, io.flutter.plugin.common.MethodCall methodCall, io.flutter.plugin.common.MethodChannel.Result result) {
    }
    
    private final void setupStreamForPressure(io.flutter.plugin.common.BinaryMessenger messenger) {
    }
    
    private final void setupStreamForAmbientTemperature(io.flutter.plugin.common.BinaryMessenger messenger) {
    }
    
    private final void setupStreamForHumidity(io.flutter.plugin.common.BinaryMessenger messenger) {
    }
    
    private final void setupStreamForLight(io.flutter.plugin.common.BinaryMessenger messenger) {
    }
    
    private final void tearDownChannel() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
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
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016\u00a8\u0006\t"}, d2 = {"Lwilliam/code/flutter_structure_app/MainActivity$FlutterLocationReceiver;", "Landroid/content/BroadcastReceiver;", "(Lwilliam/code/flutter_structure_app/MainActivity;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_debug"})
    public final class FlutterLocationReceiver extends android.content.BroadcastReceiver {
        
        public FlutterLocationReceiver() {
            super();
        }
        
        @java.lang.Override()
        public void onReceive(@org.jetbrains.annotations.Nullable()
        android.content.Context context, @org.jetbrains.annotations.Nullable()
        android.content.Intent intent) {
        }
    }
}