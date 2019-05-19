package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class zzbuo<M extends zzbun<M>, T> {
    public final int tag;
    protected final int type;
    protected final Class<T> zzciF;
    protected final boolean zzcrY;

    private zzbuo(int i, Class<T> cls, int i2, boolean z) {
        this.type = i;
        this.zzciF = cls;
        this.tag = i2;
        this.zzcrY = z;
    }

    public static <M extends zzbun<M>, T extends zzbut> zzbuo<M, T> zza(int i, Class<T> cls, long j) {
        return new zzbuo(i, cls, (int) j, false);
    }

    private T zzaa(List<zzbuv> list) {
        int i;
        int i2 = 0;
        List arrayList = new ArrayList();
        for (i = 0; i < list.size(); i++) {
            zzbuv zzbuv = (zzbuv) list.get(i);
            if (zzbuv.zzcsh.length != 0) {
                zza(zzbuv, arrayList);
            }
        }
        i = arrayList.size();
        if (i == 0) {
            return null;
        }
        T cast = this.zzciF.cast(Array.newInstance(this.zzciF.getComponentType(), i));
        while (i2 < i) {
            Array.set(cast, i2, arrayList.get(i2));
            i2++;
        }
        return cast;
    }

    private T zzab(List<zzbuv> list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.zzciF.cast(zzaM(zzbul.zzad(((zzbuv) list.get(list.size() - 1)).zzcsh)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbuo)) {
            return false;
        }
        zzbuo zzbuo = (zzbuo) obj;
        return this.type == zzbuo.type && this.zzciF == zzbuo.zzciF && this.tag == zzbuo.tag && this.zzcrY == zzbuo.zzcrY;
    }

    public int hashCode() {
        return (this.zzcrY ? 1 : 0) + ((((((this.type + 1147) * 31) + this.zzciF.hashCode()) * 31) + this.tag) * 31);
    }

    final T zzZ(List<zzbuv> list) {
        return list == null ? null : this.zzcrY ? zzaa(list) : zzab(list);
    }

    protected void zza(zzbuv zzbuv, List<Object> list) {
        list.add(zzaM(zzbul.zzad(zzbuv.zzcsh)));
    }

    void zza(Object obj, zzbum zzbum) throws IOException {
        if (this.zzcrY) {
            zzc(obj, zzbum);
        } else {
            zzb(obj, zzbum);
        }
    }

    protected Object zzaM(zzbul zzbul) {
        String valueOf;
        Class componentType = this.zzcrY ? this.zzciF.getComponentType() : this.zzciF;
        try {
            zzbut zzbut;
            switch (this.type) {
                case 10:
                    zzbut = (zzbut) componentType.newInstance();
                    zzbul.zza(zzbut, zzbuw.zzqB(this.tag));
                    return zzbut;
                case 11:
                    zzbut = (zzbut) componentType.newInstance();
                    zzbul.zza(zzbut);
                    return zzbut;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (Throwable e) {
            valueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e);
        } catch (Throwable e2) {
            valueOf = String.valueOf(componentType);
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 33).append("Error creating instance of class ").append(valueOf).toString(), e2);
        } catch (Throwable e22) {
            throw new IllegalArgumentException("Error reading extension field", e22);
        }
    }

    int zzaR(Object obj) {
        return this.zzcrY ? zzaS(obj) : zzaT(obj);
    }

    protected int zzaS(Object obj) {
        int i = 0;
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            if (Array.get(obj, i2) != null) {
                i += zzaT(Array.get(obj, i2));
            }
        }
        return i;
    }

    protected int zzaT(Object obj) {
        int zzqB = zzbuw.zzqB(this.tag);
        switch (this.type) {
            case 10:
                return zzbum.zzb(zzqB, (zzbut) obj);
            case 11:
                return zzbum.zzc(zzqB, (zzbut) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }

    protected void zzb(Object obj, zzbum zzbum) {
        try {
            zzbum.zzqt(this.tag);
            switch (this.type) {
                case 10:
                    zzbut zzbut = (zzbut) obj;
                    int zzqB = zzbuw.zzqB(this.tag);
                    zzbum.zzb(zzbut);
                    zzbum.zzJ(zzqB, 4);
                    return;
                case 11:
                    zzbum.zzc((zzbut) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    protected void zzc(Object obj, zzbum zzbum) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                zzb(obj2, zzbum);
            }
        }
    }
}
