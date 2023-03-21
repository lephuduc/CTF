package io.flutter.embedding.engine.j;

/* loaded from: classes.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    public final b.a.c.a.i f202a;

    public h(io.flutter.embedding.engine.f.a aVar) {
        this.f202a = new b.a.c.a.i(aVar, "flutter/navigation", b.a.c.a.e.f73a);
    }

    public void a() {
        b.a.b.e("NavigationChannel", "Sending message to pop route.");
        this.f202a.c("popRoute", null);
    }

    public void b(String str) {
        b.a.b.e("NavigationChannel", "Sending message to push route '" + str + "'");
        this.f202a.c("pushRoute", str);
    }

    public void c(String str) {
        b.a.b.e("NavigationChannel", "Sending message to set initial route to '" + str + "'");
        this.f202a.c("setInitialRoute", str);
    }
}
