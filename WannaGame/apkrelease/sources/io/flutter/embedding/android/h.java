package io.flutter.embedding.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.view.Surface;
import android.view.View;
import java.nio.ByteBuffer;

@TargetApi(19)
/* loaded from: classes.dex */
public class h extends View implements io.flutter.embedding.engine.renderer.c {

    /* renamed from: a  reason: collision with root package name */
    private ImageReader f116a;

    /* renamed from: b  reason: collision with root package name */
    private Image f117b;
    private Bitmap c;
    private io.flutter.embedding.engine.renderer.a d;
    private b e;
    private boolean f;

    /* loaded from: classes.dex */
    static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f118a;

        static {
            int[] iArr = new int[b.values().length];
            f118a = iArr;
            try {
                iArr[b.background.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f118a[b.overlay.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum b {
        background,
        overlay
    }

    public h(Context context, int i, int i2, b bVar) {
        this(context, f(i, i2), bVar);
    }

    h(Context context, ImageReader imageReader, b bVar) {
        super(context, null);
        this.f = false;
        this.f116a = imageReader;
        this.e = bVar;
        g();
    }

    private void e() {
        Image image = this.f117b;
        if (image != null) {
            image.close();
            this.f117b = null;
        }
    }

    @TargetApi(19)
    private static ImageReader f(int i, int i2) {
        return Build.VERSION.SDK_INT >= 29 ? ImageReader.newInstance(i, i2, 1, 3, 768L) : ImageReader.newInstance(i, i2, 1, 3);
    }

    private void g() {
        setAlpha(0.0f);
    }

    @TargetApi(29)
    private void i() {
        if (Build.VERSION.SDK_INT >= 29) {
            HardwareBuffer hardwareBuffer = this.f117b.getHardwareBuffer();
            this.c = Bitmap.wrapHardwareBuffer(hardwareBuffer, ColorSpace.get(ColorSpace.Named.SRGB));
            hardwareBuffer.close();
            return;
        }
        Image.Plane[] planes = this.f117b.getPlanes();
        if (planes.length != 1) {
            return;
        }
        Image.Plane plane = planes[0];
        int rowStride = plane.getRowStride() / plane.getPixelStride();
        int height = this.f117b.getHeight();
        Bitmap bitmap = this.c;
        if (bitmap == null || bitmap.getWidth() != rowStride || this.c.getHeight() != height) {
            this.c = Bitmap.createBitmap(rowStride, height, Bitmap.Config.ARGB_8888);
        }
        ByteBuffer buffer = plane.getBuffer();
        buffer.rewind();
        this.c.copyPixelsFromBuffer(buffer);
    }

    @Override // io.flutter.embedding.engine.renderer.c
    public void a(io.flutter.embedding.engine.renderer.a aVar) {
        if (a.f118a[this.e.ordinal()] == 1) {
            aVar.r(this.f116a.getSurface());
        }
        setAlpha(1.0f);
        this.d = aVar;
        this.f = true;
    }

    @Override // io.flutter.embedding.engine.renderer.c
    public void b() {
    }

    @Override // io.flutter.embedding.engine.renderer.c
    public void c() {
        if (this.f) {
            setAlpha(0.0f);
            d();
            this.c = null;
            e();
            invalidate();
            this.f = false;
        }
    }

    @TargetApi(19)
    public boolean d() {
        if (this.f) {
            Image acquireLatestImage = this.f116a.acquireLatestImage();
            if (acquireLatestImage != null) {
                e();
                this.f117b = acquireLatestImage;
                invalidate();
            }
            return acquireLatestImage != null;
        }
        return false;
    }

    @Override // io.flutter.embedding.engine.renderer.c
    public io.flutter.embedding.engine.renderer.a getAttachedRenderer() {
        return this.d;
    }

    public Surface getSurface() {
        return this.f116a.getSurface();
    }

    public void h(int i, int i2) {
        if (this.d == null) {
            return;
        }
        if (i == this.f116a.getWidth() && i2 == this.f116a.getHeight()) {
            return;
        }
        e();
        this.f116a.close();
        this.f116a = f(i, i2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.f117b != null) {
            i();
        }
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (!(i == this.f116a.getWidth() && i2 == this.f116a.getHeight()) && this.e == b.background && this.f) {
            h(i, i2);
            this.d.r(this.f116a.getSurface());
        }
    }
}
