package io.flutter.embedding.engine.f;

import b.a.c.a.b;
import io.flutter.embedding.engine.FlutterJNI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class b implements b.a.c.a.b, c {

    /* renamed from: a  reason: collision with root package name */
    private final FlutterJNI f169a;
    private int d = 1;

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, b.a> f170b = new HashMap();
    private final Map<Integer, b.InterfaceC0006b> c = new HashMap();

    /* loaded from: classes.dex */
    static class a implements b.InterfaceC0006b {

        /* renamed from: a  reason: collision with root package name */
        private final FlutterJNI f171a;

        /* renamed from: b  reason: collision with root package name */
        private final int f172b;
        private final AtomicBoolean c = new AtomicBoolean(false);

        a(FlutterJNI flutterJNI, int i) {
            this.f171a = flutterJNI;
            this.f172b = i;
        }

        @Override // b.a.c.a.b.InterfaceC0006b
        public void a(ByteBuffer byteBuffer) {
            if (this.c.getAndSet(true)) {
                throw new IllegalStateException("Reply already submitted");
            }
            if (byteBuffer == null) {
                this.f171a.invokePlatformMessageEmptyResponseCallback(this.f172b);
            } else {
                this.f171a.invokePlatformMessageResponseCallback(this.f172b, byteBuffer, byteBuffer.position());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(FlutterJNI flutterJNI) {
        this.f169a = flutterJNI;
    }

    private static void e(Error error) {
        Thread currentThread = Thread.currentThread();
        if (currentThread.getUncaughtExceptionHandler() == null) {
            throw error;
        }
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, error);
    }

    @Override // b.a.c.a.b
    public void a(String str, ByteBuffer byteBuffer, b.InterfaceC0006b interfaceC0006b) {
        int i;
        b.a.b.e("DartMessenger", "Sending message with callback over channel '" + str + "'");
        if (interfaceC0006b != null) {
            i = this.d;
            this.d = i + 1;
            this.c.put(Integer.valueOf(i), interfaceC0006b);
        } else {
            i = 0;
        }
        if (byteBuffer == null) {
            this.f169a.dispatchEmptyPlatformMessage(str, i);
        } else {
            this.f169a.dispatchPlatformMessage(str, byteBuffer, byteBuffer.position(), i);
        }
    }

    @Override // io.flutter.embedding.engine.f.c
    public void b(String str, ByteBuffer byteBuffer, int i) {
        b.a.b.e("DartMessenger", "Received message from Dart over channel '" + str + "'");
        b.a aVar = this.f170b.get(str);
        if (aVar != null) {
            try {
                b.a.b.e("DartMessenger", "Deferring to registered handler to process message.");
                aVar.a(byteBuffer, new a(this.f169a, i));
                if (byteBuffer == null || !byteBuffer.isDirect()) {
                    return;
                }
                byteBuffer.limit(0);
                return;
            } catch (Error e) {
                e(e);
                return;
            } catch (Exception e2) {
                b.a.b.c("DartMessenger", "Uncaught exception in binary message listener", e2);
            }
        } else {
            b.a.b.e("DartMessenger", "No registered handler for message. Responding to Dart with empty reply message.");
        }
        this.f169a.invokePlatformMessageEmptyResponseCallback(i);
    }

    @Override // b.a.c.a.b
    public void c(String str, b.a aVar) {
        if (aVar == null) {
            b.a.b.e("DartMessenger", "Removing handler for channel '" + str + "'");
            this.f170b.remove(str);
            return;
        }
        b.a.b.e("DartMessenger", "Setting handler for channel '" + str + "'");
        this.f170b.put(str, aVar);
    }

    @Override // io.flutter.embedding.engine.f.c
    public void d(int i, ByteBuffer byteBuffer) {
        b.a.b.e("DartMessenger", "Received message reply from Dart.");
        b.InterfaceC0006b remove = this.c.remove(Integer.valueOf(i));
        if (remove != null) {
            try {
                b.a.b.e("DartMessenger", "Invoking registered callback for reply from Dart.");
                remove.a(byteBuffer);
                if (byteBuffer == null || !byteBuffer.isDirect()) {
                    return;
                }
                byteBuffer.limit(0);
            } catch (Error e) {
                e(e);
            } catch (Exception e2) {
                b.a.b.c("DartMessenger", "Uncaught exception in binary message reply handler", e2);
            }
        }
    }
}
