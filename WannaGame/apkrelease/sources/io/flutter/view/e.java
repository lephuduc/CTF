package io.flutter.view;

import android.view.Choreographer;
import android.view.WindowManager;
import io.flutter.embedding.engine.FlutterJNI;

/* loaded from: classes.dex */
public class e {
    private static e c;

    /* renamed from: a  reason: collision with root package name */
    private final WindowManager f361a;

    /* renamed from: b  reason: collision with root package name */
    private final FlutterJNI.b f362b = new a();

    /* loaded from: classes.dex */
    class a implements FlutterJNI.b {

        /* renamed from: io.flutter.view.e$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class Choreographer$FrameCallbackC0025a implements Choreographer.FrameCallback {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ long f364a;

            Choreographer$FrameCallbackC0025a(long j) {
                this.f364a = j;
            }

            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                double refreshRate = e.this.f361a.getDefaultDisplay().getRefreshRate();
                Double.isNaN(refreshRate);
                FlutterJNI.nativeOnVsync(j, j + ((long) (1.0E9d / refreshRate)), this.f364a);
            }
        }

        a() {
        }

        @Override // io.flutter.embedding.engine.FlutterJNI.b
        public void a(long j) {
            Choreographer.getInstance().postFrameCallback(new Choreographer$FrameCallbackC0025a(j));
        }
    }

    private e(WindowManager windowManager) {
        this.f361a = windowManager;
    }

    public static e b(WindowManager windowManager) {
        if (c == null) {
            c = new e(windowManager);
        }
        return c;
    }

    public void c() {
        FlutterJNI.setAsyncWaitForVsyncDelegate(this.f362b);
        FlutterJNI.setRefreshRateFPS(this.f361a.getDefaultDisplay().getRefreshRate());
    }
}
