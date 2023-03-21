package io.flutter.plugin.platform;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import io.flutter.embedding.android.h;
import io.flutter.embedding.android.n;
import io.flutter.embedding.engine.FlutterOverlaySurface;
import io.flutter.embedding.engine.j.j;
import io.flutter.embedding.engine.mutatorsstack.FlutterMutatorsStack;
import io.flutter.plugin.platform.k;
import io.flutter.view.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes.dex */
public class k implements j {

    /* renamed from: b  reason: collision with root package name */
    private io.flutter.embedding.android.b f319b;
    private Context c;
    private View d;
    private io.flutter.view.d e;
    private io.flutter.plugin.editing.d f;
    private io.flutter.embedding.engine.j.j g;
    private int n = 0;
    private boolean o = false;
    private boolean p = true;
    private final j.e t = new a();

    /* renamed from: a  reason: collision with root package name */
    private final i f318a = new i();
    final HashMap<Integer, l> i = new HashMap<>();
    private final d h = new d();
    private final HashMap<Context, View> j = new HashMap<>();
    private final SparseArray<io.flutter.embedding.android.h> m = new SparseArray<>();
    private HashSet<Integer> q = new HashSet<>();
    private HashSet<Integer> r = new HashSet<>();
    private final SparseArray<f> k = new SparseArray<>();
    private final SparseArray<io.flutter.embedding.engine.mutatorsstack.a> l = new SparseArray<>();
    private final n s = n.a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements j.e {

        /* renamed from: io.flutter.plugin.platform.k$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        class RunnableC0022a implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ l f321a;

            /* renamed from: b  reason: collision with root package name */
            final /* synthetic */ Runnable f322b;

            RunnableC0022a(l lVar, Runnable runnable) {
                this.f321a = lVar;
                this.f322b = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                k.this.d0(this.f321a);
                this.f322b.run();
            }
        }

        a() {
        }

