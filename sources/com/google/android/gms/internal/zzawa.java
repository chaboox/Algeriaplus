package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzaa;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class zzawa extends zza implements Comparable<zzawa> {
    public static final Creator<zzawa> CREATOR = new zzawb();
    final int mVersionCode;
    public final int zzbzp;
    public final zzawc[] zzbzq;
    public final String[] zzbzr;
    public final Map<String, zzawc> zzbzs = new TreeMap();

    zzawa(int i, int i2, zzawc[] zzawcArr, String[] strArr) {
        this.mVersionCode = i;
        this.zzbzp = i2;
        this.zzbzq = zzawcArr;
        for (zzawc zzawc : zzawcArr) {
            this.zzbzs.put(zzawc.name, zzawc);
        }
        this.zzbzr = strArr;
        if (this.zzbzr != null) {
            Arrays.sort(this.zzbzr);
        }
    }

    public /* synthetic */ int compareTo(Object obj) {
        return zza((zzawa) obj);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzawa)) {
            return false;
        }
        zzawa zzawa = (zzawa) obj;
        return this.mVersionCode == zzawa.mVersionCode && this.zzbzp == zzawa.zzbzp && zzaa.equal(this.zzbzs, zzawa.zzbzs) && Arrays.equals(this.zzbzr, zzawa.zzbzr);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Configuration(");
        stringBuilder.append(this.mVersionCode);
        stringBuilder.append(", ");
        stringBuilder.append(this.zzbzp);
        stringBuilder.append(", ");
        stringBuilder.append("(");
        for (zzawc append : this.zzbzs.values()) {
            stringBuilder.append(append);
            stringBuilder.append(", ");
        }
        stringBuilder.append(")");
        stringBuilder.append(", ");
        stringBuilder.append("(");
        if (this.zzbzr != null) {
            for (String append2 : this.zzbzr) {
                stringBuilder.append(append2);
                stringBuilder.append(", ");
            }
        } else {
            stringBuilder.append("null");
        }
        stringBuilder.append(")");
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzawb.zza(this, parcel, i);
    }

    public int zza(zzawa zzawa) {
        return this.zzbzp - zzawa.zzbzp;
    }
}
