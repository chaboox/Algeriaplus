package com.nostra13.universalimageloader.utils;

import android.util.Log;
import com.nostra13.universalimageloader.core.ImageLoader;

/* renamed from: com.nostra13.universalimageloader.utils.L */
public final class C0382L {
    private static final String LOG_FORMAT = "%1$s\n%2$s";
    private static volatile boolean writeDebugLogs = false;
    private static volatile boolean writeLogs = true;

    private C0382L() {
    }

    @Deprecated
    public static void enableLogging() {
        C0382L.writeLogs(true);
    }

    @Deprecated
    public static void disableLogging() {
        C0382L.writeLogs(false);
    }

    public static void writeDebugLogs(boolean writeDebugLogs) {
        writeDebugLogs = writeDebugLogs;
    }

    public static void writeLogs(boolean writeLogs) {
        writeLogs = writeLogs;
    }

    /* renamed from: d */
    public static void m13d(String message, Object... args) {
        if (writeDebugLogs) {
            C0382L.log(3, null, message, args);
        }
    }

    /* renamed from: i */
    public static void m17i(String message, Object... args) {
        C0382L.log(4, null, message, args);
    }

    /* renamed from: w */
    public static void m18w(String message, Object... args) {
        C0382L.log(5, null, message, args);
    }

    /* renamed from: e */
    public static void m15e(Throwable ex) {
        C0382L.log(6, ex, null, new Object[0]);
    }

    /* renamed from: e */
    public static void m14e(String message, Object... args) {
        C0382L.log(6, null, message, args);
    }

    /* renamed from: e */
    public static void m16e(Throwable ex, String message, Object... args) {
        C0382L.log(6, ex, message, args);
    }

    private static void log(int priority, Throwable ex, String message, Object... args) {
        if (writeLogs) {
            String log;
            if (args.length > 0) {
                message = String.format(message, args);
            }
            if (ex == null) {
                log = message;
            } else {
                String logMessage;
                if (message == null) {
                    logMessage = ex.getMessage();
                } else {
                    logMessage = message;
                }
                String logBody = Log.getStackTraceString(ex);
                log = String.format(LOG_FORMAT, new Object[]{logMessage, logBody});
            }
            Log.println(priority, ImageLoader.TAG, log);
        }
    }
}
