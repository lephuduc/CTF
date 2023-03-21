package io.flutter.embedding.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import io.flutter.embedding.engine.f.a;
import io.flutter.plugin.platform.e;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class f implements d<Activity> {

    /* renamed from: a  reason: collision with root package name */
    private c f109a;

    /* renamed from: b  reason: collision with root package name */
    private io.flutter.embedding.engine.b f110b;
    private k c;
    private io.flutter.plugin.platform.e d;
    ViewTreeObserver.OnPreDrawListener e;
    private boolean f;
    private boolean g;
    private final io.flutter.embedding.engine.renderer.b h = new a();

    /* loaded from: classes.dex */
    class a implements io.flutter.embedding.engine.renderer.b {
        a() {
        }

        @Override // io.flutter.embedding.engine.renderer.b
        public void c() {
            f.this.f109a.c();
            f.this.g = true;
        }

        @Override // io.flutter.embedding.engine.renderer.b
        public void e() {
            f.this.f109a.e();
            f.this.g = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements ViewTreeObserver.OnPreDrawListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ k f112a;

        b(k kVar) {
            this.f112a = kVar;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (f.this.g && f.this.e != null) {
                this.f112a.getViewTreeObserver().removeOnPreDrawListener(this);
                f.this.e = null;
            }
            return f.this.g;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface c extends e.d {
        void A(i iVar);

        void a();

        void c();

        androidx.lifecycle.d d();

        void e();

        Activity f();

        String g();

        io.flutter.embedding.engine.e h();

        String k();

        io.flutter.embedding.engine.b l(Context context);

        boolean m();

        o n();

        boolean o();

        boolean p();

        r q();

        void r(j jVar);

        String s();

        void t(io.flutter.embedding.engine.b bVar);

        boolean u();

        String v();

        void w(io.flutter.embedding.engine.b bVar);

        q x();

        io.flutter.plugin.platform.e y(Activity activity, io.flutter.embedding.engine.b bVar);

        Context z();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(c cVar) {
        this.f109a = cVar;
    }

    private void f(k kVar) {
        if (this.f109a.n() != o.surface) {
            throw new IllegalArgumentException("Cannot delay the first Android view draw when the render mode is not set to `RenderMode.surface`.");
        }
        if (this.e != null) {
            kVar.getViewTreeObserver().removeOnPreDrawListener(this.e);
        }
        this.e = new b(kVar);
        kVar.getViewTreeObserver().addOnPreDrawListener(this.e);
    }

    private void g() {
        if (this.f109a.s() == null && !this.f110b.h().h()) {
            String g = this.f109a.g();
            if (g == null && (g = l(this.f109a.f().getIntent())) == null) {
                g = "/";
            }
            b.a.b.e("FlutterActivityAndFragmentDelegate", "Executing Dart entrypoint: " + this.f109a.v() + ", and sending initial route: " + g);
            this.f110b.m().c(g);
            String k = this.f109a.k();
            if (k == null || k.isEmpty()) {
                k = b.a.a.d().b().e();
            }
            this.f110b.h().f(new a.b(k, this.f109a.v()));
        }
    }

    private void h() {
        if (this.f109a == null) {
            throw new IllegalStateException("Cannot execute method on a destroyed FlutterActivityAndFragmentDelegate.");
        }
    }

    private String l(Intent intent) {
        Uri data;
        if (!this.f109a.p() || (data = intent.getData()) == null || data.getPath().isEmpty()) {
            return null;
        }
        String path = data.getPath();
        if (data.getQuery() != null && !data.getQuery().isEmpty()) {
            path = path + "?" + data.getQuery();
        }
        if (data.getFragment() == null || data.getFragment().isEmpty()) {
            return path;
        }
        return path + "#" + data.getFragment();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void A() {
        b.a.b.e("FlutterActivityAndFragmentDelegate", "onStop()");
        h();
        this.f110b.j().c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void B(int i) {
        h();
        io.flutter.embedding.engine.b bVar = this.f110b;
        if (bVar == null) {
            b.a.b.f("FlutterActivityAndFragmentDelegate", "onTrimMemory() invoked before FlutterFragment was attached to an Activity.");
            return;
        }
        bVar.h().i();
        if (i == 10) {
            b.a.b.e("FlutterActivityAndFragmentDelegate", "Forwarding onTrimMemory() to FlutterEngine. Level: " + i);
            this.f110b.s().a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void C() {
        h();
        if (this.f110b == null) {
            b.a.b.f("FlutterActivityAndFragmentDelegate", "onUserLeaveHint() invoked before FlutterFragment was attached to an Activity.");
            return;
        }
        b.a.b.e("FlutterActivityAndFragmentDelegate", "Forwarding onUserLeaveHint() to FlutterEngine.");
        this.f110b.g().f();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void D() {
        this.f109a = null;
        this.f110b = null;
        this.c = null;
        this.d = null;
    }

    void E() {
        b.a.b.e("FlutterActivityAndFragmentDelegate", "Setting up FlutterEngine.");
        String s = this.f109a.s();
        if (s != null) {
            io.flutter.embedding.engine.b a2 = io.flutter.embedding.engine.c.b().a(s);
            this.f110b = a2;
            this.f = true;
            if (a2 != null) {
                return;
            }
            throw new IllegalStateException("The requested cached FlutterEngine did not exist in the FlutterEngineCache: '" + s + "'");
        }
        c cVar = this.f109a;
        io.flutter.embedding.engine.b l = cVar.l(cVar.z());
        this.f110b = l;
        if (l != null) {
            this.f = true;
            return;
        }
        b.a.b.e("FlutterActivityAndFragmentDelegate", "No preferred FlutterEngine was provided. Creating a new FlutterEngine for this FlutterFragment.");
        this.f110b = new io.flutter.embedding.engine.b(this.f109a.z(), this.f109a.h().b(), false, this.f109a.u());
        this.f = false;
    }

    @Override // io.flutter.embedding.android.d
    public void a() {
        if (!this.f109a.o()) {
            this.f109a.a();
            return;
        }
        throw new AssertionError("The internal FlutterEngine created by " + this.f109a + " has been attached to by another activity. To persist a FlutterEngine beyond the ownership of this activity, explicitly create a FlutterEngine");
    }

    @Override // io.flutter.embedding.android.d
    /* renamed from: i */
    public Activity b() {
        Activity f = this.f109a.f();
        if (f != null) {
            return f;
        }
        throw new AssertionError("FlutterActivityAndFragmentDelegate's getAppComponent should only be queried after onAttach, when the host's activity should always be non-null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public io.flutter.embedding.engine.b j() {
        return this.f110b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(int i, int i2, Intent intent) {
        h();
        if (this.f110b == null) {
            b.a.b.f("FlutterActivityAndFragmentDelegate", "onActivityResult() invoked before FlutterFragment was attached to an Activity.");
            return;
        }
        b.a.b.e("FlutterActivityAndFragmentDelegate", "Forwarding onActivityResult() to FlutterEngine:\nrequestCode: " + i + "\nresultCode: " + i2 + "\ndata: " + intent);
        this.f110b.g().c(i, i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(Context context) {
        h();
        if (this.f110b == null) {
            E();
        }
        if (this.f109a.m()) {
            b.a.b.e("FlutterActivityAndFragmentDelegate", "Attaching FlutterEngine to the Activity that owns this delegate.");
            this.f110b.g().g(this, this.f109a.d());
        }
        c cVar = this.f109a;
        this.d = cVar.y(cVar.f(), this.f110b);
        this.f109a.t(this.f110b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o() {
        h();
        if (this.f110b == null) {
            b.a.b.f("FlutterActivityAndFragmentDelegate", "Invoked onBackPressed() before FlutterFragment was attached to an Activity.");
            return;
        }
        b.a.b.e("FlutterActivityAndFragmentDelegate", "Forwarding onBackPressed() to FlutterEngine.");
        this.f110b.m().a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View p(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle, int i, boolean z) {
        k kVar;
        b.a.b.e("FlutterActivityAndFragmentDelegate", "Creating FlutterView.");
        h();
        if (this.f109a.n() == o.surface) {
            i iVar = new i(this.f109a.z(), this.f109a.q() == r.transparent);
            this.f109a.A(iVar);
            kVar = new k(this.f109a.z(), iVar);
        } else {
            j jVar = new j(this.f109a.z());
            jVar.setOpaque(this.f109a.q() == r.opaque);
            this.f109a.r(jVar);
            kVar = new k(this.f109a.z(), jVar);
        }
        this.c = kVar;
        this.c.i(this.h);
        b.a.b.e("FlutterActivityAndFragmentDelegate", "Attaching FlutterEngine to FlutterView.");
        this.c.k(this.f110b);
        this.c.setId(i);
        q x = this.f109a.x();
        if (x == null) {
            if (z) {
                f(this.c);
            }
            return this.c;
        }
        b.a.b.f("FlutterActivityAndFragmentDelegate", "A splash screen was provided to Flutter, but this is deprecated. See flutter.dev/go/android-splash-migration for migration steps.");
        FlutterSplashView flutterSplashView = new FlutterSplashView(this.f109a.z());
        flutterSplashView.setId(b.a.d.d.a(486947586));
        flutterSplashView.g(this.c, x);
        return flutterSplashView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q() {
        b.a.b.e("FlutterActivityAndFragmentDelegate", "onDestroyView()");
        h();
        if (this.e != null) {
            this.c.getViewTreeObserver().removeOnPreDrawListener(this.e);
            this.e = null;
        }
        this.c.o();
        this.c.u(this.h);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void r() {
        b.a.b.e("FlutterActivityAndFragmentDelegate", "onDetach()");
        h();
        this.f109a.w(this.f110b);
        if (this.f109a.m()) {
            b.a.b.e("FlutterActivityAndFragmentDelegate", "Detaching FlutterEngine from the Activity that owns this Fragment.");
            if (this.f109a.f().isChangingConfigurations()) {
                this.f110b.g().i();
            } else {
                this.f110b.g().h();
            }
        }
        io.flutter.plugin.platform.e eVar = this.d;
        if (eVar != null) {
            eVar.o();
            this.d = null;
        }
        this.f110b.j().a();
        if (this.f109a.o()) {
            this.f110b.e();
            if (this.f109a.s() != null) {
                io.flutter.embedding.engine.c.b().d(this.f109a.s());
            }
            this.f110b = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void s(Intent intent) {
        h();
        if (this.f110b == null) {
            b.a.b.f("FlutterActivityAndFragmentDelegate", "onNewIntent() invoked before FlutterFragment was attached to an Activity.");
            return;
        }
        b.a.b.e("FlutterActivityAndFragmentDelegate", "Forwarding onNewIntent() to FlutterEngine and sending pushRoute message.");
        this.f110b.g().d(intent);
        String l = l(intent);
        if (l == null || l.isEmpty()) {
            return;
        }
        this.f110b.m().b(l);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void t() {
        b.a.b.e("FlutterActivityAndFragmentDelegate", "onPause()");
        h();
        this.f110b.j().b();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void u() {
        b.a.b.e("FlutterActivityAndFragmentDelegate", "onPostResume()");
        h();
        if (this.f110b == null) {
            b.a.b.f("FlutterActivityAndFragmentDelegate", "onPostResume() invoked before FlutterFragment was attached to an Activity.");
            return;
        }
        io.flutter.plugin.platform.e eVar = this.d;
        if (eVar != null) {
            eVar.A();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v(int i, String[] strArr, int[] iArr) {
        h();
        if (this.f110b == null) {
            b.a.b.f("FlutterActivityAndFragmentDelegate", "onRequestPermissionResult() invoked before FlutterFragment was attached to an Activity.");
            return;
        }
        b.a.b.e("FlutterActivityAndFragmentDelegate", "Forwarding onRequestPermissionsResult() to FlutterEngine:\nrequestCode: " + i + "\npermissions: " + Arrays.toString(strArr) + "\ngrantResults: " + Arrays.toString(iArr));
        this.f110b.g().b(i, strArr, iArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void w(Bundle bundle) {
        Bundle bundle2;
        b.a.b.e("FlutterActivityAndFragmentDelegate", "onRestoreInstanceState. Giving framework and plugins an opportunity to restore state.");
        h();
        byte[] bArr = null;
        if (bundle != null) {
            Bundle bundle3 = bundle.getBundle("plugins");
            bArr = bundle.getByteArray("framework");
            bundle2 = bundle3;
        } else {
            bundle2 = null;
        }
        if (this.f109a.u()) {
            this.f110b.q().j(bArr);
        }
        if (this.f109a.m()) {
            this.f110b.g().a(bundle2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void x() {
        b.a.b.e("FlutterActivityAndFragmentDelegate", "onResume()");
        h();
        this.f110b.j().d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void y(Bundle bundle) {
        b.a.b.e("FlutterActivityAndFragmentDelegate", "onSaveInstanceState. Giving framework and plugins an opportunity to save state.");
        h();
        if (this.f109a.u()) {
            bundle.putByteArray("framework", this.f110b.q().h());
        }
        if (this.f109a.m()) {
            Bundle bundle2 = new Bundle();
            this.f110b.g().e(bundle2);
            bundle.putBundle("plugins", bundle2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void z() {
        b.a.b.e("FlutterActivityAndFragmentDelegate", "onStart()");
        h();
        g();
    }
}
