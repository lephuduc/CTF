package io.flutter.embedding.engine.j;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    public final b.a.c.a.a<Object> f241a;

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final b.a.c.a.a<Object> f242a;

        /* renamed from: b  reason: collision with root package name */
        private Map<String, Object> f243b = new HashMap();

        a(b.a.c.a.a<Object> aVar) {
            this.f242a = aVar;
        }

        public void a() {
            b.a.b.e("SettingsChannel", "Sending message: \ntextScaleFactor: " + this.f243b.get("textScaleFactor") + "\nalwaysUse24HourFormat: " + this.f243b.get("alwaysUse24HourFormat") + "\nplatformBrightness: " + this.f243b.get("platformBrightness"));
            this.f242a.c(this.f243b);
        }

        public a b(b bVar) {
            this.f243b.put("platformBrightness", bVar.f245a);
            return this;
        }

        public a c(float f) {
            this.f243b.put("textScaleFactor", Float.valueOf(f));
            return this;
        }

        public a d(boolean z) {
            this.f243b.put("alwaysUse24HourFormat", Boolean.valueOf(z));
            return this;
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        light("light"),
        dark("dark");
        

        /* renamed from: a  reason: collision with root package name */
        public String f245a;

        b(String str) {
            this.f245a = str;
        }
    }

    public l(io.flutter.embedding.engine.f.a aVar) {
        this.f241a = new b.a.c.a.a<>(aVar, "flutter/settings", b.a.c.a.d.f72a);
    }

    public a a() {
        return new a(this.f241a);
    }
}
