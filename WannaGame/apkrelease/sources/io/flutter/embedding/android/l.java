package io.flutter.embedding.android;

import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import io.flutter.embedding.android.m;
import io.flutter.embedding.engine.j.d;

/* loaded from: classes.dex */
public class l implements m.c {

    /* renamed from: a  reason: collision with root package name */
    private final io.flutter.embedding.engine.j.d f136a;

    /* renamed from: b  reason: collision with root package name */
    private int f137b;

    public l(io.flutter.embedding.engine.j.d dVar) {
        this.f136a = dVar;
    }

    @Override // io.flutter.embedding.android.m.c
    public void a(KeyEvent keyEvent, final m.c.a aVar) {
        int action = keyEvent.getAction();
        if (action == 0 || action == 1) {
            this.f136a.d(new d.b(keyEvent, b(keyEvent.getUnicodeChar())), action != 0, new d.a() { // from class: io.flutter.embedding.android.a
                @Override // io.flutter.embedding.engine.j.d.a
                public final void a(boolean z) {
                    m.c.a.this.a(Boolean.valueOf(z));
                }
            });
        } else {
            aVar.a(Boolean.FALSE);
        }
    }

    Character b(int i) {
        char c = (char) i;
        if ((Integer.MIN_VALUE & i) != 0) {
            int i2 = i & Integer.MAX_VALUE;
            int i3 = this.f137b;
            if (i3 != 0) {
                i2 = KeyCharacterMap.getDeadChar(i3, i2);
            }
            this.f137b = i2;
        } else {
            int i4 = this.f137b;
            if (i4 != 0) {
                int deadChar = KeyCharacterMap.getDeadChar(i4, i);
                if (deadChar > 0) {
                    c = (char) deadChar;
                }
                this.f137b = 0;
            }
        }
        return Character.valueOf(c);
    }
}
