package io.flutter.embedding.engine;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class c {

    /* renamed from: b  reason: collision with root package name */
    private static c f156b;

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, b> f157a = new HashMap();

    c() {
    }

    public static c b() {
        if (f156b == null) {
            f156b = new c();
        }
        return f156b;
    }

    public b a(String str) {
        return this.f157a.get(str);
    }

    public void c(String str, b bVar) {
        if (bVar != null) {
            this.f157a.put(str, bVar);
        } else {
            this.f157a.remove(str);
        }
    }

    public void d(String str) {
        c(str, null);
    }
}
