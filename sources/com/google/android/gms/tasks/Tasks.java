package com.google.android.gms.tasks;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzac;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks {

    /* renamed from: com.google.android.gms.tasks.Tasks$1 */
    class C03431 implements Runnable {
        final /* synthetic */ Callable zzWN;
        final /* synthetic */ zzh zzbLL;

        C03431(zzh zzh, Callable callable) {
            this.zzbLL = zzh;
            this.zzWN = callable;
        }

        public void run() {
            try {
                this.zzbLL.setResult(this.zzWN.call());
            } catch (Exception e) {
                this.zzbLL.setException(e);
            }
        }
    }

    interface zzb extends OnFailureListener, OnSuccessListener<Object> {
    }

    private static final class zza implements zzb {
        private final CountDownLatch zzth;

        private zza() {
            this.zzth = new CountDownLatch(1);
        }

        public void await() throws InterruptedException {
            this.zzth.await();
        }

        public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.zzth.await(j, timeUnit);
        }

        public void onFailure(@NonNull Exception exception) {
            this.zzth.countDown();
        }

        public void onSuccess(Object obj) {
            this.zzth.countDown();
        }
    }

    private static final class zzc implements zzb {
        private final zzh<Void> zzbLF;
        private Exception zzbLK;
        private final int zzbLM;
        private int zzbLN;
        private int zzbLO;
        private final Object zzrN = new Object();

        public zzc(int i, zzh<Void> zzh) {
            this.zzbLM = i;
            this.zzbLF = zzh;
        }

        private void zzSh() {
            if (this.zzbLN + this.zzbLO != this.zzbLM) {
                return;
            }
            if (this.zzbLK == null) {
                this.zzbLF.setResult(null);
                return;
            }
            zzh zzh = this.zzbLF;
            int i = this.zzbLO;
            zzh.setException(new ExecutionException(i + " out of " + this.zzbLM + " underlying tasks failed", this.zzbLK));
        }

        public void onFailure(@NonNull Exception exception) {
            synchronized (this.zzrN) {
                this.zzbLO++;
                this.zzbLK = exception;
                zzSh();
            }
        }

        public void onSuccess(Object obj) {
            synchronized (this.zzrN) {
                this.zzbLN++;
                zzSh();
            }
        }
    }

    private Tasks() {
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task) throws ExecutionException, InterruptedException {
        zzac.zzxx();
        zzac.zzb((Object) task, (Object) "Task must not be null");
        if (task.isComplete()) {
            return zzb(task);
        }
        Object zza = new zza();
        zza(task, zza);
        zza.await();
        return zzb(task);
    }

    public static <TResult> TResult await(@NonNull Task<TResult> task, long j, @NonNull TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        zzac.zzxx();
        zzac.zzb((Object) task, (Object) "Task must not be null");
        zzac.zzb((Object) timeUnit, (Object) "TimeUnit must not be null");
        if (task.isComplete()) {
            return zzb(task);
        }
        Object zza = new zza();
        zza(task, zza);
        if (zza.await(j, timeUnit)) {
            return zzb(task);
        }
        throw new TimeoutException("Timed out waiting for Task");
    }

    public static <TResult> Task<TResult> call(@NonNull Callable<TResult> callable) {
        return call(TaskExecutors.MAIN_THREAD, callable);
    }

    public static <TResult> Task<TResult> call(@NonNull Executor executor, @NonNull Callable<TResult> callable) {
        zzac.zzb((Object) executor, (Object) "Executor must not be null");
        zzac.zzb((Object) callable, (Object) "Callback must not be null");
        Task zzh = new zzh();
        executor.execute(new C03431(zzh, callable));
        return zzh;
    }

    public static <TResult> Task<TResult> forException(@NonNull Exception exception) {
        Task zzh = new zzh();
        zzh.setException(exception);
        return zzh;
    }

    public static <TResult> Task<TResult> forResult(TResult tResult) {
        Task zzh = new zzh();
        zzh.setResult(tResult);
        return zzh;
    }

    public static Task<Void> whenAll(Collection<? extends Task<?>> collection) {
        if (collection.isEmpty()) {
            return forResult(null);
        }
        for (Task task : collection) {
            if (task == null) {
                throw new NullPointerException("null tasks are not accepted");
            }
        }
        Task zzh = new zzh();
        zzb zzc = new zzc(collection.size(), zzh);
        for (Task task2 : collection) {
            zza(task2, zzc);
        }
        return zzh;
    }

    public static Task<Void> whenAll(Task<?>... taskArr) {
        return taskArr.length == 0 ? forResult(null) : whenAll(Arrays.asList(taskArr));
    }

    private static void zza(Task<?> task, zzb zzb) {
        task.addOnSuccessListener(TaskExecutors.zzbLG, (OnSuccessListener) zzb);
        task.addOnFailureListener(TaskExecutors.zzbLG, (OnFailureListener) zzb);
    }

    private static <TResult> TResult zzb(Task<TResult> task) throws ExecutionException {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        throw new ExecutionException(task.getException());
    }
}
