package io.flutter.embedding.engine.j;

import b.a.c.a.q;

/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public final b.a.c.a.a<String> f195a;

    public e(io.flutter.embedding.engine.f.a aVar) {
        this.f195a = new b.a.c.a.a<>(aVar, "flutter/lifecycle", q.f89b);
    }

    public void a() {
        b.a.b.e("LifecycleChannel", "Sending AppLifecycleState.detached message.");
        this.f195a.c("AppLifecycleState.detached");
    }

    public void b() {
        b.a.b.e("LifecycleChannel", "Sending AppLifecycleState.inactive message.");
        this.f195a.c("AppLifecycleState.inactive");
    }

    public void c() {
        b.a.b.e("LifecycleChannel", "Sending AppLifecycleState.paused message.");
        this.f195a.c("AppLifecycleState.paused");
    }

    public void d() {
        b.a.b.e("LifecycleChannel", "Sending AppLifecycleState.resumed message.");
        this.f195a.c("AppLifecycleState.resumed");
    }
}