        private void j(int i) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= i) {
                return;
            }
            throw new IllegalStateException("Trying to use platform views with API " + i2 + ", required API level is: " + i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: k */
        public /* synthetic */ void l(j.b bVar, View view, boolean z) {
            if (z) {
                k.this.g.d(bVar.f230a);
            }
        }

        @Override // io.flutter.embedding.engine.j.j.e
        public void a(int i) {
            View d;
            f fVar = (f) k.this.k.get(i);
            if (fVar != null) {
                d = fVar.f();
            } else {
                j(20);
                d = k.this.i.get(Integer.valueOf(i)).d();
            }
            d.clearFocus();
        }

        @Override // io.flutter.embedding.engine.j.j.e
        public void b(boolean z) {
            k.this.p = z;
        }

        @Override // io.flutter.embedding.engine.j.j.e
        public void c(j.c cVar, Runnable runnable) {
            j(20);
            l lVar = k.this.i.get(Integer.valueOf(cVar.f232a));
            if (lVar == null) {
                throw new IllegalStateException("Trying to resize a platform view with unknown id: " + cVar.f232a);
            }
            int c0 = k.this.c0(cVar.f233b);
            int c02 = k.this.c0(cVar.c);
            k.this.f0(c0, c02);
            k.this.P(lVar);
            lVar.i(c0, c02, new RunnableC0022a(lVar, runnable));
        }

        @Override // io.flutter.embedding.engine.j.j.e
        public void d(int i) {
            f fVar = (f) k.this.k.get(i);
            io.flutter.embedding.engine.mutatorsstack.a aVar = (io.flutter.embedding.engine.mutatorsstack.a) k.this.l.get(i);
            if (fVar != null) {
                if (aVar != null) {
                    aVar.removeView(fVar.f());
                }
                k.this.k.remove(i);
                fVar.c();
            }
            if (aVar != null) {
                aVar.c();
                ((ViewGroup) aVar.getParent()).removeView(aVar);
                k.this.l.remove(i);
            }
        }

        @Override // io.flutter.embedding.engine.j.j.e
        public void e(int i) {
            j(20);
            l lVar = k.this.i.get(Integer.valueOf(i));
            if (lVar == null) {
                throw new IllegalStateException("Trying to dispose a platform view with unknown id: " + i);
            }
            if (k.this.f != null) {
                k.this.f.l(i);
            }
            k.this.j.remove(lVar.d().getContext());
            lVar.c();
            k.this.i.remove(Integer.valueOf(i));
        }

        @Override // io.flutter.embedding.engine.j.j.e
        @TargetApi(17)
        public long f(final j.b bVar) {
            j(20);
            if (!k.e0(bVar.e)) {
                throw new IllegalStateException("Trying to create a view with unknown direction value: " + bVar.e + "(view id: " + bVar.f230a + ")");
            } else if (k.this.i.containsKey(Integer.valueOf(bVar.f230a))) {
                throw new IllegalStateException("Trying to create an already created platform view, view id: " + bVar.f230a);
            } else {
                g a2 = k.this.f318a.a(bVar.f231b);
                if (a2 == null) {
                    throw new IllegalStateException("Trying to create a platform view of unregistered type: " + bVar.f231b);
                }
                Object a3 = bVar.f != null ? a2.b().a(bVar.f) : null;
                int c0 = k.this.c0(bVar.c);
                int c02 = k.this.c0(bVar.d);
                k.this.f0(c0, c02);
                d.a a4 = k.this.e.a();
                l a5 = l.a(k.this.c, k.this.h, a2, a4, c0, c02, bVar.f230a, a3, new View.OnFocusChangeListener() { // from class: io.flutter.plugin.platform.a
                    @Override // android.view.View.OnFocusChangeListener
                    public final void onFocusChange(View view, boolean z) {
                        k.a.this.l(bVar, view, z);
                    }
                });
                if (a5 == null) {
                    throw new IllegalStateException("Failed creating virtual display for a " + bVar.f231b + " with id: " + bVar.f230a);
                }
                if (k.this.d != null) {
                    a5.e(k.this.d);
                }
                k.this.i.put(Integer.valueOf(bVar.f230a), a5);
                View d = a5.d();
                d.setLayoutDirection(bVar.e);
                k.this.j.put(d.getContext(), d);
                return a4.b();
            }
        }

        @Override // io.flutter.embedding.engine.j.j.e
        @TargetApi(17)
        public void g(int i, int i2) {
            if (!k.e0(i2)) {
                throw new IllegalStateException("Trying to set unknown direction value: " + i2 + "(view id: " + i + ")");
            }
            j(20);
            View d = k.this.i.get(Integer.valueOf(i)).d();
            if (d != null) {
                d.setLayoutDirection(i2);
                return;
            }
            throw new IllegalStateException("Sending touch to an unknown view with id: " + i2);
        }

        @Override // io.flutter.embedding.engine.j.j.e
        public void h(j.b bVar) {
            j(19);
            if (!k.e0(bVar.e)) {
                throw new IllegalStateException("Trying to create a view with unknown direction value: " + bVar.e + "(view id: " + bVar.f230a + ")");
            }
            g a2 = k.this.f318a.a(bVar.f231b);
            if (a2 != null) {
                k.this.k.put(bVar.f230a, a2.a(k.this.c, bVar.f230a, bVar.f != null ? a2.b().a(bVar.f) : null));
                return;
            }
            throw new IllegalStateException("Trying to create a platform view of unregistered type: " + bVar.f231b);
        }

        @Override // io.flutter.embedding.engine.j.j.e
        public void i(j.d dVar) {
            int i = dVar.f234a;
            float f = k.this.c.getResources().getDisplayMetrics().density;
            j(20);
            if (k.this.i.containsKey(Integer.valueOf(i))) {
                k.this.i.get(Integer.valueOf(dVar.f234a)).b(k.this.b0(f, dVar, true));
            } else if (k.this.k.get(i) == null) {
                throw new IllegalStateException("Sending touch to an unknown view with id: " + i);
            } else {
                MotionEvent b0 = k.this.b0(f, dVar, false);
                View f2 = ((f) k.this.k.get(dVar.f234a)).f();
                if (f2 != null) {
                    f2.dispatchTouchEvent(b0);
                }
            }
        }
    }

    private void F(boolean z) {
        for (int i = 0; i < this.m.size(); i++) {
            int keyAt = this.m.keyAt(i);
            io.flutter.embedding.android.h valueAt = this.m.valueAt(i);
            if (this.q.contains(Integer.valueOf(keyAt))) {
                ((io.flutter.embedding.android.k) this.d).j(valueAt);
                z &= valueAt.d();
            } else {
                if (!this.o) {
                    valueAt.c();
                }
                valueAt.setVisibility(8);
            }
        }
        for (int i2 = 0; i2 < this.l.size(); i2++) {
            int keyAt2 = this.l.keyAt(i2);
            io.flutter.embedding.engine.mutatorsstack.a aVar = this.l.get(keyAt2);
            if (!this.r.contains(Integer.valueOf(keyAt2)) || (!z && this.p)) {
                aVar.setVisibility(8);
            } else {
                aVar.setVisibility(0);
            }
        }
    }

    private void G() {
        for (l lVar : this.i.values()) {
            lVar.c();
        }
        this.i.clear();
        while (this.k.size() > 0) {
            this.t.d(this.k.keyAt(0));
        }
    }

    private float H() {
        return this.c.getResources().getDisplayMetrics().density;
    }

    private void K() {
        if (!this.p || this.o) {
            return;
        }
        ((io.flutter.embedding.android.k) this.d).m();
        this.o = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: L */
    public /* synthetic */ void M(int i, View view, boolean z) {
        if (z) {
            this.g.d(i);
            return;
        }
        io.flutter.plugin.editing.d dVar = this.f;
        if (dVar != null) {
            dVar.l(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: N */
    public /* synthetic */ void O() {
        F(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P(l lVar) {
        io.flutter.plugin.editing.d dVar = this.f;
        if (dVar == null) {
            return;
        }
        dVar.u();
        lVar.g();
    }

    private static MotionEvent.PointerCoords X(Object obj, float f) {
        List list = (List) obj;
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.orientation = (float) ((Double) list.get(0)).doubleValue();
        pointerCoords.pressure = (float) ((Double) list.get(1)).doubleValue();
        pointerCoords.size = (float) ((Double) list.get(2)).doubleValue();
        pointerCoords.toolMajor = ((float) ((Double) list.get(3)).doubleValue()) * f;
        pointerCoords.toolMinor = ((float) ((Double) list.get(4)).doubleValue()) * f;
        pointerCoords.touchMajor = ((float) ((Double) list.get(5)).doubleValue()) * f;
        pointerCoords.touchMinor = ((float) ((Double) list.get(6)).doubleValue()) * f;
        pointerCoords.x = ((float) ((Double) list.get(7)).doubleValue()) * f;
        pointerCoords.y = ((float) ((Double) list.get(8)).doubleValue()) * f;
        return pointerCoords;
    }

    private static List<MotionEvent.PointerCoords> Y(Object obj, float f) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (List) obj) {
            arrayList.add(X(obj2, f));
        }
        return arrayList;
    }

    private static MotionEvent.PointerProperties Z(Object obj) {
        List list = (List) obj;
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = ((Integer) list.get(0)).intValue();
        pointerProperties.toolType = ((Integer) list.get(1)).intValue();
        return pointerProperties;
    }

    private static List<MotionEvent.PointerProperties> a0(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (List) obj) {
            arrayList.add(Z(obj2));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c0(double d) {
        double H = H();
        Double.isNaN(H);
        return (int) Math.round(d * H);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0(l lVar) {
        io.flutter.plugin.editing.d dVar = this.f;
        if (dVar == null) {
            return;
        }
        dVar.G();
        lVar.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean e0(int i) {
        return i == 0 || i == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f0(int i, int i2) {
        DisplayMetrics displayMetrics = this.c.getResources().getDisplayMetrics();
        if (i2 > displayMetrics.heightPixels || i > displayMetrics.widthPixels) {
            b.a.b.f("PlatformViewsController", "Creating a virtual display of size: [" + i + ", " + i2 + "] may result in problems(https://github.com/flutter/flutter/issues/2897).It is larger than the device screen size: [" + displayMetrics.widthPixels + ", " + displayMetrics.heightPixels + "].");
        }
    }

    @TargetApi(19)
    public FlutterOverlaySurface A(io.flutter.embedding.android.h hVar) {
        int i = this.n;
        this.n = i + 1;
        this.m.put(i, hVar);
        return new FlutterOverlaySurface(i, hVar.getSurface());
    }

    public void B() {
        for (int i = 0; i < this.m.size(); i++) {
            this.m.keyAt(i);
            io.flutter.embedding.android.h valueAt = this.m.valueAt(i);
            valueAt.c();
            View view = this.d;
            if (view != null) {
                ((io.flutter.embedding.android.k) view).removeView(valueAt);
            }
        }
        this.m.clear();
    }

    public void C() {
        io.flutter.embedding.engine.j.j jVar = this.g;
        if (jVar != null) {
            jVar.e(null);
        }
        B();
        this.g = null;
        this.c = null;
        this.e = null;
    }

    public void D() {
        B();
        this.d = null;
        for (l lVar : this.i.values()) {
            lVar.f();
        }
    }

    public void E() {
        this.f = null;
    }

    public h I() {
        return this.f318a;
    }

    void J(final int i) {
        f fVar = this.k.get(i);
        if (fVar == null) {
            throw new IllegalStateException("Platform view hasn't been initialized from the platform view channel.");
        }
        if (this.l.get(i) != null) {
            return;
        }
        if (fVar.f() == null) {
            throw new IllegalStateException("PlatformView#getView() returned null, but an Android view reference was expected.");
        }
        if (fVar.f().getParent() != null) {
            throw new IllegalStateException("The Android view returned from PlatformView#getView() was already added to a parent view.");
        }
        Context context = this.c;
        io.flutter.embedding.engine.mutatorsstack.a aVar = new io.flutter.embedding.engine.mutatorsstack.a(context, context.getResources().getDisplayMetrics().density, this.f319b);
        aVar.setOnDescendantFocusChangeListener(new View.OnFocusChangeListener() { // from class: io.flutter.plugin.platform.c
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                k.this.M(i, view, z);
            }
        });
        this.l.put(i, aVar);
        aVar.addView(fVar.f());
        ((io.flutter.embedding.android.k) this.d).addView(aVar);
    }

    public void Q() {
    }

    public void R() {
        this.q.clear();
        this.r.clear();
    }

    public void S() {
        G();
    }

    public void T(int i, int i2, int i3, int i4, int i5) {
        if (this.m.get(i) == null) {
            throw new IllegalStateException("The overlay surface (id:" + i + ") doesn't exist");
        }
        K();
        io.flutter.embedding.android.h hVar = this.m.get(i);
        if (hVar.getParent() == null) {
            ((io.flutter.embedding.android.k) this.d).addView(hVar);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i4, i5);
        layoutParams.leftMargin = i2;
        layoutParams.topMargin = i3;
        hVar.setLayoutParams(layoutParams);
        hVar.setVisibility(0);
        hVar.bringToFront();
        this.q.add(Integer.valueOf(i));
    }

    public void U(int i, int i2, int i3, int i4, int i5, int i6, int i7, FlutterMutatorsStack flutterMutatorsStack) {
        K();
        J(i);
        io.flutter.embedding.engine.mutatorsstack.a aVar = this.l.get(i);
        aVar.b(flutterMutatorsStack, i2, i3, i4, i5);
        aVar.setVisibility(0);
        aVar.bringToFront();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i6, i7);
        View f = this.k.get(i).f();
        if (f != null) {
            f.setLayoutParams(layoutParams);
            f.bringToFront();
        }
        this.r.add(Integer.valueOf(i));
    }

    public void V() {
        io.flutter.embedding.android.k kVar = (io.flutter.embedding.android.k) this.d;
        boolean z = false;
        if (this.o && this.r.isEmpty()) {
            this.o = false;
            kVar.w(new Runnable() { // from class: io.flutter.plugin.platform.b
                @Override // java.lang.Runnable
                public final void run() {
                    k.this.O();
                }
            });
            return;
        }
        if (this.o && kVar.g()) {
            z = true;
        }
        F(z);
    }

    public void W() {
        G();
    }

    @Override // io.flutter.plugin.platform.j
    public View a(Integer num) {
        if (this.k.get(num.intValue()) != null) {
            return this.k.get(num.intValue()).f();
        }
        l lVar = this.i.get(num);
        if (lVar == null) {
            return null;
        }
        return lVar.d();
    }

    @Override // io.flutter.plugin.platform.j
    public boolean b(Integer num) {
        return this.i.containsKey(num);
    }

    public MotionEvent b0(float f, j.d dVar, boolean z) {
        MotionEvent b2 = this.s.b(n.a.c(dVar.p));
        MotionEvent.PointerProperties[] pointerPropertiesArr = (MotionEvent.PointerProperties[]) a0(dVar.f).toArray(new MotionEvent.PointerProperties[dVar.e]);
        MotionEvent.PointerCoords[] pointerCoordsArr = (MotionEvent.PointerCoords[]) Y(dVar.g, f).toArray(new MotionEvent.PointerCoords[dVar.e]);
        return (z || b2 == null) ? MotionEvent.obtain(dVar.f235b.longValue(), dVar.c.longValue(), dVar.d, dVar.e, pointerPropertiesArr, pointerCoordsArr, dVar.h, dVar.i, dVar.j, dVar.k, dVar.l, dVar.m, dVar.n, dVar.o) : MotionEvent.obtain(b2.getDownTime(), b2.getEventTime(), b2.getAction(), dVar.e, pointerPropertiesArr, pointerCoordsArr, b2.getMetaState(), b2.getButtonState(), b2.getXPrecision(), b2.getYPrecision(), b2.getDeviceId(), b2.getEdgeFlags(), b2.getSource(), b2.getFlags());
    }

    @Override // io.flutter.plugin.platform.j
    public void c(io.flutter.view.c cVar) {
        this.h.b(cVar);
    }

    @Override // io.flutter.plugin.platform.j
    public void d() {
        this.h.b(null);
    }

    public void u(Context context, io.flutter.view.d dVar, io.flutter.embedding.engine.f.a aVar) {
        if (this.c != null) {
            throw new AssertionError("A PlatformViewsController can only be attached to a single output target.\nattach was called while the PlatformViewsController was already attached.");
        }
        this.c = context;
        this.e = dVar;
        io.flutter.embedding.engine.j.j jVar = new io.flutter.embedding.engine.j.j(aVar);
        this.g = jVar;
        jVar.e(this.t);
    }

    public void v(io.flutter.plugin.editing.d dVar) {
        this.f = dVar;
    }

    public void w(io.flutter.embedding.engine.renderer.a aVar) {
        this.f319b = new io.flutter.embedding.android.b(aVar, true);
    }

    public void x(View view) {
        this.d = view;
        for (l lVar : this.i.values()) {
            lVar.e(view);
        }
    }

    public boolean y(View view) {
        if (view != null && this.j.containsKey(view.getContext())) {
            View view2 = this.j.get(view.getContext());
            if (view2 == view) {
                return true;
            }
            return view2.checkInputConnectionProxy(view);
        }
        return false;
    }

    @TargetApi(19)
    public FlutterOverlaySurface z() {
        return A(new io.flutter.embedding.android.h(this.d.getContext(), this.d.getWidth(), this.d.getHeight(), h.b.overlay));
    }
}
