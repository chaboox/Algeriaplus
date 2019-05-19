package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class zzbuq implements Cloneable {
    private Object value;
    private zzbuo<?, ?> zzcsd;
    private List<zzbuv> zzcse = new ArrayList();

    zzbuq() {
    }

    private byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzv()];
        zza(zzbum.zzae(bArr));
        return bArr;
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzacQ();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbuq)) {
            return false;
        }
        zzbuq zzbuq = (zzbuq) obj;
        if (this.value != null && zzbuq.value != null) {
            return this.zzcsd == zzbuq.zzcsd ? !this.zzcsd.zzciF.isArray() ? this.value.equals(zzbuq.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzbuq.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzbuq.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzbuq.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzbuq.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzbuq.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzbuq.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzbuq.value) : false;
        } else {
            if (this.zzcse != null && zzbuq.zzcse != null) {
                return this.zzcse.equals(zzbuq.zzcse);
            }
            try {
                return Arrays.equals(toByteArray(), zzbuq.toByteArray());
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    void zza(zzbum zzbum) throws IOException {
        if (this.value != null) {
            this.zzcsd.zza(this.value, zzbum);
            return;
        }
        for (zzbuv zza : this.zzcse) {
            zza.zza(zzbum);
        }
    }

    void zza(zzbuv zzbuv) {
        this.zzcse.add(zzbuv);
    }

    public final zzbuq zzacQ() {
        zzbuq zzbuq = new zzbuq();
        try {
            zzbuq.zzcsd = this.zzcsd;
            if (this.zzcse == null) {
                zzbuq.zzcse = null;
            } else {
                zzbuq.zzcse.addAll(this.zzcse);
            }
            if (this.value != null) {
                if (this.value instanceof zzbut) {
                    zzbuq.value = (zzbut) ((zzbut) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    zzbuq.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    r4 = new byte[bArr.length][];
                    zzbuq.value = r4;
                    for (r2 = 0; r2 < bArr.length; r2++) {
                        r4[r2] = (byte[]) bArr[r2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    zzbuq.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    zzbuq.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    zzbuq.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    zzbuq.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    zzbuq.value = ((double[]) this.value).clone();
                } else if (this.value instanceof zzbut[]) {
                    zzbut[] zzbutArr = (zzbut[]) this.value;
                    r4 = new zzbut[zzbutArr.length];
                    zzbuq.value = r4;
                    for (r2 = 0; r2 < zzbutArr.length; r2++) {
                        r4[r2] = (zzbut) zzbutArr[r2].clone();
                    }
                }
            }
            return zzbuq;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    <T> T zzb(zzbuo<?, T> zzbuo) {
        if (this.value == null) {
            this.zzcsd = zzbuo;
            this.value = zzbuo.zzZ(this.zzcse);
            this.zzcse = null;
        } else if (!this.zzcsd.equals(zzbuo)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }

    int zzv() {
        if (this.value != null) {
            return this.zzcsd.zzaR(this.value);
        }
        int i = 0;
        for (zzbuv zzv : this.zzcse) {
            i = zzv.zzv() + i;
        }
        return i;
    }
}
