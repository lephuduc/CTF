package io.flutter.embedding.android;

import android.util.LongSparseArray;
import android.view.MotionEvent;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public final class n {
    private static n c;

    /* renamed from: a  reason: collision with root package name */
    private final LongSparseArray<MotionEvent> f144a = new LongSparseArray<>();

    /* renamed from: b  reason: collision with root package name */
    private final PriorityQueue<Long> f145b = new PriorityQueue<>();

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: b  reason: collision with root package name */
        private static final AtomicLong f146b = new AtomicLong(0);

        /* renamed from: a  reason: collision with root package name */
        private final long f147a;

        private a(long j) {
            this.f147a = j;
        }

        public static a b() {
            return c(f146b.incrementAndGet());
        }

        public static a c(long j) {
            return new a(j);
        }

        public long d() {
            return this.f147a;
        }
    }

    private n() {
    }

    public static n a() {
        if (c == null) {
            c = new n();
        }
        return c;
    }

    public MotionEvent b(a aVar) {
        while (!this.f145b.isEmpty() && this.f145b.peek().longValue() < aVar.f147a) {
            this.f144a.remove(this.f145b.poll().longValue());
        }
        if (!this.f145b.isEmpty() && this.f145b.peek().longValue() == aVar.f147a) {
            this.f145b.poll();
        }
        MotionEvent motionEvent = this.f144a.get(aVar.f147a);
        this.f144a.remove(aVar.f147a);
        return motionEvent;
    }

    public a c(MotionEvent motionEvent) {
        a b2 = a.b();
        this.f144a.put(b2.f147a, MotionEvent.obtain(motionEvent));
        this.f145b.add(Long.valueOf(b2.f147a));
        return b2;
    }
}
