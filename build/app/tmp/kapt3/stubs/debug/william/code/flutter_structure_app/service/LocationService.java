package william.code.flutter_structure_app.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.embedding.android.FlutterActivity;
import william.code.flutter_structure_app.ConstantEnvironmentSensor;
import william.code.flutter_structure_app.R;
import william.code.flutter_structure_app.SensorStreamHandler;
import william.code.flutter_structure_app.scan.ConstantEnvironmentActivity;
import william.code.flutter_structure_app.scan.ScanActivity;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001$B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u001aH\u0016J\b\u0010 \u001a\u00020\u001aH\u0016J\"\u0010!\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\bH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082.\u00a2\u0006\u0004\n\u0002\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lwilliam/code/flutter_structure_app/service/LocationService;", "Landroid/app/Service;", "()V", "LOCATION_DISTANCE", "", "LOCATION_INTERVAL", "", "NOTIFICATION_ID", "", "TAG", "", "getTAG", "()Ljava/lang/String;", "broadcastManager", "Landroidx/localbroadcastmanager/content/LocalBroadcastManager;", "getBroadcastManager", "()Landroidx/localbroadcastmanager/content/LocalBroadcastManager;", "setBroadcastManager", "(Landroidx/localbroadcastmanager/content/LocalBroadcastManager;)V", "mLocationListeners", "", "Lwilliam/code/flutter_structure_app/service/LocationService$LocationListener;", "[Lwilliam/code/flutter_structure_app/service/LocationService$LocationListener;", "mLocationManager", "Landroid/location/LocationManager;", "initializeLocationManager", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "LocationListener", "app_debug"})
public final class LocationService extends android.app.Service {
    private android.location.LocationManager mLocationManager;
    private final long LOCATION_INTERVAL = 200L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "LocationService";
    private final float LOCATION_DISTANCE = 0.1F;
    private final int NOTIFICATION_ID = 123;
    public androidx.localbroadcastmanager.content.LocalBroadcastManager broadcastManager;
    private william.code.flutter_structure_app.service.LocationService.LocationListener[] mLocationListeners;
    
    public LocationService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTAG() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.localbroadcastmanager.content.LocalBroadcastManager getBroadcastManager() {
        return null;
    }
    
    public final void setBroadcastManager(@org.jetbrains.annotations.NotNull()
    androidx.localbroadcastmanager.content.LocalBroadcastManager p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void initializeLocationManager() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lwilliam/code/flutter_structure_app/service/LocationService$LocationListener;", "Landroid/location/LocationListener;", "provider", "", "broadcastManager", "Landroidx/localbroadcastmanager/content/LocalBroadcastManager;", "(Ljava/lang/String;Landroidx/localbroadcastmanager/content/LocalBroadcastManager;)V", "onLocationChanged", "", "location", "Landroid/location/Location;", "onProviderDisabled", "onProviderEnabled", "app_debug"})
    public static final class LocationListener implements android.location.LocationListener {
        private final java.lang.String provider = null;
        private final androidx.localbroadcastmanager.content.LocalBroadcastManager broadcastManager = null;
        
        public LocationListener(@org.jetbrains.annotations.NotNull()
        java.lang.String provider, @org.jetbrains.annotations.NotNull()
        androidx.localbroadcastmanager.content.LocalBroadcastManager broadcastManager) {
            super();
        }
        
        @java.lang.Override()
        public void onLocationChanged(@org.jetbrains.annotations.NotNull()
        android.location.Location location) {
        }
        
        @java.lang.Override()
        public void onProviderEnabled(@org.jetbrains.annotations.NotNull()
        java.lang.String provider) {
        }
        
        @java.lang.Override()
        public void onProviderDisabled(@org.jetbrains.annotations.NotNull()
        java.lang.String provider) {
        }
    }
}