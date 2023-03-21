package io.flutter.plugin.editing;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build;
import android.os.Bundle;
import android.text.DynamicLayout;
import android.text.Editable;
import android.text.Layout;
import android.text.Selection;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CursorAnchorInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputMethodManager;
import io.flutter.embedding.android.m;
import io.flutter.embedding.engine.FlutterJNI;
import io.flutter.embedding.engine.j.n;
import io.flutter.plugin.editing.c;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b extends BaseInputConnection implements c.b {

    /* renamed from: a  reason: collision with root package name */
    private final View f279a;

    /* renamed from: b  reason: collision with root package name */
    private final int f280b;
    private final n c;
    private final c d;
    private final EditorInfo e;
    private ExtractedTextRequest f;
    private boolean g;
    private CursorAnchorInfo.Builder h;
    private ExtractedText i;
    private InputMethodManager j;
    private final Layout k;
    private a l;
    private final m m;
    private int n;

    public b(View view, int i, n nVar, m mVar, c cVar, EditorInfo editorInfo) {
        this(view, i, nVar, mVar, cVar, editorInfo, new FlutterJNI());
    }

    public b(View view, int i, n nVar, m mVar, c cVar, EditorInfo editorInfo, FlutterJNI flutterJNI) {
        super(view, true);
        this.g = false;
        this.i = new ExtractedText();
        this.n = 0;
        this.f279a = view;
        this.f280b = i;
        this.c = nVar;
        this.d = cVar;
        cVar.a(this);
        this.e = editorInfo;
        this.m = mVar;
        this.l = new a(flutterJNI);
        this.k = new DynamicLayout(cVar, new TextPaint(), Integer.MAX_VALUE, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        this.j = (InputMethodManager) view.getContext().getSystemService("input_method");
    }

    private boolean b(int i) {
        if (i == 16908319) {
            setSelection(0, this.d.length());
            return true;
        } else if (i == 16908320) {
            int selectionStart = Selection.getSelectionStart(this.d);
            int selectionEnd = Selection.getSelectionEnd(this.d);
            if (selectionStart != selectionEnd) {
                int min = Math.min(selectionStart, selectionEnd);
                int max = Math.max(selectionStart, selectionEnd);
                ((ClipboardManager) this.f279a.getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label?", this.d.subSequence(min, max)));
                this.d.delete(min, max);
                setSelection(min, min);
            }
            return true;
        } else if (i == 16908321) {
            int selectionStart2 = Selection.getSelectionStart(this.d);
            int selectionEnd2 = Selection.getSelectionEnd(this.d);
            if (selectionStart2 != selectionEnd2) {
                ((ClipboardManager) this.f279a.getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label?", this.d.subSequence(Math.min(selectionStart2, selectionEnd2), Math.max(selectionStart2, selectionEnd2))));
            }
            return true;
        } else if (i == 16908322) {
            ClipData primaryClip = ((ClipboardManager) this.f279a.getContext().getSystemService("clipboard")).getPrimaryClip();
            if (primaryClip != null) {
                CharSequence coerceToText = primaryClip.getItemAt(0).coerceToText(this.f279a.getContext());
                int max2 = Math.max(0, Selection.getSelectionStart(this.d));
                int max3 = Math.max(0, Selection.getSelectionEnd(this.d));
                int min2 = Math.min(max2, max3);
                int max4 = Math.max(max2, max3);
                if (min2 != max4) {
                    this.d.delete(min2, max4);
                }
                this.d.insert(min2, coerceToText);
                int length = min2 + coerceToText.length();
                setSelection(length, length);
            }
            return true;
        } else {
            return false;
        }
    }

    private CursorAnchorInfo c() {
        if (Build.VERSION.SDK_INT < 21) {
            return null;
        }
        CursorAnchorInfo.Builder builder = this.h;
        if (builder == null) {
            this.h = new CursorAnchorInfo.Builder();
        } else {
            builder.reset();
        }
        this.h.setSelectionRange(this.d.g(), this.d.f());
        int e = this.d.e();
        int d = this.d.d();
        if (e < 0 || d <= e) {
            this.h.setComposingText(-1, "");
        } else {
            this.h.setComposingText(e, this.d.toString().subSequence(e, d));
        }
        return this.h.build();
    }

    private ExtractedText d(ExtractedTextRequest extractedTextRequest) {
        ExtractedText extractedText = this.i;
        extractedText.startOffset = 0;
        extractedText.partialStartOffset = -1;
        extractedText.partialEndOffset = -1;
        extractedText.selectionStart = this.d.g();
        this.i.selectionEnd = this.d.f();
        this.i.text = (extractedTextRequest == null || (extractedTextRequest.flags & 1) == 0) ? this.d.toString() : this.d;
        return this.i;
    }

    private boolean e(boolean z, boolean z2) {
        int selectionStart = Selection.getSelectionStart(this.d);
        int selectionEnd = Selection.getSelectionEnd(this.d);
        boolean z3 = false;
        if (selectionStart < 0 || selectionEnd < 0) {
            return false;
        }
        int max = z ? Math.max(this.l.b(this.d, selectionEnd), 0) : Math.min(this.l.a(this.d, selectionEnd), this.d.length());
        if (selectionStart == selectionEnd && !z2) {
            z3 = true;
        }
        if (z3) {
            setSelection(max, max);
        } else {
            setSelection(selectionStart, max);
        }
        return true;
    }

    private boolean g(boolean z, boolean z2) {
        int selectionStart = Selection.getSelectionStart(this.d);
        int selectionEnd = Selection.getSelectionEnd(this.d);
        boolean z3 = false;
        if (selectionStart < 0 || selectionEnd < 0) {
            return false;
        }
        if (selectionStart == selectionEnd && !z2) {
            z3 = true;
        }
        beginBatchEdit();
        if (z3) {
            if (z) {
                Selection.moveUp(this.d, this.k);
            } else {
                Selection.moveDown(this.d, this.k);
            }
            int selectionStart2 = Selection.getSelectionStart(this.d);
            setSelection(selectionStart2, selectionStart2);
        } else {
            if (z) {
                Selection.extendUp(this.d, this.k);
            } else {
                Selection.extendDown(this.d, this.k);
            }
            setSelection(Selection.getSelectionStart(this.d), Selection.getSelectionEnd(this.d));
        }
        endBatchEdit();
        return true;
    }

    @Override // io.flutter.plugin.editing.c.b
    public void a(boolean z, boolean z2, boolean z3) {
        this.j.updateSelection(this.f279a, this.d.g(), this.d.f(), this.d.e(), this.d.d());
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        ExtractedTextRequest extractedTextRequest = this.f;
        if (extractedTextRequest != null) {
            this.j.updateExtractedText(this.f279a, extractedTextRequest.token, d(extractedTextRequest));
        }
        if (this.g) {
            this.j.updateCursorAnchorInfo(this.f279a, c());
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean beginBatchEdit() {
        this.d.b();
        this.n++;
        return super.beginBatchEdit();
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean clearMetaKeyStates(int i) {
        return super.clearMetaKeyStates(i);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public void closeConnection() {
        super.closeConnection();
        this.d.j(this);
        while (this.n > 0) {
            endBatchEdit();
            this.n--;
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean commitText(CharSequence charSequence, int i) {
        return super.commitText(charSequence, i);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingText(int i, int i2) {
        if (this.d.g() == -1) {
            return true;
        }
        return super.deleteSurroundingText(i, i2);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        return super.deleteSurroundingTextInCodePoints(i, i2);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean endBatchEdit() {
        boolean endBatchEdit = super.endBatchEdit();
        this.n--;
        this.d.c();
        return endBatchEdit;
    }

    public boolean f(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            if (keyEvent.getAction() == 1 && (keyEvent.getKeyCode() == 59 || keyEvent.getKeyCode() == 60)) {
                int selectionEnd = Selection.getSelectionEnd(this.d);
                setSelection(selectionEnd, selectionEnd);
                return true;
            }
            return false;
        } else if (keyEvent.getKeyCode() == 21) {
            return e(true, keyEvent.isShiftPressed());
        } else {
            if (keyEvent.getKeyCode() == 22) {
                return e(false, keyEvent.isShiftPressed());
            }
            if (keyEvent.getKeyCode() == 19) {
                return g(true, keyEvent.isShiftPressed());
            }
            if (keyEvent.getKeyCode() == 20) {
                return g(false, keyEvent.isShiftPressed());
            }
            if (keyEvent.getKeyCode() == 66 || keyEvent.getKeyCode() == 160) {
                EditorInfo editorInfo = this.e;
                if ((131072 & editorInfo.inputType) == 0) {
                    performEditorAction(editorInfo.imeOptions & 255);
                    return true;
                }
            }
            int selectionStart = Selection.getSelectionStart(this.d);
            int selectionEnd2 = Selection.getSelectionEnd(this.d);
            int unicodeChar = keyEvent.getUnicodeChar();
            if (selectionStart < 0 || selectionEnd2 < 0 || unicodeChar == 0) {
                return false;
            }
            int min = Math.min(selectionStart, selectionEnd2);
            int max = Math.max(selectionStart, selectionEnd2);
            beginBatchEdit();
            if (min != max) {
                this.d.delete(min, max);
            }
            this.d.insert(min, (CharSequence) String.valueOf((char) unicodeChar));
            int i = min + 1;
            setSelection(i, i);
            endBatchEdit();
            return true;
        }
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean finishComposingText() {
        return super.finishComposingText();
    }

    @Override // android.view.inputmethod.BaseInputConnection
    public Editable getEditable() {
        return this.d;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i) {
        boolean z = (i & 1) != 0;
        if (z == (this.f == null)) {
            StringBuilder sb = new StringBuilder();
            sb.append("The input method toggled text monitoring ");
            sb.append(z ? "on" : "off");
            b.a.b.a("InputConnectionAdaptor", sb.toString());
        }
        this.f = z ? extractedTextRequest : null;
        return d(extractedTextRequest);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performContextMenuAction(int i) {
        beginBatchEdit();
        boolean b2 = b(i);
        endBatchEdit();
        return b2;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performEditorAction(int i) {
        if (i == 0) {
            this.c.m(this.f280b);
        } else if (i == 1) {
            this.c.e(this.f280b);
        } else if (i == 2) {
            this.c.d(this.f280b);
        } else if (i == 3) {
            this.c.j(this.f280b);
        } else if (i == 4) {
            this.c.k(this.f280b);
        } else if (i == 5) {
            this.c.f(this.f280b);
        } else if (i != 7) {
            this.c.c(this.f280b);
        } else {
            this.c.h(this.f280b);
        }
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean performPrivateCommand(String str, Bundle bundle) {
        this.c.g(this.f280b, str, bundle);
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean requestCursorUpdates(int i) {
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        if ((i & 1) != 0) {
            this.j.updateCursorAnchorInfo(this.f279a, c());
        }
        boolean z = (i & 2) != 0;
        if (z != this.g) {
            StringBuilder sb = new StringBuilder();
            sb.append("The input method toggled cursor monitoring ");
            sb.append(z ? "on" : "off");
            b.a.b.a("InputConnectionAdaptor", sb.toString());
        }
        this.g = z;
        return true;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean sendKeyEvent(KeyEvent keyEvent) {
        return this.m.c(keyEvent);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setComposingRegion(int i, int i2) {
        return super.setComposingRegion(i, i2);
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setComposingText(CharSequence charSequence, int i) {
        beginBatchEdit();
        boolean commitText = charSequence.length() == 0 ? super.commitText(charSequence, i) : super.setComposingText(charSequence, i);
        endBatchEdit();
        return commitText;
    }

    @Override // android.view.inputmethod.BaseInputConnection, android.view.inputmethod.InputConnection
    public boolean setSelection(int i, int i2) {
        beginBatchEdit();
        boolean selection = super.setSelection(i, i2);
        endBatchEdit();
        return selection;
    }
}
