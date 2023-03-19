package william.code.flutter_structure_app.service;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.MethodChannel;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0014"}, d2 = {"Lwilliam/code/flutter_structure_app/service/EnvironmentChannelServiceLocation;", "", "flutterEngine", "Lio/flutter/embedding/engine/FlutterEngine;", "(Lio/flutter/embedding/engine/FlutterEngine;)V", "sendLocationEC", "Lio/flutter/plugin/common/EventChannel;", "getSendLocationEC", "()Lio/flutter/plugin/common/EventChannel;", "setSendLocationEC", "(Lio/flutter/plugin/common/EventChannel;)V", "serviceTrackingMC", "Lio/flutter/plugin/common/MethodChannel;", "getServiceTrackingMC", "()Lio/flutter/plugin/common/MethodChannel;", "setServiceTrackingMC", "(Lio/flutter/plugin/common/MethodChannel;)V", "tearDown", "", "Companion", "app_debug"})
public final class EnvironmentChannelServiceLocation {
    @org.jetbrains.annotations.NotNull()
    public static final william.code.flutter_structure_app.service.EnvironmentChannelServiceLocation.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String methodStartService = "start_tracking_location";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String methodStopService = "stop_tracking_location";
    @org.jetbrains.annotations.NotNull()
    private io.flutter.plugin.common.MethodChannel serviceTrackingMC;
    @org.jetbrains.annotations.NotNull()
    private io.flutter.plugin.common.EventChannel sendLocationEC;
    
    public EnvironmentChannelServiceLocation(@org.jetbrains.annotations.NotNull()
    io.flutter.embedding.engine.FlutterEngine flutterEngine) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.flutter.plugin.common.MethodChannel getServiceTrackingMC() {
        return null;
    }
    
    public final void setServiceTrackingMC(@org.jetbrains.annotations.NotNull()
    io.flutter.plugin.common.MethodChannel p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.flutter.plugin.common.EventChannel getSendLocationEC() {
        return null;
    }
    
    public final void setSendLocationEC(@org.jetbrains.annotations.NotNull()
    io.flutter.plugin.common.EventChannel p0) {
    }
    
    public final void tearDown() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lwilliam/code/flutter_structure_app/service/EnvironmentChannelServiceLocation$Companion;", "", "()V", "methodStartService", "", "methodStopService", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}