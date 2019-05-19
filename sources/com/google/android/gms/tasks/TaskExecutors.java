package com.google.android.gms.tasks;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public final class TaskExecutors {
    public static final Executor MAIN_THREAD = new zza();
    static final Executor zzbLG = new C03421();

    /* renamed from: com.google.android.gms.tasks.TaskExecutors$1 */
    class C03421 implements Executor {
        C03421() {
        }

        public void execute(@NonNull Runnable runnable) {
            runnable.run();
        }
    }

    private static final class zza implements Executor {
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        public void execute(@NonNull Runnable runnable) {
            this.mHandler.post(runnable);
        }
    }

    private TaskExecutors() {
    }
}
