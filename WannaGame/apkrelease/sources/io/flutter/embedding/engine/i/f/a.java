package io.flutter.embedding.engine.i.f;

import io.flutter.embedding.engine.b;

/* loaded from: classes.dex */
public class a {
    public static void a(b bVar) {
        try {
            Class.forName("io.flutter.plugins.GeneratedPluginRegistrant").getDeclaredMethod("registerWith", b.class).invoke(null, bVar);
        } catch (Exception e) {
            b.a.b.b("GeneratedPluginsRegister", "Tried to automatically register plugins with FlutterEngine (" + bVar + ") but could not find or invoke the GeneratedPluginRegistrant.");
            b.a.b.c("GeneratedPluginsRegister", "Received exception while registering", e);
        }
    }
}
