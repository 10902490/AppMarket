-dontwarn org.slf4j.helpers.SubstituteLogger

-keep class com.app.market.data.install.backend.root.RootBridgeMain {
    public static void main(java.lang.String[]);
}

-keep class com.app.market.install.InstallResultReceiver { *; }
-keep class com.app.market.install.InstallForegroundService { *; }
-keep class com.app.market.install.InstallNotificationActionReceiver { *; }
