package io.flutter.embedding.android;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.Keep;
import io.flutter.embedding.android.k;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class FlutterSplashView extends FrameLayout {
    private static String j = "FlutterSplashView";

    /* renamed from: a  reason: collision with root package name */
    private q f96a;

    /* renamed from: b  reason: collision with root package name */
    private k f97b;
    private View c;
    private Bundle d;
    private String e;
    private String f;
    private final k.d g;
    private final io.flutter.embedding.engine.renderer.b h;
    private final Runnable i;

    @Keep
    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static Parcelable.Creator<SavedState> CREATOR = new a();
        private String previousCompletedSplashIsolate;
        private Bundle splashScreenState;

        /* loaded from: classes.dex */
        static class a implements Parcelable.Creator<SavedState> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.previousCompletedSplashIsolate = parcel.readString();
            this.splashScreenState = parcel.readBundle(getClass().getClassLoader());
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.previousCompletedSplashIsolate);
            parcel.writeBundle(this.splashScreenState);
        }
    }

    /* loaded from: classes.dex */
    class a implements k.d {
        a() {
        }

        @Override // io.flutter.embedding.android.k.d
        public void a(io.flutter.embedding.engine.b bVar) {
            FlutterSplashView.this.f97b.t(this);
            FlutterSplashView flutterSplashView = FlutterSplashView.this;
            flutterSplashView.g(flutterSplashView.f97b, FlutterSplashView.this.f96a);
        }

        @Override // io.flutter.embedding.android.k.d
        public void b() {
        }
    }

    /* loaded from: classes.dex */
    class b implements io.flutter.embedding.engine.renderer.b {
        b() {
        }

        @Override // io.flutter.embedding.engine.renderer.b
        public void c() {
            if (FlutterSplashView.this.f96a != null) {
                FlutterSplashView.this.k();
            }
        }

        @Override // io.flutter.embedding.engine.renderer.b
        public void e() {
        }
    }

    /* loaded from: classes.dex */
    class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            FlutterSplashView flutterSplashView = FlutterSplashView.this;
            flutterSplashView.removeView(flutterSplashView.c);
            FlutterSplashView flutterSplashView2 = FlutterSplashView.this;
            flutterSplashView2.f = flutterSplashView2.e;
        }
    }

    public FlutterSplashView(Context context) {
        this(context, null, 0);
    }

    public FlutterSplashView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = new a();
        this.h = new b();
        this.i = new c();
        setSaveEnabled(true);
    }

    private boolean h() {
        k kVar = this.f97b;
        if (kVar != null) {
            if (kVar.s()) {
                return this.f97b.getAttachedFlutterEngine().h().g() != null && this.f97b.getAttachedFlutterEngine().h().g().equals(this.f);
            }
            throw new IllegalStateException("Cannot determine if splash has completed when no FlutterEngine is attached to our FlutterView. This question depends on an isolate ID to differentiate Flutter experiences.");
        }
        throw new IllegalStateException("Cannot determine if splash has completed when no FlutterView is set.");
    }

    private boolean i() {
        k kVar = this.f97b;
        return (kVar == null || !kVar.s() || this.f97b.q() || h()) ? false : true;
    }

    private boolean j() {
        q qVar;
        k kVar = this.f97b;
        return kVar != null && kVar.s() && (qVar = this.f96a) != null && qVar.b() && l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.e = this.f97b.getAttachedFlutterEngine().h().g();
        String str = j;
        b.a.b.e(str, "Transitioning splash screen to a Flutter UI. Isolate: " + this.e);
        this.f96a.a(this.i);
    }

    private boolean l() {
        k kVar = this.f97b;
        if (kVar != null) {
            if (kVar.s()) {
                return this.f97b.q() && !h();
            }
            throw new IllegalStateException("Cannot determine if previous splash transition was interrupted when no FlutterEngine is attached to our FlutterView. This question depends on an isolate ID to differentiate Flutter experiences.");
        }
        throw new IllegalStateException("Cannot determine if previous splash transition was interrupted when no FlutterView is set.");
    }

    public void g(k kVar, q qVar) {
        k kVar2 = this.f97b;
        if (kVar2 != null) {
            kVar2.u(this.h);
            removeView(this.f97b);
        }
        View view = this.c;
        if (view != null) {
            removeView(view);
        }
        this.f97b = kVar;
        addView(kVar);
        this.f96a = qVar;
        if (qVar != null) {
            if (i()) {
                b.a.b.e(j, "Showing splash screen UI.");
                View c2 = qVar.c(getContext(), this.d);
                this.c = c2;
                addView(c2);
                kVar.i(this.h);
            } else if (!j()) {
                if (kVar.s()) {
                    return;
                }
                b.a.b.e(j, "FlutterView is not yet attached to a FlutterEngine. Showing nothing until a FlutterEngine is attached.");
                kVar.h(this.g);
            } else {
                b.a.b.e(j, "Showing an immediate splash transition to Flutter due to previously interrupted transition.");
                View c3 = qVar.c(getContext(), this.d);
                this.c = c3;
                addView(c3);
                k();
            }
        }
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.f = savedState.previousCompletedSplashIsolate;
        this.d = savedState.splashScreenState;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.previousCompletedSplashIsolate = this.f;
        q qVar = this.f96a;
        savedState.splashScreenState = qVar != null ? qVar.d() : null;
        return savedState;
    }
}
