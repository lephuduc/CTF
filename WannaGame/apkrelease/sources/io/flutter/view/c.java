package io.flutter.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.ContentObserver;
import android.graphics.Rect;
import android.net.Uri;
import android.opengl.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.LocaleSpan;
import android.text.style.TtsSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import io.flutter.embedding.engine.j.b;
import io.flutter.view.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class c extends AccessibilityNodeProvider {
    private static int y = 267386881;

    /* renamed from: a  reason: collision with root package name */
    private final View f337a;

    /* renamed from: b  reason: collision with root package name */
    private final io.flutter.embedding.engine.j.b f338b;
    private final AccessibilityManager c;
    private final AccessibilityViewEmbedder d;
    private final io.flutter.plugin.platform.j e;
    private final ContentResolver f;
    private final Map<Integer, l> g;
    private final Map<Integer, h> h;
    private l i;
    private Integer j;
    private Integer k;
    private int l;
    private l m;
    private l n;
    private l o;
    private final List<Integer> p;
    private int q;
    private Integer r;
    private k s;
    private boolean t;
    private final b.InterfaceC0015b u;
    private final AccessibilityManager.AccessibilityStateChangeListener v;
    @TargetApi(19)
    private final AccessibilityManager.TouchExplorationStateChangeListener w;
    private final ContentObserver x;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements b.InterfaceC0015b {
        a() {
        }

        @Override // io.flutter.embedding.engine.FlutterJNI.a
        public void a(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            for (ByteBuffer byteBuffer2 : byteBufferArr) {
                byteBuffer2.order(ByteOrder.LITTLE_ENDIAN);
            }
            c.this.T(byteBuffer, strArr, byteBufferArr);
        }

        @Override // io.flutter.embedding.engine.FlutterJNI.a
        public void b(ByteBuffer byteBuffer, String[] strArr) {
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            c.this.S(byteBuffer, strArr);
        }

        @Override // io.flutter.embedding.engine.j.b.InterfaceC0015b
        public void c(String str) {
            AccessibilityEvent C = c.this.C(0, 32);
            C.getText().add(str);
            c.this.M(C);
        }

        @Override // io.flutter.embedding.engine.j.b.InterfaceC0015b
        public void d(int i) {
            c.this.L(i, 2);
        }

        @Override // io.flutter.embedding.engine.j.b.InterfaceC0015b
        public void e(int i) {
            c.this.L(i, 1);
        }

        @Override // io.flutter.embedding.engine.j.b.InterfaceC0015b
        public void f(String str) {
            c.this.f337a.announceForAccessibility(str);
        }
    }

    /* loaded from: classes.dex */
    class b implements AccessibilityManager.AccessibilityStateChangeListener {
        b() {
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
        public void onAccessibilityStateChanged(boolean z) {
            if (c.this.t) {
                return;
            }
            io.flutter.embedding.engine.j.b bVar = c.this.f338b;
            if (z) {
                bVar.g(c.this.u);
                c.this.f338b.e();
            } else {
                bVar.g(null);
                c.this.f338b.d();
            }
            if (c.this.s != null) {
                c.this.s.a(z, c.this.c.isTouchExplorationEnabled());
            }
        }
    }

    /* renamed from: io.flutter.view.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class C0024c extends ContentObserver {
        C0024c(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            onChange(z, null);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            c cVar;
            int i;
            if (c.this.t) {
                return;
            }
            String string = Build.VERSION.SDK_INT < 17 ? null : Settings.Global.getString(c.this.f, "transition_animation_scale");
            if (string != null && string.equals("0")) {
                cVar = c.this;
                i = cVar.l | f.DISABLE_ANIMATIONS.f346a;
            } else {
                cVar = c.this;
                i = cVar.l & (f.DISABLE_ANIMATIONS.f346a ^ (-1));
            }
            cVar.l = i;
            c.this.N();
        }
    }

    /* loaded from: classes.dex */
    class d implements AccessibilityManager.TouchExplorationStateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AccessibilityManager f342a;

        d(AccessibilityManager accessibilityManager) {
            this.f342a = accessibilityManager;
        }

        @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
        public void onTouchExplorationStateChanged(boolean z) {
            int i;
            if (c.this.t) {
                return;
            }
            c cVar = c.this;
            if (z) {
                i = cVar.l | f.ACCESSIBLE_NAVIGATION.f346a;
            } else {
                cVar.F();
                cVar = c.this;
                i = cVar.l & (f.ACCESSIBLE_NAVIGATION.f346a ^ (-1));
            }
            cVar.l = i;
            c.this.N();
            if (c.this.s != null) {
                c.this.s.a(this.f342a.isEnabled(), z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class e {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f344a;

        static {
            int[] iArr = new int[o.values().length];
            f344a = iArr;
            try {
                iArr[o.SPELLOUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f344a[o.LOCALE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum f {
        ACCESSIBLE_NAVIGATION(1),
        INVERT_COLORS(2),
        DISABLE_ANIMATIONS(4);
        

        /* renamed from: a  reason: collision with root package name */
        final int f346a;

        f(int i) {
            this.f346a = i;
        }
    }

    /* loaded from: classes.dex */
    public enum g {
        TAP(1),
        LONG_PRESS(2),
        SCROLL_LEFT(4),
        SCROLL_RIGHT(8),
        SCROLL_UP(16),
        SCROLL_DOWN(32),
        INCREASE(64),
        DECREASE(128),
        SHOW_ON_SCREEN(256),
        MOVE_CURSOR_FORWARD_BY_CHARACTER(512),
        MOVE_CURSOR_BACKWARD_BY_CHARACTER(1024),
        SET_SELECTION(2048),
        COPY(4096),
        CUT(8192),
        PASTE(16384),
        DID_GAIN_ACCESSIBILITY_FOCUS(32768),
        DID_LOSE_ACCESSIBILITY_FOCUS(65536),
        CUSTOM_ACTION(131072),
        DISMISS(262144),
        MOVE_CURSOR_FORWARD_BY_WORD(524288),
        MOVE_CURSOR_BACKWARD_BY_WORD(1048576),
        SET_TEXT(2097152);
        

        /* renamed from: a  reason: collision with root package name */
        public final int f348a;

        g(int i) {
            this.f348a = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class h {

        /* renamed from: a  reason: collision with root package name */
        private int f349a = -1;

        /* renamed from: b  reason: collision with root package name */
        private int f350b = -1;
        private int c = -1;
        private String d;
        private String e;

        h() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public enum i {
        HAS_CHECKED_STATE(1),
        IS_CHECKED(2),
        IS_SELECTED(4),
        IS_BUTTON(8),
        IS_TEXT_FIELD(16),
        IS_FOCUSED(32),
        HAS_ENABLED_STATE(64),
        IS_ENABLED(128),
        IS_IN_MUTUALLY_EXCLUSIVE_GROUP(256),
        IS_HEADER(512),
        IS_OBSCURED(1024),
        SCOPES_ROUTE(2048),
        NAMES_ROUTE(4096),
        IS_HIDDEN(8192),
        IS_IMAGE(16384),
        IS_LIVE_REGION(32768),
        HAS_TOGGLED_STATE(65536),
        IS_TOGGLED(131072),
        HAS_IMPLICIT_SCROLLING(262144),
        IS_READ_ONLY(1048576),
        IS_FOCUSABLE(2097152),
        IS_LINK(4194304),
        IS_SLIDER(8388608),
        IS_KEYBOARD_KEY(16777216);
        

        /* renamed from: a  reason: collision with root package name */
        final int f352a;

        i(int i) {
            this.f352a = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class j extends n {
        String d;

        private j() {
            super(null);
        }

        /* synthetic */ j(a aVar) {
            this();
        }
    }

    /* loaded from: classes.dex */
    public interface k {
        void a(boolean z, boolean z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class l {
        private int A;
        private int B;
        private int C;
        private int D;
        private float E;
        private String F;
        private String G;
        private float H;
        private float I;
        private float J;
        private float K;
        private float[] L;
        private l M;
        private List<h> P;
        private h Q;
        private h R;
        private float[] T;
        private float[] V;
        private Rect W;

        /* renamed from: a  reason: collision with root package name */
        final c f353a;
        private int c;
        private int d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;
        private int j;
        private int k;
        private float l;
        private float m;
        private float n;
        private String o;
        private List<n> p;
        private String q;
        private List<n> r;
        private String s;
        private List<n> t;
        private String u;
        private List<n> v;
        private String w;
        private List<n> x;

        /* renamed from: b  reason: collision with root package name */
        private int f354b = -1;
        private int y = -1;
        private boolean z = false;
        private List<l> N = new ArrayList();
        private List<l> O = new ArrayList();
        private boolean S = true;
        private boolean U = true;

        l(c cVar) {
            this.f353a = cVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void Z(List<l> list) {
            if (m0(i.SCOPES_ROUTE)) {
                list.add(this);
            }
            for (l lVar : this.N) {
                lVar.Z(list);
            }
        }

        @TargetApi(21)
        private SpannableString a0(String str, List<n> list) {
            if (str == null) {
                return null;
            }
            SpannableString spannableString = new SpannableString(str);
            if (list != null) {
                for (n nVar : list) {
                    int i = e.f344a[nVar.c.ordinal()];
                    if (i == 1) {
                        spannableString.setSpan(new TtsSpan.Builder("android.type.verbatim").build(), nVar.f355a, nVar.f356b, 0);
                    } else if (i == 2) {
                        spannableString.setSpan(new LocaleSpan(Locale.forLanguageTag(((j) nVar).d)), nVar.f355a, nVar.f356b, 0);
                    }
                }
            }
            return spannableString;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b0() {
            String str;
            String str2 = this.o;
            if (str2 == null && this.G == null) {
                return false;
            }
            return str2 == null || (str = this.G) == null || !str2.equals(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c0() {
            return (Float.isNaN(this.l) || Float.isNaN(this.E) || this.E == this.l) ? false : true;
        }

        private void d0() {
            if (this.S) {
                this.S = false;
                if (this.T == null) {
                    this.T = new float[16];
                }
                if (Matrix.invertM(this.T, 0, this.L, 0)) {
                    return;
                }
                Arrays.fill(this.T, 0.0f);
            }
        }

        private l e0(b.a.d.c<l> cVar) {
            for (l lVar = this.M; lVar != null; lVar = lVar.M) {
                if (cVar.a(lVar)) {
                    return lVar;
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Rect f0() {
            return this.W;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String g0() {
            String str;
            if (!m0(i.NAMES_ROUTE) || (str = this.o) == null || str.isEmpty()) {
                for (l lVar : this.N) {
                    String g0 = lVar.g0();
                    if (g0 != null && !g0.isEmpty()) {
                        return g0;
                    }
                }
                return null;
            }
            return this.o;
        }

        private List<n> h0(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr) {
            int i = byteBuffer.getInt();
            if (i == -1) {
                return null;
            }
            ArrayList arrayList = new ArrayList(i);
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = byteBuffer.getInt();
                int i4 = byteBuffer.getInt();
                o oVar = o.values()[byteBuffer.getInt()];
                int i5 = e.f344a[oVar.ordinal()];
                if (i5 == 1) {
                    byteBuffer.getInt();
                    m mVar = new m(null);
                    mVar.f355a = i3;
                    mVar.f356b = i4;
                    mVar.c = oVar;
                    arrayList.add(mVar);
                } else if (i5 == 2) {
                    ByteBuffer byteBuffer2 = byteBufferArr[byteBuffer.getInt()];
                    j jVar = new j(null);
                    jVar.f355a = i3;
                    jVar.f356b = i4;
                    jVar.c = oVar;
                    jVar.d = Charset.forName("UTF-8").decode(byteBuffer2).toString();
                    arrayList.add(jVar);
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CharSequence i0() {
            CharSequence[] charSequenceArr;
            CharSequence charSequence = null;
            for (CharSequence charSequence2 : Build.VERSION.SDK_INT < 21 ? new CharSequence[]{this.q, this.o, this.w} : new CharSequence[]{a0(this.q, this.r), a0(this.o, this.p), a0(this.w, this.x)}) {
                if (charSequence2 != null && charSequence2.length() > 0) {
                    charSequence = (charSequence == null || charSequence.length() == 0) ? charSequence2 : TextUtils.concat(charSequence, ", ", charSequence2);
                }
            }
            return charSequence;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean j0(g gVar) {
            return (gVar.f348a & this.B) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean k0(i iVar) {
            return (iVar.f352a & this.A) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean l0(g gVar) {
            return (gVar.f348a & this.d) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean m0(i iVar) {
            return (iVar.f352a & this.c) != 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public l n0(float[] fArr) {
            float f = fArr[3];
            float f2 = fArr[0] / f;
            float f3 = fArr[1] / f;
            if (f2 < this.H || f2 >= this.J || f3 < this.I || f3 >= this.K) {
                return null;
            }
            float[] fArr2 = new float[4];
            for (l lVar : this.O) {
                if (!lVar.m0(i.IS_HIDDEN)) {
                    lVar.d0();
                    Matrix.multiplyMV(fArr2, 0, lVar.T, 0, fArr, 0);
                    l n0 = lVar.n0(fArr2);
                    if (n0 != null) {
                        return n0;
                    }
                }
            }
            if (o0()) {
                return this;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean o0() {
            String str;
            String str2;
            String str3;
            if (m0(i.SCOPES_ROUTE)) {
                return false;
            }
            if (m0(i.IS_FOCUSABLE)) {
                return true;
            }
            return ((((((g.SCROLL_RIGHT.f348a | g.SCROLL_LEFT.f348a) | g.SCROLL_UP.f348a) | g.SCROLL_DOWN.f348a) ^ (-1)) & this.d) == 0 && this.c == 0 && ((str = this.o) == null || str.isEmpty()) && (((str2 = this.q) == null || str2.isEmpty()) && ((str3 = this.w) == null || str3.isEmpty()))) ? false : true;
        }

        private float p0(float f, float f2, float f3, float f4) {
            return Math.max(f, Math.max(f2, Math.max(f3, f4)));
        }

        private float q0(float f, float f2, float f3, float f4) {
            return Math.min(f, Math.min(f2, Math.min(f3, f4)));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean r0(l lVar, b.a.d.c<l> cVar) {
            return (lVar == null || lVar.e0(cVar) == null) ? false : true;
        }

        private void s0(float[] fArr, float[] fArr2, float[] fArr3) {
            Matrix.multiplyMV(fArr, 0, fArr2, 0, fArr3, 0);
            float f = fArr[3];
            fArr[0] = fArr[0] / f;
            fArr[1] = fArr[1] / f;
            fArr[2] = fArr[2] / f;
            fArr[3] = 0.0f;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void t0(float[] fArr, Set<l> set, boolean z) {
            set.add(this);
            if (this.U) {
                z = true;
            }
            if (z) {
                if (this.V == null) {
                    this.V = new float[16];
                }
                Matrix.multiplyMM(this.V, 0, fArr, 0, this.L, 0);
                float[] fArr2 = {this.H, this.I, 0.0f, 1.0f};
                float[] fArr3 = new float[4];
                float[] fArr4 = new float[4];
                float[] fArr5 = new float[4];
                float[] fArr6 = new float[4];
                s0(fArr3, this.V, fArr2);
                fArr2[0] = this.J;
                fArr2[1] = this.I;
                s0(fArr4, this.V, fArr2);
                fArr2[0] = this.J;
                fArr2[1] = this.K;
                s0(fArr5, this.V, fArr2);
                fArr2[0] = this.H;
                fArr2[1] = this.K;
                s0(fArr6, this.V, fArr2);
                if (this.W == null) {
                    this.W = new Rect();
                }
                this.W.set(Math.round(q0(fArr3[0], fArr4[0], fArr5[0], fArr6[0])), Math.round(q0(fArr3[1], fArr4[1], fArr5[1], fArr6[1])), Math.round(p0(fArr3[0], fArr4[0], fArr5[0], fArr6[0])), Math.round(p0(fArr3[1], fArr4[1], fArr5[1], fArr6[1])));
                this.U = false;
            }
            int i = -1;
            for (l lVar : this.N) {
                lVar.y = i;
                i = lVar.f354b;
                lVar.t0(this.V, set, z);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void u0(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
            this.z = true;
            this.F = this.q;
            this.G = this.o;
            this.A = this.c;
            this.B = this.d;
            this.C = this.g;
            this.D = this.h;
            this.E = this.l;
            this.c = byteBuffer.getInt();
            this.d = byteBuffer.getInt();
            this.e = byteBuffer.getInt();
            this.f = byteBuffer.getInt();
            this.g = byteBuffer.getInt();
            this.h = byteBuffer.getInt();
            this.i = byteBuffer.getInt();
            this.j = byteBuffer.getInt();
            this.k = byteBuffer.getInt();
            this.l = byteBuffer.getFloat();
            this.m = byteBuffer.getFloat();
            this.n = byteBuffer.getFloat();
            int i = byteBuffer.getInt();
            this.o = i == -1 ? null : strArr[i];
            this.p = h0(byteBuffer, byteBufferArr);
            int i2 = byteBuffer.getInt();
            this.q = i2 == -1 ? null : strArr[i2];
            this.r = h0(byteBuffer, byteBufferArr);
            int i3 = byteBuffer.getInt();
            this.s = i3 == -1 ? null : strArr[i3];
            this.t = h0(byteBuffer, byteBufferArr);
            int i4 = byteBuffer.getInt();
            this.u = i4 == -1 ? null : strArr[i4];
            this.v = h0(byteBuffer, byteBufferArr);
            int i5 = byteBuffer.getInt();
            this.w = i5 == -1 ? null : strArr[i5];
            this.x = h0(byteBuffer, byteBufferArr);
            p.a(byteBuffer.getInt());
            this.H = byteBuffer.getFloat();
            this.I = byteBuffer.getFloat();
            this.J = byteBuffer.getFloat();
            this.K = byteBuffer.getFloat();
            if (this.L == null) {
                this.L = new float[16];
            }
            for (int i6 = 0; i6 < 16; i6++) {
                this.L[i6] = byteBuffer.getFloat();
            }
            this.S = true;
            this.U = true;
            int i7 = byteBuffer.getInt();
            this.N.clear();
            this.O.clear();
            for (int i8 = 0; i8 < i7; i8++) {
                l v = this.f353a.v(byteBuffer.getInt());
                v.M = this;
                this.N.add(v);
            }
            for (int i9 = 0; i9 < i7; i9++) {
                l v2 = this.f353a.v(byteBuffer.getInt());
                v2.M = this;
                this.O.add(v2);
            }
            int i10 = byteBuffer.getInt();
            if (i10 == 0) {
                this.P = null;
                return;
            }
            List<h> list = this.P;
            if (list == null) {
                this.P = new ArrayList(i10);
            } else {
                list.clear();
            }
            for (int i11 = 0; i11 < i10; i11++) {
                h u = this.f353a.u(byteBuffer.getInt());
                if (u.c == g.TAP.f348a) {
                    this.Q = u;
                } else if (u.c == g.LONG_PRESS.f348a) {
                    this.R = u;
                } else {
                    this.P.add(u);
                }
                this.P.add(u);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class m extends n {
        private m() {
            super(null);
        }

        /* synthetic */ m(a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class n {

        /* renamed from: a  reason: collision with root package name */
        int f355a;

        /* renamed from: b  reason: collision with root package name */
        int f356b;
        o c;

        private n() {
        }

        /* synthetic */ n(a aVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum o {
        SPELLOUT,
        LOCALE
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public enum p {
        UNKNOWN,
        LTR,
        RTL;

        public static p a(int i) {
            return i != 1 ? i != 2 ? UNKNOWN : LTR : RTL;
        }
    }

    public c(View view, io.flutter.embedding.engine.j.b bVar, AccessibilityManager accessibilityManager, ContentResolver contentResolver, io.flutter.plugin.platform.j jVar) {
        this(view, bVar, accessibilityManager, contentResolver, new AccessibilityViewEmbedder(view, 65536), jVar);
    }

    public c(View view, io.flutter.embedding.engine.j.b bVar, AccessibilityManager accessibilityManager, ContentResolver contentResolver, AccessibilityViewEmbedder accessibilityViewEmbedder, io.flutter.plugin.platform.j jVar) {
        this.g = new HashMap();
        this.h = new HashMap();
        this.l = 0;
        this.p = new ArrayList();
        this.q = 0;
        this.r = 0;
        this.t = false;
        this.u = new a();
        b bVar2 = new b();
        this.v = bVar2;
        C0024c c0024c = new C0024c(new Handler());
        this.x = c0024c;
        this.f337a = view;
        this.f338b = bVar;
        this.c = accessibilityManager;
        this.f = contentResolver;
        this.d = accessibilityViewEmbedder;
        this.e = jVar;
        bVar2.onAccessibilityStateChanged(accessibilityManager.isEnabled());
        accessibilityManager.addAccessibilityStateChangeListener(bVar2);
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 19) {
            d dVar = new d(accessibilityManager);
            this.w = dVar;
            dVar.onTouchExplorationStateChanged(accessibilityManager.isTouchExplorationEnabled());
            accessibilityManager.addTouchExplorationStateChangeListener(dVar);
        } else {
            this.w = null;
        }
        if (i2 >= 17) {
            c0024c.onChange(false);
            contentResolver.registerContentObserver(Settings.Global.getUriFor("transition_animation_scale"), false, c0024c);
        }
        if (jVar != null) {
            jVar.c(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean A(l lVar, l lVar2) {
        return lVar2 == lVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AccessibilityEvent C(int i2, int i3) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i3);
        obtain.setPackageName(this.f337a.getContext().getPackageName());
        obtain.setSource(this.f337a, i2);
        return obtain;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        l lVar = this.o;
        if (lVar != null) {
            L(lVar.f354b, 256);
            this.o = null;
        }
    }

    private void G(l lVar) {
        String g0 = lVar.g0();
        if (g0 == null) {
            g0 = " ";
        }
        if (Build.VERSION.SDK_INT >= 28) {
            P(g0);
            return;
        }
        AccessibilityEvent C = C(lVar.f354b, 32);
        C.getText().add(g0);
        M(C);
    }

    @TargetApi(18)
    private boolean H(l lVar, int i2, Bundle bundle, boolean z) {
        int i3 = bundle.getInt("ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT");
        boolean z2 = bundle.getBoolean("ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN");
        J(lVar, i3, z, z2);
        if (i3 == 1) {
            if (z) {
                g gVar = g.MOVE_CURSOR_FORWARD_BY_CHARACTER;
                if (lVar.l0(gVar)) {
                    this.f338b.c(i2, gVar, Boolean.valueOf(z2));
                    return true;
                }
            }
            if (z) {
                return false;
            }
            g gVar2 = g.MOVE_CURSOR_BACKWARD_BY_CHARACTER;
            if (lVar.l0(gVar2)) {
                this.f338b.c(i2, gVar2, Boolean.valueOf(z2));
                return true;
            }
            return false;
        } else if (i3 != 2) {
            return i3 == 4 || i3 == 8 || i3 == 16;
        } else {
            if (z) {
                g gVar3 = g.MOVE_CURSOR_FORWARD_BY_WORD;
                if (lVar.l0(gVar3)) {
                    this.f338b.c(i2, gVar3, Boolean.valueOf(z2));
                    return true;
                }
            }
            if (z) {
                return false;
            }
            g gVar4 = g.MOVE_CURSOR_BACKWARD_BY_WORD;
            if (lVar.l0(gVar4)) {
                this.f338b.c(i2, gVar4, Boolean.valueOf(z2));
                return true;
            }
            return false;
        }
    }

    @TargetApi(21)
    private boolean I(l lVar, int i2, Bundle bundle) {
        String string = (bundle == null || !bundle.containsKey("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE")) ? "" : bundle.getString("ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE");
        this.f338b.c(i2, g.SET_TEXT, string);
        lVar.q = string;
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0022, code lost:
        if (r6 != false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0024, code lost:
        r5 = r4.q.length();
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0031, code lost:
        r4.h = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0060, code lost:
        if (r5.find() != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
        r4.h += r5.start(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0092, code lost:
        if (r5.find() != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0094, code lost:
        r5 = r5.start(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c6, code lost:
        if (r5.find() != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00eb, code lost:
        if (r5.find() != false) goto L35;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void J(io.flutter.view.c.l r4, int r5, boolean r6, boolean r7) {
        /*
            r3 = this;
            int r0 = io.flutter.view.c.l.k(r4)
            if (r0 < 0) goto L11d
            int r0 = io.flutter.view.c.l.i(r4)
            if (r0 >= 0) goto Le
            goto L11d
        Le:
            r0 = 1
            if (r5 == r0) goto Lee
            r1 = 2
            r2 = 0
            if (r5 == r1) goto L99
            r1 = 4
            if (r5 == r1) goto L36
            r0 = 8
            if (r5 == r0) goto L22
            r0 = 16
            if (r5 == r0) goto L22
            goto L114
        L22:
            if (r6 == 0) goto L31
        L24:
            java.lang.String r5 = io.flutter.view.c.l.o(r4)
            int r5 = r5.length()
        L2c:
            io.flutter.view.c.l.l(r4, r5)
            goto L114
        L31:
            io.flutter.view.c.l.l(r4, r2)
            goto L114
        L36:
            if (r6 == 0) goto L70
            int r5 = io.flutter.view.c.l.k(r4)
            java.lang.String r1 = io.flutter.view.c.l.o(r4)
            int r1 = r1.length()
            if (r5 >= r1) goto L70
            java.lang.String r5 = "(?!^)(\\n)"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)
            java.lang.String r6 = io.flutter.view.c.l.o(r4)
            int r1 = io.flutter.view.c.l.k(r4)
            java.lang.String r6 = r6.substring(r1)
            java.util.regex.Matcher r5 = r5.matcher(r6)
            boolean r6 = r5.find()
            if (r6 == 0) goto L24
        L62:
            int r6 = io.flutter.view.c.l.k(r4)
            int r5 = r5.start(r0)
            int r6 = r6 + r5
            io.flutter.view.c.l.l(r4, r6)
            goto L114
        L70:
            if (r6 != 0) goto L114
            int r5 = io.flutter.view.c.l.k(r4)
            if (r5 <= 0) goto L114
            java.lang.String r5 = "(?s:.*)(\\n)"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)
            java.lang.String r6 = io.flutter.view.c.l.o(r4)
            int r1 = io.flutter.view.c.l.k(r4)
            java.lang.String r6 = r6.substring(r2, r1)
            java.util.regex.Matcher r5 = r5.matcher(r6)
            boolean r6 = r5.find()
            if (r6 == 0) goto L31
        L94:
            int r5 = r5.start(r0)
            goto L2c
        L99:
            if (r6 == 0) goto Lc9
            int r5 = io.flutter.view.c.l.k(r4)
            java.lang.String r1 = io.flutter.view.c.l.o(r4)
            int r1 = r1.length()
            if (r5 >= r1) goto Lc9
            java.lang.String r5 = "\\p{L}(\\b)"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)
            java.lang.String r6 = io.flutter.view.c.l.o(r4)
            int r1 = io.flutter.view.c.l.k(r4)
            java.lang.String r6 = r6.substring(r1)
            java.util.regex.Matcher r5 = r5.matcher(r6)
            r5.find()
            boolean r6 = r5.find()
            if (r6 == 0) goto L24
            goto L62
        Lc9:
            if (r6 != 0) goto L114
            int r5 = io.flutter.view.c.l.k(r4)
            if (r5 <= 0) goto L114
            java.lang.String r5 = "(?s:.*)(\\b)\\p{L}"
            java.util.regex.Pattern r5 = java.util.regex.Pattern.compile(r5)
            java.lang.String r6 = io.flutter.view.c.l.o(r4)
            int r1 = io.flutter.view.c.l.k(r4)
            java.lang.String r6 = r6.substring(r2, r1)
            java.util.regex.Matcher r5 = r5.matcher(r6)
            boolean r6 = r5.find()
            if (r6 == 0) goto L114
            goto L94
        Lee:
            if (r6 == 0) goto L105
            int r5 = io.flutter.view.c.l.k(r4)
            java.lang.String r1 = io.flutter.view.c.l.o(r4)
            int r1 = r1.length()
            if (r5 >= r1) goto L105
            int r5 = io.flutter.view.c.l.k(r4)
            int r5 = r5 + r0
            goto L2c
        L105:
            if (r6 != 0) goto L114
            int r5 = io.flutter.view.c.l.k(r4)
            if (r5 <= 0) goto L114
            int r5 = io.flutter.view.c.l.k(r4)
            int r5 = r5 - r0
            goto L2c
        L114:
            if (r7 != 0) goto L11d
            int r5 = io.flutter.view.c.l.k(r4)
            io.flutter.view.c.l.j(r4, r5)
        L11d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.view.c.J(io.flutter.view.c$l, int, boolean, boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L(int i2, int i3) {
        if (this.c.isEnabled()) {
            M(C(i2, i3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(AccessibilityEvent accessibilityEvent) {
        if (this.c.isEnabled()) {
            this.f337a.getParent().requestSendAccessibilityEvent(this.f337a, accessibilityEvent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N() {
        this.f338b.f(this.l);
    }

    private void O(int i2) {
        AccessibilityEvent C = C(i2, 2048);
        if (Build.VERSION.SDK_INT >= 19) {
            C.setContentChangeTypes(1);
        }
        M(C);
    }

    @TargetApi(28)
    private void P(String str) {
        this.f337a.setAccessibilityPaneTitle(str);
    }

    private boolean R(final l lVar) {
        return lVar.j > 0 && (l.r0(this.i, new b.a.d.c() { // from class: io.flutter.view.b
            @Override // b.a.d.c
            public final boolean a(Object obj) {
                return c.A(c.l.this, (c.l) obj);
            }
        }) || !l.r0(this.i, io.flutter.view.a.f335a));
    }

    @TargetApi(19)
    private void U(l lVar) {
        View a2;
        Integer num;
        lVar.M = null;
        if (lVar.i != -1 && (num = this.j) != null && this.d.platformViewOfNode(num.intValue()) == this.e.a(Integer.valueOf(lVar.i))) {
            L(this.j.intValue(), 65536);
            this.j = null;
        }
        if (lVar.i != -1 && !this.e.b(Integer.valueOf(lVar.i)) && (a2 = this.e.a(Integer.valueOf(lVar.i))) != null) {
            a2.setImportantForAccessibility(4);
        }
        l lVar2 = this.i;
        if (lVar2 == lVar) {
            L(lVar2.f354b, 65536);
            this.i = null;
        }
        if (this.m == lVar) {
            this.m = null;
        }
        if (this.o == lVar) {
            this.o = null;
        }
    }

    private AccessibilityEvent q(int i2, String str, String str2) {
        AccessibilityEvent C = C(i2, 16);
        C.setBeforeText(str);
        C.getText().add(str2);
        int i3 = 0;
        while (i3 < str.length() && i3 < str2.length() && str.charAt(i3) == str2.charAt(i3)) {
            i3++;
        }
        if (i3 < str.length() || i3 < str2.length()) {
            C.setFromIndex(i3);
            int length = str.length() - 1;
            int length2 = str2.length() - 1;
            while (length >= i3 && length2 >= i3 && str.charAt(length) == str2.charAt(length2)) {
                length--;
                length2--;
            }
            C.setRemovedCount((length - i3) + 1);
            C.setAddedCount((length2 - i3) + 1);
            return C;
        }
        return null;
    }

    @TargetApi(28)
    private boolean r() {
        Activity b2 = b.a.d.d.b(this.f337a.getContext());
        if (b2 == null || b2.getWindow() == null) {
            return false;
        }
        int i2 = b2.getWindow().getAttributes().layoutInDisplayCutoutMode;
        return i2 == 2 || i2 == 0;
    }

    private Rect t(Rect rect) {
        Rect rect2 = new Rect(rect);
        int[] iArr = new int[2];
        this.f337a.getLocationOnScreen(iArr);
        rect2.offset(iArr[0], iArr[1]);
        return rect2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public h u(int i2) {
        h hVar = this.h.get(Integer.valueOf(i2));
        if (hVar == null) {
            h hVar2 = new h();
            hVar2.f350b = i2;
            hVar2.f349a = y + i2;
            this.h.put(Integer.valueOf(i2), hVar2);
            return hVar2;
        }
        return hVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public l v(int i2) {
        l lVar = this.g.get(Integer.valueOf(i2));
        if (lVar == null) {
            l lVar2 = new l(this);
            lVar2.f354b = i2;
            this.g.put(Integer.valueOf(i2), lVar2);
            return lVar2;
        }
        return lVar;
    }

    private l w() {
        return this.g.get(0);
    }

    private void x(float f2, float f3) {
        l n0;
        if (this.g.isEmpty() || (n0 = w().n0(new float[]{f2, f3, 0.0f, 1.0f})) == this.o) {
            return;
        }
        if (n0 != null) {
            L(n0.f354b, 128);
        }
        l lVar = this.o;
        if (lVar != null) {
            L(lVar.f354b, 256);
        }
        this.o = n0;
    }

    public AccessibilityNodeInfo D(View view, int i2) {
        return AccessibilityNodeInfo.obtain(view, i2);
    }

    public boolean E(MotionEvent motionEvent) {
        if (this.c.isTouchExplorationEnabled() && !this.g.isEmpty()) {
            l n0 = w().n0(new float[]{motionEvent.getX(), motionEvent.getY(), 0.0f, 1.0f});
            if (n0 == null || n0.i == -1) {
                if (motionEvent.getAction() == 9 || motionEvent.getAction() == 7) {
                    x(motionEvent.getX(), motionEvent.getY());
                } else if (motionEvent.getAction() != 10) {
                    b.a.b.a("flutter", "unexpected accessibility hover event: " + motionEvent);
                    return false;
                } else {
                    F();
                }
                return true;
            }
            return this.d.onAccessibilityHoverEvent(n0.f354b, motionEvent);
        }
        return false;
    }

    public void K() {
        this.t = true;
        io.flutter.plugin.platform.j jVar = this.e;
        if (jVar != null) {
            jVar.d();
        }
        Q(null);
        this.c.removeAccessibilityStateChangeListener(this.v);
        if (Build.VERSION.SDK_INT >= 19) {
            this.c.removeTouchExplorationStateChangeListener(this.w);
        }
        this.f.unregisterContentObserver(this.x);
        this.f338b.g(null);
    }

    public void Q(k kVar) {
        this.s = kVar;
    }

    void S(ByteBuffer byteBuffer, String[] strArr) {
        while (byteBuffer.hasRemaining()) {
            h u = u(byteBuffer.getInt());
            u.c = byteBuffer.getInt();
            int i2 = byteBuffer.getInt();
            String str = null;
            u.d = i2 == -1 ? null : strArr[i2];
            int i3 = byteBuffer.getInt();
            if (i3 != -1) {
                str = strArr[i3];
            }
            u.e = str;
        }
    }

    void T(ByteBuffer byteBuffer, String[] strArr, ByteBuffer[] byteBufferArr) {
        l lVar;
        l lVar2;
        float f2;
        float f3;
        WindowInsets rootWindowInsets;
        Integer valueOf;
        View a2;
        ArrayList arrayList = new ArrayList();
        while (byteBuffer.hasRemaining()) {
            l v = v(byteBuffer.getInt());
            v.u0(byteBuffer, strArr, byteBufferArr);
            if (!v.m0(i.IS_HIDDEN)) {
                if (v.m0(i.IS_FOCUSED)) {
                    this.m = v;
                }
                if (v.z) {
                    arrayList.add(v);
                }
                if (v.i != -1 && !this.e.b(Integer.valueOf(v.i)) && (a2 = this.e.a(Integer.valueOf(v.i))) != null) {
                    a2.setImportantForAccessibility(0);
                }
            }
        }
        HashSet hashSet = new HashSet();
        l w = w();
        ArrayList<l> arrayList2 = new ArrayList();
        if (w != null) {
            float[] fArr = new float[16];
            Matrix.setIdentityM(fArr, 0);
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 23) {
                if ((i2 >= 28 ? r() : true) && (rootWindowInsets = this.f337a.getRootWindowInsets()) != null) {
                    if (!this.r.equals(Integer.valueOf(rootWindowInsets.getSystemWindowInsetLeft()))) {
                        w.U = true;
                        w.S = true;
                    }
                    this.r = Integer.valueOf(rootWindowInsets.getSystemWindowInsetLeft());
                    Matrix.translateM(fArr, 0, valueOf.intValue(), 0.0f, 0.0f);
                }
            }
            w.t0(fArr, hashSet, false);
            w.Z(arrayList2);
        }
        l lVar3 = null;
        for (l lVar4 : arrayList2) {
            if (!this.p.contains(Integer.valueOf(lVar4.f354b))) {
                lVar3 = lVar4;
            }
        }
        if (lVar3 == null && arrayList2.size() > 0) {
            lVar3 = (l) arrayList2.get(arrayList2.size() - 1);
        }
        if (lVar3 != null && (lVar3.f354b != this.q || arrayList2.size() != this.p.size())) {
            this.q = lVar3.f354b;
            G(lVar3);
        }
        this.p.clear();
        for (l lVar5 : arrayList2) {
            this.p.add(Integer.valueOf(lVar5.f354b));
        }
        Iterator<Map.Entry<Integer, l>> it = this.g.entrySet().iterator();
        while (it.hasNext()) {
            l value = it.next().getValue();
            if (!hashSet.contains(value)) {
                U(value);
                it.remove();
            }
        }
        O(0);
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            l lVar6 = (l) it2.next();
            if (lVar6.c0()) {
                AccessibilityEvent C = C(lVar6.f354b, 4096);
                float f4 = lVar6.l;
                float f5 = lVar6.m;
                if (Float.isInfinite(lVar6.m)) {
                    if (f4 > 70000.0f) {
                        f4 = 70000.0f;
                    }
                    f5 = 100000.0f;
                }
                if (Float.isInfinite(lVar6.n)) {
                    f2 = f5 + 100000.0f;
                    if (f4 < -70000.0f) {
                        f4 = -70000.0f;
                    }
                    f3 = f4 + 100000.0f;
                } else {
                    f2 = f5 - lVar6.n;
                    f3 = f4 - lVar6.n;
                }
                if (lVar6.j0(g.SCROLL_UP) || lVar6.j0(g.SCROLL_DOWN)) {
                    C.setScrollY((int) f3);
                    C.setMaxScrollY((int) f2);
                } else if (lVar6.j0(g.SCROLL_LEFT) || lVar6.j0(g.SCROLL_RIGHT)) {
                    C.setScrollX((int) f3);
                    C.setMaxScrollX((int) f2);
                }
                if (lVar6.j > 0) {
                    C.setItemCount(lVar6.j);
                    C.setFromIndex(lVar6.k);
                    int i3 = 0;
                    for (l lVar7 : lVar6.O) {
                        if (!lVar7.m0(i.IS_HIDDEN)) {
                            i3++;
                        }
                    }
                    C.setToIndex((lVar6.k + i3) - 1);
                }
                M(C);
            }
            if (lVar6.m0(i.IS_LIVE_REGION) && lVar6.b0()) {
                O(lVar6.f354b);
            }
            l lVar8 = this.i;
            if (lVar8 != null && lVar8.f354b == lVar6.f354b) {
                i iVar = i.IS_SELECTED;
                if (!lVar6.k0(iVar) && lVar6.m0(iVar)) {
                    AccessibilityEvent C2 = C(lVar6.f354b, 4);
                    C2.getText().add(lVar6.o);
                    M(C2);
                }
            }
            l lVar9 = this.m;
            if (lVar9 != null && lVar9.f354b == lVar6.f354b && ((lVar2 = this.n) == null || lVar2.f354b != this.m.f354b)) {
                this.n = this.m;
                M(C(lVar6.f354b, 8));
            } else if (this.m == null) {
                this.n = null;
            }
            l lVar10 = this.m;
            if (lVar10 != null && lVar10.f354b == lVar6.f354b) {
                i iVar2 = i.IS_TEXT_FIELD;
                if (lVar6.k0(iVar2) && lVar6.m0(iVar2) && ((lVar = this.i) == null || lVar.f354b == this.m.f354b)) {
                    String str = lVar6.F != null ? lVar6.F : "";
                    String str2 = lVar6.q != null ? lVar6.q : "";
                    AccessibilityEvent q = q(lVar6.f354b, str, str2);
                    if (q != null) {
                        M(q);
                    }
                    if (lVar6.C != lVar6.g || lVar6.D != lVar6.h) {
                        AccessibilityEvent C3 = C(lVar6.f354b, 8192);
                        C3.getText().add(str2);
                        C3.setFromIndex(lVar6.g);
                        C3.setToIndex(lVar6.h);
                        C3.setItemCount(str2.length());
                        M(C3);
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:220:0x03e4  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0415 A[LOOP:0: B:232:0x040f->B:234:0x0415, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:238:0x043a  */
    @Override // android.view.accessibility.AccessibilityNodeProvider
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.accessibility.AccessibilityNodeInfo createAccessibilityNodeInfo(int r15) {
        /*
            Method dump skipped, instructions count: 1148
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.view.c.createAccessibilityNodeInfo(int):android.view.accessibility.AccessibilityNodeInfo");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0016, code lost:
        if (r2 != null) goto L11;
     */
    @Override // android.view.accessibility.AccessibilityNodeProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.accessibility.AccessibilityNodeInfo findFocus(int r2) {
        /*
            r1 = this;
            r0 = 1
            if (r2 == r0) goto L7
            r0 = 2
            if (r2 == r0) goto L1d
            goto L27
        L7:
            io.flutter.view.c$l r2 = r1.m
            if (r2 == 0) goto L14
        Lb:
            int r2 = io.flutter.view.c.l.a(r2)
        Lf:
            android.view.accessibility.AccessibilityNodeInfo r2 = r1.createAccessibilityNodeInfo(r2)
            return r2
        L14:
            java.lang.Integer r2 = r1.k
            if (r2 == 0) goto L1d
        L18:
            int r2 = r2.intValue()
            goto Lf
        L1d:
            io.flutter.view.c$l r2 = r1.i
            if (r2 == 0) goto L22
            goto Lb
        L22:
            java.lang.Integer r2 = r1.j
            if (r2 == 0) goto L27
            goto L18
        L27:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.view.c.findFocus(int):android.view.accessibility.AccessibilityNodeInfo");
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public boolean performAction(int i2, int i3, Bundle bundle) {
        int i4;
        int i5 = Build.VERSION.SDK_INT;
        if (i2 >= 65536) {
            boolean performAction = this.d.performAction(i2, i3, bundle);
            if (performAction && i3 == 128) {
                this.j = null;
            }
            return performAction;
        }
        l lVar = this.g.get(Integer.valueOf(i2));
        boolean z = false;
        if (lVar == null) {
            return false;
        }
        switch (i3) {
            case 16:
                this.f338b.b(i2, g.TAP);
                return true;
            case 32:
                this.f338b.b(i2, g.LONG_PRESS);
                return true;
            case 64:
                this.f338b.b(i2, g.DID_GAIN_ACCESSIBILITY_FOCUS);
                L(i2, 32768);
                if (this.i == null) {
                    this.f337a.invalidate();
                }
                this.i = lVar;
                if (lVar.l0(g.INCREASE) || lVar.l0(g.DECREASE)) {
                    L(i2, 4);
                }
                return true;
            case 128:
                this.f338b.b(i2, g.DID_LOSE_ACCESSIBILITY_FOCUS);
                L(i2, 65536);
                this.i = null;
                this.j = null;
                return true;
            case 256:
                if (i5 < 18) {
                    return false;
                }
                return H(lVar, i2, bundle, true);
            case 512:
                if (i5 < 18) {
                    return false;
                }
                return H(lVar, i2, bundle, false);
            case 4096:
                g gVar = g.SCROLL_UP;
                if (!lVar.l0(gVar)) {
                    gVar = g.SCROLL_LEFT;
                    if (!lVar.l0(gVar)) {
                        gVar = g.INCREASE;
                        if (!lVar.l0(gVar)) {
                            return false;
                        }
                        lVar.q = lVar.s;
                        lVar.r = lVar.t;
                        L(i2, 4);
                    }
                }
                this.f338b.b(i2, gVar);
                return true;
            case 8192:
                g gVar2 = g.SCROLL_DOWN;
                if (!lVar.l0(gVar2)) {
                    gVar2 = g.SCROLL_RIGHT;
                    if (!lVar.l0(gVar2)) {
                        gVar2 = g.DECREASE;
                        if (!lVar.l0(gVar2)) {
                            return false;
                        }
                        lVar.q = lVar.u;
                        lVar.r = lVar.v;
                        L(i2, 4);
                    }
                }
                this.f338b.b(i2, gVar2);
                return true;
            case 16384:
                this.f338b.b(i2, g.COPY);
                return true;
            case 32768:
                this.f338b.b(i2, g.PASTE);
                return true;
            case 65536:
                this.f338b.b(i2, g.CUT);
                return true;
            case 131072:
                if (i5 < 18) {
                    return false;
                }
                HashMap hashMap = new HashMap();
                if (bundle != null && bundle.containsKey("ACTION_ARGUMENT_SELECTION_START_INT") && bundle.containsKey("ACTION_ARGUMENT_SELECTION_END_INT")) {
                    z = true;
                }
                if (z) {
                    hashMap.put("base", Integer.valueOf(bundle.getInt("ACTION_ARGUMENT_SELECTION_START_INT")));
                    i4 = bundle.getInt("ACTION_ARGUMENT_SELECTION_END_INT");
                } else {
                    hashMap.put("base", Integer.valueOf(lVar.h));
                    i4 = lVar.h;
                }
                hashMap.put("extent", Integer.valueOf(i4));
                this.f338b.c(i2, g.SET_SELECTION, hashMap);
                l lVar2 = this.g.get(Integer.valueOf(i2));
                lVar2.g = ((Integer) hashMap.get("base")).intValue();
                lVar2.h = ((Integer) hashMap.get("extent")).intValue();
                return true;
            case 1048576:
                this.f338b.b(i2, g.DISMISS);
                return true;
            case 2097152:
                if (i5 < 21) {
                    return false;
                }
                return I(lVar, i2, bundle);
            case 16908342:
                this.f338b.b(i2, g.SHOW_ON_SCREEN);
                return true;
            default:
                h hVar = this.h.get(Integer.valueOf(i3 - y));
                if (hVar != null) {
                    this.f338b.c(i2, g.CUSTOM_ACTION, Integer.valueOf(hVar.f350b));
                    return true;
                }
                return false;
        }
    }

    public boolean s(View view, View view2, AccessibilityEvent accessibilityEvent) {
        Integer recordFlutterId;
        if (this.d.requestSendAccessibilityEvent(view, view2, accessibilityEvent) && (recordFlutterId = this.d.getRecordFlutterId(view, accessibilityEvent)) != null) {
            int eventType = accessibilityEvent.getEventType();
            if (eventType == 8) {
                this.k = recordFlutterId;
                this.m = null;
                return true;
            } else if (eventType == 128) {
                this.o = null;
                return true;
            } else if (eventType == 32768) {
                this.j = recordFlutterId;
                this.i = null;
                return true;
            } else if (eventType != 65536) {
                return true;
            } else {
                this.k = null;
                this.j = null;
                return true;
            }
        }
        return false;
    }

    public boolean y() {
        return this.c.isEnabled();
    }

    public boolean z() {
        return this.c.isTouchExplorationEnabled();
    }
}
