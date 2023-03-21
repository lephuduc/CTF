package io.flutter.embedding.engine.renderer;

import android.annotation.TargetApi;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.os.Handler;
import android.view.Surface;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.view.d;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

@TargetApi(16)
/* loaded from: classes.dex */
public class a implements d {

    /* renamed from: a  reason: collision with root package name */
    private final FlutterJNI f269a;
    private Surface c;
    private final io.flutter.embedding.engine.renderer.b e;

    /* renamed from: b  reason: collision with root package name */
    private final AtomicLong f270b = new AtomicLong(0);
    private boolean d = false;

    /* renamed from: io.flutter.embedding.engine.renderer.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0019a implements io.flutter.embedding.engine.renderer.b {
        C0019a() {
        }

        @Override // io.flutter.embedding.engine.renderer.b
        public void c() {
            a.this.d = true;
        }

        @Override // io.flutter.embedding.engine.renderer.b
        public void e() {
            a.this.d = false;
        }
    }

    /* loaded from: classes.dex */
    final class b implements d.a {

        /* renamed from: a  reason: collision with root package name */
        private final long f272a;

        /* renamed from: b  reason: collision with root package name */
        private final SurfaceTextureWrapper f273b;
        private boolean c;
        private SurfaceTexture.OnFrameAvailableListener d = new C0020a();

        /* renamed from: io.flutter.embedding.engine.renderer.a$b$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class C0020a implements SurfaceTexture.OnFrameAvailableListener {
            C0020a() {
            }

            @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                if (b.this.c || !a.this.f269a.isAttached()) {
                    return;
                }
                b bVar = b.this;
                a.this.j(bVar.f272a);
            }
        }

        b(long j, SurfaceTexture surfaceTexture) {
            this.f272a = j;
            this.f273b = new SurfaceTextureWrapper(surfaceTexture);
            if (Build.VERSION.SDK_INT >= 21) {
                c().setOnFrameAvailableListener(this.d, new Handler());
            } else {
                c().setOnFrameAvailableListener(this.d);
            }
        }

        @Override // io.flutter.view.d.a
        public void a() {
            if (this.c) {
                return;
            }
            b.a.b.e("FlutterRenderer", "Releasing a SurfaceTexture (" + this.f272a + ").");
            this.f273b.release();
            a.this.s(this.f272a);
            this.c = true;
        }

        @Override // io.flutter.view.d.a
        public long b() {
            return this.f272a;
        }

        @Override // io.flutter.view.d.a
        public SurfaceTexture c() {
            return this.f273b.surfaceTexture();
        }

        public SurfaceTextureWrapper f() {
            return this.f273b;
        }
    }

    /* loaded from: classes.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public float f275a = 1.0f;

        /* renamed from: b  reason: collision with root package name */
        public int f276b = 0;
        public int c = 0;
        public int d = 0;
        public int e = 0;
        public int f = 0;
        public int g = 0;
        public int h = 0;
        public int i = 0;
        public int j = 0;
        public int k = 0;
        public int l = 0;
        public int m = 0;
        public int n = 0;
        public int o = 0;
        public int p = -1;

        boolean a() {
            return this.f276b > 0 && this.c > 0 && this.f275a > 0.0f;
        }
    }

    public a(FlutterJNI flutterJNI) {
        C0019a c0019a = new C0019a();
        this.e = c0019a;
        this.f269a = flutterJNI;
        flutterJNI.addIsDisplayingFlutterUiListener(c0019a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(long j) {
        this.f269a.markTextureFrameAvailable(j);
    }

    private void k(long j, SurfaceTextureWrapper surfaceTextureWrapper) {
        this.f269a.registerTexture(j, surfaceTextureWrapper);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(long j) {
        this.f269a.unregisterTexture(j);
    }

    @Override // io.flutter.view.d
    public d.a a() {
        b.a.b.e("FlutterRenderer", "Creating a SurfaceTexture.");
        SurfaceTexture surfaceTexture = new SurfaceTexture(0);
        surfaceTexture.detachFromGLContext();
        b bVar = new b(this.f270b.getAndIncrement(), surfaceTexture);
        b.a.b.e("FlutterRenderer", "New SurfaceTexture ID: " + bVar.b());
        k(bVar.b(), bVar.f());
        return bVar;
    }

    public void f(io.flutter.embedding.engine.renderer.b bVar) {
        this.f269a.addIsDisplayingFlutterUiListener(bVar);
        if (this.d) {
            bVar.c();
        }
    }

    public void g(ByteBuffer byteBuffer, int i) {
        this.f269a.dispatchPointerDataPacket(byteBuffer, i);
    }

    public boolean h() {
        return this.d;
    }

    public boolean i() {
        return this.f269a.getIsSoftwareRenderingEnabled();
    }

    public void l(io.flutter.embedding.engine.renderer.b bVar) {
        this.f269a.removeIsDisplayingFlutterUiListener(bVar);
    }

    public void m(boolean z) {
        this.f269a.setSemanticsEnabled(z);
    }

    public void n(c cVar) {
        if (cVar.a()) {
            b.a.b.e("FlutterRenderer", "Setting viewport metrics\nSize: " + cVar.f276b + " x " + cVar.c + "\nPadding - L: " + cVar.g + ", T: " + cVar.d + ", R: " + cVar.e + ", B: " + cVar.f + "\nInsets - L: " + cVar.k + ", T: " + cVar.h + ", R: " + cVar.i + ", B: " + cVar.j + "\nSystem Gesture Insets - L: " + cVar.o + ", T: " + cVar.l + ", R: " + cVar.m + ", B: " + cVar.j);
            this.f269a.setViewportMetrics(cVar.f275a, cVar.f276b, cVar.c, cVar.d, cVar.e, cVar.f, cVar.g, cVar.h, cVar.i, cVar.j, cVar.k, cVar.l, cVar.m, cVar.n, cVar.o, cVar.p);
        }
    }

    public void o(Surface surface) {
        if (this.c != null) {
            p();
        }
        this.c = surface;
        this.f269a.onSurfaceCreated(surface);
    }

    public void p() {
        this.f269a.onSurfaceDestroyed();
        this.c = null;
        if (this.d) {
            this.e.e();
        }
        this.d = false;
    }

    public void q(int i, int i2) {
        this.f269a.onSurfaceChanged(i, i2);
    }

    public void r(Surface surface) {
        this.c = surface;
        this.f269a.onSurfaceWindowChanged(surface);
    }
}
