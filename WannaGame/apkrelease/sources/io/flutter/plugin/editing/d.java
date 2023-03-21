package io.flutter.plugin.editing;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewStructure;
import android.view.WindowInsets;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import io.flutter.embedding.android.m;
import io.flutter.embedding.engine.j.n;
import io.flutter.plugin.editing.c;
import io.flutter.plugin.platform.k;
import java.util.HashMap;

/* loaded from: classes.dex */
public class d implements c.b {

    /* renamed from: a  reason: collision with root package name */
    private final View f284a;

    /* renamed from: b  reason: collision with root package name */
    private final InputMethodManager f285b;
    private final AutofillManager c;
    private final n d;
    private c e = new c(c.a.NO_TARGET, 0);
    private n.b f;
    private SparseArray<n.b> g;
    private io.flutter.plugin.editing.c h;
    private boolean i;
    private InputConnection j;
    private k k;
    private Rect l;
    private ImeSyncDeferringInsetsCallback m;
    private n.e n;
    private boolean o;

    /* loaded from: classes.dex */
    class a implements n.f {
        a() {
        }

        @Override // io.flutter.embedding.engine.j.n.f
        public void a(String str, Bundle bundle) {
            d.this.B(str, bundle);
        }

        @Override // io.flutter.embedding.engine.j.n.f
        public void b() {
            d.this.x();
        }

        @Override // io.flutter.embedding.engine.j.n.f
        public void c() {
            d.this.m();
        }

        @Override // io.flutter.embedding.engine.j.n.f
        public void d() {
            d dVar = d.this;
            dVar.F(dVar.f284a);
        }

        @Override // io.flutter.embedding.engine.j.n.f
        public void e(int i, n.b bVar) {
            d.this.D(i, bVar);
        }

        @Override // io.flutter.embedding.engine.j.n.f
        public void f(int i, boolean z) {
            d.this.C(i, z);
        }

        @Override // io.flutter.embedding.engine.j.n.f
        public void g(double d, double d2, double[] dArr) {
            d.this.A(d, d2, dArr);
        }

        @Override // io.flutter.embedding.engine.j.n.f
        public void h(boolean z) {
            if (Build.VERSION.SDK_INT < 26 || d.this.c == null) {
                return;
            }
            if (z) {
                d.this.c.commit();
            } else {
                d.this.c.cancel();
            }
        }

        @Override // io.flutter.embedding.engine.j.n.f
        public void i() {
            if (d.this.e.f289a == c.a.HC_PLATFORM_VIEW) {
                d.this.y();
                return;
            }
            d dVar = d.this;
            dVar.s(dVar.f284a);
        }

