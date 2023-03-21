package io.flutter.embedding.engine;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import b.a.c.a.k;
import b.a.c.a.l;
import b.a.c.a.m;
import b.a.c.a.n;
import io.flutter.embedding.engine.i.a;
import io.flutter.embedding.engine.i.b.c;
import io.flutter.embedding.engine.plugins.lifecycle.HiddenLifecycleReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d implements io.flutter.embedding.engine.i.b.b {

    /* renamed from: b  reason: collision with root package name */
    private final io.flutter.embedding.engine.b f159b;
    private final a.b c;
    @Deprecated
    private Activity e;
    private io.flutter.embedding.android.d<Activity> f;
    private c g;
    private Service j;
    private BroadcastReceiver l;
    private ContentProvider n;

    /* renamed from: a  reason: collision with root package name */
    private final Map<Class<? extends io.flutter.embedding.engine.i.a>, io.flutter.embedding.engine.i.a> f158a = new HashMap();
    private final Map<Class<? extends io.flutter.embedding.engine.i.a>, io.flutter.embedding.engine.i.b.a> d = new HashMap();
    private boolean h = false;
    private final Map<Class<? extends io.flutter.embedding.engine.i.a>, io.flutter.embedding.engine.i.e.a> i = new HashMap();
    private final Map<Class<? extends io.flutter.embedding.engine.i.a>, io.flutter.embedding.engine.i.c.a> k = new HashMap();
    private final Map<Class<? extends io.flutter.embedding.engine.i.a>, io.flutter.embedding.engine.i.d.a> m = new HashMap();

    /* loaded from: classes.dex */
    private static class b implements a.InterfaceC0014a {
        private b(io.flutter.embedding.engine.h.c cVar) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c implements io.flutter.embedding.engine.i.b.c {

        /* renamed from: a  reason: collision with root package name */
        private final Set<m> f160a = new HashSet();

        /* renamed from: b  reason: collision with root package name */
        private final Set<k> f161b = new HashSet();
        private final Set<l> c = new HashSet();
        private final Set<n> d = new HashSet();
        private final Set<c.a> e = new HashSet();

        public c(Activity activity, androidx.lifecycle.d dVar) {
            new HiddenLifecycleReference(dVar);
        }

        boolean a(int i, int i2, Intent intent) {
            boolean z;
            Iterator it = new HashSet(this.f161b).iterator();
            while (true) {
                while (it.hasNext()) {
                    z = ((k) it.next()).c(i, i2, intent) || z;
                }
                return z;
            }
        }

        void b(Intent intent) {
            for (l lVar : this.c) {
                lVar.d(intent);
            }
        }

        boolean c(int i, String[] strArr, int[] iArr) {
            boolean z;
            while (true) {
                for (m mVar : this.f160a) {
                    z = mVar.b(i, strArr, iArr) || z;
                }
                return z;
            }
        }

        void d(Bundle bundle) {
            for (c.a aVar : this.e) {
                aVar.a(bundle);
            }
        }

        void e(Bundle bundle) {
            for (c.a aVar : this.e) {
                aVar.e(bundle);
            }
        }

        void f() {
            for (n nVar : this.d) {
                nVar.f();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(Context context, io.flutter.embedding.engine.b bVar, io.flutter.embedding.engine.h.c cVar) {
        this.f159b = bVar;
        this.c = new a.b(context, bVar, bVar.h(), bVar.p(), bVar.o().I(), new b(cVar));
    }

    private void j(Activity activity, androidx.lifecycle.d dVar) {
        this.g = new c(activity, dVar);
        this.f159b.o().u(activity, this.f159b.p(), this.f159b.h());
        for (io.flutter.embedding.engine.i.b.a aVar : this.d.values()) {
            if (this.h) {
                aVar.c(this.g);
            } else {
                aVar.b(this.g);
            }
        }
        this.h = false;
    }

    private Activity k() {
        io.flutter.embedding.android.d<Activity> dVar = this.f;
        return dVar != null ? dVar.b() : this.e;
    }

    private void m() {
        this.f159b.o().C();
        this.f = null;
        this.e = null;
        this.g = null;
    }

    private void n() {
        if (r()) {
            h();
        } else if (u()) {
            q();
        } else if (s()) {
            o();
        } else if (t()) {
            p();
        }
    }

    private boolean r() {
        return (this.e == null && this.f == null) ? false : true;
    }

    private boolean s() {
        return this.l != null;
    }

    private boolean t() {
        return this.n != null;
    }

    private boolean u() {
        return this.j != null;
    }

    @Override // io.flutter.embedding.engine.i.b.b
    public void a(Bundle bundle) {
        b.a.b.e("FlutterEngineCxnRegstry", "Forwarding onRestoreInstanceState() to plugins.");
        if (r()) {
            this.g.d(bundle);
        } else {
            b.a.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onRestoreInstanceState, but no Activity was attached.");
        }
    }

    @Override // io.flutter.embedding.engine.i.b.b
    public boolean b(int i, String[] strArr, int[] iArr) {
        b.a.b.e("FlutterEngineCxnRegstry", "Forwarding onRequestPermissionsResult() to plugins.");
        if (r()) {
            return this.g.c(i, strArr, iArr);
        }
        b.a.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onRequestPermissionsResult, but no Activity was attached.");
        return false;
    }

    @Override // io.flutter.embedding.engine.i.b.b
    public boolean c(int i, int i2, Intent intent) {
        b.a.b.e("FlutterEngineCxnRegstry", "Forwarding onActivityResult() to plugins.");
        if (r()) {
            return this.g.a(i, i2, intent);
        }
        b.a.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onActivityResult, but no Activity was attached.");
        return false;
    }

    @Override // io.flutter.embedding.engine.i.b.b
    public void d(Intent intent) {
        b.a.b.e("FlutterEngineCxnRegstry", "Forwarding onNewIntent() to plugins.");
        if (r()) {
            this.g.b(intent);
        } else {
            b.a.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onNewIntent, but no Activity was attached.");
        }
    }

    @Override // io.flutter.embedding.engine.i.b.b
    public void e(Bundle bundle) {
        b.a.b.e("FlutterEngineCxnRegstry", "Forwarding onSaveInstanceState() to plugins.");
        if (r()) {
            this.g.e(bundle);
        } else {
            b.a.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onSaveInstanceState, but no Activity was attached.");
        }
    }

    @Override // io.flutter.embedding.engine.i.b.b
    public void f() {
        b.a.b.e("FlutterEngineCxnRegstry", "Forwarding onUserLeaveHint() to plugins.");
        if (r()) {
            this.g.f();
        } else {
            b.a.b.b("FlutterEngineCxnRegstry", "Attempted to notify ActivityAware plugins of onUserLeaveHint, but no Activity was attached.");
        }
    }

    @Override // io.flutter.embedding.engine.i.b.b
    public void g(io.flutter.embedding.android.d<Activity> dVar, androidx.lifecycle.d dVar2) {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Attaching to an exclusive Activity: ");
        sb.append(dVar.b());
        if (r()) {
            str = " evicting previous activity " + k();
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(".");
        sb.append(this.h ? " This is after a config change." : "");
        b.a.b.e("FlutterEngineCxnRegstry", sb.toString());
        io.flutter.embedding.android.d<Activity> dVar3 = this.f;
        if (dVar3 != null) {
            dVar3.a();
        }
        n();
        if (this.e != null) {
            throw new AssertionError("Only activity or exclusiveActivity should be set");
        }
        this.f = dVar;
        j(dVar.b(), dVar2);
    }

    @Override // io.flutter.embedding.engine.i.b.b
    public void h() {
        if (!r()) {
            b.a.b.b("FlutterEngineCxnRegstry", "Attempted to detach plugins from an Activity when no Activity was attached.");
            return;
        }
        b.a.b.e("FlutterEngineCxnRegstry", "Detaching from an Activity: " + k());
        for (io.flutter.embedding.engine.i.b.a aVar : this.d.values()) {
            aVar.a();
        }
        m();
    }

    @Override // io.flutter.embedding.engine.i.b.b
    public void i() {
        if (!r()) {
            b.a.b.b("FlutterEngineCxnRegstry", "Attempted to detach plugins from an Activity when no Activity was attached.");
            return;
        }
        b.a.b.e("FlutterEngineCxnRegstry", "Detaching from an Activity for config changes: " + k());
        this.h = true;
        for (io.flutter.embedding.engine.i.b.a aVar : this.d.values()) {
            aVar.d();
        }
        m();
    }

    public void l() {
        b.a.b.e("FlutterEngineCxnRegstry", "Destroying.");
        n();
        x();
    }

    public void o() {
        if (!s()) {
            b.a.b.b("FlutterEngineCxnRegstry", "Attempted to detach plugins from a BroadcastReceiver when no BroadcastReceiver was attached.");
            return;
        }
        b.a.b.e("FlutterEngineCxnRegstry", "Detaching from BroadcastReceiver: " + this.l);
        for (io.flutter.embedding.engine.i.c.a aVar : this.k.values()) {
            aVar.a();
        }
    }

    public void p() {
        if (!t()) {
            b.a.b.b("FlutterEngineCxnRegstry", "Attempted to detach plugins from a ContentProvider when no ContentProvider was attached.");
            return;
        }
        b.a.b.e("FlutterEngineCxnRegstry", "Detaching from ContentProvider: " + this.n);
        for (io.flutter.embedding.engine.i.d.a aVar : this.m.values()) {
            aVar.a();
        }
    }

    public void q() {
        if (!u()) {
            b.a.b.b("FlutterEngineCxnRegstry", "Attempted to detach plugins from a Service when no Service was attached.");
            return;
        }
        b.a.b.e("FlutterEngineCxnRegstry", "Detaching from a Service: " + this.j);
        for (io.flutter.embedding.engine.i.e.a aVar : this.i.values()) {
            aVar.a();
        }
        this.j = null;
    }

    public void v(Class<? extends io.flutter.embedding.engine.i.a> cls) {
        io.flutter.embedding.engine.i.a aVar = this.f158a.get(cls);
        if (aVar != null) {
            b.a.b.e("FlutterEngineCxnRegstry", "Removing plugin: " + aVar);
            if (aVar instanceof io.flutter.embedding.engine.i.b.a) {
                if (r()) {
                    ((io.flutter.embedding.engine.i.b.a) aVar).a();
                }
                this.d.remove(cls);
            }
            if (aVar instanceof io.flutter.embedding.engine.i.e.a) {
                if (u()) {
                    ((io.flutter.embedding.engine.i.e.a) aVar).a();
                }
                this.i.remove(cls);
            }
            if (aVar instanceof io.flutter.embedding.engine.i.c.a) {
                if (s()) {
                    ((io.flutter.embedding.engine.i.c.a) aVar).a();
                }
                this.k.remove(cls);
            }
            if (aVar instanceof io.flutter.embedding.engine.i.d.a) {
                if (t()) {
                    ((io.flutter.embedding.engine.i.d.a) aVar).a();
                }
                this.m.remove(cls);
            }
            aVar.a(this.c);
            this.f158a.remove(cls);
        }
    }

    public void w(Set<Class<? extends io.flutter.embedding.engine.i.a>> set) {
        for (Class<? extends io.flutter.embedding.engine.i.a> cls : set) {
            v(cls);
        }
    }

    public void x() {
        w(new HashSet(this.f158a.keySet()));
        this.f158a.clear();
    }
}
