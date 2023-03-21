package io.flutter.embedding.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.DisplayCutout;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewStructure;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.FrameLayout;
import b.a.c.c.a;
import io.flutter.embedding.android.h;
import io.flutter.embedding.engine.j.l;
import io.flutter.embedding.engine.renderer.a;
import io.flutter.view.c;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class k extends FrameLayout implements a.c {

    /* renamed from: a  reason: collision with root package name */
    private i f128a;

    /* renamed from: b  reason: collision with root package name */
    private j f129b;
    private h c;
    private io.flutter.embedding.engine.renderer.c d;
    private io.flutter.embedding.engine.renderer.c e;
    private final Set<io.flutter.embedding.engine.renderer.b> f;
    private boolean g;
    private io.flutter.embedding.engine.b h;
    private final Set<d> i;
    private b.a.c.c.a j;
    private io.flutter.plugin.editing.d k;
    private b.a.c.b.a l;
    private m m;
    private io.flutter.embedding.android.b n;
    private io.flutter.view.c o;
    private final a.c p;
    private final c.k q;
    private final io.flutter.embedding.engine.renderer.b r;

    /* loaded from: classes.dex */
    class a implements c.k {
        a() {
        }

        @Override // io.flutter.view.c.k
        public void a(boolean z, boolean z2) {
            k.this.v(z, z2);
        }
    }

    /* loaded from: classes.dex */
    class b implements io.flutter.embedding.engine.renderer.b {
        b() {
        }

        @Override // io.flutter.embedding.engine.renderer.b
        public void c() {
            k.this.g = true;
            for (io.flutter.embedding.engine.renderer.b bVar : k.this.f) {
                bVar.c();
            }
        }

        @Override // io.flutter.embedding.engine.renderer.b
        public void e() {
            k.this.g = false;
            for (io.flutter.embedding.engine.renderer.b bVar : k.this.f) {
                bVar.e();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class c implements io.flutter.embedding.engine.renderer.b {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ io.flutter.embedding.engine.renderer.a f132a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ Runnable f133b;

        c(io.flutter.embedding.engine.renderer.a aVar, Runnable runnable) {
            this.f132a = aVar;
            this.f133b = runnable;
        }

        @Override // io.flutter.embedding.engine.renderer.b
        public void c() {
            this.f132a.l(this);
            this.f133b.run();
            if (k.this.d instanceof h) {
                return;
            }
            k.this.c.c();
        }

        @Override // io.flutter.embedding.engine.renderer.b
        public void e() {
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        void a(io.flutter.embedding.engine.b bVar);

        void b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum e {
        NONE,
        LEFT,
        RIGHT,
        BOTH
    }

    private k(Context context, AttributeSet attributeSet, i iVar) {
        super(context, attributeSet);
        this.f = new HashSet();
        this.i = new HashSet();
        this.p = new a.c();
        this.q = new a();
        this.r = new b();
        this.f128a = iVar;
        this.d = iVar;
        r();
    }

    private k(Context context, AttributeSet attributeSet, j jVar) {
        super(context, attributeSet);
        this.f = new HashSet();
        this.i = new HashSet();
        this.p = new a.c();
        this.q = new a();
        this.r = new b();
        this.f129b = jVar;
        this.d = jVar;
        r();
    }

    public k(Context context, i iVar) {
        this(context, (AttributeSet) null, iVar);
    }

    public k(Context context, j jVar) {
        this(context, (AttributeSet) null, jVar);
    }

    private e l() {
        Context context = getContext();
        int i = context.getResources().getConfiguration().orientation;
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        if (i == 2) {
            if (rotation == 1) {
                return e.RIGHT;
            }
            if (rotation == 3) {
                return Build.VERSION.SDK_INT >= 23 ? e.LEFT : e.RIGHT;
            } else if (rotation == 0 || rotation == 2) {
                return e.BOTH;
            }
        }
        return e.NONE;
    }

    @TargetApi(20)
    private int p(WindowInsets windowInsets) {
        double height = getRootView().getHeight();
        Double.isNaN(height);
        if (windowInsets.getSystemWindowInsetBottom() < height * 0.18d) {
            return 0;
        }
        return windowInsets.getSystemWindowInsetBottom();
    }

    private void r() {
        View view;
        b.a.b.e("FlutterView", "Initializing FlutterView");
        if (this.f128a != null) {
            b.a.b.e("FlutterView", "Internally using a FlutterSurfaceView.");
            view = this.f128a;
        } else if (this.f129b != null) {
            b.a.b.e("FlutterView", "Internally using a FlutterTextureView.");
            view = this.f129b;
        } else {
            b.a.b.e("FlutterView", "Internally using a FlutterImageView.");
            view = this.c;
        }
        addView(view);
        setFocusable(true);
        setFocusableInTouchMode(true);
        if (Build.VERSION.SDK_INT >= 26) {
            setImportantForAutofill(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(boolean z, boolean z2) {
        boolean z3 = false;
        if (!this.h.p().i() && !z && !z2) {
            z3 = true;
        }
        setWillNotDraw(z3);
    }

    private void y() {
        if (!s()) {
            b.a.b.f("FlutterView", "Tried to send viewport metrics from Android to Flutter but this FlutterView was not attached to a FlutterEngine.");
            return;
        }
        this.p.f275a = getResources().getDisplayMetrics().density;
        this.p.p = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        this.h.p().n(this.p);
    }

    @Override // b.a.c.c.a.c
    @TargetApi(24)
    public PointerIcon a(int i) {
        return PointerIcon.getSystemIcon(getContext(), i);
    }

    @Override // android.view.View
    public void autofill(SparseArray<AutofillValue> sparseArray) {
        this.k.j(sparseArray);
    }

    @Override // android.view.View
    public boolean checkInputConnectionProxy(View view) {
        io.flutter.embedding.engine.b bVar = this.h;
        return bVar != null ? bVar.o().y(view) : super.checkInputConnectionProxy(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
            getKeyDispatcherState().startTracking(keyEvent, this);
        } else if (keyEvent.getAction() == 1) {
            getKeyDispatcherState().handleUpEvent(keyEvent);
        }
        return (s() && this.m.c(keyEvent)) || super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    protected boolean fitSystemWindows(Rect rect) {
        if (Build.VERSION.SDK_INT <= 19) {
            a.c cVar = this.p;
            cVar.d = rect.top;
            cVar.e = rect.right;
            cVar.f = 0;
            cVar.g = rect.left;
            cVar.h = 0;
            cVar.i = 0;
            cVar.j = rect.bottom;
            cVar.k = 0;
            b.a.b.e("FlutterView", "Updating window insets (fitSystemWindows()):\nStatus bar insets: Top: " + this.p.d + ", Left: " + this.p.g + ", Right: " + this.p.e + "\nKeyboard insets: Bottom: " + this.p.j + ", Left: " + this.p.k + ", Right: " + this.p.i);
            y();
            return true;
        }
        return super.fitSystemWindows(rect);
    }

    public boolean g() {
        h hVar = this.c;
        if (hVar != null) {
            return hVar.d();
        }
        return false;
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        io.flutter.view.c cVar = this.o;
        if (cVar == null || !cVar.y()) {
            return null;
        }
        return this.o;
    }

    public io.flutter.embedding.engine.b getAttachedFlutterEngine() {
        return this.h;
    }

    public void h(d dVar) {
        this.i.add(dVar);
    }

    public void i(io.flutter.embedding.engine.renderer.b bVar) {
        this.f.add(bVar);
    }

    public void j(h hVar) {
        io.flutter.embedding.engine.b bVar = this.h;
        if (bVar != null) {
            hVar.a(bVar.p());
        }
    }

    public void k(io.flutter.embedding.engine.b bVar) {
        b.a.b.e("FlutterView", "Attaching to a FlutterEngine: " + bVar);
        if (s()) {
            if (bVar == this.h) {
                b.a.b.e("FlutterView", "Already attached to this engine. Doing nothing.");
                return;
            } else {
                b.a.b.e("FlutterView", "Currently attached to a different engine. Detaching and then attaching to new engine.");
                o();
            }
        }
        this.h = bVar;
        io.flutter.embedding.engine.renderer.a p = bVar.p();
        this.g = p.h();
        this.d.a(p);
        p.f(this.r);
        if (Build.VERSION.SDK_INT >= 24) {
            this.j = new b.a.c.c.a(this, this.h.l());
        }
        this.k = new io.flutter.plugin.editing.d(this, this.h.t(), this.h.o());
        this.l = this.h.k();
        this.m = new m(this, this.k, new l[]{new l(bVar.i())});
        this.n = new io.flutter.embedding.android.b(this.h.p(), false);
        io.flutter.view.c cVar = new io.flutter.view.c(this, bVar.f(), (AccessibilityManager) getContext().getSystemService("accessibility"), getContext().getContentResolver(), this.h.o());
        this.o = cVar;
        cVar.Q(this.q);
        v(this.o.y(), this.o.z());
        this.h.o().c(this.o);
        this.h.o().w(this.h.p());
        this.k.q().restartInput(this);
        x();
        this.l.d(getResources().getConfiguration());
        y();
        bVar.o().x(this);
        for (d dVar : this.i) {
            dVar.a(bVar);
        }
        if (this.g) {
            this.r.c();
        }
    }

    public void m() {
        this.d.b();
        h hVar = this.c;
        if (hVar == null) {
            h n = n();
            this.c = n;
            addView(n);
        } else {
            hVar.h(getWidth(), getHeight());
        }
        this.e = this.d;
        h hVar2 = this.c;
        this.d = hVar2;
        io.flutter.embedding.engine.b bVar = this.h;
        if (bVar != null) {
            hVar2.a(bVar.p());
        }
    }

    public h n() {
        return new h(getContext(), getWidth(), getHeight(), h.b.background);
    }

    public void o() {
        b.a.b.e("FlutterView", "Detaching from a FlutterEngine: " + this.h);
        if (!s()) {
            b.a.b.e("FlutterView", "FlutterView not attached to an engine. Not detaching.");
            return;
        }
        for (d dVar : this.i) {
            dVar.b();
        }
        this.h.o().D();
        this.h.o().d();
        this.o.K();
        this.o = null;
        this.k.q().restartInput(this);
        this.k.p();
        this.m.b();
        b.a.c.c.a aVar = this.j;
        if (aVar != null) {
            aVar.c();
        }
        io.flutter.embedding.engine.renderer.a p = this.h.p();
        this.g = false;
        p.l(this.r);
        p.p();
        p.m(false);
        this.d.c();
        this.c = null;
        this.e = null;
        this.h = null;
    }

    @Override // android.view.View
    @SuppressLint({"InlinedApi", "NewApi"})
    @TargetApi(20)
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        WindowInsets onApplyWindowInsets = super.onApplyWindowInsets(windowInsets);
        int i = Build.VERSION.SDK_INT;
        if (i == 29) {
            Insets systemGestureInsets = windowInsets.getSystemGestureInsets();
            a.c cVar = this.p;
            cVar.l = systemGestureInsets.top;
            cVar.m = systemGestureInsets.right;
            cVar.n = systemGestureInsets.bottom;
            cVar.o = systemGestureInsets.left;
        }
        boolean z = (getWindowSystemUiVisibility() & 4) == 0;
        boolean z2 = (getWindowSystemUiVisibility() & 2) == 0;
        if (i >= 30) {
            int navigationBars = z2 ? 0 | WindowInsets.Type.navigationBars() : 0;
            if (z) {
                navigationBars |= WindowInsets.Type.statusBars();
            }
            Insets insets = windowInsets.getInsets(navigationBars);
            a.c cVar2 = this.p;
            cVar2.d = insets.top;
            cVar2.e = insets.right;
            cVar2.f = insets.bottom;
            cVar2.g = insets.left;
            Insets insets2 = windowInsets.getInsets(WindowInsets.Type.ime());
            a.c cVar3 = this.p;
            cVar3.h = insets2.top;
            cVar3.i = insets2.right;
            cVar3.j = insets2.bottom;
            cVar3.k = insets2.left;
            Insets insets3 = windowInsets.getInsets(WindowInsets.Type.systemGestures());
            a.c cVar4 = this.p;
            cVar4.l = insets3.top;
            cVar4.m = insets3.right;
            cVar4.n = insets3.bottom;
            cVar4.o = insets3.left;
            DisplayCutout displayCutout = windowInsets.getDisplayCutout();
            if (displayCutout != null) {
                Insets waterfallInsets = displayCutout.getWaterfallInsets();
                a.c cVar5 = this.p;
                cVar5.d = Math.max(Math.max(cVar5.d, waterfallInsets.top), displayCutout.getSafeInsetTop());
                a.c cVar6 = this.p;
                cVar6.e = Math.max(Math.max(cVar6.e, waterfallInsets.right), displayCutout.getSafeInsetRight());
                a.c cVar7 = this.p;
                cVar7.f = Math.max(Math.max(cVar7.f, waterfallInsets.bottom), displayCutout.getSafeInsetBottom());
                a.c cVar8 = this.p;
                cVar8.g = Math.max(Math.max(cVar8.g, waterfallInsets.left), displayCutout.getSafeInsetLeft());
            }
        } else {
            e eVar = e.NONE;
            if (!z2) {
                eVar = l();
            }
            this.p.d = z ? windowInsets.getSystemWindowInsetTop() : 0;
            this.p.e = (eVar == e.RIGHT || eVar == e.BOTH) ? 0 : windowInsets.getSystemWindowInsetRight();
            this.p.f = (z2 && p(windowInsets) == 0) ? windowInsets.getSystemWindowInsetBottom() : 0;
            this.p.g = (eVar == e.LEFT || eVar == e.BOTH) ? 0 : windowInsets.getSystemWindowInsetLeft();
            a.c cVar9 = this.p;
            cVar9.h = 0;
            cVar9.i = 0;
            cVar9.j = p(windowInsets);
            this.p.k = 0;
        }
        b.a.b.e("FlutterView", "Updating window insets (onApplyWindowInsets()):\nStatus bar insets: Top: " + this.p.d + ", Left: " + this.p.g + ", Right: " + this.p.e + "\nKeyboard insets: Bottom: " + this.p.j + ", Left: " + this.p.k + ", Right: " + this.p.i + "System Gesture Insets - Left: " + this.p.o + ", Top: " + this.p.l + ", Right: " + this.p.m + ", Bottom: " + this.p.j);
        y();
        return onApplyWindowInsets;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.h != null) {
            b.a.b.e("FlutterView", "Configuration changed. Sending locales and user settings to Flutter.");
            this.l.d(configuration);
            x();
        }
    }

    @Override // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return !s() ? super.onCreateInputConnection(editorInfo) : this.k.o(this, this.m, editorInfo);
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (s() && this.n.d(motionEvent)) {
            return true;
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        return !s() ? super.onHoverEvent(motionEvent) : this.o.E(motionEvent);
    }

    @Override // android.view.View
    public void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i) {
        super.onProvideAutofillVirtualStructure(viewStructure, i);
        this.k.z(viewStructure, i);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        b.a.b.e("FlutterView", "Size changed. Sending Flutter new viewport metrics. FlutterView was " + i3 + " x " + i4 + ", it is now " + i + " x " + i2);
        a.c cVar = this.p;
        cVar.f276b = i;
        cVar.c = i2;
        y();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (s()) {
            if (Build.VERSION.SDK_INT >= 21) {
                requestUnbufferedDispatch(motionEvent);
            }
            return this.n.e(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean q() {
        return this.g;
    }

    public boolean s() {
        io.flutter.embedding.engine.b bVar = this.h;
        return bVar != null && bVar.p() == this.d.getAttachedRenderer();
    }

    public void t(d dVar) {
        this.i.remove(dVar);
    }

    public void u(io.flutter.embedding.engine.renderer.b bVar) {
        this.f.remove(bVar);
    }

    public void w(Runnable runnable) {
        h hVar = this.c;
        if (hVar == null) {
            b.a.b.e("FlutterView", "Tried to revert the image view, but no image view is used.");
            return;
        }
        io.flutter.embedding.engine.renderer.c cVar = this.e;
        if (cVar == null) {
            b.a.b.e("FlutterView", "Tried to revert the image view, but no previous surface was used.");
            return;
        }
        this.d = cVar;
        this.e = null;
        io.flutter.embedding.engine.b bVar = this.h;
        if (bVar == null) {
            hVar.c();
            runnable.run();
            return;
        }
        io.flutter.embedding.engine.renderer.a p = bVar.p();
        if (p == null) {
            this.c.c();
            runnable.run();
            return;
        }
        this.d.a(p);
        p.f(new c(p, runnable));
    }

    void x() {
        l.b bVar = (getResources().getConfiguration().uiMode & 48) == 32 ? l.b.dark : l.b.light;
        l.a a2 = this.h.r().a();
        a2.c(getResources().getConfiguration().fontScale);
        a2.d(DateFormat.is24HourFormat(getContext()));
        a2.b(bVar);
        a2.a();
    }
}
