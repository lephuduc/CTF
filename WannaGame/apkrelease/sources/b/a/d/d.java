package b.a.d;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.view.View;

/* loaded from: classes.dex */
public final class d {
    public static int a(int i) {
        return Build.VERSION.SDK_INT >= 17 ? View.generateViewId() : i;
    }

    public static Activity b(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return b(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }
}
