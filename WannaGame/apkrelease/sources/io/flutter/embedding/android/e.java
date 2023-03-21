package io.flutter.embedding.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.lifecycle.d;
import io.flutter.embedding.android.f;

/* loaded from: classes.dex */
public class e extends Activity implements f.c, androidx.lifecycle.g {
    public static final int c = b.a.d.d.a(61938);

    /* renamed from: a  reason: collision with root package name */
    protected f f107a;

    /* renamed from: b  reason: collision with root package name */
    private androidx.lifecycle.h f108b = new androidx.lifecycle.h(this);

    private void B() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(1073741824);
            window.getDecorView().setSystemUiVisibility(1280);
        }
    }

    private void C() {
        if (E() == g.transparent) {
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    private View D() {
        return this.f107a.p(null, null, null, c, n() == o.surface);
    }

    private Drawable H() {
        try {
            Bundle G = G();
            int i = G != null ? G.getInt("io.flutter.embedding.android.SplashScreenDrawable") : 0;
            if (i != 0) {
                return a.c.a.a.a.a(getResources(), i, getTheme());
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        } catch (Resources.NotFoundException e) {
            b.a.b.b("FlutterActivity", "Splash screen not found. Ensure the drawable exists and that it's valid.");
            throw e;
        }
    }

    private boolean I() {
        return (getApplicationInfo().flags & 2) != 0;
    }

    private void J() {
        this.f107a.q();
        this.f107a.r();
        this.f107a.D();
        this.f107a = null;
    }

    private boolean K(String str) {
        if (this.f107a == null) {
            b.a.b.f("FlutterActivity", "FlutterActivity " + hashCode() + " " + str + " called after release.");
            return false;
        }
        return true;
    }

    private void L() {
        try {
            Bundle G = G();
            if (G != null) {
                int i = G.getInt("io.flutter.embedding.android.NormalTheme", -1);
                if (i != -1) {
                    setTheme(i);
                }
            } else {
                b.a.b.e("FlutterActivity", "Using the launch theme as normal theme.");
            }
        } catch (PackageManager.NameNotFoundException unused) {
            b.a.b.b("FlutterActivity", "Could not read meta-data for FlutterActivity. Using the launch theme as normal theme.");
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public void A(i iVar) {
    }

    protected g E() {
        return getIntent().hasExtra("background_mode") ? g.valueOf(getIntent().getStringExtra("background_mode")) : g.opaque;
    }

    protected io.flutter.embedding.engine.b F() {
        return this.f107a.j();
    }

    protected Bundle G() {
        return getPackageManager().getActivityInfo(getComponentName(), 128).metaData;
    }

    @Override // io.flutter.embedding.android.f.c
    public void a() {
        b.a.b.f("FlutterActivity", "FlutterActivity " + this + " connection to the engine " + F() + " evicted by another attaching activity");
        J();
    }

    @Override // io.flutter.plugin.platform.e.d
    public boolean b() {
        return false;
    }

    @Override // io.flutter.embedding.android.f.c
    public void c() {
        if (Build.VERSION.SDK_INT >= 21) {
            reportFullyDrawn();
        }
    }

    @Override // io.flutter.embedding.android.f.c, androidx.lifecycle.g
    public androidx.lifecycle.d d() {
        return this.f108b;
    }

    @Override // io.flutter.embedding.android.f.c
    public void e() {
    }

    @Override // io.flutter.embedding.android.f.c
    public Activity f() {
        return this;
    }

    @Override // io.flutter.embedding.android.f.c
    public String g() {
        if (getIntent().hasExtra("route")) {
            return getIntent().getStringExtra("route");
        }
        try {
            Bundle G = G();
            if (G != null) {
                return G.getString("io.flutter.InitialRoute");
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public io.flutter.embedding.engine.e h() {
        return io.flutter.embedding.engine.e.a(getIntent());
    }

    @Override // io.flutter.embedding.android.f.c
    public String k() {
        String dataString;
        if (I() && "android.intent.action.RUN".equals(getIntent().getAction()) && (dataString = getIntent().getDataString()) != null) {
            return dataString;
        }
        return null;
    }

    @Override // io.flutter.embedding.android.f.c
    public io.flutter.embedding.engine.b l(Context context) {
        return null;
    }

    @Override // io.flutter.embedding.android.f.c
    public boolean m() {
        return true;
    }

    @Override // io.flutter.embedding.android.f.c
    public o n() {
        return E() == g.opaque ? o.surface : o.texture;
    }

    @Override // io.flutter.embedding.android.f.c
    public boolean o() {
        boolean booleanExtra = getIntent().getBooleanExtra("destroy_engine_with_activity", false);
        return (s() != null || this.f107a.k()) ? booleanExtra : getIntent().getBooleanExtra("destroy_engine_with_activity", true);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (K("onActivityResult")) {
            this.f107a.m(i, i2, intent);
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (K("onBackPressed")) {
            this.f107a.o();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        L();
        super.onCreate(bundle);
        f fVar = new f(this);
        this.f107a = fVar;
        fVar.n(this);
        this.f107a.w(bundle);
        this.f108b.g(d.a.ON_CREATE);
        C();
        setContentView(D());
        B();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (K("onDestroy")) {
            J();
        }
        this.f108b.g(d.a.ON_DESTROY);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (K("onNewIntent")) {
            this.f107a.s(intent);
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (K("onPause")) {
            this.f107a.t();
        }
        this.f108b.g(d.a.ON_PAUSE);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        if (K("onPostResume")) {
            this.f107a.u();
        }
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (K("onRequestPermissionsResult")) {
            this.f107a.v(i, strArr, iArr);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.f108b.g(d.a.ON_RESUME);
        if (K("onResume")) {
            this.f107a.x();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (K("onSaveInstanceState")) {
            this.f107a.y(bundle);
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        this.f108b.g(d.a.ON_START);
        if (K("onStart")) {
            this.f107a.z();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (K("onStop")) {
            this.f107a.A();
        }
        this.f108b.g(d.a.ON_STOP);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (K("onTrimMemory")) {
            this.f107a.B(i);
        }
    }

    @Override // android.app.Activity
    public void onUserLeaveHint() {
        if (K("onUserLeaveHint")) {
            this.f107a.C();
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public boolean p() {
        try {
            Bundle G = G();
            if (G != null) {
                return G.getBoolean("flutter_deeplinking_enabled");
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public r q() {
        return E() == g.opaque ? r.opaque : r.transparent;
    }

    @Override // io.flutter.embedding.android.f.c
    public void r(j jVar) {
    }

    @Override // io.flutter.embedding.android.f.c
    public String s() {
        return getIntent().getStringExtra("cached_engine_id");
    }

    @Override // io.flutter.embedding.android.f.c
    public void t(io.flutter.embedding.engine.b bVar) {
        if (this.f107a.k()) {
            return;
        }
        io.flutter.embedding.engine.i.f.a.a(bVar);
    }

    @Override // io.flutter.embedding.android.f.c
    public boolean u() {
        return getIntent().hasExtra("enable_state_restoration") ? getIntent().getBooleanExtra("enable_state_restoration", false) : s() == null;
    }

    @Override // io.flutter.embedding.android.f.c
    public String v() {
        try {
            Bundle G = G();
            String string = G != null ? G.getString("io.flutter.Entrypoint") : null;
            return string != null ? string : "main";
        } catch (PackageManager.NameNotFoundException unused) {
            return "main";
        }
    }

    @Override // io.flutter.embedding.android.f.c
    public void w(io.flutter.embedding.engine.b bVar) {
    }

    @Override // io.flutter.embedding.android.f.c
    public q x() {
        Drawable H = H();
        if (H != null) {
            return new c(H);
        }
        return null;
    }

    @Override // io.flutter.embedding.android.f.c
    public io.flutter.plugin.platform.e y(Activity activity, io.flutter.embedding.engine.b bVar) {
        f();
        return new io.flutter.plugin.platform.e(this, bVar.n(), this);
    }

    @Override // io.flutter.embedding.android.f.c
    public Context z() {
        return this;
    }
}
