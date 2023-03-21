package io.flutter.plugin.platform;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.view.Window;
import io.flutter.embedding.engine.j.i;
import java.io.FileNotFoundException;
import java.util.List;

/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final Activity f310a;

    /* renamed from: b  reason: collision with root package name */
    private final io.flutter.embedding.engine.j.i f311b;
    private final d c;
    private i.j d;
    private int e;
    final i.h f;

    /* loaded from: classes.dex */
    class a implements i.h {
        a() {
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public void b() {
            e.this.r();
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public void c() {
            e.this.v();
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public void d(int i) {
            e.this.y(i);
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public void e(i.c cVar) {
            e.this.u(cVar);
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public void f(i.g gVar) {
            e.this.B(gVar);
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public void g(List<i.l> list) {
            e.this.x(list);
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public void h() {
            e.this.s();
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public void i(String str) {
            e.this.t(str);
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public boolean j() {
            return e.this.n();
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public void k(i.EnumC0016i enumC0016i) {
            e.this.q(enumC0016i);
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public CharSequence l(i.e eVar) {
            return e.this.p(eVar);
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public void m(i.j jVar) {
            e.this.z(jVar);
        }

        @Override // io.flutter.embedding.engine.j.i.h
        public void n(i.k kVar) {
            e.this.w(kVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class b implements View.OnSystemUiVisibilityChangeListener {
        b() {
        }

        @Override // android.view.View.OnSystemUiVisibilityChangeListener
        public void onSystemUiVisibilityChange(int i) {
            io.flutter.embedding.engine.j.i iVar;
            boolean z;
            if ((i & 4) == 0) {
                iVar = e.this.f311b;
                z = false;
            } else {
                iVar = e.this.f311b;
                z = true;
            }
            iVar.m(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static /* synthetic */ class c {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f314a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f315b;
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[i.d.values().length];
            c = iArr;
            try {
                iArr[i.d.DARK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[i.d.LIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[i.l.values().length];
            f315b = iArr2;
            try {
                iArr2[i.l.TOP_OVERLAYS.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f315b[i.l.BOTTOM_OVERLAYS.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr3 = new int[i.g.values().length];
            f314a = iArr3;
            try {
                iArr3[i.g.STANDARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f314a[i.g.LIGHT_IMPACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f314a[i.g.MEDIUM_IMPACT.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f314a[i.g.HEAVY_IMPACT.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f314a[i.g.SELECTION_CLICK.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* loaded from: classes.dex */
    public interface d {
        boolean b();
    }

    public e(Activity activity, io.flutter.embedding.engine.j.i iVar, d dVar) {
        a aVar = new a();
        this.f = aVar;
        this.f310a = activity;
        this.f311b = iVar;
        iVar.l(aVar);
        this.c = dVar;
        this.e = 1280;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n() {
        ClipDescription primaryClipDescription;
        ClipboardManager clipboardManager = (ClipboardManager) this.f310a.getSystemService("clipboard");
        if (clipboardManager.hasPrimaryClip() && (primaryClipDescription = clipboardManager.getPrimaryClipDescription()) != null) {
            return primaryClipDescription.hasMimeType("text/*");
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CharSequence p(i.e eVar) {
        ClipboardManager clipboardManager = (ClipboardManager) this.f310a.getSystemService("clipboard");
        if (clipboardManager.hasPrimaryClip()) {
            try {
                ClipData primaryClip = clipboardManager.getPrimaryClip();
                if (primaryClip == null) {
                    return null;
                }
                if (eVar != null && eVar != i.e.PLAIN_TEXT) {
                    return null;
                }
                ClipData.Item itemAt = primaryClip.getItemAt(0);
                if (itemAt.getUri() != null) {
                    this.f310a.getContentResolver().openTypedAssetFileDescriptor(itemAt.getUri(), "text/*", null);
                }
                return itemAt.coerceToText(this.f310a);
            } catch (FileNotFoundException unused) {
                return null;
            } catch (SecurityException e) {
                b.a.b.g("PlatformPlugin", "Attempted to get clipboard data that requires additional permission(s).\nSee the exception details for which permission(s) are required, and consider adding them to your Android Manifest as described in:\nhttps://developer.android.com/guide/topics/permissions/overview", e);
                return null;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(i.EnumC0016i enumC0016i) {
        if (enumC0016i == i.EnumC0016i.CLICK) {
            this.f310a.getWindow().getDecorView().playSoundEffect(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        d dVar = this.c;
        if (dVar == null || !dVar.b()) {
            Activity activity = this.f310a;
            if (activity instanceof androidx.activity.d) {
                ((androidx.activity.d) activity).i().b();
                throw null;
            } else {
                activity.finish();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        A();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(String str) {
        ((ClipboardManager) this.f310a.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label?", str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(i.c cVar) {
        int i = Build.VERSION.SDK_INT;
        if (i < 21) {
            return;
        }
        if (i < 28 && i > 21) {
            this.f310a.setTaskDescription(new ActivityManager.TaskDescription(cVar.f209b, (Bitmap) null, cVar.f208a));
        }
        if (i >= 28) {
            this.f310a.setTaskDescription(new ActivityManager.TaskDescription(cVar.f209b, 0, cVar.f208a));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        this.f310a.getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w(i.k kVar) {
        int i = Build.VERSION.SDK_INT;
        int i2 = 1798;
        if (kVar != i.k.LEAN_BACK || i < 16) {
            if (kVar == i.k.IMMERSIVE && i >= 19) {
                i2 = 3846;
            } else if (kVar == i.k.IMMERSIVE_STICKY && i >= 19) {
                i2 = 5894;
            } else if (kVar == i.k.EDGE_TO_EDGE && i >= 16) {
                i2 = 1792;
            }
        }
        this.e = i2;
        A();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(List<i.l> list) {
        int i = (list.size() != 0 || Build.VERSION.SDK_INT < 19) ? 1798 : 5894;
        for (int i2 = 0; i2 < list.size(); i2++) {
            int i3 = c.f315b[list.get(i2).ordinal()];
            if (i3 == 1) {
                i &= -5;
            } else if (i3 == 2) {
                i = i & (-513) & (-3);
            }
        }
        this.e = i;
        A();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y(int i) {
        this.f310a.setRequestedOrientation(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(i.j jVar) {
        Window window = this.f310a.getWindow();
        View decorView = window.getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        int i = Build.VERSION.SDK_INT;
        if (i >= 23) {
            i.d dVar = jVar.f221b;
            if (dVar != null) {
                int i2 = c.c[dVar.ordinal()];
                if (i2 == 1) {
                    systemUiVisibility |= 8192;
                } else if (i2 == 2) {
                    systemUiVisibility &= -8193;
                }
            }
            Integer num = jVar.f220a;
            if (num != null) {
                window.setStatusBarColor(num.intValue());
            }
        }
        boolean z = jVar.c;
        if (!z && i >= 29) {
            window.setStatusBarContrastEnforced(z);
        }
        if (i >= 26) {
            i.d dVar2 = jVar.e;
            if (dVar2 != null) {
                int i3 = c.c[dVar2.ordinal()];
                if (i3 == 1) {
                    systemUiVisibility |= 16;
                } else if (i3 == 2) {
                    systemUiVisibility &= -17;
                }
            }
            Integer num2 = jVar.d;
            if (num2 != null) {
                window.setNavigationBarColor(num2.intValue());
            }
        }
        if (jVar.f != null && i >= 28) {
            window.addFlags(Integer.MIN_VALUE);
            window.clearFlags(134217728);
            window.setNavigationBarDividerColor(jVar.f.intValue());
        }
        boolean z2 = jVar.g;
        if (!z2 && i >= 29) {
            window.setNavigationBarContrastEnforced(z2);
        }
        decorView.setSystemUiVisibility(systemUiVisibility);
        this.d = jVar;
    }

    public void A() {
        this.f310a.getWindow().getDecorView().setSystemUiVisibility(this.e);
        i.j jVar = this.d;
        if (jVar != null) {
            z(jVar);
        }
    }

    void B(i.g gVar) {
        int i;
        int i2 = Build.VERSION.SDK_INT;
        View decorView = this.f310a.getWindow().getDecorView();
        int i3 = c.f314a[gVar.ordinal()];
        int i4 = 1;
        if (i3 != 1) {
            if (i3 != 2) {
                i4 = 3;
                if (i3 != 3) {
                    i4 = 4;
                    if (i3 != 4) {
                        if (i3 != 5 || i2 < 21) {
                            return;
                        }
                    } else if (i2 < 23) {
                        return;
                    } else {
                        i = 6;
                    }
                }
            }
            decorView.performHapticFeedback(i4);
            return;
        }
        i = 0;
        decorView.performHapticFeedback(i);
    }

    public void o() {
        this.f311b.l(null);
    }
}
