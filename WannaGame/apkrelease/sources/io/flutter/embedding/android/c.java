package io.flutter.embedding.android;

import android.animation.Animator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/* loaded from: classes.dex */
public final class c implements q {

    /* renamed from: a  reason: collision with root package name */
    private final Drawable f104a;

    /* renamed from: b  reason: collision with root package name */
    private final ImageView.ScaleType f105b;
    private final long c;
    private b d;

    /* loaded from: classes.dex */
    class a implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Runnable f106a;

        a(c cVar, Runnable runnable) {
            this.f106a = runnable;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f106a.run();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f106a.run();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* loaded from: classes.dex */
    public static class b extends ImageView {
        public b(Context context) {
            this(context, null, 0);
        }

        public b(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
        }

        public void a(Drawable drawable, ImageView.ScaleType scaleType) {
            setScaleType(scaleType);
            setImageDrawable(drawable);
        }

        public void setSplashDrawable(Drawable drawable) {
            a(drawable, ImageView.ScaleType.FIT_XY);
        }
    }

    public c(Drawable drawable) {
        this(drawable, ImageView.ScaleType.FIT_XY, 500L);
    }

    public c(Drawable drawable, ImageView.ScaleType scaleType, long j) {
        this.f104a = drawable;
        this.f105b = scaleType;
        this.c = j;
    }

    @Override // io.flutter.embedding.android.q
    public void a(Runnable runnable) {
        b bVar = this.d;
        if (bVar == null) {
            runnable.run();
        } else {
            bVar.animate().alpha(0.0f).setDuration(this.c).setListener(new a(this, runnable));
        }
    }

    @Override // io.flutter.embedding.android.q
    public /* synthetic */ boolean b() {
        return p.a(this);
    }

    @Override // io.flutter.embedding.android.q
    public View c(Context context, Bundle bundle) {
        b bVar = new b(context);
        this.d = bVar;
        bVar.a(this.f104a, this.f105b);
        return this.d;
    }

    @Override // io.flutter.embedding.android.q
    public /* synthetic */ Bundle d() {
        return p.b(this);
    }
}
