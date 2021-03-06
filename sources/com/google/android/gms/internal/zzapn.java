package com.google.android.gms.internal;

import android.os.RemoteException;

public abstract class zzapn<T> {
    private final int zzAG;
    private final String zzAH;
    private final T zzAI;

    public static class zza extends zzapn<Boolean> {
        public zza(int i, String str, Boolean bool) {
            super(i, str, bool);
        }

        public /* synthetic */ Object zza(zzapq zzapq) {
            return zzb(zzapq);
        }

        public Boolean zzb(zzapq zzapq) {
            try {
                return Boolean.valueOf(zzapq.getBooleanFlagValue(getKey(), ((Boolean) zzfm()).booleanValue(), getSource()));
            } catch (RemoteException e) {
                return (Boolean) zzfm();
            }
        }
    }

    public static class zzb extends zzapn<Integer> {
        public zzb(int i, String str, Integer num) {
            super(i, str, num);
        }

        public /* synthetic */ Object zza(zzapq zzapq) {
            return zzc(zzapq);
        }

        public Integer zzc(zzapq zzapq) {
            try {
                return Integer.valueOf(zzapq.getIntFlagValue(getKey(), ((Integer) zzfm()).intValue(), getSource()));
            } catch (RemoteException e) {
                return (Integer) zzfm();
            }
        }
    }

    public static class zzc extends zzapn<Long> {
        public zzc(int i, String str, Long l) {
            super(i, str, l);
        }

        public /* synthetic */ Object zza(zzapq zzapq) {
            return zzd(zzapq);
        }

        public Long zzd(zzapq zzapq) {
            try {
                return Long.valueOf(zzapq.getLongFlagValue(getKey(), ((Long) zzfm()).longValue(), getSource()));
            } catch (RemoteException e) {
                return (Long) zzfm();
            }
        }
    }

    public static class zzd extends zzapn<String> {
        public zzd(int i, String str, String str2) {
            super(i, str, str2);
        }

        public /* synthetic */ Object zza(zzapq zzapq) {
            return zze(zzapq);
        }

        public String zze(zzapq zzapq) {
            try {
                return zzapq.getStringFlagValue(getKey(), (String) zzfm(), getSource());
            } catch (RemoteException e) {
                return (String) zzfm();
            }
        }
    }

    private zzapn(int i, String str, T t) {
        this.zzAG = i;
        this.zzAH = str;
        this.zzAI = t;
        zzapr.zzCQ().zza(this);
    }

    public static zza zzb(int i, String str, Boolean bool) {
        return new zza(i, str, bool);
    }

    public static zzb zzb(int i, String str, int i2) {
        return new zzb(i, str, Integer.valueOf(i2));
    }

    public static zzc zzb(int i, String str, long j) {
        return new zzc(i, str, Long.valueOf(j));
    }

    public static zzd zzc(int i, String str, String str2) {
        return new zzd(i, str, str2);
    }

    public T get() {
        return zzapr.zzCR().zzb(this);
    }

    public String getKey() {
        return this.zzAH;
    }

    public int getSource() {
        return this.zzAG;
    }

    protected abstract T zza(zzapq zzapq);

    public T zzfm() {
        return this.zzAI;
    }
}