        @Override // io.flutter.embedding.engine.j.n.f
        public void j(n.e eVar) {
            d dVar = d.this;
            dVar.E(dVar.f284a, eVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements InterfaceC0021d {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ boolean f287a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ double[] f288b;
        final /* synthetic */ double[] c;

        b(d dVar, boolean z, double[] dArr, double[] dArr2) {
            this.f287a = z;
            this.f288b = dArr;
            this.c = dArr2;
        }

        @Override // io.flutter.plugin.editing.d.InterfaceC0021d
        public void a(double d, double d2) {
            double d3 = 1.0d;
            if (!this.f287a) {
                double[] dArr = this.f288b;
                d3 = 1.0d / (((dArr[3] * d) + (dArr[7] * d2)) + dArr[15]);
            }
            double[] dArr2 = this.f288b;
            double d4 = ((dArr2[0] * d) + (dArr2[4] * d2) + dArr2[12]) * d3;
            double d5 = ((dArr2[1] * d) + (dArr2[5] * d2) + dArr2[13]) * d3;
            double[] dArr3 = this.c;
            if (d4 < dArr3[0]) {
                dArr3[0] = d4;
            } else if (d4 > dArr3[1]) {
                dArr3[1] = d4;
            }
            if (d5 < dArr3[2]) {
                dArr3[2] = d5;
            } else if (d5 > dArr3[3]) {
                dArr3[3] = d5;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class c {

        /* renamed from: a  reason: collision with root package name */
        a f289a;

        /* renamed from: b  reason: collision with root package name */
        int f290b;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public enum a {
            NO_TARGET,
            FRAMEWORK_CLIENT,
            VD_PLATFORM_VIEW,
            HC_PLATFORM_VIEW
        }

        public c(a aVar, int i) {
            this.f289a = aVar;
            this.f290b = i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: io.flutter.plugin.editing.d$d  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0021d {
        void a(double d, double d2);
    }

    @SuppressLint({"NewApi"})
    public d(View view, n nVar, k kVar) {
        this.f284a = view;
        this.f285b = (InputMethodManager) view.getContext().getSystemService("input_method");
        int i = Build.VERSION.SDK_INT;
        this.c = i >= 26 ? (AutofillManager) view.getContext().getSystemService(AutofillManager.class) : null;
        if (i >= 30) {
            int navigationBars = (view.getWindowSystemUiVisibility() & 2) == 0 ? 0 | WindowInsets.Type.navigationBars() : 0;
            ImeSyncDeferringInsetsCallback imeSyncDeferringInsetsCallback = new ImeSyncDeferringInsetsCallback(view, (view.getWindowSystemUiVisibility() & 4) == 0 ? navigationBars | WindowInsets.Type.statusBars() : navigationBars, WindowInsets.Type.ime());
            this.m = imeSyncDeferringInsetsCallback;
            imeSyncDeferringInsetsCallback.install();
        }
        this.d = nVar;
        nVar.l(new a());
        nVar.i();
        this.k = kVar;
        kVar.v(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A(double d, double d2, double[] dArr) {
        double[] dArr2 = new double[4];
        boolean z = dArr[3] == 0.0d && dArr[7] == 0.0d && dArr[15] == 1.0d;
        double d3 = dArr[12] / dArr[15];
        dArr2[1] = d3;
        dArr2[0] = d3;
        double d4 = dArr[13] / dArr[15];
        dArr2[3] = d4;
        dArr2[2] = d4;
        b bVar = new b(this, z, dArr, dArr2);
        bVar.a(d, 0.0d);
        bVar.a(d, d2);
        bVar.a(0.0d, d2);
        Float valueOf = Float.valueOf(this.f284a.getContext().getResources().getDisplayMetrics().density);
        double d5 = dArr2[0];
        double floatValue = valueOf.floatValue();
        Double.isNaN(floatValue);
        double d6 = dArr2[2];
        double floatValue2 = valueOf.floatValue();
        Double.isNaN(floatValue2);
        double d7 = dArr2[1];
        double floatValue3 = valueOf.floatValue();
        Double.isNaN(floatValue3);
        double d8 = dArr2[3];
        double floatValue4 = valueOf.floatValue();
        Double.isNaN(floatValue4);
        this.l = new Rect((int) (d5 * floatValue), (int) (d6 * floatValue2), (int) Math.ceil(d7 * floatValue3), (int) Math.ceil(d8 * floatValue4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C(int i, boolean z) {
        if (!z) {
            this.e = new c(c.a.HC_PLATFORM_VIEW, i);
            this.j = null;
            return;
        }
        this.f284a.requestFocus();
        this.e = new c(c.a.VD_PLATFORM_VIEW, i);
        this.f285b.restartInput(this.f284a);
        this.i = false;
    }

    private void H(n.b bVar) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        if (bVar == null || bVar.i == null) {
            this.g = null;
            return;
        }
        n.b[] bVarArr = bVar.j;
        SparseArray<n.b> sparseArray = new SparseArray<>();
        this.g = sparseArray;
        if (bVarArr == null) {
            sparseArray.put(bVar.i.f252a.hashCode(), bVar);
            return;
        }
        for (n.b bVar2 : bVarArr) {
            n.b.a aVar = bVar2.i;
            if (aVar != null) {
                this.g.put(aVar.f252a.hashCode(), bVar2);
                this.c.notifyValueChanged(this.f284a, aVar.f252a.hashCode(), AutofillValue.forText(aVar.c.f258a));
            }
        }
    }

    private boolean k() {
        n.c cVar;
        n.b bVar = this.f;
        return bVar == null || (cVar = bVar.f) == null || cVar.f254a != n.g.NONE;
    }

    private static boolean n(n.e eVar, n.e eVar2) {
        int i = eVar.e - eVar.d;
        if (i != eVar2.e - eVar2.d) {
            return true;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (eVar.f258a.charAt(eVar.d + i2) != eVar2.f258a.charAt(eVar2.d + i2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s(View view) {
        y();
        this.f285b.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    private static int t(n.c cVar, boolean z, boolean z2, boolean z3, boolean z4, n.d dVar) {
        n.g gVar = cVar.f254a;
        if (gVar == n.g.DATETIME) {
            return 4;
        }
        if (gVar == n.g.NUMBER) {
            int i = cVar.f255b ? 4098 : 2;
            return cVar.c ? i | 8192 : i;
        } else if (gVar == n.g.PHONE) {
            return 3;
        } else {
            if (gVar == n.g.NONE) {
                return 0;
            }
            int i2 = 1;
            if (gVar == n.g.MULTILINE) {
                i2 = 131073;
            } else if (gVar == n.g.EMAIL_ADDRESS) {
                i2 = 33;
            } else if (gVar == n.g.URL) {
                i2 = 17;
            } else if (gVar == n.g.VISIBLE_PASSWORD) {
                i2 = 145;
            } else if (gVar == n.g.NAME) {
                i2 = 97;
            } else if (gVar == n.g.POSTAL_ADDRESS) {
                i2 = 113;
            }
            if (z) {
                i2 = i2 | 524288 | 128;
            } else {
                if (z2) {
                    i2 |= 32768;
                }
                if (!z3) {
                    i2 |= 524288;
                }
            }
            return dVar == n.d.CHARACTERS ? i2 | 4096 : dVar == n.d.WORDS ? i2 | 8192 : dVar == n.d.SENTENCES ? i2 | 16384 : i2;
        }
    }

    private boolean v() {
        return this.g != null;
    }

    private void w(String str) {
        if (Build.VERSION.SDK_INT < 26 || this.c == null || !v()) {
            return;
        }
        this.c.notifyValueChanged(this.f284a, this.f.i.f252a.hashCode(), AutofillValue.forText(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        if (Build.VERSION.SDK_INT < 26 || this.c == null || !v()) {
            return;
        }
        String str = this.f.i.f252a;
        int[] iArr = new int[2];
        this.f284a.getLocationOnScreen(iArr);
        Rect rect = new Rect(this.l);
        rect.offset(iArr[0], iArr[1]);
        this.c.notifyViewEntered(this.f284a, str.hashCode(), rect);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        n.b bVar;
        if (Build.VERSION.SDK_INT < 26 || this.c == null || (bVar = this.f) == null || bVar.i == null || !v()) {
            return;
        }
        this.c.notifyViewExited(this.f284a, this.f.i.f252a.hashCode());
    }

    public void B(String str, Bundle bundle) {
        this.f285b.sendAppPrivateCommand(this.f284a, str, bundle);
    }

    void D(int i, n.b bVar) {
        y();
        this.f = bVar;
        this.e = k() ? new c(c.a.FRAMEWORK_CLIENT, i) : new c(c.a.NO_TARGET, i);
        io.flutter.plugin.editing.c cVar = this.h;
        if (cVar != null) {
            cVar.j(this);
        }
        n.b.a aVar = bVar.i;
        this.h = new io.flutter.plugin.editing.c(aVar != null ? aVar.c : null, this.f284a);
        H(bVar);
        this.i = true;
        G();
        this.l = null;
        this.h.a(this);
    }

    void E(View view, n.e eVar) {
        n.e eVar2;
        if (!this.i && (eVar2 = this.n) != null && eVar2.b()) {
            boolean n = n(this.n, eVar);
            this.i = n;
            if (n) {
                b.a.b.d("TextInputPlugin", "Composing region changed by the framework. Restarting the input method.");
            }
        }
        this.n = eVar;
        this.h.l(eVar);
        if (this.i) {
            this.f285b.restartInput(view);
            this.i = false;
        }
    }

    void F(View view) {
        if (!k()) {
            s(view);
            return;
        }
        view.requestFocus();
        this.f285b.showSoftInput(view, 0);
    }

    public void G() {
        this.o = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0047, code lost:
        if (r7 == r0.e) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    @Override // io.flutter.plugin.editing.c.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(boolean r9, boolean r10, boolean r11) {
        /*
            r8 = this;
            if (r9 == 0) goto Lb
            io.flutter.plugin.editing.c r9 = r8.h
            java.lang.String r9 = r9.toString()
            r8.w(r9)
        Lb:
            io.flutter.plugin.editing.c r9 = r8.h
            int r9 = r9.g()
            io.flutter.plugin.editing.c r10 = r8.h
            int r10 = r10.f()
            io.flutter.plugin.editing.c r11 = r8.h
            int r11 = r11.e()
            io.flutter.plugin.editing.c r0 = r8.h
            int r7 = r0.d()
            io.flutter.embedding.engine.j.n$e r0 = r8.n
            if (r0 == 0) goto L4c
            io.flutter.plugin.editing.c r0 = r8.h
            java.lang.String r0 = r0.toString()
            io.flutter.embedding.engine.j.n$e r1 = r8.n
            java.lang.String r1 = r1.f258a
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L4a
            io.flutter.embedding.engine.j.n$e r0 = r8.n
            int r1 = r0.f259b
            if (r9 != r1) goto L4a
            int r1 = r0.c
            if (r10 != r1) goto L4a
            int r1 = r0.d
            if (r11 != r1) goto L4a
            int r0 = r0.e
            if (r7 != r0) goto L4a
            goto L4c
        L4a:
            r0 = 0
            goto L4d
        L4c:
            r0 = 1
        L4d:
            if (r0 != 0) goto L90
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "send EditingState to flutter: "
            r0.append(r1)
            io.flutter.plugin.editing.c r1 = r8.h
            java.lang.String r1 = r1.toString()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "TextInputPlugin"
            b.a.b.e(r1, r0)
            io.flutter.embedding.engine.j.n r0 = r8.d
            io.flutter.plugin.editing.d$c r1 = r8.e
            int r1 = r1.f290b
            io.flutter.plugin.editing.c r2 = r8.h
            java.lang.String r2 = r2.toString()
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r7
            r0.n(r1, r2, r3, r4, r5, r6)
            io.flutter.embedding.engine.j.n$e r6 = new io.flutter.embedding.engine.j.n$e
            io.flutter.plugin.editing.c r0 = r8.h
            java.lang.String r1 = r0.toString()
            r0 = r6
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r7
            r0.<init>(r1, r2, r3, r4, r5)
            r8.n = r6
        L90:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.flutter.plugin.editing.d.a(boolean, boolean, boolean):void");
    }

    public void j(SparseArray<AutofillValue> sparseArray) {
        n.b.a aVar;
        n.b.a aVar2;
        if (Build.VERSION.SDK_INT >= 26 && (aVar = this.f.i) != null) {
            HashMap<String, n.e> hashMap = new HashMap<>();
            for (int i = 0; i < sparseArray.size(); i++) {
                n.b bVar = this.g.get(sparseArray.keyAt(i));
                if (bVar != null && (aVar2 = bVar.i) != null) {
                    String charSequence = sparseArray.valueAt(i).getTextValue().toString();
                    n.e eVar = new n.e(charSequence, charSequence.length(), charSequence.length(), -1, -1);
                    if (aVar2.f252a.equals(aVar.f252a)) {
                        this.h.l(eVar);
                    } else {
                        hashMap.put(aVar2.f252a, eVar);
                    }
                }
            }
            this.d.o(this.e.f290b, hashMap);
        }
    }

    public void l(int i) {
        c cVar = this.e;
        c.a aVar = cVar.f289a;
        if ((aVar == c.a.VD_PLATFORM_VIEW || aVar == c.a.HC_PLATFORM_VIEW) && cVar.f290b == i) {
            this.e = new c(c.a.NO_TARGET, 0);
            y();
            this.f285b.hideSoftInputFromWindow(this.f284a.getApplicationWindowToken(), 0);
            this.f285b.restartInput(this.f284a);
            this.i = false;
        }
    }

    void m() {
        if (this.e.f289a == c.a.VD_PLATFORM_VIEW) {
            return;
        }
        this.h.j(this);
        y();
        H(null);
        this.e = new c(c.a.NO_TARGET, 0);
        G();
        this.l = null;
    }

    public InputConnection o(View view, m mVar, EditorInfo editorInfo) {
        c cVar = this.e;
        c.a aVar = cVar.f289a;
        if (aVar == c.a.NO_TARGET) {
            this.j = null;
            return null;
        } else if (aVar == c.a.HC_PLATFORM_VIEW) {
            return null;
        } else {
            if (aVar == c.a.VD_PLATFORM_VIEW) {
                if (this.o) {
                    return this.j;
                }
                InputConnection onCreateInputConnection = this.k.a(Integer.valueOf(cVar.f290b)).onCreateInputConnection(editorInfo);
                this.j = onCreateInputConnection;
                return onCreateInputConnection;
            }
            n.b bVar = this.f;
            int t = t(bVar.f, bVar.f250a, bVar.f251b, bVar.c, bVar.d, bVar.e);
            editorInfo.inputType = t;
            editorInfo.imeOptions = 33554432;
            if (Build.VERSION.SDK_INT >= 26 && !this.f.d) {
                editorInfo.imeOptions = 33554432 | 16777216;
            }
            Integer num = this.f.g;
            int intValue = num == null ? (t & 131072) != 0 ? 1 : 6 : num.intValue();
            String str = this.f.h;
            if (str != null) {
                editorInfo.actionLabel = str;
                editorInfo.actionId = intValue;
            }
            editorInfo.imeOptions = intValue | editorInfo.imeOptions;
            io.flutter.plugin.editing.b bVar2 = new io.flutter.plugin.editing.b(view, this.e.f290b, this.d, mVar, this.h, editorInfo);
            editorInfo.initialSelStart = this.h.g();
            editorInfo.initialSelEnd = this.h.f();
            this.j = bVar2;
            return bVar2;
        }
    }

    @SuppressLint({"NewApi"})
    public void p() {
        this.k.E();
        this.d.l(null);
        y();
        io.flutter.plugin.editing.c cVar = this.h;
        if (cVar != null) {
            cVar.j(this);
        }
        ImeSyncDeferringInsetsCallback imeSyncDeferringInsetsCallback = this.m;
        if (imeSyncDeferringInsetsCallback != null) {
            imeSyncDeferringInsetsCallback.remove();
        }
    }

    public InputMethodManager q() {
        return this.f285b;
    }

    public boolean r(KeyEvent keyEvent) {
        InputConnection inputConnection;
        if (!q().isAcceptingText() || (inputConnection = this.j) == null) {
            return false;
        }
        return inputConnection instanceof io.flutter.plugin.editing.b ? ((io.flutter.plugin.editing.b) inputConnection).f(keyEvent) : inputConnection.sendKeyEvent(keyEvent);
    }

    public void u() {
        if (this.e.f289a == c.a.VD_PLATFORM_VIEW) {
            this.o = true;
        }
    }

    public void z(ViewStructure viewStructure, int i) {
        ViewStructure viewStructure2;
        CharSequence charSequence;
        Rect rect;
        if (Build.VERSION.SDK_INT < 26 || !v()) {
            return;
        }
        String str = this.f.i.f252a;
        AutofillId autofillId = viewStructure.getAutofillId();
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            int keyAt = this.g.keyAt(i2);
            n.b.a aVar = this.g.valueAt(i2).i;
            if (aVar != null) {
                viewStructure.addChildCount(1);
                ViewStructure newChild = viewStructure.newChild(i2);
                newChild.setAutofillId(autofillId, keyAt);
                newChild.setAutofillHints(aVar.f253b);
                newChild.setAutofillType(1);
                newChild.setVisibility(0);
                if (str.hashCode() != keyAt || (rect = this.l) == null) {
                    viewStructure2 = newChild;
                    viewStructure2.setDimens(0, 0, 0, 0, 1, 1);
                    charSequence = aVar.c.f258a;
                } else {
                    viewStructure2 = newChild;
                    newChild.setDimens(rect.left, rect.top, 0, 0, rect.width(), this.l.height());
                    charSequence = this.h;
                }
                viewStructure2.setAutofillValue(AutofillValue.forText(charSequence));
            }
        }
    }
}
