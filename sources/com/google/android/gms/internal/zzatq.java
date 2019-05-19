package com.google.android.gms.internal;

import android.os.Binder;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.common.util.zzx;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzate.zza;
import com.google.android.gms.measurement.AppMeasurement.zzf;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class zzatq extends zza {
    private final zzatp zzbpw;
    private Boolean zzbtL;
    @Nullable
    private String zzbtM;

    public zzatq(zzatp zzatp) {
        this(zzatp, null);
    }

    public zzatq(zzatp zzatp, @Nullable String str) {
        zzac.zzw(zzatp);
        this.zzbpw = zzatp;
        this.zzbtM = str;
    }

    @BinderThread
    private void zzb(zzasq zzasq, boolean z) {
        zzac.zzw(zzasq);
        zzm(zzasq.packageName, z);
        this.zzbpw.zzJp().zzgd(zzasq.zzbqf);
    }

    @BinderThread
    private void zzm(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            this.zzbpw.zzJt().zzLa().log("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        try {
            zzn(str, z);
        } catch (SecurityException e) {
            this.zzbpw.zzJt().zzLa().zzj("Measurement Service called with invalid calling package. appId", zzati.zzfI(str));
            throw e;
        }
    }

    @BinderThread
    public List<zzaub> zza(final zzasq zzasq, boolean z) {
        Object e;
        zzb(zzasq, false);
        try {
            List<zzaud> list = (List) this.zzbpw.zzJs().zzd(new Callable<List<zzaud>>(this) {
                final /* synthetic */ zzatq zzbtO;

                public /* synthetic */ Object call() throws Exception {
                    return zzLO();
                }

                public List<zzaud> zzLO() throws Exception {
                    this.zzbtO.zzbpw.zzLL();
                    return this.zzbtO.zzbpw.zzJo().zzfx(zzasq.packageName);
                }
            }).get();
            List<zzaub> arrayList = new ArrayList(list.size());
            for (zzaud zzaud : list) {
                if (z || !zzaue.zzgg(zzaud.mName)) {
                    arrayList.add(new zzaub(zzaud));
                }
            }
            return arrayList;
        } catch (InterruptedException e2) {
            e = e2;
            this.zzbpw.zzJt().zzLa().zze("Failed to get user attributes. appId", zzati.zzfI(zzasq.packageName), e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            this.zzbpw.zzJt().zzLa().zze("Failed to get user attributes. appId", zzati.zzfI(zzasq.packageName), e);
            return null;
        }
    }

    @BinderThread
    public void zza(long j, String str, String str2, String str3) {
        final String str4 = str2;
        final String str5 = str3;
        final String str6 = str;
        final long j2 = j;
        this.zzbpw.zzJs().zzm(new Runnable(this) {
            final /* synthetic */ zzatq zzbtO;

            public void run() {
                if (str4 == null) {
                    this.zzbtO.zzbpw.zzJm().zza(str5, null);
                    return;
                }
                zzf zzf = new zzf();
                zzf.zzbpz = str6;
                zzf.zzbpA = str4;
                zzf.zzbpB = j2;
                this.zzbtO.zzbpw.zzJm().zza(str5, zzf);
            }
        });
    }

    @BinderThread
    public void zza(final zzasq zzasq) {
        zzb(zzasq, false);
        this.zzbpw.zzJs().zzm(new Runnable(this) {
            final /* synthetic */ zzatq zzbtO;

            public void run() {
                this.zzbtO.zzbpw.zzLL();
                this.zzbtO.zzbpw.zze(zzasq);
            }
        });
    }

    @BinderThread
    public void zza(final zzatb zzatb, final zzasq zzasq) {
        zzac.zzw(zzatb);
        zzb(zzasq, false);
        this.zzbpw.zzJs().zzm(new Runnable(this) {
            final /* synthetic */ zzatq zzbtO;

            public void run() {
                this.zzbtO.zzbpw.zzLL();
                this.zzbtO.zzbpw.zzb(zzatb, zzasq);
            }
        });
    }

    @BinderThread
    public void zza(final zzatb zzatb, final String str, String str2) {
        zzac.zzw(zzatb);
        zzac.zzdv(str);
        zzm(str, true);
        this.zzbpw.zzJs().zzm(new Runnable(this) {
            final /* synthetic */ zzatq zzbtO;

            public void run() {
                this.zzbtO.zzbpw.zzLL();
                this.zzbtO.zzbpw.zzb(zzatb, str);
            }
        });
    }

    @BinderThread
    public void zza(final zzaub zzaub, final zzasq zzasq) {
        zzac.zzw(zzaub);
        zzb(zzasq, false);
        if (zzaub.getValue() == null) {
            this.zzbpw.zzJs().zzm(new Runnable(this) {
                final /* synthetic */ zzatq zzbtO;

                public void run() {
                    this.zzbtO.zzbpw.zzLL();
                    this.zzbtO.zzbpw.zzc(zzaub, zzasq);
                }
            });
        } else {
            this.zzbpw.zzJs().zzm(new Runnable(this) {
                final /* synthetic */ zzatq zzbtO;

                public void run() {
                    this.zzbtO.zzbpw.zzLL();
                    this.zzbtO.zzbpw.zzb(zzaub, zzasq);
                }
            });
        }
    }

    @BinderThread
    public byte[] zza(final zzatb zzatb, final String str) {
        Object e;
        zzac.zzdv(str);
        zzac.zzw(zzatb);
        zzm(str, true);
        this.zzbpw.zzJt().zzLf().zzj("Log and bundle. event", zzatb.name);
        long nanoTime = this.zzbpw.zznq().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zzbpw.zzJs().zze(new Callable<byte[]>(this) {
                final /* synthetic */ zzatq zzbtO;

                public /* synthetic */ Object call() throws Exception {
                    return zzLN();
                }

                public byte[] zzLN() throws Exception {
                    this.zzbtO.zzbpw.zzLL();
                    return this.zzbtO.zzbpw.zza(zzatb, str);
                }
            }).get();
            if (bArr == null) {
                this.zzbpw.zzJt().zzLa().zzj("Log and bundle returned null. appId", zzati.zzfI(str));
                bArr = new byte[0];
            }
            this.zzbpw.zzJt().zzLf().zzd("Log and bundle processed. event, size, time_ms", zzatb.name, Integer.valueOf(bArr.length), Long.valueOf((this.zzbpw.zznq().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException e2) {
            e = e2;
            this.zzbpw.zzJt().zzLa().zzd("Failed to log and bundle. appId, event, error", zzati.zzfI(str), zzatb.name, e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            this.zzbpw.zzJt().zzLa().zzd("Failed to log and bundle. appId, event, error", zzati.zzfI(str), zzatb.name, e);
            return null;
        }
    }

    @BinderThread
    public void zzb(final zzasq zzasq) {
        zzb(zzasq, false);
        this.zzbpw.zzJs().zzm(new Runnable(this) {
            final /* synthetic */ zzatq zzbtO;

            public void run() {
                this.zzbtO.zzbpw.zzLL();
                this.zzbtO.zzbpw.zzd(zzasq);
            }
        });
    }

    @BinderThread
    public String zzc(zzasq zzasq) {
        zzb(zzasq, false);
        return this.zzbpw.zzfR(zzasq.packageName);
    }

    protected void zzn(String str, boolean z) throws SecurityException {
        if (z) {
            if (this.zzbtL == null) {
                boolean z2 = "com.google.android.gms".equals(this.zzbtM) || zzx.zzf(this.zzbpw.getContext(), Binder.getCallingUid()) || com.google.android.gms.common.zzf.zzav(this.zzbpw.getContext()).zza(this.zzbpw.getContext().getPackageManager(), Binder.getCallingUid());
                this.zzbtL = Boolean.valueOf(z2);
            }
            if (this.zzbtL.booleanValue()) {
                return;
            }
        }
        if (this.zzbtM == null && zze.zzc(this.zzbpw.getContext(), Binder.getCallingUid(), str)) {
            this.zzbtM = str;
        }
        if (!str.equals(this.zzbtM)) {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
        }
    }
}
