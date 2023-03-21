package io.flutter.embedding.engine.j;

import b.a.c.a.i;
import b.a.c.a.p;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private final b.a.c.a.i f226a;

    /* renamed from: b  reason: collision with root package name */
    private e f227b;
    private final i.c c;

    /* loaded from: classes.dex */
    class a implements i.c {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: io.flutter.embedding.engine.j.j$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0017a implements Runnable {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ i.d f229a;

            RunnableC0017a(a aVar, i.d dVar) {
                this.f229a = dVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f229a.b(null);
            }
        }

        a() {
        }

        private void b(b.a.c.a.h hVar, i.d dVar) {
            try {
                j.this.f227b.a(((Integer) hVar.a()).intValue());
                dVar.b(null);
            } catch (IllegalStateException e) {
                dVar.a("error", j.c(e), null);
            }
        }

        private void c(b.a.c.a.h hVar, i.d dVar) {
            Map map = (Map) hVar.a();
            boolean z = map.containsKey("hybrid") && ((Boolean) map.get("hybrid")).booleanValue();
            b bVar = new b(((Integer) map.get("id")).intValue(), (String) map.get("viewType"), z ? 0.0d : ((Double) map.get("width")).doubleValue(), z ? 0.0d : ((Double) map.get("height")).doubleValue(), ((Integer) map.get("direction")).intValue(), map.containsKey("params") ? ByteBuffer.wrap((byte[]) map.get("params")) : null);
            try {
                if (z) {
                    j.this.f227b.h(bVar);
                    dVar.b(null);
                } else {
                    dVar.b(Long.valueOf(j.this.f227b.f(bVar)));
                }
            } catch (IllegalStateException e) {
                dVar.a("error", j.c(e), null);
            }
        }

        private void d(b.a.c.a.h hVar, i.d dVar) {
            Map map = (Map) hVar.a();
            int intValue = ((Integer) map.get("id")).intValue();
            try {
                if (map.containsKey("hybrid") && ((Boolean) map.get("hybrid")).booleanValue()) {
                    j.this.f227b.d(intValue);
                } else {
                    j.this.f227b.e(intValue);
                }
                dVar.b(null);
            } catch (IllegalStateException e) {
                dVar.a("error", j.c(e), null);
            }
        }

        private void e(b.a.c.a.h hVar, i.d dVar) {
            Map map = (Map) hVar.a();
            try {
                j.this.f227b.c(new c(((Integer) map.get("id")).intValue(), ((Double) map.get("width")).doubleValue(), ((Double) map.get("height")).doubleValue()), new RunnableC0017a(this, dVar));
            } catch (IllegalStateException e) {
                dVar.a("error", j.c(e), null);
            }
        }

        private void f(b.a.c.a.h hVar, i.d dVar) {
            Map map = (Map) hVar.a();
            try {
                j.this.f227b.g(((Integer) map.get("id")).intValue(), ((Integer) map.get("direction")).intValue());
                dVar.b(null);
            } catch (IllegalStateException e) {
                dVar.a("error", j.c(e), null);
            }
        }

        private void g(b.a.c.a.h hVar, i.d dVar) {
            try {
                j.this.f227b.b(((Boolean) hVar.a()).booleanValue());
                dVar.b(null);
            } catch (IllegalStateException e) {
                dVar.a("error", j.c(e), null);
            }
        }

        private void h(b.a.c.a.h hVar, i.d dVar) {
            i.d dVar2;
            List list = (List) hVar.a();
            try {
                j.this.f227b.i(new d(((Integer) list.get(0)).intValue(), (Number) list.get(1), (Number) list.get(2), ((Integer) list.get(3)).intValue(), ((Integer) list.get(4)).intValue(), list.get(5), list.get(6), ((Integer) list.get(7)).intValue(), ((Integer) list.get(8)).intValue(), (float) ((Double) list.get(9)).doubleValue(), (float) ((Double) list.get(10)).doubleValue(), ((Integer) list.get(11)).intValue(), ((Integer) list.get(12)).intValue(), ((Integer) list.get(13)).intValue(), ((Integer) list.get(14)).intValue(), ((Number) list.get(15)).longValue()));
                dVar2 = dVar;
                try {
                    dVar2.b(null);
                } catch (IllegalStateException e) {
                    e = e;
                    dVar2.a("error", j.c(e), null);
                }
            } catch (IllegalStateException e2) {
                e = e2;
                dVar2 = dVar;
            }
        }

        @Override // b.a.c.a.i.c
        public void a(b.a.c.a.h hVar, i.d dVar) {
            if (j.this.f227b == null) {
                return;
            }
            b.a.b.e("PlatformViewsChannel", "Received '" + hVar.f74a + "' message.");
            String str = hVar.f74a;
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1352294148:
                    if (str.equals("create")) {
                        c = 0;
                        break;
                    }
                    break;
                case -934437708:
                    if (str.equals("resize")) {
                        c = 1;
                        break;
                    }
                    break;
                case -756050293:
                    if (str.equals("clearFocus")) {
                        c = 2;
                        break;
                    }
                    break;
                case -308988850:
                    if (str.equals("synchronizeToNativeViewHierarchy")) {
                        c = 3;
                        break;
                    }
                    break;
                case 110550847:
                    if (str.equals("touch")) {
                        c = 4;
                        break;
                    }
                    break;
                case 576796989:
                    if (str.equals("setDirection")) {
                        c = 5;
                        break;
                    }
                    break;
                case 1671767583:
                    if (str.equals("dispose")) {
                        c = 6;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    c(hVar, dVar);
                    return;
                case 1:
                    e(hVar, dVar);
                    return;
                case 2:
                    b(hVar, dVar);
                    return;
                case 3:
                    g(hVar, dVar);
                    return;
                case 4:
                    h(hVar, dVar);
                    return;
                case 5:
                    f(hVar, dVar);
                    return;
                case 6:
                    d(hVar, dVar);
                    return;
                default:
                    dVar.c();
                    return;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final int f230a;

        /* renamed from: b  reason: collision with root package name */
        public final String f231b;
        public final double c;
        public final double d;
        public final int e;
        public final ByteBuffer f;

        public b(int i, String str, double d, double d2, int i2, ByteBuffer byteBuffer) {
            this.f230a = i;
            this.f231b = str;
            this.c = d;
            this.d = d2;
            this.e = i2;
            this.f = byteBuffer;
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final int f232a;

        /* renamed from: b  reason: collision with root package name */
        public final double f233b;
        public final double c;

        public c(int i, double d, double d2) {
            this.f232a = i;
            this.f233b = d;
            this.c = d2;
        }
    }

    /* loaded from: classes.dex */
    public static class d {

        /* renamed from: a  reason: collision with root package name */
        public final int f234a;

        /* renamed from: b  reason: collision with root package name */
        public final Number f235b;
        public final Number c;
        public final int d;
        public final int e;
        public final Object f;
        public final Object g;
        public final int h;
        public final int i;
        public final float j;
        public final float k;
        public final int l;
        public final int m;
        public final int n;
        public final int o;
        public final long p;

        public d(int i, Number number, Number number2, int i2, int i3, Object obj, Object obj2, int i4, int i5, float f, float f2, int i6, int i7, int i8, int i9, long j) {
            this.f234a = i;
            this.f235b = number;
            this.c = number2;
            this.d = i2;
            this.e = i3;
            this.f = obj;
            this.g = obj2;
            this.h = i4;
            this.i = i5;
            this.j = f;
            this.k = f2;
            this.l = i6;
            this.m = i7;
            this.n = i8;
            this.o = i9;
            this.p = j;
        }
    }

    /* loaded from: classes.dex */
    public interface e {
        void a(int i);

        void b(boolean z);

        void c(c cVar, Runnable runnable);

        void d(int i);

        void e(int i);

        long f(b bVar);

        void g(int i, int i2);

        void h(b bVar);

        void i(d dVar);
    }

    public j(io.flutter.embedding.engine.f.a aVar) {
        a aVar2 = new a();
        this.c = aVar2;
        b.a.c.a.i iVar = new b.a.c.a.i(aVar, "flutter/platform_views", p.f86b);
        this.f226a = iVar;
        iVar.e(aVar2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(Exception exc) {
        StringWriter stringWriter = new StringWriter();
        exc.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public void d(int i) {
        b.a.c.a.i iVar = this.f226a;
        if (iVar == null) {
            return;
        }
        iVar.c("viewFocused", Integer.valueOf(i));
    }

    public void e(e eVar) {
        this.f227b = eVar;
    }
}
