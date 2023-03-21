package io.flutter.embedding.engine;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import io.flutter.embedding.engine.j.f;
import io.flutter.embedding.engine.j.g;
import io.flutter.embedding.engine.j.h;
import io.flutter.embedding.engine.j.i;
import io.flutter.embedding.engine.j.k;
import io.flutter.embedding.engine.j.l;
import io.flutter.embedding.engine.j.m;
import io.flutter.embedding.engine.j.n;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final FlutterJNI f153a;

    /* renamed from: b  reason: collision with root package name */
    private final io.flutter.embedding.engine.renderer.a f154b;
    private final io.flutter.embedding.engine.f.a c;
    private final d d;
    private final b.a.c.b.a e;
    private final io.flutter.embedding.engine.j.b f;
    private final io.flutter.embedding.engine.j.c g;
    private final io.flutter.embedding.engine.j.d h;
    private final io.flutter.embedding.engine.j.e i;
    private final f j;
    private final g k;
    private final h l;
    private final k m;
    private final i n;
    private final l o;
    private final m p;
    private final n q;
    private final io.flutter.plugin.platform.k r;
    private final Set<InterfaceC0010b> s;
    private final InterfaceC0010b t;

    /* loaded from: classes.dex */
    class a implements InterfaceC0010b {
        a() {
        }

        @Override // io.flutter.embedding.engine.b.InterfaceC0010b
        public void a() {
            b.a.b.e("FlutterEngine", "onPreEngineRestart()");
            for (InterfaceC0010b interfaceC0010b : b.this.s) {
                interfaceC0010b.a();
            }
            b.this.r.W();
            b.this.m.g();
        }

        @Override // io.flutter.embedding.engine.b.InterfaceC0010b
        public void b() {
        }
    }

    /* renamed from: io.flutter.embedding.engine.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0010b {
        void a();

        void b();
    }

    public b(Context context, io.flutter.embedding.engine.h.c cVar, FlutterJNI flutterJNI, io.flutter.plugin.platform.k kVar, String[] strArr, boolean z, boolean z2) {
        AssetManager assets;
        this.s = new HashSet();
        this.t = new a();
        try {
            assets = context.createPackageContext(context.getPackageName(), 0).getAssets();
        } catch (PackageManager.NameNotFoundException unused) {
            assets = context.getAssets();
        }
        b.a.a d = b.a.a.d();
        flutterJNI = flutterJNI == null ? d.c().a() : flutterJNI;
        this.f153a = flutterJNI;
        io.flutter.embedding.engine.f.a aVar = new io.flutter.embedding.engine.f.a(flutterJNI, assets);
        this.c = aVar;
        aVar.j();
        io.flutter.embedding.engine.g.a a2 = b.a.a.d().a();
        this.f = new io.flutter.embedding.engine.j.b(aVar, flutterJNI);
        io.flutter.embedding.engine.j.c cVar2 = new io.flutter.embedding.engine.j.c(aVar);
        this.g = cVar2;
        this.h = new io.flutter.embedding.engine.j.d(aVar);
        this.i = new io.flutter.embedding.engine.j.e(aVar);
        f fVar = new f(aVar);
        this.j = fVar;
        this.k = new g(aVar);
        this.l = new h(aVar);
        this.n = new i(aVar);
        this.m = new k(aVar, z2);
        this.o = new l(aVar);
        this.p = new m(aVar);
        this.q = new n(aVar);
        if (a2 != null) {
            a2.f(cVar2);
        }
        b.a.c.b.a aVar2 = new b.a.c.b.a(context, fVar);
        this.e = aVar2;
        cVar = cVar == null ? d.b() : cVar;
        if (!flutterJNI.isAttached()) {
            cVar.g(context.getApplicationContext());
            cVar.d(context, strArr);
        }
        flutterJNI.addEngineLifecycleListener(this.t);
        flutterJNI.setPlatformViewsController(kVar);
        flutterJNI.setLocalizationPlugin(aVar2);
        flutterJNI.setDeferredComponentManager(d.a());
        if (!flutterJNI.isAttached()) {
            d();
        }
        this.f154b = new io.flutter.embedding.engine.renderer.a(flutterJNI);
        this.r = kVar;
        kVar.Q();
        this.d = new d(context.getApplicationContext(), this, cVar);
        if (z && cVar.c()) {
            io.flutter.embedding.engine.i.f.a.a(this);
        }
    }

    public b(Context context, String[] strArr, boolean z, boolean z2) {
        this(context, null, null, new io.flutter.plugin.platform.k(), strArr, z, z2);
    }

    private void d() {
        b.a.b.e("FlutterEngine", "Attaching to JNI.");
        this.f153a.attachToNative(false);
        if (!u()) {
            throw new RuntimeException("FlutterEngine failed to attach to its native Object reference.");
        }
    }

    private boolean u() {
        return this.f153a.isAttached();
    }

    public void e() {
        b.a.b.e("FlutterEngine", "Destroying.");
        for (InterfaceC0010b interfaceC0010b : this.s) {
            interfaceC0010b.b();
        }
        this.d.l();
        this.r.S();
        this.c.k();
        this.f153a.removeEngineLifecycleListener(this.t);
        this.f153a.setDeferredComponentManager(null);
        this.f153a.detachFromNativeAndReleaseResources();
        if (b.a.a.d().a() != null) {
            b.a.a.d().a().d();
            this.g.c(null);
        }
    }

    public io.flutter.embedding.engine.j.b f() {
        return this.f;
    }

    public io.flutter.embedding.engine.i.b.b g() {
        return this.d;
    }

    public io.flutter.embedding.engine.f.a h() {
        return this.c;
    }

    public io.flutter.embedding.engine.j.d i() {
        return this.h;
    }

    public io.flutter.embedding.engine.j.e j() {
        return this.i;
    }

    public b.a.c.b.a k() {
        return this.e;
    }

    public g l() {
        return this.k;
    }

    public h m() {
        return this.l;
    }

    public i n() {
        return this.n;
    }

    public io.flutter.plugin.platform.k o() {
        return this.r;
    }

    public io.flutter.embedding.engine.renderer.a p() {
        return this.f154b;
    }

    public k q() {
        return this.m;
    }

    public l r() {
        return this.o;
    }

    public m s() {
        return this.p;
    }

    public n t() {
        return this.q;
    }
}
