package io.flutter.embedding.engine.j;

import android.os.Build;
import android.view.InputDevice;
import android.view.KeyEvent;
import b.a.c.a.a;
import io.flutter.embedding.engine.j.d;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public final b.a.c.a.a<Object> f192a;

    /* loaded from: classes.dex */
    public interface a {
        void a(boolean z);
    }

    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public final KeyEvent f193a;

        /* renamed from: b  reason: collision with root package name */
        public final Character f194b;

        public b(KeyEvent keyEvent, Character ch) {
            this.f193a = keyEvent;
            this.f194b = ch;
        }
    }

    public d(b.a.c.a.b bVar) {
        this.f192a = new b.a.c.a.a<>(bVar, "flutter/keyevent", b.a.c.a.d.f72a);
    }

    private static a.e<Object> a(final a aVar) {
        return new a.e() { // from class: io.flutter.embedding.engine.j.a
            @Override // b.a.c.a.a.e
            public final void a(Object obj) {
                d.c(d.a.this, obj);
            }
        };
    }

    private Map<String, Object> b(b bVar, boolean z) {
        int i;
        HashMap hashMap = new HashMap();
        hashMap.put("type", z ? "keyup" : "keydown");
        hashMap.put("keymap", "android");
        hashMap.put("flags", Integer.valueOf(bVar.f193a.getFlags()));
        int i2 = 0;
        hashMap.put("plainCodePoint", Integer.valueOf(bVar.f193a.getUnicodeChar(0)));
        hashMap.put("codePoint", Integer.valueOf(bVar.f193a.getUnicodeChar()));
        hashMap.put("keyCode", Integer.valueOf(bVar.f193a.getKeyCode()));
        hashMap.put("scanCode", Integer.valueOf(bVar.f193a.getScanCode()));
        hashMap.put("metaState", Integer.valueOf(bVar.f193a.getMetaState()));
        Character ch = bVar.f194b;
        if (ch != null) {
            hashMap.put("character", ch.toString());
        }
        hashMap.put("source", Integer.valueOf(bVar.f193a.getSource()));
        InputDevice device = InputDevice.getDevice(bVar.f193a.getDeviceId());
        if (device == null || Build.VERSION.SDK_INT < 19) {
            i = 0;
        } else {
            i2 = device.getVendorId();
            i = device.getProductId();
        }
        hashMap.put("vendorId", Integer.valueOf(i2));
        hashMap.put("productId", Integer.valueOf(i));
        hashMap.put("deviceId", Integer.valueOf(bVar.f193a.getDeviceId()));
        hashMap.put("repeatCount", Integer.valueOf(bVar.f193a.getRepeatCount()));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void c(a aVar, Object obj) {
        boolean z = false;
        if (obj != null) {
            try {
                z = ((JSONObject) obj).getBoolean("handled");
            } catch (JSONException e) {
                b.a.b.b("KeyEventChannel", "Unable to unpack JSON message: " + e);
            }
        }
        aVar.a(z);
    }

    public void d(b bVar, boolean z, a aVar) {
        this.f192a.d(b(bVar, z), a(aVar));
    }
}
