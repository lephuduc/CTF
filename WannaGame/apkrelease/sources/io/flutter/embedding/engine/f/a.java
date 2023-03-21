package io.flutter.embedding.engine.f;

import android.content.res.AssetManager;
import b.a.c.a.b;
import b.a.c.a.q;
import io.flutter.embedding.engine.FlutterJNI;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public class a implements b.a.c.a.b {

    /* renamed from: a  reason: collision with root package name */
    private final FlutterJNI f163a;

    /* renamed from: b  reason: collision with root package name */
    private final AssetManager f164b;
    private final io.flutter.embedding.engine.f.b c;
    private final b.a.c.a.b d;
    private boolean e;
    private String f;
    private d g;
    private final b.a h;

    /* renamed from: io.flutter.embedding.engine.f.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0011a implements b.a {
        C0011a() {
        }

        @Override // b.a.c.a.b.a
        public void a(ByteBuffer byteBuffer, b.InterfaceC0006b interfaceC0006b) {
            a.this.f = q.f89b.a(byteBuffer);
            if (a.this.g != null) {
                a.this.g.a(a.this.f);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final String f166a;

        /* renamed from: b  reason: collision with root package name */
        public final String f167b = null;
        public final String c;

        public b(String str, String str2) {
            this.f166a = str;
            this.c = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.f166a.equals(bVar.f166a)) {
                return this.c.equals(bVar.c);
            }
            return false;
        }

        public int hashCode() {
            return (this.f166a.hashCode() * 31) + this.c.hashCode();
        }

        public String toString() {
            return "DartEntrypoint( bundle path: " + this.f166a + ", function: " + this.c + " )";
        }
    }

    /* loaded from: classes.dex */
    private static class c implements b.a.c.a.b {

        /* renamed from: a  reason: collision with root package name */
        private final io.flutter.embedding.engine.f.b f168a;

        private c(io.flutter.embedding.engine.f.b bVar) {
            this.f168a = bVar;
        }

        /* synthetic */ c(io.flutter.embedding.engine.f.b bVar, C0011a c0011a) {
            this(bVar);
        }

        @Override // b.a.c.a.b
        public void a(String str, ByteBuffer byteBuffer, b.InterfaceC0006b interfaceC0006b) {
            this.f168a.a(str, byteBuffer, interfaceC0006b);
        }

        @Override // b.a.c.a.b
        public void c(String str, b.a aVar) {
            this.f168a.c(str, aVar);
        }
    }

    /* loaded from: classes.dex */
    interface d {
        void a(String str);
    }

    public a(FlutterJNI flutterJNI, AssetManager assetManager) {
        this.e = false;
        C0011a c0011a = new C0011a();
        this.h = c0011a;
        this.f163a = flutterJNI;
        this.f164b = assetManager;
        io.flutter.embedding.engine.f.b bVar = new io.flutter.embedding.engine.f.b(flutterJNI);
        this.c = bVar;
        bVar.c("flutter/isolate", c0011a);
        this.d = new c(bVar, null);
        if (flutterJNI.isAttached()) {
            this.e = true;
        }
    }

    @Override // b.a.c.a.b
    @Deprecated
    public void a(String str, ByteBuffer byteBuffer, b.InterfaceC0006b interfaceC0006b) {
        this.d.a(str, byteBuffer, interfaceC0006b);
    }

    @Override // b.a.c.a.b
    @Deprecated
    public void c(String str, b.a aVar) {
        this.d.c(str, aVar);
    }

    public void f(b bVar) {
        if (this.e) {
            b.a.b.f("DartExecutor", "Attempted to run a DartExecutor that is already running.");
            return;
        }
        b.a.b.e("DartExecutor", "Executing Dart entrypoint: " + bVar);
        this.f163a.runBundleAndSnapshotFromLibrary(bVar.f166a, bVar.c, bVar.f167b, this.f164b);
        this.e = true;
    }

    public String g() {
        return this.f;
    }

    public boolean h() {
        return this.e;
    }

    public void i() {
        if (this.f163a.isAttached()) {
            this.f163a.notifyLowMemoryWarning();
        }
    }

    public void j() {
        b.a.b.e("DartExecutor", "Attached to JNI. Registering the platform message handler for this Dart execution context.");
        this.f163a.setPlatformMessageHandler(this.c);
    }

    public void k() {
        b.a.b.e("DartExecutor", "Detached from JNI. De-registering the platform message handler for this Dart execution context.");
        this.f163a.setPlatformMessageHandler(null);
    }
}
