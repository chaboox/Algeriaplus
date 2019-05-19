package com.google.android.gms.internal;

import com.google.android.gms.internal.zzm.zza;
import com.google.android.gms.internal.zzm.zzb;
import java.io.UnsupportedEncodingException;

public class zzab extends zzk<String> {
    private final zzb<String> zzaF;

    public zzab(int i, String str, zzb<String> zzb, zza zza) {
        super(i, str, zza);
        this.zzaF = zzb;
    }

    protected zzm<String> zza(zzi zzi) {
        Object str;
        try {
            str = new String(zzi.data, zzx.zza(zzi.zzy));
        } catch (UnsupportedEncodingException e) {
            str = new String(zzi.data);
        }
        return zzm.zza(str, zzx.zzb(zzi));
    }

    protected /* synthetic */ void zza(Object obj) {
        zzi((String) obj);
    }

    protected void zzi(String str) {
        this.zzaF.zzb(str);
    }
}
