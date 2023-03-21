package io.flutter.embedding.engine.j;

import java.util.HashMap;

/* loaded from: classes.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    public final b.a.c.a.a<Object> f246a;

    public m(io.flutter.embedding.engine.f.a aVar) {
        this.f246a = new b.a.c.a.a<>(aVar, "flutter/system", b.a.c.a.d.f72a);
    }

    public void a() {
        b.a.b.e("SystemChannel", "Sending memory pressure warning to Flutter.");
        HashMap hashMap = new HashMap(1);
        hashMap.put("type", "memoryPressure");
        this.f246a.c(hashMap);
    }
}
