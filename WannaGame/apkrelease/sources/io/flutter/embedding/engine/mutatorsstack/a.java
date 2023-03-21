package io.flutter.embedding.engine.mutatorsstack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import io.flutter.embedding.android.b;

/* loaded from: classes.dex */
public class a extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private FlutterMutatorsStack f265a;

    /* renamed from: b  reason: collision with root package name */
    private float f266b;
    private int c;
    private int d;
    private int e;
    private int f;
    private final b g;
    ViewTreeObserver.OnGlobalFocusChangeListener h;

    /* renamed from: io.flutter.embedding.engine.mutatorsstack.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    class ViewTreeObserver$OnGlobalFocusChangeListenerC0018a implements ViewTreeObserver.OnGlobalFocusChangeListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ View.OnFocusChangeListener f267a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ View f268b;

        ViewTreeObserver$OnGlobalFocusChangeListenerC0018a(a aVar, View.OnFocusChangeListener onFocusChangeListener, View view) {
            this.f267a = onFocusChangeListener;
            this.f268b = view;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
        public void onGlobalFocusChanged(View view, View view2) {
            View.OnFocusChangeListener onFocusChangeListener = this.f267a;
            View view3 = this.f268b;
            onFocusChangeListener.onFocusChange(view3, a.a(view3));
        }
    }

    public a(Context context, float f, b bVar) {
        super(context, null);
        this.f266b = f;
        this.g = bVar;
    }

    public static boolean a(View view) {
        if (view == null) {
            return false;
        }
        if (view.hasFocus()) {
            return true;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                if (a(viewGroup.getChildAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    private Matrix getPlatformViewMatrix() {
        Matrix matrix = new Matrix(this.f265a.getFinalMatrix());
        float f = this.f266b;
        matrix.preScale(1.0f / f, 1.0f / f);
        matrix.postTranslate(-this.c, -this.d);
        return matrix;
    }

    public void b(FlutterMutatorsStack flutterMutatorsStack, int i, int i2, int i3, int i4) {
        this.f265a = flutterMutatorsStack;
        this.c = i;
        this.d = i2;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i3, i4);
        layoutParams.leftMargin = i;
        layoutParams.topMargin = i2;
        setLayoutParams(layoutParams);
        setWillNotDraw(false);
    }

    public void c() {
        ViewTreeObserver.OnGlobalFocusChangeListener onGlobalFocusChangeListener;
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (!viewTreeObserver.isAlive() || (onGlobalFocusChangeListener = this.h) == null) {
            return;
        }
        this.h = null;
        viewTreeObserver.removeOnGlobalFocusChangeListener(onGlobalFocusChangeListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.concat(getPlatformViewMatrix());
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        canvas.save();
        for (Path path : this.f265a.getFinalClippingPaths()) {
            Path path2 = new Path(path);
            path2.offset(-this.c, -this.d);
            canvas.clipPath(path2);
        }
        super.draw(canvas);
        canvas.restore();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        float f;
        if (this.g == null) {
            return super.onTouchEvent(motionEvent);
        }
        Matrix matrix = new Matrix();
        int action = motionEvent.getAction();
        if (action == 0) {
            int i2 = this.c;
            this.e = i2;
            i = this.d;
            this.f = i;
            f = i2;
        } else if (action == 2) {
            matrix.postTranslate(this.e, this.f);
            this.e = this.c;
            this.f = this.d;
            return this.g.f(motionEvent, matrix);
        } else {
            f = this.c;
            i = this.d;
        }
        matrix.postTranslate(f, i);
        return this.g.f(motionEvent, matrix);
    }

    public void setOnDescendantFocusChangeListener(View.OnFocusChangeListener onFocusChangeListener) {
        c();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive() && this.h == null) {
            ViewTreeObserver$OnGlobalFocusChangeListenerC0018a viewTreeObserver$OnGlobalFocusChangeListenerC0018a = new ViewTreeObserver$OnGlobalFocusChangeListenerC0018a(this, onFocusChangeListener, this);
            this.h = viewTreeObserver$OnGlobalFocusChangeListenerC0018a;
            viewTreeObserver.addOnGlobalFocusChangeListener(viewTreeObserver$OnGlobalFocusChangeListenerC0018a);
        }
    }
}
