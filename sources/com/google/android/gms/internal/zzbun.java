package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzbun<M extends zzbun<M>> extends zzbut {
    protected zzbup zzcrX;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzacN();
    }

    public final <T> T zza(zzbuo<M, T> zzbuo) {
        if (this.zzcrX == null) {
            return null;
        }
        zzbuq zzqx = this.zzcrX.zzqx(zzbuw.zzqB(zzbuo.tag));
        return zzqx != null ? zzqx.zzb(zzbuo) : null;
    }

    public void zza(zzbum zzbum) throws IOException {
        if (this.zzcrX != null) {
            for (int i = 0; i < this.zzcrX.size(); i++) {
                this.zzcrX.zzqy(i).zza(zzbum);
            }
        }
    }

    protected final boolean zza(zzbul zzbul, int i) throws IOException {
        int position = zzbul.getPosition();
        if (!zzbul.zzqh(i)) {
            return false;
        }
        int zzqB = zzbuw.zzqB(i);
        zzbuv zzbuv = new zzbuv(i, zzbul.zzE(position, zzbul.getPosition() - position));
        zzbuq zzbuq = null;
        if (this.zzcrX == null) {
            this.zzcrX = new zzbup();
        } else {
            zzbuq = this.zzcrX.zzqx(zzqB);
        }
        if (zzbuq == null) {
            zzbuq = new zzbuq();
            this.zzcrX.zza(zzqB, zzbuq);
        }
        zzbuq.zza(zzbuv);
        return true;
    }

    public M zzacN() throws CloneNotSupportedException {
        zzbun zzbun = (zzbun) super.zzacO();
        zzbur.zza(this, zzbun);
        return zzbun;
    }

    public /* synthetic */ zzbut zzacO() throws CloneNotSupportedException {
        return (zzbun) clone();
    }

    protected int zzv() {
        int i = 0;
        if (this.zzcrX == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.zzcrX.size()) {
            i2 += this.zzcrX.zzqy(i).zzv();
            i++;
        }
        return i2;
    }
}
