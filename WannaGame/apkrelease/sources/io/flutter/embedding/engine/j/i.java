package io.flutter.embedding.engine.j;

import b.a.c.a.i;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    public final b.a.c.a.i f203a;

    /* renamed from: b  reason: collision with root package name */
    private h f204b;
    final i.c c;

    /* loaded from: classes.dex */
    class a implements i.c {
        a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:62:0x011f A[Catch: JSONException -> 0x0216, TryCatch #9 {JSONException -> 0x0216, blocks: (B:6:0x002c, B:7:0x0030, B:50:0x00c3, B:51:0x00c8, B:52:0x00e1, B:53:0x00f0, B:54:0x00f5, B:56:0x00f9, B:60:0x0113, B:62:0x011f, B:58:0x00fe, B:63:0x012c, B:72:0x0158, B:73:0x0162, B:101:0x01e2, B:106:0x01fc, B:70:0x014f, B:71:0x0153, B:80:0x0185, B:87:0x01a3, B:92:0x01bf, B:99:0x01dc, B:104:0x01f6, B:109:0x0210, B:9:0x0035, B:12:0x0040, B:15:0x004b, B:18:0x0057, B:21:0x0062, B:24:0x006c, B:27:0x0077, B:30:0x0081, B:33:0x008b, B:36:0x0095, B:39:0x009f, B:42:0x00a9, B:45:0x00b4, B:89:0x01a8), top: B:118:0x002c, inners: #6, #8, #10, #11 }] */
        @Override // b.a.c.a.i.c
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(b.a.c.a.h r6, b.a.c.a.i.d r7) {
            /*
                Method dump skipped, instructions count: 644
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.engine.j.i.a.a(b.a.c.a.h, b.a.c.a.i$d):void");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class b {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f206a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f207b;
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[k.values().length];
            c = iArr;
            try {
                iArr[k.LEAN_BACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[k.IMMERSIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[k.IMMERSIVE_STICKY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                c[k.EDGE_TO_EDGE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[l.values().length];
            f207b = iArr2;
            try {
                iArr2[l.TOP_OVERLAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f207b[l.BOTTOM_OVERLAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            int[] iArr3 = new int[f.values().length];
            f206a = iArr3;
            try {
                iArr3[f.PORTRAIT_UP.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f206a[f.PORTRAIT_DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f206a[f.LANDSCAPE_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f206a[f.LANDSCAPE_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        public final int f208a;

        /* renamed from: b  reason: collision with root package name */
        public final String f209b;

        public c(int i, String str) {
            this.f208a = i;
            this.f209b = str;
        }
    }

    /* loaded from: classes.dex */
    public enum d {
        LIGHT("Brightness.light"),
        DARK("Brightness.dark");
        

        /* renamed from: a  reason: collision with root package name */
        private String f211a;

        d(String str) {
            this.f211a = str;
        }

        static d a(String str) {
            d[] values;
            for (d dVar : values()) {
                if (dVar.f211a.equals(str)) {
                    return dVar;
                }
            }
            throw new NoSuchFieldException("No such Brightness: " + str);
        }
    }

    /* loaded from: classes.dex */
    public enum e {
        PLAIN_TEXT("text/plain");
        

        /* renamed from: a  reason: collision with root package name */
        private String f213a;

        e(String str) {
            this.f213a = str;
        }

        static e a(String str) {
            e[] values;
            for (e eVar : values()) {
                if (eVar.f213a.equals(str)) {
                    return eVar;
                }
            }
            throw new NoSuchFieldException("No such ClipboardContentFormat: " + str);
        }
    }

    /* loaded from: classes.dex */
    public enum f {
        PORTRAIT_UP("DeviceOrientation.portraitUp"),
        PORTRAIT_DOWN("DeviceOrientation.portraitDown"),
        LANDSCAPE_LEFT("DeviceOrientation.landscapeLeft"),
        LANDSCAPE_RIGHT("DeviceOrientation.landscapeRight");
        

        /* renamed from: a  reason: collision with root package name */
        private String f215a;

        f(String str) {
            this.f215a = str;
        }

        static f a(String str) {
            f[] values;
            for (f fVar : values()) {
                if (fVar.f215a.equals(str)) {
                    return fVar;
                }
            }
            throw new NoSuchFieldException("No such DeviceOrientation: " + str);
        }
    }

    /* loaded from: classes.dex */
    public enum g {
        STANDARD(null),
        LIGHT_IMPACT("HapticFeedbackType.lightImpact"),
        MEDIUM_IMPACT("HapticFeedbackType.mediumImpact"),
        HEAVY_IMPACT("HapticFeedbackType.heavyImpact"),
        SELECTION_CLICK("HapticFeedbackType.selectionClick");
        

        /* renamed from: a  reason: collision with root package name */
        private final String f217a;

        g(String str) {
            this.f217a = str;
        }

        static g a(String str) {
            g[] values;
            for (g gVar : values()) {
                String str2 = gVar.f217a;
                if ((str2 == null && str == null) || (str2 != null && str2.equals(str))) {
                    return gVar;
                }
            }
            throw new NoSuchFieldException("No such HapticFeedbackType: " + str);
        }
    }

    /* loaded from: classes.dex */
    public interface h {
        void b();

        void c();

        void d(int i);

        void e(c cVar);

        void f(g gVar);

        void g(List<l> list);

        void h();

        void i(String str);

        boolean j();

        void k(EnumC0016i enumC0016i);

        CharSequence l(e eVar);

        void m(j jVar);

        void n(k kVar);
    }

    /* renamed from: io.flutter.embedding.engine.j.i$i  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public enum EnumC0016i {
        CLICK("SystemSoundType.click"),
        ALERT("SystemSoundType.alert");
        

        /* renamed from: a  reason: collision with root package name */
        private final String f219a;

        EnumC0016i(String str) {
            this.f219a = str;
        }

        static EnumC0016i a(String str) {
            EnumC0016i[] values;
            for (EnumC0016i enumC0016i : values()) {
                if (enumC0016i.f219a.equals(str)) {
                    return enumC0016i;
                }
            }
            throw new NoSuchFieldException("No such SoundType: " + str);
        }
    }

    /* loaded from: classes.dex */
    public static class j {

        /* renamed from: a  reason: collision with root package name */
        public final Integer f220a;

        /* renamed from: b  reason: collision with root package name */
        public final d f221b;
        public final boolean c;
        public final Integer d;
        public final d e;
        public final Integer f;
        public final boolean g;

        public j(Integer num, d dVar, boolean z, Integer num2, d dVar2, Integer num3, boolean z2) {
            this.f220a = num;
            this.f221b = dVar;
            this.c = z;
            this.d = num2;
            this.e = dVar2;
            this.f = num3;
            this.g = z2;
        }
    }

    /* loaded from: classes.dex */
    public enum k {
        LEAN_BACK("SystemUiMode.leanBack"),
        IMMERSIVE("SystemUiMode.immersive"),
        IMMERSIVE_STICKY("SystemUiMode.immersiveSticky"),
        EDGE_TO_EDGE("SystemUiMode.edgeToEdge");
        

        /* renamed from: a  reason: collision with root package name */
        private String f223a;

        k(String str) {
            this.f223a = str;
        }

        static k a(String str) {
            k[] values;
            for (k kVar : values()) {
                if (kVar.f223a.equals(str)) {
                    return kVar;
                }
            }
            throw new NoSuchFieldException("No such SystemUiMode: " + str);
        }
    }

    /* loaded from: classes.dex */
    public enum l {
        TOP_OVERLAYS("SystemUiOverlay.top"),
        BOTTOM_OVERLAYS("SystemUiOverlay.bottom");
        

        /* renamed from: a  reason: collision with root package name */
        private String f225a;

        l(String str) {
            this.f225a = str;
        }

        static l a(String str) {
            l[] values;
            for (l lVar : values()) {
                if (lVar.f225a.equals(str)) {
                    return lVar;
                }
            }
            throw new NoSuchFieldException("No such SystemUiOverlay: " + str);
        }
    }

    public i(io.flutter.embedding.engine.f.a aVar) {
        a aVar2 = new a();
        this.c = aVar2;
        b.a.c.a.i iVar = new b.a.c.a.i(aVar, "flutter/platform", b.a.c.a.e.f73a);
        this.f203a = iVar;
        iVar.e(aVar2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public c g(JSONObject jSONObject) {
        int i = jSONObject.getInt("primaryColor");
        if (i != 0) {
            i |= -16777216;
        }
        return new c(i, jSONObject.getString("label"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0053 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int h(org.json.JSONArray r10) {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
        L4:
            int r4 = r10.length()
            r5 = 4
            r6 = 2
            r7 = 1
            if (r1 >= r4) goto L38
            java.lang.String r4 = r10.getString(r1)
            io.flutter.embedding.engine.j.i$f r4 = io.flutter.embedding.engine.j.i.f.a(r4)
            int[] r8 = io.flutter.embedding.engine.j.i.b.f206a
            int r4 = r4.ordinal()
            r4 = r8[r4]
            if (r4 == r7) goto L30
            if (r4 == r6) goto L2d
            r6 = 3
            if (r4 == r6) goto L2a
            if (r4 == r5) goto L27
            goto L32
        L27:
            r2 = r2 | 8
            goto L32
        L2a:
            r2 = r2 | 2
            goto L32
        L2d:
            r2 = r2 | 4
            goto L32
        L30:
            r2 = r2 | 1
        L32:
            if (r3 != 0) goto L35
            r3 = r2
        L35:
            int r1 = r1 + 1
            goto L4
        L38:
            if (r2 == 0) goto L57
            r10 = 9
            r1 = 8
            switch(r2) {
                case 2: goto L56;
                case 3: goto L4d;
                case 4: goto L4c;
                case 5: goto L4a;
                case 6: goto L4d;
                case 7: goto L4d;
                case 8: goto L49;
                case 9: goto L4d;
                case 10: goto L46;
                case 11: goto L45;
                case 12: goto L4d;
                case 13: goto L4d;
                case 14: goto L4d;
                case 15: goto L42;
                default: goto L41;
            }
        L41:
            goto L53
        L42:
            r10 = 13
            return r10
        L45:
            return r6
        L46:
            r10 = 11
            return r10
        L49:
            return r1
        L4a:
            r10 = 12
        L4c:
            return r10
        L4d:
            if (r3 == r6) goto L56
            if (r3 == r5) goto L55
            if (r3 == r1) goto L54
        L53:
            return r7
        L54:
            return r1
        L55:
            return r10
        L56:
            return r0
        L57:
            r10 = -1
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.embedding.engine.j.i.h(org.json.JSONArray):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public j i(JSONObject jSONObject) {
        return new j(!jSONObject.isNull("statusBarColor") ? Integer.valueOf(jSONObject.getInt("statusBarColor")) : null, !jSONObject.isNull("statusBarIconBrightness") ? d.a(jSONObject.getString("statusBarIconBrightness")) : null, !jSONObject.isNull("systemStatusBarContrastEnforced") ? jSONObject.getBoolean("systemStatusBarContrastEnforced") : true, !jSONObject.isNull("systemNavigationBarColor") ? Integer.valueOf(jSONObject.getInt("systemNavigationBarColor")) : null, !jSONObject.isNull("systemNavigationBarIconBrightness") ? d.a(jSONObject.getString("systemNavigationBarIconBrightness")) : null, jSONObject.isNull("systemNavigationBarDividerColor") ? null : Integer.valueOf(jSONObject.getInt("systemNavigationBarDividerColor")), !jSONObject.isNull("systemNavigationBarContrastEnforced") ? jSONObject.getBoolean("systemNavigationBarContrastEnforced") : true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public k j(String str) {
        int i = b.c[k.a(str).ordinal()];
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? k.EDGE_TO_EDGE : k.EDGE_TO_EDGE : k.IMMERSIVE_STICKY : k.IMMERSIVE : k.LEAN_BACK;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<l> k(JSONArray jSONArray) {
        l lVar;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            int i2 = b.f207b[l.a(jSONArray.getString(i)).ordinal()];
            if (i2 == 1) {
                lVar = l.TOP_OVERLAYS;
            } else if (i2 == 2) {
                lVar = l.BOTTOM_OVERLAYS;
            }
            arrayList.add(lVar);
        }
        return arrayList;
    }

    public void l(h hVar) {
        this.f204b = hVar;
    }

    public void m(boolean z) {
        b.a.b.e("PlatformChannel", "Sending 'systemUIChange' message.");
        this.f203a.c("SystemChrome.systemUIChange", Arrays.asList(Boolean.valueOf(z)));
    }
}
