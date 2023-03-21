package io.flutter.embedding.android;

import android.view.KeyEvent;
import android.view.View;
import java.util.HashSet;

/* loaded from: classes.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    protected final c[] f138a;

    /* renamed from: b  reason: collision with root package name */
    private final HashSet<KeyEvent> f139b = new HashSet<>();
    private final io.flutter.plugin.editing.d c;
    private final View d;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class b {

        /* renamed from: a  reason: collision with root package name */
        final KeyEvent f140a;

        /* renamed from: b  reason: collision with root package name */
        int f141b;
        boolean c = false;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public class a implements c.a {

            /* renamed from: a  reason: collision with root package name */
            boolean f142a;

            private a() {
                this.f142a = false;
            }

            @Override // io.flutter.embedding.android.m.c.a
            public void a(Boolean bool) {
                if (this.f142a) {
                    throw new IllegalStateException("The onKeyEventHandledCallback should be called exactly once.");
                }
                this.f142a = true;
                b bVar = b.this;
                bVar.f141b--;
                bVar.c = bool.booleanValue() | bVar.c;
                b bVar2 = b.this;
                if (bVar2.f141b != 0 || bVar2.c) {
                    return;
                }
                m.this.d(bVar2.f140a);
            }
        }

        b(KeyEvent keyEvent) {
            this.f141b = m.this.f138a.length;
            this.f140a = keyEvent;
        }

        public c.a a() {
            return new a();
        }
    }

    /* loaded from: classes.dex */
    interface c {

        /* loaded from: classes.dex */
        public interface a {
            void a(Boolean bool);
        }

        void a(KeyEvent keyEvent, a aVar);
    }

    public m(View view, io.flutter.plugin.editing.d dVar, c[] cVarArr) {
        this.d = view;
        this.c = dVar;
        this.f138a = cVarArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(KeyEvent keyEvent) {
        if (this.c.r(keyEvent) || this.d == null) {
            return;
        }
        this.f139b.add(keyEvent);
        this.d.getRootView().dispatchKeyEvent(keyEvent);
        if (this.f139b.remove(keyEvent)) {
            b.a.b.f("KeyboardManager", "A redispatched key event was consumed before reaching KeyboardManager");
        }
    }

    public void b() {
        int size = this.f139b.size();
        if (size > 0) {
            b.a.b.f("KeyboardManager", "A KeyboardManager was destroyed with " + String.valueOf(size) + " unhandled redispatch event(s).");
        }
    }

    public boolean c(KeyEvent keyEvent) {
        if (this.f139b.remove(keyEvent)) {
            return false;
        }
        if (this.f138a.length <= 0) {
            d(keyEvent);
            return true;
        }
        b bVar = new b(keyEvent);
        for (c cVar : this.f138a) {
            cVar.a(keyEvent, bVar.a());
        }
        return true;
    }
}
