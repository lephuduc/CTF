package b.a;

import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.embedding.engine.h.c;

/* loaded from: classes.dex */
public final class a {
    private static a d;

    /* renamed from: a  reason: collision with root package name */
    private c f58a;

    /* renamed from: b  reason: collision with root package name */
    private io.flutter.embedding.engine.g.a f59b;
    private FlutterJNI.c c;

    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        private c f60a;

        /* renamed from: b  reason: collision with root package name */
        private io.flutter.embedding.engine.g.a f61b;
        private FlutterJNI.c c;

        private void b() {
            if (this.c == null) {
                this.c = new FlutterJNI.c();
            }
            if (this.f60a == null) {
                this.f60a = new c(this.c.a());
            }
        }

        public a a() {
            b();
            return new a(this.f60a, this.f61b, this.c);
        }
    }

    private a(c cVar, io.flutter.embedding.engine.g.a aVar, FlutterJNI.c cVar2) {
        this.f58a = cVar;
        this.f59b = aVar;
        this.c = cVar2;
    }

    public static a d() {
        if (d == null) {
            d = new b().a();
        }
        return d;
    }

    public io.flutter.embedding.engine.g.a a() {
        return this.f59b;
    }

    public c b() {
        return this.f58a;
    }

    public FlutterJNI.c c() {
        return this.c;
    }
}
