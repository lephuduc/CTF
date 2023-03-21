package androidx.lifecycle;

import androidx.lifecycle.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class h extends d {
    private final WeakReference<g> c;

    /* renamed from: a  reason: collision with root package name */
    private a.a.a.a.a<f, b> f46a = new a.a.a.a.a<>();
    private int d = 0;
    private boolean e = false;
    private boolean f = false;
    private ArrayList<d.b> g = new ArrayList<>();

    /* renamed from: b  reason: collision with root package name */
    private d.b f47b = d.b.INITIALIZED;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f48a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f49b;

        static {
            int[] iArr = new int[d.b.values().length];
            f49b = iArr;
            try {
                iArr[d.b.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f49b[d.b.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f49b[d.b.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f49b[d.b.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f49b[d.b.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr2 = new int[d.a.values().length];
            f48a = iArr2;
            try {
                iArr2[d.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f48a[d.a.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f48a[d.a.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f48a[d.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f48a[d.a.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f48a[d.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f48a[d.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        d.b f50a;

        /* renamed from: b  reason: collision with root package name */
        e f51b;

        void a(g gVar, d.a aVar) {
            d.b f = h.f(aVar);
            this.f50a = h.i(this.f50a, f);
            this.f51b.g(gVar, aVar);
            this.f50a = f;
        }
    }

    public h(g gVar) {
        this.c = new WeakReference<>(gVar);
    }

    private void c(g gVar) {
        Iterator<Map.Entry<f, b>> a2 = this.f46a.a();
        while (a2.hasNext() && !this.f) {
            Map.Entry<f, b> next = a2.next();
            b value = next.getValue();
            while (value.f50a.compareTo(this.f47b) > 0 && !this.f && this.f46a.contains(next.getKey())) {
                d.a d = d(value.f50a);
                l(f(d));
                value.a(gVar, d);
                k();
            }
        }
    }

    private static d.a d(d.b bVar) {
        int i = a.f49b[bVar.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            throw new IllegalArgumentException("Unexpected state value " + bVar);
                        }
                        throw new IllegalArgumentException();
                    }
                    return d.a.ON_PAUSE;
                }
                return d.a.ON_STOP;
            }
            return d.a.ON_DESTROY;
        }
        throw new IllegalArgumentException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void e(g gVar) {
        a.a.a.a.b<f, b>.d d = this.f46a.d();
        while (d.hasNext() && !this.f) {
            Map.Entry next = d.next();
            b bVar = (b) next.getValue();
            while (bVar.f50a.compareTo(this.f47b) < 0 && !this.f && this.f46a.contains(next.getKey())) {
                l(bVar.f50a);
                bVar.a(gVar, n(bVar.f50a));
                k();
            }
        }
    }

    static d.b f(d.a aVar) {
        switch (a.f48a[aVar.ordinal()]) {
            case 1:
            case 2:
                return d.b.CREATED;
            case 3:
            case 4:
                return d.b.STARTED;
            case 5:
                return d.b.RESUMED;
            case 6:
                return d.b.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + aVar);
        }
    }

    private boolean h() {
        if (this.f46a.size() == 0) {
            return true;
        }
        d.b bVar = this.f46a.b().getValue().f50a;
        d.b bVar2 = this.f46a.e().getValue().f50a;
        return bVar == bVar2 && this.f47b == bVar2;
    }

    static d.b i(d.b bVar, d.b bVar2) {
        return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
    }

    private void j(d.b bVar) {
        if (this.f47b == bVar) {
            return;
        }
        this.f47b = bVar;
        if (this.e || this.d != 0) {
            this.f = true;
            return;
        }
        this.e = true;
        m();
        this.e = false;
    }

    private void k() {
        ArrayList<d.b> arrayList = this.g;
        arrayList.remove(arrayList.size() - 1);
    }

    private void l(d.b bVar) {
        this.g.add(bVar);
    }

    private void m() {
        g gVar = this.c.get();
        if (gVar == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
        }
        while (true) {
            boolean h = h();
            this.f = false;
            if (h) {
                return;
            }
            if (this.f47b.compareTo(this.f46a.b().getValue().f50a) < 0) {
                c(gVar);
            }
            Map.Entry<f, b> e = this.f46a.e();
            if (!this.f && e != null && this.f47b.compareTo(e.getValue().f50a) > 0) {
                e(gVar);
            }
        }
    }

    private static d.a n(d.b bVar) {
        int i = a.f49b[bVar.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return d.a.ON_START;
            }
            if (i == 3) {
                return d.a.ON_RESUME;
            }
            if (i == 4) {
                throw new IllegalArgumentException();
            }
            if (i != 5) {
                throw new IllegalArgumentException("Unexpected state value " + bVar);
            }
        }
        return d.a.ON_CREATE;
    }

    @Override // androidx.lifecycle.d
    public d.b a() {
        return this.f47b;
    }

    @Override // androidx.lifecycle.d
    public void b(f fVar) {
        this.f46a.f(fVar);
    }

    public void g(d.a aVar) {
        j(f(aVar));
    }
}
