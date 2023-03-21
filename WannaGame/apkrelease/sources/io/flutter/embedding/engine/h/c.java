package io.flutter.embedding.engine.h;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.view.WindowManager;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.view.e;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private boolean f177a;

    /* renamed from: b  reason: collision with root package name */
    private C0013c f178b;
    private long c;
    private io.flutter.embedding.engine.h.b d;
    private FlutterJNI e;
    Future<b> f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class a implements Callable<b> {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Context f179a;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: io.flutter.embedding.engine.h.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public class RunnableC0012a implements Runnable {
            RunnableC0012a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.e.prefetchDefaultFontManager();
            }
        }

        a(Context context) {
            this.f179a = context;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public b call() {
            d f = c.this.f(this.f179a);
            c.this.e.loadLibrary();
            Executors.newSingleThreadExecutor().execute(new RunnableC0012a());
            if (f == null) {
                return new b(b.a.d.a.c(this.f179a), b.a.d.a.a(this.f179a), b.a.d.a.b(this.f179a), null);
            }
            f.a();
            throw null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        final String f182a;

        /* renamed from: b  reason: collision with root package name */
        final String f183b;

        private b(String str, String str2, String str3) {
            this.f182a = str;
            this.f183b = str2;
        }

        /* synthetic */ b(String str, String str2, String str3, a aVar) {
            this(str, str2, str3);
        }
    }

    /* renamed from: io.flutter.embedding.engine.h.c$c  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public static class C0013c {

        /* renamed from: a  reason: collision with root package name */
        private String f184a;

        public String a() {
            return this.f184a;
        }
    }

    public c() {
        this(b.a.a.d().c().a());
    }

    public c(FlutterJNI flutterJNI) {
        this.f177a = false;
        this.e = flutterJNI;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public d f(Context context) {
        return null;
    }

    public boolean c() {
        return this.d.e;
    }

    public void d(Context context, String[] strArr) {
        if (this.f177a) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("ensureInitializationComplete must be called on the main thread");
        }
        if (this.f178b == null) {
            throw new IllegalStateException("ensureInitializationComplete must be called after startInitialization");
        }
        try {
            b bVar = this.f.get();
            ArrayList arrayList = new ArrayList();
            arrayList.add("--icu-symbol-prefix=_binary_icudtl_dat");
            StringBuilder sb = new StringBuilder();
            sb.append("--icu-native-lib-path=");
            sb.append(this.d.d);
            String str = File.separator;
            sb.append(str);
            sb.append("libflutter.so");
            arrayList.add(sb.toString());
            if (strArr != null) {
                Collections.addAll(arrayList, strArr);
            }
            arrayList.add("--aot-shared-library-name=" + this.d.f175a);
            arrayList.add("--aot-shared-library-name=" + this.d.d + str + this.d.f175a);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("--cache-dir-path=");
            sb2.append(bVar.f183b);
            arrayList.add(sb2.toString());
            if (this.d.c != null) {
                arrayList.add("--domain-network-policy=" + this.d.c);
            }
            if (this.f178b.a() != null) {
                arrayList.add("--log-tag=" + this.f178b.a());
            }
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            int i = bundle != null ? bundle.getInt("io.flutter.embedding.android.OldGenHeapSize") : 0;
            if (i == 0) {
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
                double d = memoryInfo.totalMem;
                Double.isNaN(d);
                i = (int) ((d / 1000000.0d) / 2.0d);
            }
            arrayList.add("--old-gen-heap-size=" + i);
            if (bundle != null && bundle.getBoolean("io.flutter.embedding.android.EnableSkParagraph")) {
                arrayList.add("--enable-skparagraph");
            }
            this.e.init(context, (String[]) arrayList.toArray(new String[0]), null, bVar.f182a, bVar.f183b, SystemClock.uptimeMillis() - this.c);
            this.f177a = true;
        } catch (Exception e) {
            b.a.b.c("FlutterLoader", "Flutter initialization failed.", e);
            throw new RuntimeException(e);
        }
    }

    public String e() {
        return this.d.f176b;
    }

    public void g(Context context) {
        h(context, new C0013c());
    }

    public void h(Context context, C0013c c0013c) {
        if (this.f178b != null) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("startInitialization must be called on the main thread");
        }
        Context applicationContext = context.getApplicationContext();
        this.f178b = c0013c;
        this.c = SystemClock.uptimeMillis();
        this.d = io.flutter.embedding.engine.h.a.e(applicationContext);
        e.b((WindowManager) applicationContext.getSystemService("window")).c();
        this.f = Executors.newSingleThreadExecutor().submit(new a(applicationContext));
    }
}
