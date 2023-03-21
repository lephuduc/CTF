package io.flutter.embedding.engine.j;

import b.a.c.a.a;
import b.a.c.a.o;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.view.c;
import java.util.HashMap;

/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public final b.a.c.a.a<Object> f186a;

    /* renamed from: b  reason: collision with root package name */
    public final FlutterJNI f187b;
    private InterfaceC0015b c;
    private final a.d<Object> d;

    /* loaded from: classes.dex */
    class a implements a.d<Object> {
        a() {
        }

        @Override // b.a.c.a.a.d
        public void a(Object obj, a.e<Object> eVar) {
            if (b.this.c == null) {
                return;
            }
            HashMap hashMap = (HashMap) obj;
            String str = (String) hashMap.get("type");
            HashMap hashMap2 = (HashMap) hashMap.get("data");
            b.a.b.e("AccessibilityChannel", "Received " + str + " message.");
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case -1140076541:
                    if (str.equals("tooltip")) {
                        c = 0;
                        break;
                    }
                    break;
                case -649620375:
                    if (str.equals("announce")) {
                        c = 1;
                        break;
                    }
                    break;
                case 114595:
                    if (str.equals("tap")) {
                        c = 2;
                        break;
                    }
                    break;
                case 114203431:
                    if (str.equals("longPress")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    String str2 = (String) hashMap2.get("message");
                    if (str2 != null) {
                        b.this.c.c(str2);
                        break;
                    }
                    break;
                case 1:
                    String str3 = (String) hashMap2.get("message");
                    if (str3 != null) {
                        b.this.c.f(str3);
                        break;
                    }
                    break;
                case 2:
                    Integer num = (Integer) hashMap.get("nodeId");
                    if (num != null) {
                        b.this.c.e(num.intValue());
                        break;
                    }
                    break;
                case 3:
                    Integer num2 = (Integer) hashMap.get("nodeId");
                    if (num2 != null) {
                        b.this.c.d(num2.intValue());
                        break;
                    }
                    break;
            }
            eVar.a(null);
        }
    }

    /* renamed from: io.flutter.embedding.engine.j.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0015b extends FlutterJNI.a {
        void c(String str);

        void d(int i);

        void e(int i);

        void f(String str);
    }

    public b(io.flutter.embedding.engine.f.a aVar, FlutterJNI flutterJNI) {
        a aVar2 = new a();
        this.d = aVar2;
        b.a.c.a.a<Object> aVar3 = new b.a.c.a.a<>(aVar, "flutter/accessibility", o.f84a);
        this.f186a = aVar3;
        aVar3.e(aVar2);
        this.f187b = flutterJNI;
    }

    public void b(int i, c.g gVar) {
        this.f187b.dispatchSemanticsAction(i, gVar);
    }

    public void c(int i, c.g gVar, Object obj) {
        this.f187b.dispatchSemanticsAction(i, gVar, obj);
    }

    public void d() {
        this.f187b.setSemanticsEnabled(false);
    }

    public void e() {
        this.f187b.setSemanticsEnabled(true);
    }

    public void f(int i) {
        this.f187b.setAccessibilityFeatures(i);
    }

    public void g(InterfaceC0015b interfaceC0015b) {
        this.c = interfaceC0015b;
        this.f187b.setAccessibilityDelegate(interfaceC0015b);
    }
}
