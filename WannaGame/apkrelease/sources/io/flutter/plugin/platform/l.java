package io.flutter.plugin.platform;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewTreeObserver;
import io.flutter.plugin.platform.SingleViewPresentation;
import io.flutter.view.d;

/* JADX INFO: Access modifiers changed from: package-private */
@TargetApi(20)
/* loaded from: classes.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private final Context f323a;

    /* renamed from: b  reason: collision with root package name */
    private final d f324b;
    private final int c;
    private final d.a d;
    private final View.OnFocusChangeListener e;
    private VirtualDisplay f;
    SingleViewPresentation g;
    private Surface h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements View.OnAttachStateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ View f325a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Runnable f326b;

        /* renamed from: io.flutter.plugin.platform.l$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class RunnableC0023a implements Runnable {
            RunnableC0023a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a aVar = a.this;
                aVar.f325a.postDelayed(aVar.f326b, 128L);
            }
        }

        a(l lVar, View view, Runnable runnable) {
            this.f325a = view;
            this.f326b = runnable;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            b.a(this.f325a, new RunnableC0023a());
            this.f325a.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
        }
    }

    @TargetApi(16)
    /* loaded from: classes.dex */
    static class b implements ViewTreeObserver.OnDrawListener {

        /* renamed from: a  reason: collision with root package name */
        final View f328a;

        /* renamed from: b  reason: collision with root package name */
        Runnable f329b;

        /* loaded from: classes.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f328a.getViewTreeObserver().removeOnDrawListener(b.this);
            }
        }

        b(View view, Runnable runnable) {
            this.f328a = view;
            this.f329b = runnable;
        }

        static void a(View view, Runnable runnable) {
            view.getViewTreeObserver().addOnDrawListener(new b(view, runnable));
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public void onDraw() {
            Runnable runnable = this.f329b;
            if (runnable == null) {
                return;
            }
            runnable.run();
            this.f329b = null;
            this.f328a.post(new a());
        }
    }

    private l(Context context, d dVar, VirtualDisplay virtualDisplay, g gVar, Surface surface, d.a aVar, View.OnFocusChangeListener onFocusChangeListener, int i, Object obj) {
        this.f323a = context;
        this.f324b = dVar;
        this.d = aVar;
        this.e = onFocusChangeListener;
        this.h = surface;
        this.f = virtualDisplay;
        this.c = context.getResources().getDisplayMetrics().densityDpi;
        SingleViewPresentation singleViewPresentation = new SingleViewPresentation(context, this.f.getDisplay(), gVar, dVar, i, obj, onFocusChangeListener);
        this.g = singleViewPresentation;
        singleViewPresentation.show();
    }

    public static l a(Context context, d dVar, g gVar, d.a aVar, int i, int i2, int i3, Object obj, View.OnFocusChangeListener onFocusChangeListener) {
        aVar.c().setDefaultBufferSize(i, i2);
        Surface surface = new Surface(aVar.c());
        VirtualDisplay createVirtualDisplay = ((DisplayManager) context.getSystemService("display")).createVirtualDisplay("flutter-vd", i, i2, context.getResources().getDisplayMetrics().densityDpi, surface, 0);
        if (createVirtualDisplay == null) {
            return null;
        }
        return new l(context, dVar, createVirtualDisplay, gVar, surface, aVar, onFocusChangeListener, i3, obj);
    }

    public void b(MotionEvent motionEvent) {
        SingleViewPresentation singleViewPresentation = this.g;
        if (singleViewPresentation == null) {
            return;
        }
        singleViewPresentation.dispatchTouchEvent(motionEvent);
    }

    public void c() {
        f view = this.g.getView();
        this.g.cancel();
        this.g.detachState();
        view.c();
        this.f.release();
        this.d.a();
    }

    public View d() {
        SingleViewPresentation singleViewPresentation = this.g;
        if (singleViewPresentation == null) {
            return null;
        }
        return singleViewPresentation.getView().f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(View view) {
        SingleViewPresentation singleViewPresentation = this.g;
        if (singleViewPresentation == null || singleViewPresentation.getView() == null) {
            return;
        }
        this.g.getView().d(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        SingleViewPresentation singleViewPresentation = this.g;
        if (singleViewPresentation == null || singleViewPresentation.getView() == null) {
            return;
        }
        this.g.getView().e();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g() {
        SingleViewPresentation singleViewPresentation = this.g;
        if (singleViewPresentation == null || singleViewPresentation.getView() == null) {
            return;
        }
        this.g.getView().b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h() {
        SingleViewPresentation singleViewPresentation = this.g;
        if (singleViewPresentation == null || singleViewPresentation.getView() == null) {
            return;
        }
        this.g.getView().a();
    }

    public void i(int i, int i2, Runnable runnable) {
        boolean isFocused = d().isFocused();
        SingleViewPresentation.e detachState = this.g.detachState();
        this.f.setSurface(null);
        this.f.release();
        this.d.c().setDefaultBufferSize(i, i2);
        this.f = ((DisplayManager) this.f323a.getSystemService("display")).createVirtualDisplay("flutter-vd", i, i2, this.c, this.h, 0);
        View d = d();
        d.addOnAttachStateChangeListener(new a(this, d, runnable));
        SingleViewPresentation singleViewPresentation = new SingleViewPresentation(this.f323a, this.f.getDisplay(), this.f324b, detachState, this.e, isFocused);
        singleViewPresentation.show();
        this.g.cancel();
        this.g = singleViewPresentation;
    }
}
