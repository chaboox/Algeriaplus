package com.google.android.gms.internal;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.internal.zzauh.zzb;
import com.google.android.gms.internal.zzauh.zzc;
import com.google.android.gms.internal.zzauh.zzd;
import com.google.android.gms.internal.zzauh.zzg;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzatp {
    private static volatile zzatp zzbtc;
    private final Context mContext;
    private final boolean zzacO;
    private long zzbtA;
    private FileLock zzbtB;
    private FileChannel zzbtC;
    private List<Long> zzbtD;
    private int zzbtE;
    private int zzbtF;
    private long zzbtG = -1;
    private final zzast zzbtd;
    private final zzatl zzbte;
    private final zzati zzbtf;
    private final zzato zzbtg;
    private final zzaty zzbth;
    private final zzatn zzbti;
    private final AppMeasurement zzbtj;
    private final FirebaseAnalytics zzbtk;
    private final zzaue zzbtl;
    private final zzasu zzbtm;
    private final zzatg zzbtn;
    private final zzatj zzbto;
    private final zzatv zzbtp;
    private final zzatw zzbtq;
    private final zzasw zzbtr;
    private final zzatu zzbts;
    private final zzatf zzbtt;
    private final zzatk zzbtu;
    private final zzaua zzbtv;
    private final zzass zzbtw;
    private final zzaso zzbtx;
    private boolean zzbty;
    private Boolean zzbtz;
    private final zze zzuI;

    /* renamed from: com.google.android.gms.internal.zzatp$1 */
    class C02961 implements Runnable {
        final /* synthetic */ zzatp zzbtH;

        C02961(zzatp zzatp) {
            this.zzbtH = zzatp;
        }

        public void run() {
            this.zzbtH.start();
        }
    }

    /* renamed from: com.google.android.gms.internal.zzatp$4 */
    class C05534 implements zza {
        final /* synthetic */ zzatp zzbtH;

        C05534(zzatp zzatp) {
            this.zzbtH = zzatp;
        }

        public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
            this.zzbtH.zza(i, th, bArr);
        }
    }

    /* renamed from: com.google.android.gms.internal.zzatp$5 */
    class C05545 implements zza {
        final /* synthetic */ zzatp zzbtH;

        C05545(zzatp zzatp) {
            this.zzbtH = zzatp;
        }

        public void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
            this.zzbtH.zzb(str, i, th, bArr, map);
        }
    }

    private class zza implements zzb {
        final /* synthetic */ zzatp zzbtH;
        zzauh.zze zzbtI;
        List<Long> zzbtJ;
        long zzbtK;
        List<zzb> zztf;

        private zza(zzatp zzatp) {
            this.zzbtH = zzatp;
        }

        private long zza(zzb zzb) {
            return ((zzb.zzbvW.longValue() / 1000) / 60) / 60;
        }

        boolean isEmpty() {
            return this.zztf == null || this.zztf.isEmpty();
        }

        public boolean zza(long j, zzb zzb) {
            zzac.zzw(zzb);
            if (this.zztf == null) {
                this.zztf = new ArrayList();
            }
            if (this.zzbtJ == null) {
                this.zzbtJ = new ArrayList();
            }
            if (this.zztf.size() > 0 && zza((zzb) this.zztf.get(0)) != zza(zzb)) {
                return false;
            }
            long zzacZ = this.zzbtK + ((long) zzb.zzacZ());
            if (zzacZ >= ((long) this.zzbtH.zzJv().zzKr())) {
                return false;
            }
            this.zzbtK = zzacZ;
            this.zztf.add(zzb);
            this.zzbtJ.add(Long.valueOf(j));
            return this.zztf.size() < this.zzbtH.zzJv().zzKs();
        }

        public void zzb(zzauh.zze zze) {
            zzac.zzw(zze);
            this.zzbtI = zze;
        }
    }

    zzatp(zzatt zzatt) {
        zzac.zzw(zzatt);
        this.mContext = zzatt.mContext;
        this.zzuI = zzatt.zzn(this);
        this.zzbtd = zzatt.zza(this);
        zzatl zzb = zzatt.zzb(this);
        zzb.initialize();
        this.zzbte = zzb;
        zzati zzc = zzatt.zzc(this);
        zzc.initialize();
        this.zzbtf = zzc;
        zzJt().zzLe().zzj("App measurement is starting up, version", Long.valueOf(zzJv().zzJD()));
        zzJt().zzLe().log("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzJt().zzLf().log("Debug-level message logging enabled");
        zzJt().zzLf().zzj("AppMeasurement singleton hash", Integer.valueOf(System.identityHashCode(this)));
        zzaue zzj = zzatt.zzj(this);
        zzj.initialize();
        this.zzbtl = zzj;
        zzasw zzq = zzatt.zzq(this);
        zzq.initialize();
        this.zzbtr = zzq;
        zzatf zzr = zzatt.zzr(this);
        zzr.initialize();
        this.zzbtt = zzr;
        zzJv().zzKk();
        String zzjI = zzr.zzjI();
        if (zzJp().zzgh(zzjI)) {
            zzJt().zzLe().log("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
        } else {
            com.google.android.gms.internal.zzati.zza zzLe = zzJt().zzLe();
            String str = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ";
            zzjI = String.valueOf(zzjI);
            zzLe.log(zzjI.length() != 0 ? str.concat(zzjI) : new String(str));
        }
        zzasu zzk = zzatt.zzk(this);
        zzk.initialize();
        this.zzbtm = zzk;
        zzatg zzl = zzatt.zzl(this);
        zzl.initialize();
        this.zzbtn = zzl;
        zzass zzu = zzatt.zzu(this);
        zzu.initialize();
        this.zzbtw = zzu;
        this.zzbtx = zzatt.zzv(this);
        zzatj zzm = zzatt.zzm(this);
        zzm.initialize();
        this.zzbto = zzm;
        zzatv zzo = zzatt.zzo(this);
        zzo.initialize();
        this.zzbtp = zzo;
        zzatw zzp = zzatt.zzp(this);
        zzp.initialize();
        this.zzbtq = zzp;
        zzatu zzi = zzatt.zzi(this);
        zzi.initialize();
        this.zzbts = zzi;
        zzaua zzt = zzatt.zzt(this);
        zzt.initialize();
        this.zzbtv = zzt;
        this.zzbtu = zzatt.zzs(this);
        this.zzbtj = zzatt.zzh(this);
        this.zzbtk = zzatt.zzg(this);
        zzaty zze = zzatt.zze(this);
        zze.initialize();
        this.zzbth = zze;
        zzatn zzf = zzatt.zzf(this);
        zzf.initialize();
        this.zzbti = zzf;
        zzato zzd = zzatt.zzd(this);
        zzd.initialize();
        this.zzbtg = zzd;
        if (this.zzbtE != this.zzbtF) {
            zzJt().zzLa().zze("Not all components initialized", Integer.valueOf(this.zzbtE), Integer.valueOf(this.zzbtF));
        }
        this.zzacO = true;
        this.zzbtd.zzKk();
        if (!(this.mContext.getApplicationContext() instanceof Application)) {
            zzJt().zzLc().log("Application context is not an Application");
        } else if (VERSION.SDK_INT >= 14) {
            zzJi().zzLQ();
        } else {
            zzJt().zzLf().log("Not tracking deep linking pre-ICS");
        }
        this.zzbtg.zzm(new C02961(this));
    }

    private boolean zzLH() {
        zzmq();
        zznA();
        return zzJo().zzKM() || !TextUtils.isEmpty(zzJo().zzKG());
    }

    @WorkerThread
    private void zzLI() {
        zzmq();
        zznA();
        if (!zzLM()) {
            return;
        }
        if (zzLt() && zzLH()) {
            long zzLJ = zzLJ();
            if (zzLJ == 0) {
                zzLz().unregister();
                zzLA().cancel();
                return;
            } else if (zzLy().zzpA()) {
                long j = zzJu().zzbsi.get();
                long zzKw = zzJv().zzKw();
                if (!zzJp().zzf(j, zzKw)) {
                    zzLJ = Math.max(zzLJ, j + zzKw);
                }
                zzLz().unregister();
                zzLJ -= zznq().currentTimeMillis();
                if (zzLJ <= 0) {
                    zzLJ = zzJv().zzKz();
                    zzJu().zzbsg.set(zznq().currentTimeMillis());
                }
                zzJt().zzLg().zzj("Upload scheduled in approximately ms", Long.valueOf(zzLJ));
                zzLA().zzx(zzLJ);
                return;
            } else {
                zzLz().zzpx();
                zzLA().cancel();
                return;
            }
        }
        zzLz().unregister();
        zzLA().cancel();
    }

    private long zzLJ() {
        long currentTimeMillis = zznq().currentTimeMillis();
        long zzKC = zzJv().zzKC();
        Object obj = (zzJo().zzKN() || zzJo().zzKH()) ? 1 : null;
        long zzKy = obj != null ? zzJv().zzKy() : zzJv().zzKx();
        long j = zzJu().zzbsg.get();
        long j2 = zzJu().zzbsh.get();
        long max = Math.max(zzJo().zzKK(), zzJo().zzKL());
        if (max == 0) {
            return 0;
        }
        max = currentTimeMillis - Math.abs(max - currentTimeMillis);
        j2 = currentTimeMillis - Math.abs(j2 - currentTimeMillis);
        j = Math.max(currentTimeMillis - Math.abs(j - currentTimeMillis), j2);
        currentTimeMillis = max + zzKC;
        if (obj != null && j > 0) {
            currentTimeMillis = Math.min(max, j) + zzKy;
        }
        if (!zzJp().zzf(j, zzKy)) {
            currentTimeMillis = j + zzKy;
        }
        if (j2 == 0 || j2 < max) {
            return currentTimeMillis;
        }
        for (int i = 0; i < zzJv().zzKE(); i++) {
            currentTimeMillis += ((long) (1 << i)) * zzJv().zzKD();
            if (currentTimeMillis > j2) {
                return currentTimeMillis;
            }
        }
        return 0;
    }

    private void zza(zzatr zzatr) {
        if (zzatr == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private void zza(zzats zzats) {
        if (zzats == null) {
            throw new IllegalStateException("Component not created");
        } else if (!zzats.isInitialized()) {
            throw new IllegalStateException("Component not initialized");
        }
    }

    private boolean zza(zzasx zzasx) {
        if (zzasx.zzbqI == null) {
            return false;
        }
        Iterator it = zzasx.zzbqI.iterator();
        while (it.hasNext()) {
            if ("_r".equals((String) it.next())) {
                return true;
            }
        }
        return zzJq().zzY(zzasx.zzVQ, zzasx.mName) && zzJo().zza(zzLE(), zzasx.zzVQ, false, false, false, false, false).zzbqz < ((long) zzJv().zzfp(zzasx.zzVQ));
    }

    private com.google.android.gms.internal.zzauh.zza[] zza(String str, zzg[] zzgArr, zzb[] zzbArr) {
        zzac.zzdv(str);
        return zzJh().zza(str, zzbArr, zzgArr);
    }

    public static zzatp zzbu(Context context) {
        zzac.zzw(context);
        zzac.zzw(context.getApplicationContext());
        if (zzbtc == null) {
            synchronized (zzatp.class) {
                if (zzbtc == null) {
                    zzbtc = new zzatt(context).zzLP();
                }
            }
        }
        return zzbtc;
    }

    @WorkerThread
    private void zzf(zzasq zzasq) {
        zzmq();
        zznA();
        zzac.zzw(zzasq);
        zzac.zzdv(zzasq.packageName);
        zzasp zzfy = zzJo().zzfy(zzasq.packageName);
        String zzfL = zzJu().zzfL(zzasq.packageName);
        Object obj = null;
        if (zzfy == null) {
            zzasp zzasp = new zzasp(this, zzasq.packageName);
            zzasp.zzfh(zzJu().zzLj());
            zzasp.zzfj(zzfL);
            zzfy = zzasp;
            obj = 1;
        } else if (!zzfL.equals(zzfy.zzJx())) {
            zzfy.zzfj(zzfL);
            zzfy.zzfh(zzJu().zzLj());
            int i = 1;
        }
        if (!(TextUtils.isEmpty(zzasq.zzbqf) || zzasq.zzbqf.equals(zzfy.getGmpAppId()))) {
            zzfy.zzfi(zzasq.zzbqf);
            obj = 1;
        }
        if (!(TextUtils.isEmpty(zzasq.zzbqn) || zzasq.zzbqn.equals(zzfy.zzJy()))) {
            zzfy.zzfk(zzasq.zzbqn);
            obj = 1;
        }
        if (!(zzasq.zzbqh == 0 || zzasq.zzbqh == zzfy.zzJD())) {
            zzfy.zzaa(zzasq.zzbqh);
            obj = 1;
        }
        if (!(TextUtils.isEmpty(zzasq.zzbhg) || zzasq.zzbhg.equals(zzfy.zzmy()))) {
            zzfy.setAppVersion(zzasq.zzbhg);
            obj = 1;
        }
        if (zzasq.zzbqm != zzfy.zzJB()) {
            zzfy.zzZ(zzasq.zzbqm);
            obj = 1;
        }
        if (!(TextUtils.isEmpty(zzasq.zzbqg) || zzasq.zzbqg.equals(zzfy.zzJC()))) {
            zzfy.zzfl(zzasq.zzbqg);
            obj = 1;
        }
        if (zzasq.zzbqi != zzfy.zzJE()) {
            zzfy.zzab(zzasq.zzbqi);
            obj = 1;
        }
        if (zzasq.zzbqk != zzfy.zzJF()) {
            zzfy.setMeasurementEnabled(zzasq.zzbqk);
            obj = 1;
        }
        if (!(TextUtils.isEmpty(zzasq.zzbqj) || zzasq.zzbqj.equals(zzfy.zzJQ()))) {
            zzfy.zzfm(zzasq.zzbqj);
            obj = 1;
        }
        if (obj != null) {
            zzJo().zza(zzfy);
        }
    }

    private boolean zzj(String str, long j) {
        zzJo().beginTransaction();
        try {
            zzatp zzatp = this;
            zza zza = new zza();
            zzJo().zza(str, j, this.zzbtG, zza);
            if (zza.isEmpty()) {
                zzJo().setTransactionSuccessful();
                zzJo().endTransaction();
                return false;
            }
            int i;
            boolean z = false;
            zzauh.zze zze = zza.zzbtI;
            zze.zzbwd = new zzb[zza.zztf.size()];
            int i2 = 0;
            int i3 = 0;
            while (i3 < zza.zztf.size()) {
                boolean z2;
                Object obj;
                if (zzJq().zzX(zza.zzbtI.zzaR, ((zzb) zza.zztf.get(i3)).name)) {
                    zzJt().zzLc().zze("Dropping blacklisted raw event. appId", zzati.zzfI(str), ((zzb) zza.zztf.get(i3)).name);
                    obj = (zzJp().zzgj(zza.zzbtI.zzaR) || zzJp().zzgk(zza.zzbtI.zzaR)) ? 1 : null;
                    if (obj != null || "_err".equals(((zzb) zza.zztf.get(i3)).name)) {
                        i = i2;
                        z2 = z;
                    } else {
                        zzJp().zza(11, "_ev", ((zzb) zza.zztf.get(i3)).name, 0);
                        i = i2;
                        z2 = z;
                    }
                } else {
                    boolean zzfW;
                    int i4;
                    if (zzJq().zzY(zza.zzbtI.zzaR, ((zzb) zza.zztf.get(i3)).name)) {
                        zzc[] zzcArr;
                        zzc zzc;
                        zzb zzb;
                        Object obj2 = null;
                        Object obj3 = null;
                        if (((zzb) zza.zztf.get(i3)).zzbvV == null) {
                            ((zzb) zza.zztf.get(i3)).zzbvV = new zzc[0];
                        }
                        zzc[] zzcArr2 = ((zzb) zza.zztf.get(i3)).zzbvV;
                        int length = zzcArr2.length;
                        int i5 = 0;
                        while (i5 < length) {
                            zzc zzc2 = zzcArr2[i5];
                            if ("_c".equals(zzc2.name)) {
                                zzc2.zzbvZ = Long.valueOf(1);
                                obj2 = 1;
                                obj = obj3;
                            } else if ("_r".equals(zzc2.name)) {
                                zzc2.zzbvZ = Long.valueOf(1);
                                obj = 1;
                            } else {
                                obj = obj3;
                            }
                            i5++;
                            obj3 = obj;
                        }
                        if (obj2 == null) {
                            zzJt().zzLg().zzj("Marking event as conversion", ((zzb) zza.zztf.get(i3)).name);
                            zzcArr = (zzc[]) Arrays.copyOf(((zzb) zza.zztf.get(i3)).zzbvV, ((zzb) zza.zztf.get(i3)).zzbvV.length + 1);
                            zzc = new zzc();
                            zzc.name = "_c";
                            zzc.zzbvZ = Long.valueOf(1);
                            zzcArr[zzcArr.length - 1] = zzc;
                            ((zzb) zza.zztf.get(i3)).zzbvV = zzcArr;
                        }
                        if (obj3 == null) {
                            zzJt().zzLg().zzj("Marking event as real-time", ((zzb) zza.zztf.get(i3)).name);
                            zzcArr = (zzc[]) Arrays.copyOf(((zzb) zza.zztf.get(i3)).zzbvV, ((zzb) zza.zztf.get(i3)).zzbvV.length + 1);
                            zzc = new zzc();
                            zzc.name = "_r";
                            zzc.zzbvZ = Long.valueOf(1);
                            zzcArr[zzcArr.length - 1] = zzc;
                            ((zzb) zza.zztf.get(i3)).zzbvV = zzcArr;
                        }
                        boolean z3 = true;
                        zzfW = zzaue.zzfW(((zzb) zza.zztf.get(i3)).name);
                        if (zzJo().zza(zzLE(), zza.zzbtI.zzaR, false, false, false, false, true).zzbqz > ((long) zzJv().zzfp(zza.zzbtI.zzaR))) {
                            zzb = (zzb) zza.zztf.get(i3);
                            i4 = 0;
                            while (i4 < zzb.zzbvV.length) {
                                if ("_r".equals(zzb.zzbvV[i4].name)) {
                                    Object obj4 = new zzc[(zzb.zzbvV.length - 1)];
                                    if (i4 > 0) {
                                        System.arraycopy(zzb.zzbvV, 0, obj4, 0, i4);
                                    }
                                    if (i4 < obj4.length) {
                                        System.arraycopy(zzb.zzbvV, i4 + 1, obj4, i4, obj4.length - i4);
                                    }
                                    zzb.zzbvV = obj4;
                                    z3 = z;
                                } else {
                                    i4++;
                                }
                            }
                            z3 = z;
                        }
                        if (zzfW && zzJo().zza(zzLE(), zza.zzbtI.zzaR, false, false, true, false, false).zzbqx > ((long) zzJv().zzfo(zza.zzbtI.zzaR))) {
                            zzJt().zzLc().zzj("Too many conversions. Not logging as conversion. appId", zzati.zzfI(str));
                            zzb = (zzb) zza.zztf.get(i3);
                            Object obj5 = null;
                            zzc zzc3 = null;
                            zzc[] zzcArr3 = zzb.zzbvV;
                            int length2 = zzcArr3.length;
                            int i6 = 0;
                            while (i6 < length2) {
                                zzc = zzcArr3[i6];
                                if ("_c".equals(zzc.name)) {
                                    obj3 = obj5;
                                } else if ("_err".equals(zzc.name)) {
                                    zzc zzc4 = zzc3;
                                    int i7 = 1;
                                    zzc = zzc4;
                                } else {
                                    zzc = zzc3;
                                    obj3 = obj5;
                                }
                                i6++;
                                obj5 = obj3;
                                zzc3 = zzc;
                            }
                            if (obj5 != null && zzc3 != null) {
                                zzcArr3 = new zzc[(zzb.zzbvV.length - 1)];
                                int i8 = 0;
                                zzc[] zzcArr4 = zzb.zzbvV;
                                int length3 = zzcArr4.length;
                                i6 = 0;
                                while (i6 < length3) {
                                    zzc zzc5 = zzcArr4[i6];
                                    if (zzc5 != zzc3) {
                                        i4 = i8 + 1;
                                        zzcArr3[i8] = zzc5;
                                    } else {
                                        i4 = i8;
                                    }
                                    i6++;
                                    i8 = i4;
                                }
                                zzb.zzbvV = zzcArr3;
                                zzfW = z3;
                            } else if (zzc3 != null) {
                                zzc3.name = "_err";
                                zzc3.zzbvZ = Long.valueOf(10);
                                zzfW = z3;
                            } else {
                                zzJt().zzLa().zzj("Did not find conversion parameter. appId", zzati.zzfI(str));
                            }
                        }
                        zzfW = z3;
                    } else {
                        zzfW = z;
                    }
                    i4 = i2 + 1;
                    zze.zzbwd[i2] = (zzb) zza.zztf.get(i3);
                    i = i4;
                    z2 = zzfW;
                }
                i3++;
                i2 = i;
                z = z2;
            }
            if (i2 < zza.zztf.size()) {
                zze.zzbwd = (zzb[]) Arrays.copyOf(zze.zzbwd, i2);
            }
            zze.zzbww = zza(zza.zzbtI.zzaR, zza.zzbtI.zzbwe, zze.zzbwd);
            zze.zzbwg = Long.valueOf(Long.MAX_VALUE);
            zze.zzbwh = Long.valueOf(Long.MIN_VALUE);
            for (zzb zzb2 : zze.zzbwd) {
                if (zzb2.zzbvW.longValue() < zze.zzbwg.longValue()) {
                    zze.zzbwg = zzb2.zzbvW;
                }
                if (zzb2.zzbvW.longValue() > zze.zzbwh.longValue()) {
                    zze.zzbwh = zzb2.zzbvW;
                }
            }
            String str2 = zza.zzbtI.zzaR;
            zzasp zzfy = zzJo().zzfy(str2);
            if (zzfy == null) {
                zzJt().zzLa().zzj("Bundling raw events w/o app info. appId", zzati.zzfI(str));
            } else if (zze.zzbwd.length > 0) {
                long zzJA = zzfy.zzJA();
                zze.zzbwj = zzJA != 0 ? Long.valueOf(zzJA) : null;
                long zzJz = zzfy.zzJz();
                if (zzJz != 0) {
                    zzJA = zzJz;
                }
                zze.zzbwi = zzJA != 0 ? Long.valueOf(zzJA) : null;
                zzfy.zzJJ();
                zze.zzbwu = Integer.valueOf((int) zzfy.zzJG());
                zzfy.zzX(zze.zzbwg.longValue());
                zzfy.zzY(zze.zzbwh.longValue());
                zze.zzbqj = zzfy.zzJR();
                zzJo().zza(zzfy);
            }
            if (zze.zzbwd.length > 0) {
                zzJv().zzKk();
                zzaug.zzb zzfO = zzJq().zzfO(zza.zzbtI.zzaR);
                if (zzfO == null || zzfO.zzbvK == null) {
                    zzJt().zzLc().zzj("Did not find measurement config or missing version info. appId", zzati.zzfI(str));
                } else {
                    zze.zzbwB = zzfO.zzbvK;
                }
                zzJo().zza(zze, z);
            }
            zzJo().zzG(zza.zzbtJ);
            zzJo().zzfF(str2);
            zzJo().setTransactionSuccessful();
            boolean z4 = zze.zzbwd.length > 0;
            zzJo().endTransaction();
            return z4;
        } catch (Throwable th) {
            zzJo().endTransaction();
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getGmpAppIdOnPackageSide(final String str) {
        Object e;
        zzJd();
        try {
            return (String) zzJs().zze(new Callable<String>(this) {
                final /* synthetic */ zzatp zzbtH;

                public /* synthetic */ Object call() throws Exception {
                    return zzou();
                }

                public String zzou() throws Exception {
                    zzasp zzfy = this.zzbtH.zzJo().zzfy(str);
                    return zzfy == null ? null : zzfy.getGmpAppId();
                }
            }).get(30000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e2) {
            e = e2;
        } catch (InterruptedException e3) {
            e = e3;
        } catch (ExecutionException e4) {
            e = e4;
        }
        zzJt().zzLa().zze("Failed to get gmp app id. appId", zzati.zzfI(str), e);
        return null;
    }

    @WorkerThread
    public boolean isEnabled() {
        boolean z = false;
        zzmq();
        zznA();
        if (zzJv().zzKl()) {
            return false;
        }
        Boolean zzKm = zzJv().zzKm();
        if (zzKm != null) {
            z = zzKm.booleanValue();
        } else if (!zzJv().zzwk()) {
            z = true;
        }
        return zzJu().zzaG(z);
    }

    @WorkerThread
    protected void start() {
        zzmq();
        zzJo().zzKI();
        if (zzJu().zzbsg.get() == 0) {
            zzJu().zzbsg.set(zznq().currentTimeMillis());
        }
        if (zzLt()) {
            zzJv().zzKk();
            if (!TextUtils.isEmpty(zzJj().getGmpAppId())) {
                String zzLm = zzJu().zzLm();
                if (zzLm == null) {
                    zzJu().zzfM(zzJj().getGmpAppId());
                } else if (!zzLm.equals(zzJj().getGmpAppId())) {
                    zzJt().zzLe().log("Rechecking which service to use due to a GMP App Id change");
                    zzJu().zzLo();
                    this.zzbtq.disconnect();
                    this.zzbtq.zzoc();
                    zzJu().zzfM(zzJj().getGmpAppId());
                }
            }
            zzJv().zzKk();
            if (!TextUtils.isEmpty(zzJj().getGmpAppId())) {
                zzJi().zzLR();
            }
        } else if (isEnabled()) {
            if (!zzJp().zzbV("android.permission.INTERNET")) {
                zzJt().zzLa().log("App is missing INTERNET permission");
            }
            if (!zzJp().zzbV("android.permission.ACCESS_NETWORK_STATE")) {
                zzJt().zzLa().log("App is missing ACCESS_NETWORK_STATE permission");
            }
            zzJv().zzKk();
            zzacx.zzaQ(getContext());
            if (!zzatm.zzi(getContext(), false)) {
                zzJt().zzLa().log("AppMeasurementReceiver not registered/enabled");
            }
            if (!zzatx.zzj(getContext(), false)) {
                zzJt().zzLa().log("AppMeasurementService not registered/enabled");
            }
            zzJt().zzLa().log("Uploading is not possible. App measurement disabled");
        }
        zzLI();
    }

    protected void zzH(List<Long> list) {
        zzac.zzas(!list.isEmpty());
        if (this.zzbtD != null) {
            zzJt().zzLa().log("Set uploading progress before finishing the previous upload");
        } else {
            this.zzbtD = new ArrayList(list);
        }
    }

    void zzJd() {
        zzJv().zzKk();
        throw new IllegalStateException("Unexpected call on client side");
    }

    void zzJe() {
        zzJv().zzKk();
    }

    public zzaso zzJg() {
        zza(this.zzbtx);
        return this.zzbtx;
    }

    public zzass zzJh() {
        zza(this.zzbtw);
        return this.zzbtw;
    }

    public zzatu zzJi() {
        zza(this.zzbts);
        return this.zzbts;
    }

    public zzatf zzJj() {
        zza(this.zzbtt);
        return this.zzbtt;
    }

    public zzasw zzJk() {
        zza(this.zzbtr);
        return this.zzbtr;
    }

    public zzatw zzJl() {
        zza(this.zzbtq);
        return this.zzbtq;
    }

    public zzatv zzJm() {
        zza(this.zzbtp);
        return this.zzbtp;
    }

    public zzatg zzJn() {
        zza(this.zzbtn);
        return this.zzbtn;
    }

    public zzasu zzJo() {
        zza(this.zzbtm);
        return this.zzbtm;
    }

    public zzaue zzJp() {
        zza(this.zzbtl);
        return this.zzbtl;
    }

    public zzatn zzJq() {
        zza(this.zzbti);
        return this.zzbti;
    }

    public zzaty zzJr() {
        zza(this.zzbth);
        return this.zzbth;
    }

    public zzato zzJs() {
        zza(this.zzbtg);
        return this.zzbtg;
    }

    public zzati zzJt() {
        zza(this.zzbtf);
        return this.zzbtf;
    }

    public zzatl zzJu() {
        zza(this.zzbte);
        return this.zzbte;
    }

    public zzast zzJv() {
        return this.zzbtd;
    }

    public zzaua zzLA() {
        zza(this.zzbtv);
        return this.zzbtv;
    }

    FileChannel zzLB() {
        return this.zzbtC;
    }

    @WorkerThread
    void zzLC() {
        zzmq();
        zznA();
        if (zzLM() && zzLD()) {
            zzv(zza(zzLB()), zzJj().zzKZ());
        }
    }

    @WorkerThread
    boolean zzLD() {
        zzmq();
        try {
            this.zzbtC = new RandomAccessFile(new File(getContext().getFilesDir(), this.zzbtm.zznV()), "rw").getChannel();
            this.zzbtB = this.zzbtC.tryLock();
            if (this.zzbtB != null) {
                zzJt().zzLg().log("Storage concurrent access okay");
                return true;
            }
            zzJt().zzLa().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            zzJt().zzLa().zzj("Failed to acquire storage lock", e);
        } catch (IOException e2) {
            zzJt().zzLa().zzj("Failed to access storage lock file", e2);
        }
    }

    long zzLE() {
        return ((((zznq().currentTimeMillis() + zzJu().zzLk()) / 1000) / 60) / 60) / 24;
    }

    @WorkerThread
    protected boolean zzLF() {
        zzmq();
        return this.zzbtD != null;
    }

    @WorkerThread
    public void zzLG() {
        int i = 0;
        zzmq();
        zznA();
        zzJv().zzKk();
        Boolean zzLn = zzJu().zzLn();
        if (zzLn == null) {
            zzJt().zzLc().log("Upload data called on the client side before use of service was decided");
        } else if (zzLn.booleanValue()) {
            zzJt().zzLa().log("Upload called in the client side when service should be used");
        } else if (zzLF()) {
            zzJt().zzLc().log("Uploading requested multiple times");
        } else if (zzLy().zzpA()) {
            long currentTimeMillis = zznq().currentTimeMillis();
            zzao(currentTimeMillis - zzJv().zzKv());
            long j = zzJu().zzbsg.get();
            if (j != 0) {
                zzJt().zzLf().zzj("Uploading events. Elapsed time since last upload attempt (ms)", Long.valueOf(Math.abs(currentTimeMillis - j)));
            }
            String zzKG = zzJo().zzKG();
            Object zzam;
            if (TextUtils.isEmpty(zzKG)) {
                this.zzbtG = -1;
                zzam = zzJo().zzam(currentTimeMillis - zzJv().zzKv());
                if (!TextUtils.isEmpty(zzam)) {
                    zzasp zzfy = zzJo().zzfy(zzam);
                    if (zzfy != null) {
                        zzb(zzfy);
                        return;
                    }
                    return;
                }
                return;
            }
            if (this.zzbtG == -1) {
                this.zzbtG = zzJo().zzKO();
            }
            List<Pair> zzn = zzJo().zzn(zzKG, zzJv().zzfu(zzKG), zzJv().zzfv(zzKG));
            if (!zzn.isEmpty()) {
                zzauh.zze zze;
                Object obj;
                List subList;
                for (Pair pair : zzn) {
                    zze = (zzauh.zze) pair.first;
                    if (!TextUtils.isEmpty(zze.zzbwq)) {
                        obj = zze.zzbwq;
                        break;
                    }
                }
                obj = null;
                if (obj != null) {
                    for (int i2 = 0; i2 < zzn.size(); i2++) {
                        zze = (zzauh.zze) ((Pair) zzn.get(i2)).first;
                        if (!TextUtils.isEmpty(zze.zzbwq) && !zze.zzbwq.equals(obj)) {
                            subList = zzn.subList(0, i2);
                            break;
                        }
                    }
                }
                subList = zzn;
                zzd zzd = new zzd();
                zzd.zzbwa = new zzauh.zze[subList.size()];
                List arrayList = new ArrayList(subList.size());
                while (i < zzd.zzbwa.length) {
                    zzd.zzbwa[i] = (zzauh.zze) ((Pair) subList.get(i)).first;
                    arrayList.add((Long) ((Pair) subList.get(i)).second);
                    zzd.zzbwa[i].zzbwp = Long.valueOf(zzJv().zzJD());
                    zzd.zzbwa[i].zzbwf = Long.valueOf(currentTimeMillis);
                    zzd.zzbwa[i].zzbwv = Boolean.valueOf(zzJv().zzKk());
                    i++;
                }
                zzam = zzJt().zzai(2) ? zzaue.zzb(zzd) : null;
                byte[] zza = zzJp().zza(zzd);
                String zzKu = zzJv().zzKu();
                try {
                    URL url = new URL(zzKu);
                    zzH(arrayList);
                    zzJu().zzbsh.set(currentTimeMillis);
                    Object obj2 = "?";
                    if (zzd.zzbwa.length > 0) {
                        obj2 = zzd.zzbwa[0].zzaR;
                    }
                    zzJt().zzLg().zzd("Uploading data. app, uncompressed size, data", obj2, Integer.valueOf(zza.length), zzam);
                    zzLy().zza(zzKG, url, zza, null, new C05534(this));
                } catch (MalformedURLException e) {
                    zzJt().zzLa().zze("Failed to parse upload URL. Not uploading. appId", zzati.zzfI(zzKG), zzKu);
                }
            }
        } else {
            zzJt().zzLc().log("Network not connected, ignoring upload request");
            zzLI();
        }
    }

    void zzLK() {
        this.zzbtF++;
    }

    @WorkerThread
    void zzLL() {
        zzmq();
        zznA();
        if (!this.zzbty) {
            zzJt().zzLe().log("This instance being marked as an uploader");
            zzLC();
        }
        this.zzbty = true;
    }

    @WorkerThread
    boolean zzLM() {
        zzmq();
        zznA();
        return this.zzbty;
    }

    @WorkerThread
    protected boolean zzLt() {
        boolean z = false;
        zznA();
        zzmq();
        if (this.zzbtz == null || this.zzbtA == 0 || !(this.zzbtz == null || this.zzbtz.booleanValue() || Math.abs(zznq().elapsedRealtime() - this.zzbtA) <= 1000)) {
            this.zzbtA = zznq().elapsedRealtime();
            zzJv().zzKk();
            if (zzJp().zzbV("android.permission.INTERNET") && zzJp().zzbV("android.permission.ACCESS_NETWORK_STATE")) {
                zzacx.zzaQ(getContext());
                if (zzatm.zzi(getContext(), false) && zzatx.zzj(getContext(), false)) {
                    z = true;
                }
            }
            this.zzbtz = Boolean.valueOf(z);
            if (this.zzbtz.booleanValue()) {
                this.zzbtz = Boolean.valueOf(zzJp().zzgd(zzJj().getGmpAppId()));
            }
        }
        return this.zzbtz.booleanValue();
    }

    public zzati zzLu() {
        return (this.zzbtf == null || !this.zzbtf.isInitialized()) ? null : this.zzbtf;
    }

    zzato zzLv() {
        return this.zzbtg;
    }

    public AppMeasurement zzLw() {
        return this.zzbtj;
    }

    public FirebaseAnalytics zzLx() {
        return this.zzbtk;
    }

    public zzatj zzLy() {
        zza(this.zzbto);
        return this.zzbto;
    }

    public zzatk zzLz() {
        if (this.zzbtu != null) {
            return this.zzbtu;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    public void zzV(boolean z) {
        zzLI();
    }

    @WorkerThread
    int zza(FileChannel fileChannel) {
        int i = 0;
        zzmq();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzJt().zzLa().log("Bad chanel to read from");
        } else {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            try {
                fileChannel.position(0);
                int read = fileChannel.read(allocate);
                if (read == 4) {
                    allocate.flip();
                    i = allocate.getInt();
                } else if (read != -1) {
                    zzJt().zzLc().zzj("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
            } catch (IOException e) {
                zzJt().zzLa().zzj("Failed to read from channel", e);
            }
        }
        return i;
    }

    @WorkerThread
    protected void zza(int i, Throwable th, byte[] bArr) {
        int i2 = 0;
        zzmq();
        zznA();
        if (bArr == null) {
            bArr = new byte[0];
        }
        List<Long> list = this.zzbtD;
        this.zzbtD = null;
        if ((i == Callback.DEFAULT_DRAG_ANIMATION_DURATION || i == 204) && th == null) {
            zzJu().zzbsg.set(zznq().currentTimeMillis());
            zzJu().zzbsh.set(0);
            zzLI();
            zzJt().zzLg().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
            zzJo().beginTransaction();
            try {
                for (Long longValue : list) {
                    zzJo().zzal(longValue.longValue());
                }
                zzJo().setTransactionSuccessful();
                if (zzLy().zzpA() && zzLH()) {
                    zzLG();
                    return;
                }
                this.zzbtG = -1;
                zzLI();
            } finally {
                zzJo().endTransaction();
            }
        } else {
            zzJt().zzLg().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            zzJu().zzbsh.set(zznq().currentTimeMillis());
            if (i == 503 || i == 429) {
                i2 = 1;
            }
            if (i2 != 0) {
                zzJu().zzbsi.set(zznq().currentTimeMillis());
            }
            zzLI();
        }
    }

    @WorkerThread
    void zza(zzasq zzasq, long j) {
        zzasp zzfy = zzJo().zzfy(zzasq.packageName);
        if (!(zzfy == null || zzfy.getGmpAppId() == null || zzfy.getGmpAppId().equals(zzasq.zzbqf))) {
            zzJt().zzLc().zzj("New GMP App Id passed in. Removing cached database data. appId", zzati.zzfI(zzfy.zzjI()));
            zzJo().zzfD(zzfy.zzjI());
            zzfy = null;
        }
        if (zzfy != null && zzfy.zzmy() != null && !zzfy.zzmy().equals(zzasq.zzbhg)) {
            Bundle bundle = new Bundle();
            bundle.putString("_pv", zzfy.zzmy());
            zzb(new zzatb("_au", new zzasz(bundle), "auto", j), zzasq);
        }
    }

    void zza(zzasx zzasx, zzasq zzasq) {
        zzmq();
        zznA();
        zzac.zzw(zzasx);
        zzac.zzw(zzasq);
        zzac.zzdv(zzasx.zzVQ);
        zzac.zzas(zzasx.zzVQ.equals(zzasq.packageName));
        zzauh.zze zze = new zzauh.zze();
        zze.zzbwc = Integer.valueOf(1);
        zze.zzbwk = "android";
        zze.zzaR = zzasq.packageName;
        zze.zzbqg = zzasq.zzbqg;
        zze.zzbhg = zzasq.zzbhg;
        zze.zzbwx = Integer.valueOf((int) zzasq.zzbqm);
        zze.zzbwo = Long.valueOf(zzasq.zzbqh);
        zze.zzbqf = zzasq.zzbqf;
        zze.zzbwt = zzasq.zzbqi == 0 ? null : Long.valueOf(zzasq.zzbqi);
        Pair zzfK = zzJu().zzfK(zzasq.packageName);
        if (!TextUtils.isEmpty((CharSequence) zzfK.first)) {
            zze.zzbwq = (String) zzfK.first;
            zze.zzbwr = (Boolean) zzfK.second;
        } else if (!zzJk().zzbt(this.mContext)) {
            String string = Secure.getString(this.mContext.getContentResolver(), "android_id");
            if (string == null) {
                zzJt().zzLc().zzj("null secure ID. appId", zzati.zzfI(zze.zzaR));
                string = "null";
            } else if (string.isEmpty()) {
                zzJt().zzLc().zzj("empty secure ID. appId", zzati.zzfI(zze.zzaR));
            }
            zze.zzbwA = string;
        }
        zze.zzbwl = zzJk().zzkm();
        zze.zzba = zzJk().zzKU();
        zze.zzbwn = Integer.valueOf((int) zzJk().zzKV());
        zze.zzbwm = zzJk().zzKW();
        zze.zzbwp = null;
        zze.zzbwf = null;
        zze.zzbwg = null;
        zze.zzbwh = null;
        zzasp zzfy = zzJo().zzfy(zzasq.packageName);
        if (zzfy == null) {
            zzfy = new zzasp(this, zzasq.packageName);
            zzfy.zzfh(zzJu().zzLj());
            zzfy.zzfk(zzasq.zzbqn);
            zzfy.zzfi(zzasq.zzbqf);
            zzfy.zzfj(zzJu().zzfL(zzasq.packageName));
            zzfy.zzac(0);
            zzfy.zzX(0);
            zzfy.zzY(0);
            zzfy.setAppVersion(zzasq.zzbhg);
            zzfy.zzZ(zzasq.zzbqm);
            zzfy.zzfl(zzasq.zzbqg);
            zzfy.zzaa(zzasq.zzbqh);
            zzfy.zzab(zzasq.zzbqi);
            zzfy.setMeasurementEnabled(zzasq.zzbqk);
            zzJo().zza(zzfy);
        }
        zze.zzbws = zzfy.getAppInstanceId();
        zze.zzbqn = zzfy.zzJy();
        List zzfx = zzJo().zzfx(zzasq.packageName);
        zze.zzbwe = new zzg[zzfx.size()];
        for (int i = 0; i < zzfx.size(); i++) {
            zzg zzg = new zzg();
            zze.zzbwe[i] = zzg;
            zzg.name = ((zzaud) zzfx.get(i)).mName;
            zzg.zzbwF = Long.valueOf(((zzaud) zzfx.get(i)).zzbvd);
            zzJp().zza(zzg, ((zzaud) zzfx.get(i)).zzYe);
        }
        try {
            zzJo().zza(zzasx, zzJo().zza(zze), zza(zzasx));
        } catch (IOException e) {
            zzJt().zzLa().zze("Data loss. Failed to insert raw event metadata. appId", zzati.zzfI(zze.zzaR), e);
        }
    }

    @WorkerThread
    boolean zza(int i, FileChannel fileChannel) {
        zzmq();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzJt().zzLa().log("Bad chanel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() == 4) {
                return true;
            }
            zzJt().zzLa().zzj("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            return true;
        } catch (IOException e) {
            zzJt().zzLa().zzj("Failed to write to channel", e);
            return false;
        }
    }

    @WorkerThread
    public byte[] zza(@NonNull zzatb zzatb, @Size(min = 1) String str) {
        zznA();
        zzmq();
        zzJd();
        zzac.zzw(zzatb);
        zzac.zzdv(str);
        zzd zzd = new zzd();
        zzJo().beginTransaction();
        try {
            zzasp zzfy = zzJo().zzfy(str);
            byte[] bArr;
            if (zzfy == null) {
                zzJt().zzLf().zzj("Log and bundle not available. package_name", str);
                bArr = new byte[0];
                return bArr;
            } else if (zzfy.zzJF()) {
                long j;
                zzauh.zze zze = new zzauh.zze();
                zzd.zzbwa = new zzauh.zze[]{zze};
                zze.zzbwc = Integer.valueOf(1);
                zze.zzbwk = "android";
                zze.zzaR = zzfy.zzjI();
                zze.zzbqg = zzfy.zzJC();
                zze.zzbhg = zzfy.zzmy();
                zze.zzbwx = Integer.valueOf((int) zzfy.zzJB());
                zze.zzbwo = Long.valueOf(zzfy.zzJD());
                zze.zzbqf = zzfy.getGmpAppId();
                zze.zzbwt = Long.valueOf(zzfy.zzJE());
                Pair zzfK = zzJu().zzfK(zzfy.zzjI());
                if (!TextUtils.isEmpty((CharSequence) zzfK.first)) {
                    zze.zzbwq = (String) zzfK.first;
                    zze.zzbwr = (Boolean) zzfK.second;
                }
                zze.zzbwl = zzJk().zzkm();
                zze.zzba = zzJk().zzKU();
                zze.zzbwn = Integer.valueOf((int) zzJk().zzKV());
                zze.zzbwm = zzJk().zzKW();
                zze.zzbws = zzfy.getAppInstanceId();
                zze.zzbqn = zzfy.zzJy();
                List zzfx = zzJo().zzfx(zzfy.zzjI());
                zze.zzbwe = new zzg[zzfx.size()];
                for (int i = 0; i < zzfx.size(); i++) {
                    zzg zzg = new zzg();
                    zze.zzbwe[i] = zzg;
                    zzg.name = ((zzaud) zzfx.get(i)).mName;
                    zzg.zzbwF = Long.valueOf(((zzaud) zzfx.get(i)).zzbvd);
                    zzJp().zza(zzg, ((zzaud) zzfx.get(i)).zzYe);
                }
                Bundle zzKY = zzatb.zzbqP.zzKY();
                if ("_iap".equals(zzatb.name)) {
                    zzKY.putLong("_c", 1);
                    zzJt().zzLf().log("Marking in-app purchase as real-time");
                    zzKY.putLong("_r", 1);
                }
                zzKY.putString("_o", zzatb.zzbqQ);
                if (zzJp().zzgh(zze.zzaR)) {
                    zzJp().zza(zzKY, "_dbg", Long.valueOf(1));
                    zzJp().zza(zzKY, "_r", Long.valueOf(1));
                }
                zzasy zzP = zzJo().zzP(str, zzatb.name);
                if (zzP == null) {
                    zzJo().zza(new zzasy(str, zzatb.name, 1, 0, zzatb.zzbqR));
                    j = 0;
                } else {
                    j = zzP.zzbqL;
                    zzJo().zza(zzP.zzan(zzatb.zzbqR).zzKX());
                }
                zzasx zzasx = new zzasx(this, zzatb.zzbqQ, str, zzatb.name, zzatb.zzbqR, j, zzKY);
                zzb zzb = new zzb();
                zze.zzbwd = new zzb[]{zzb};
                zzb.zzbvW = Long.valueOf(zzasx.zzavX);
                zzb.name = zzasx.mName;
                zzb.zzbvX = Long.valueOf(zzasx.zzbqH);
                zzb.zzbvV = new zzc[zzasx.zzbqI.size()];
                Iterator it = zzasx.zzbqI.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    zzc zzc = new zzc();
                    int i3 = i2 + 1;
                    zzb.zzbvV[i2] = zzc;
                    zzc.name = str2;
                    zzJp().zza(zzc, zzasx.zzbqI.get(str2));
                    i2 = i3;
                }
                zze.zzbww = zza(zzfy.zzjI(), zze.zzbwe, zze.zzbwd);
                zze.zzbwg = zzb.zzbvW;
                zze.zzbwh = zzb.zzbvW;
                long zzJA = zzfy.zzJA();
                zze.zzbwj = zzJA != 0 ? Long.valueOf(zzJA) : null;
                long zzJz = zzfy.zzJz();
                if (zzJz != 0) {
                    zzJA = zzJz;
                }
                zze.zzbwi = zzJA != 0 ? Long.valueOf(zzJA) : null;
                zzfy.zzJJ();
                zze.zzbwu = Integer.valueOf((int) zzfy.zzJG());
                zze.zzbwp = Long.valueOf(zzJv().zzJD());
                zze.zzbwf = Long.valueOf(zznq().currentTimeMillis());
                zze.zzbwv = Boolean.TRUE;
                zzfy.zzX(zze.zzbwg.longValue());
                zzfy.zzY(zze.zzbwh.longValue());
                zzJo().zza(zzfy);
                zzJo().setTransactionSuccessful();
                zzJo().endTransaction();
                try {
                    bArr = new byte[zzd.zzacZ()];
                    zzbum zzae = zzbum.zzae(bArr);
                    zzd.zza(zzae);
                    zzae.zzacM();
                    return zzJp().zzk(bArr);
                } catch (IOException e) {
                    zzJt().zzLa().zze("Data loss. Failed to bundle and serialize. appId", zzati.zzfI(str), e);
                    return null;
                }
            } else {
                zzJt().zzLf().zzj("Log and bundle disabled. package_name", str);
                bArr = new byte[0];
                zzJo().endTransaction();
                return bArr;
            }
        } finally {
            zzJo().endTransaction();
        }
    }

    boolean zzao(long j) {
        return zzj(null, j);
    }

    void zzb(zzasp zzasp) {
        String zzO = zzJv().zzO(zzasp.getGmpAppId(), zzasp.getAppInstanceId());
        try {
            URL url = new URL(zzO);
            zzJt().zzLg().zzj("Fetching remote configuration", zzasp.zzjI());
            zzaug.zzb zzfO = zzJq().zzfO(zzasp.zzjI());
            Map map = null;
            CharSequence zzfP = zzJq().zzfP(zzasp.zzjI());
            if (!(zzfO == null || TextUtils.isEmpty(zzfP))) {
                map = new ArrayMap();
                map.put("If-Modified-Since", zzfP);
            }
            zzLy().zza(zzasp.zzjI(), url, map, new C05545(this));
        } catch (MalformedURLException e) {
            zzJt().zzLa().zze("Failed to parse config URL. Not fetching. appId", zzati.zzfI(zzasp.zzjI()), zzO);
        }
    }

    @WorkerThread
    void zzb(zzasq zzasq, long j) {
        zzmq();
        zznA();
        Bundle bundle = new Bundle();
        bundle.putLong("_c", 1);
        bundle.putLong("_r", 1);
        bundle.putLong("_uwa", 0);
        bundle.putLong("_pfo", 0);
        bundle.putLong("_sys", 0);
        bundle.putLong("_sysu", 0);
        if (getContext().getPackageManager() == null) {
            zzJt().zzLa().zzj("PackageManager is null, first open report might be inaccurate. appId", zzati.zzfI(zzasq.packageName));
        } else {
            PackageInfo packageInfo;
            ApplicationInfo applicationInfo;
            try {
                packageInfo = zzacx.zzaQ(getContext()).getPackageInfo(zzasq.packageName, 0);
            } catch (NameNotFoundException e) {
                zzJt().zzLa().zze("Package info is null, first open report might be inaccurate. appId", zzati.zzfI(zzasq.packageName), e);
                packageInfo = null;
            }
            if (!(packageInfo == null || packageInfo.firstInstallTime == 0 || packageInfo.firstInstallTime == packageInfo.lastUpdateTime)) {
                bundle.putLong("_uwa", 1);
            }
            try {
                applicationInfo = zzacx.zzaQ(getContext()).getApplicationInfo(zzasq.packageName, 0);
            } catch (NameNotFoundException e2) {
                zzJt().zzLa().zze("Application info is null, first open report might be inaccurate. appId", zzati.zzfI(zzasq.packageName), e2);
                applicationInfo = null;
            }
            if (applicationInfo != null) {
                if ((applicationInfo.flags & 1) != 0) {
                    bundle.putLong("_sys", 1);
                }
                if ((applicationInfo.flags & 128) != 0) {
                    bundle.putLong("_sysu", 1);
                }
            }
        }
        long zzfE = zzJo().zzfE(zzasq.packageName);
        if (zzfE >= 0) {
            bundle.putLong("_pfo", zzfE);
        }
        zzb(new zzatb("_f", new zzasz(bundle), "auto", j), zzasq);
    }

    @WorkerThread
    void zzb(zzatb zzatb, zzasq zzasq) {
        long nanoTime = System.nanoTime();
        zzmq();
        zznA();
        String str = zzasq.packageName;
        zzac.zzdv(str);
        if (!zzaue.zzc(zzatb, zzasq)) {
            return;
        }
        if (!zzasq.zzbqk && !"_in".equals(zzatb.name)) {
            zzf(zzasq);
        } else if (zzJq().zzX(str, zzatb.name)) {
            zzJt().zzLc().zze("Dropping blacklisted event. appId", zzati.zzfI(str), zzatb.name);
            Object obj = (zzJp().zzgj(str) || zzJp().zzgk(str)) ? 1 : null;
            if (obj == null && !"_err".equals(zzatb.name)) {
                zzJp().zza(11, "_ev", zzatb.name, 0);
            }
            if (obj != null) {
                zzasp zzfy = zzJo().zzfy(str);
                if (zzfy != null) {
                    if (Math.abs(zznq().currentTimeMillis() - Math.max(zzfy.zzJI(), zzfy.zzJH())) > zzJv().zzKp()) {
                        zzJt().zzLf().log("Fetching config for blacklisted app");
                        zzb(zzfy);
                    }
                }
            }
        } else {
            if (zzJt().zzai(2)) {
                zzJt().zzLg().zzj("Logging event", zzatb);
            }
            zzJo().beginTransaction();
            try {
                Bundle zzKY = zzatb.zzbqP.zzKY();
                zzf(zzasq);
                if ("_iap".equals(zzatb.name) || Event.ECOMMERCE_PURCHASE.equals(zzatb.name)) {
                    long round;
                    Object string = zzKY.getString(Param.CURRENCY);
                    if (Event.ECOMMERCE_PURCHASE.equals(zzatb.name)) {
                        double d = zzKY.getDouble(Param.VALUE) * 1000000.0d;
                        if (d == 0.0d) {
                            d = ((double) zzKY.getLong(Param.VALUE)) * 1000000.0d;
                        }
                        if (d > 9.223372036854776E18d || d < -9.223372036854776E18d) {
                            zzJt().zzLc().zze("Data lost. Currency value is too big. appId", zzati.zzfI(str), Double.valueOf(d));
                            zzJo().setTransactionSuccessful();
                            zzJo().endTransaction();
                            return;
                        }
                        round = Math.round(d);
                    } else {
                        round = zzKY.getLong(Param.VALUE);
                    }
                    if (!TextUtils.isEmpty(string)) {
                        String toUpperCase = string.toUpperCase(Locale.US);
                        if (toUpperCase.matches("[A-Z]{3}")) {
                            zzaud zzaud;
                            String valueOf = String.valueOf("_ltv_");
                            toUpperCase = String.valueOf(toUpperCase);
                            String concat = toUpperCase.length() != 0 ? valueOf.concat(toUpperCase) : new String(valueOf);
                            zzaud zzR = zzJo().zzR(str, concat);
                            if (zzR == null || !(zzR.zzYe instanceof Long)) {
                                zzJo().zzz(str, zzJv().zzfr(str) - 1);
                                zzaud = new zzaud(str, concat, zznq().currentTimeMillis(), Long.valueOf(round));
                            } else {
                                zzaud = new zzaud(str, concat, zznq().currentTimeMillis(), Long.valueOf(round + ((Long) zzR.zzYe).longValue()));
                            }
                            if (!zzJo().zza(zzaud)) {
                                zzJt().zzLa().zzd("Too many unique user properties are set. Ignoring user property. appId", zzati.zzfI(str), zzaud.mName, zzaud.zzYe);
                                zzJp().zza(9, null, null, 0);
                            }
                        }
                    }
                }
                boolean zzfW = zzaue.zzfW(zzatb.name);
                boolean equals = "_err".equals(zzatb.name);
                com.google.android.gms.internal.zzasu.zza zza = zzJo().zza(zzLE(), str, true, zzfW, false, equals, false);
                long zzKc = zza.zzbqw - zzJv().zzKc();
                if (zzKc > 0) {
                    if (zzKc % 1000 == 1) {
                        zzJt().zzLa().zze("Data loss. Too many events logged. appId, count", zzati.zzfI(str), Long.valueOf(zza.zzbqw));
                    }
                    zzJp().zza(16, "_ev", zzatb.name, 0);
                    zzJo().setTransactionSuccessful();
                    return;
                }
                zzasy zzasy;
                if (zzfW) {
                    zzKc = zza.zzbqv - zzJv().zzKd();
                    if (zzKc > 0) {
                        if (zzKc % 1000 == 1) {
                            zzJt().zzLa().zze("Data loss. Too many public events logged. appId, count", zzati.zzfI(str), Long.valueOf(zza.zzbqv));
                        }
                        zzJp().zza(16, "_ev", zzatb.name, 0);
                        zzJo().setTransactionSuccessful();
                        zzJo().endTransaction();
                        return;
                    }
                }
                if (equals) {
                    zzKc = zza.zzbqy - ((long) zzJv().zzfn(zzasq.packageName));
                    if (zzKc > 0) {
                        if (zzKc == 1) {
                            zzJt().zzLa().zze("Too many error events logged. appId, count", zzati.zzfI(str), Long.valueOf(zza.zzbqy));
                        }
                        zzJo().setTransactionSuccessful();
                        zzJo().endTransaction();
                        return;
                    }
                }
                zzJp().zza(zzKY, "_o", zzatb.zzbqQ);
                if (zzJp().zzgh(str)) {
                    zzJp().zza(zzKY, "_dbg", Long.valueOf(1));
                    zzJp().zza(zzKY, "_r", Long.valueOf(1));
                }
                long zzfz = zzJo().zzfz(str);
                if (zzfz > 0) {
                    zzJt().zzLc().zze("Data lost. Too many events stored on disk, deleted. appId", zzati.zzfI(str), Long.valueOf(zzfz));
                }
                zzasx zzasx = new zzasx(this, zzatb.zzbqQ, str, zzatb.name, zzatb.zzbqR, 0, zzKY);
                zzasy zzP = zzJo().zzP(str, zzasx.mName);
                if (zzP == null) {
                    zzfz = zzJo().zzfG(str);
                    zzJv().zzKb();
                    if (zzfz >= 500) {
                        zzJt().zzLa().zzd("Too many event names used, ignoring event. appId, name, supported count", zzati.zzfI(str), zzasx.mName, Integer.valueOf(zzJv().zzKb()));
                        zzJp().zza(8, null, null, 0);
                        zzJo().endTransaction();
                        return;
                    }
                    zzasy = new zzasy(str, zzasx.mName, 0, 0, zzasx.zzavX);
                } else {
                    zzasx = zzasx.zza(this, zzP.zzbqL);
                    zzasy = zzP.zzan(zzasx.zzavX);
                }
                zzJo().zza(zzasy);
                zza(zzasx, zzasq);
                zzJo().setTransactionSuccessful();
                if (zzJt().zzai(2)) {
                    zzJt().zzLg().zzj("Event recorded", zzasx);
                }
                zzJo().endTransaction();
                zzLI();
                zzJt().zzLg().zzj("Background event processing time, ms", Long.valueOf(((System.nanoTime() - nanoTime) + 500000) / 1000000));
            } finally {
                zzJo().endTransaction();
            }
        }
    }

    @WorkerThread
    void zzb(zzatb zzatb, String str) {
        zzasp zzfy = zzJo().zzfy(str);
        if (zzfy == null || TextUtils.isEmpty(zzfy.zzmy())) {
            zzJt().zzLf().zzj("No app data available; dropping event", str);
            return;
        }
        try {
            String str2 = zzacx.zzaQ(getContext()).getPackageInfo(str, 0).versionName;
            if (!(zzfy.zzmy() == null || zzfy.zzmy().equals(str2))) {
                zzJt().zzLc().zzj("App version does not match; dropping event. appId", zzati.zzfI(str));
                return;
            }
        } catch (NameNotFoundException e) {
            if (!"_ui".equals(zzatb.name)) {
                zzJt().zzLc().zzj("Could not find package. appId", zzati.zzfI(str));
            }
        }
        zzatb zzatb2 = zzatb;
        zzb(zzatb2, new zzasq(str, zzfy.getGmpAppId(), zzfy.zzmy(), zzfy.zzJB(), zzfy.zzJC(), zzfy.zzJD(), zzfy.zzJE(), null, zzfy.zzJF(), false, zzfy.zzJy()));
    }

    void zzb(zzats zzats) {
        this.zzbtE++;
    }

    @WorkerThread
    void zzb(zzaub zzaub, zzasq zzasq) {
        int i = 0;
        zzmq();
        zznA();
        if (!TextUtils.isEmpty(zzasq.zzbqf)) {
            if (zzasq.zzbqk) {
                int zzga = zzJp().zzga(zzaub.name);
                String zza;
                if (zzga != 0) {
                    zza = zzJp().zza(zzaub.name, zzJv().zzJV(), true);
                    if (zzaub.name != null) {
                        i = zzaub.name.length();
                    }
                    zzJp().zza(zzga, "_ev", zza, i);
                    return;
                }
                zzga = zzJp().zzm(zzaub.name, zzaub.getValue());
                if (zzga != 0) {
                    zza = zzJp().zza(zzaub.name, zzJv().zzJV(), true);
                    Object value = zzaub.getValue();
                    if (value != null && ((value instanceof String) || (value instanceof CharSequence))) {
                        i = String.valueOf(value).length();
                    }
                    zzJp().zza(zzga, "_ev", zza, i);
                    return;
                }
                Object zzn = zzJp().zzn(zzaub.name, zzaub.getValue());
                if (zzn != null) {
                    zzaud zzaud = new zzaud(zzasq.packageName, zzaub.name, zzaub.zzbuZ, zzn);
                    zzJt().zzLf().zze("Setting user property", zzaud.mName, zzn);
                    zzJo().beginTransaction();
                    try {
                        zzf(zzasq);
                        boolean zza2 = zzJo().zza(zzaud);
                        zzJo().setTransactionSuccessful();
                        if (zza2) {
                            zzJt().zzLf().zze("User property set", zzaud.mName, zzaud.zzYe);
                        } else {
                            zzJt().zzLa().zze("Too many unique user properties are set. Ignoring user property", zzaud.mName, zzaud.zzYe);
                            zzJp().zza(9, null, null, 0);
                        }
                        zzJo().endTransaction();
                        return;
                    } catch (Throwable th) {
                        zzJo().endTransaction();
                    }
                } else {
                    return;
                }
            }
            zzf(zzasq);
        }
    }

    @WorkerThread
    void zzb(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        int i2 = 0;
        zzmq();
        zznA();
        zzac.zzdv(str);
        if (bArr == null) {
            bArr = new byte[0];
        }
        zzJo().beginTransaction();
        try {
            zzasp zzfy = zzJo().zzfy(str);
            int i3 = ((i == Callback.DEFAULT_DRAG_ANIMATION_DURATION || i == 204 || i == 304) && th == null) ? 1 : 0;
            if (zzfy == null) {
                zzJt().zzLc().zzj("App does not exist in onConfigFetched. appId", zzati.zzfI(str));
            } else if (i3 != 0 || i == 404) {
                List list = map != null ? (List) map.get("Last-Modified") : null;
                String str2 = (list == null || list.size() <= 0) ? null : (String) list.get(0);
                if (i == 404 || i == 304) {
                    if (zzJq().zzfO(str) == null && !zzJq().zzb(str, null, null)) {
                        zzJo().endTransaction();
                        return;
                    }
                } else if (!zzJq().zzb(str, bArr, str2)) {
                    zzJo().endTransaction();
                    return;
                }
                zzfy.zzad(zznq().currentTimeMillis());
                zzJo().zza(zzfy);
                if (i == 404) {
                    zzJt().zzLc().zzj("Config not found. Using empty config. appId", zzati.zzfI(str));
                } else {
                    zzJt().zzLg().zze("Successfully fetched config. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                }
                if (zzLy().zzpA() && zzLH()) {
                    zzLG();
                } else {
                    zzLI();
                }
            } else {
                zzfy.zzae(zznq().currentTimeMillis());
                zzJo().zza(zzfy);
                zzJt().zzLg().zze("Fetching config failed. code, error", Integer.valueOf(i), th);
                zzJq().zzfQ(str);
                zzJu().zzbsh.set(zznq().currentTimeMillis());
                if (i == 503 || i == 429) {
                    i2 = 1;
                }
                if (i2 != 0) {
                    zzJu().zzbsi.set(zznq().currentTimeMillis());
                }
                zzLI();
            }
            zzJo().setTransactionSuccessful();
        } finally {
            zzJo().endTransaction();
        }
    }

    @WorkerThread
    void zzc(zzasq zzasq, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong("_et", 1);
        zzb(new zzatb("_e", new zzasz(bundle), "auto", j), zzasq);
    }

    @WorkerThread
    void zzc(zzaub zzaub, zzasq zzasq) {
        zzmq();
        zznA();
        if (!TextUtils.isEmpty(zzasq.zzbqf)) {
            if (zzasq.zzbqk) {
                zzJt().zzLf().zzj("Removing user property", zzaub.name);
                zzJo().beginTransaction();
                try {
                    zzf(zzasq);
                    zzJo().zzQ(zzasq.packageName, zzaub.name);
                    zzJo().setTransactionSuccessful();
                    zzJt().zzLf().zzj("User property removed", zzaub.name);
                } finally {
                    zzJo().endTransaction();
                }
            } else {
                zzf(zzasq);
            }
        }
    }

    void zzd(zzasq zzasq) {
        zzmq();
        zznA();
        zzac.zzdv(zzasq.packageName);
        zzf(zzasq);
    }

    @WorkerThread
    void zzd(zzasq zzasq, long j) {
        zzb(new zzatb("_cd", new zzasz(new Bundle()), "auto", j), zzasq);
    }

    @WorkerThread
    public void zze(zzasq zzasq) {
        zzmq();
        zznA();
        zzac.zzw(zzasq);
        zzac.zzdv(zzasq.packageName);
        if (!TextUtils.isEmpty(zzasq.zzbqf)) {
            if (zzasq.zzbqk) {
                long currentTimeMillis = zznq().currentTimeMillis();
                zzJo().beginTransaction();
                try {
                    zza(zzasq, currentTimeMillis);
                    zzf(zzasq);
                    if (zzJo().zzP(zzasq.packageName, "_f") == null) {
                        zzb(new zzaub("_fot", currentTimeMillis, Long.valueOf((1 + (currentTimeMillis / 3600000)) * 3600000), "auto"), zzasq);
                        zzb(zzasq, currentTimeMillis);
                        zzc(zzasq, currentTimeMillis);
                    } else if (zzasq.zzbql) {
                        zzd(zzasq, currentTimeMillis);
                    }
                    zzJo().setTransactionSuccessful();
                } finally {
                    zzJo().endTransaction();
                }
            } else {
                zzf(zzasq);
            }
        }
    }

    public String zzfR(final String str) {
        Object e;
        try {
            return (String) zzJs().zzd(new Callable<String>(this) {
                final /* synthetic */ zzatp zzbtH;

                public /* synthetic */ Object call() throws Exception {
                    return zzou();
                }

                public String zzou() throws Exception {
                    zzasp zzfy = this.zzbtH.zzJo().zzfy(str);
                    return zzfy == null ? null : zzfy.getAppInstanceId();
                }
            }).get(30000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e2) {
            e = e2;
        } catch (InterruptedException e3) {
            e = e3;
        } catch (ExecutionException e4) {
            e = e4;
        }
        zzJt().zzLa().zze("Failed to get app instance id. appId", zzati.zzfI(str), e);
        return null;
    }

    @WorkerThread
    public void zzmq() {
        zzJs().zzmq();
    }

    void zznA() {
        if (!this.zzacO) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    public zze zznq() {
        return this.zzuI;
    }

    @WorkerThread
    boolean zzv(int i, int i2) {
        zzmq();
        if (i > i2) {
            zzJt().zzLa().zze("Panic: can't downgrade version. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
            return false;
        }
        if (i < i2) {
            if (zza(i2, zzLB())) {
                zzJt().zzLg().zze("Storage version upgraded. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
            } else {
                zzJt().zzLa().zze("Storage version upgrade failed. Previous, current version", Integer.valueOf(i), Integer.valueOf(i2));
                return false;
            }
        }
        return true;
    }
}
