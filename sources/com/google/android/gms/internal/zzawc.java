package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzaa;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;

public class zzawc extends com.google.android.gms.common.internal.safeparcel.zza implements Comparable<zzawc> {
    public static final Creator<zzawc> CREATOR = new zzawd();
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final zza zzbzx = new zza();
    final int mVersionCode;
    public final String name;
    final String zzaFy;
    final boolean zzbgG;
    final double zzbgI;
    final long zzbzt;
    final byte[] zzbzu;
    public final int zzbzv;
    public final int zzbzw;

    public static class zza implements Comparator<zzawc> {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return zza((zzawc) obj, (zzawc) obj2);
        }

        public int zza(zzawc zzawc, zzawc zzawc2) {
            return zzawc.zzbzw == zzawc2.zzbzw ? zzawc.name.compareTo(zzawc2.name) : zzawc.zzbzw - zzawc2.zzbzw;
        }
    }

    zzawc(int i, String str, long j, boolean z, double d, String str2, byte[] bArr, int i2, int i3) {
        this.mVersionCode = i;
        this.name = str;
        this.zzbzt = j;
        this.zzbgG = z;
        this.zzbgI = d;
        this.zzaFy = str2;
        this.zzbzu = bArr;
        this.zzbzv = i2;
        this.zzbzw = i3;
    }

    private static int compare(byte b, byte b2) {
        return b - b2;
    }

    private static int compare(int i, int i2) {
        return i < i2 ? -1 : i == i2 ? 0 : 1;
    }

    private static int compare(long j, long j2) {
        return j < j2 ? -1 : j == j2 ? 0 : 1;
    }

    private static int compare(String str, String str2) {
        return str == str2 ? 0 : str == null ? -1 : str2 == null ? 1 : str.compareTo(str2);
    }

    private static int compare(boolean z, boolean z2) {
        return z == z2 ? 0 : z ? 1 : -1;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return zza((zzawc) obj);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzawc)) {
            return false;
        }
        zzawc zzawc = (zzawc) obj;
        if (this.mVersionCode != zzawc.mVersionCode || !zzaa.equal(this.name, zzawc.name) || this.zzbzv != zzawc.zzbzv || this.zzbzw != zzawc.zzbzw) {
            return false;
        }
        switch (this.zzbzv) {
            case 1:
                return this.zzbzt == zzawc.zzbzt;
            case 2:
                return this.zzbgG == zzawc.zzbgG;
            case 3:
                return this.zzbgI == zzawc.zzbgI;
            case 4:
                return zzaa.equal(this.zzaFy, zzawc.zzaFy);
            case 5:
                return Arrays.equals(this.zzbzu, zzawc.zzbzu);
            default:
                throw new AssertionError("Invalid enum value: " + this.zzbzv);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        zza(stringBuilder);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzawd.zza(this, parcel, i);
    }

    public int zza(zzawc zzawc) {
        int i = 0;
        int compareTo = this.name.compareTo(zzawc.name);
        if (compareTo != 0) {
            return compareTo;
        }
        compareTo = compare(this.zzbzv, zzawc.zzbzv);
        if (compareTo != 0) {
            return compareTo;
        }
        switch (this.zzbzv) {
            case 1:
                return compare(this.zzbzt, zzawc.zzbzt);
            case 2:
                return compare(this.zzbgG, zzawc.zzbgG);
            case 3:
                return Double.compare(this.zzbgI, zzawc.zzbgI);
            case 4:
                return compare(this.zzaFy, zzawc.zzaFy);
            case 5:
                if (this.zzbzu == zzawc.zzbzu) {
                    return 0;
                }
                if (this.zzbzu == null) {
                    return -1;
                }
                if (zzawc.zzbzu == null) {
                    return 1;
                }
                while (i < Math.min(this.zzbzu.length, zzawc.zzbzu.length)) {
                    compareTo = compare(this.zzbzu[i], zzawc.zzbzu[i]);
                    if (compareTo != 0) {
                        return compareTo;
                    }
                    i++;
                }
                return compare(this.zzbzu.length, zzawc.zzbzu.length);
            default:
                throw new AssertionError("Invalid enum value: " + this.zzbzv);
        }
    }

    public String zza(StringBuilder stringBuilder) {
        stringBuilder.append("Flag(");
        stringBuilder.append(this.mVersionCode);
        stringBuilder.append(", ");
        stringBuilder.append(this.name);
        stringBuilder.append(", ");
        switch (this.zzbzv) {
            case 1:
                stringBuilder.append(this.zzbzt);
                break;
            case 2:
                stringBuilder.append(this.zzbgG);
                break;
            case 3:
                stringBuilder.append(this.zzbgI);
                break;
            case 4:
                stringBuilder.append("'");
                stringBuilder.append(this.zzaFy);
                stringBuilder.append("'");
                break;
            case 5:
                if (this.zzbzu != null) {
                    stringBuilder.append("'");
                    stringBuilder.append(new String(this.zzbzu, UTF_8));
                    stringBuilder.append("'");
                    break;
                }
                stringBuilder.append("null");
                break;
            default:
                String str = this.name;
                throw new AssertionError(new StringBuilder(String.valueOf(str).length() + 27).append("Invalid type: ").append(str).append(", ").append(this.zzbzv).toString());
        }
        stringBuilder.append(", ");
        stringBuilder.append(this.zzbzv);
        stringBuilder.append(", ");
        stringBuilder.append(this.zzbzw);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
