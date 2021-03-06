package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.safeparcel.zzb.zza;
import com.google.android.gms.common.internal.safeparcel.zzc;

public class zzatc implements Creator<zzatb> {
    static void zza(zzatb zzatb, Parcel parcel, int i) {
        int zzaV = zzc.zzaV(parcel);
        zzc.zzc(parcel, 1, zzatb.versionCode);
        zzc.zza(parcel, 2, zzatb.name, false);
        zzc.zza(parcel, 3, zzatb.zzbqP, i, false);
        zzc.zza(parcel, 4, zzatb.zzbqQ, false);
        zzc.zza(parcel, 5, zzatb.zzbqR);
        zzc.zzJ(parcel, zzaV);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzhM(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzlr(i);
    }

    public zzatb zzhM(Parcel parcel) {
        String str = null;
        int zzaU = zzb.zzaU(parcel);
        int i = 0;
        long j = 0;
        zzasz zzasz = null;
        String str2 = null;
        while (parcel.dataPosition() < zzaU) {
            int zzaT = zzb.zzaT(parcel);
            switch (zzb.zzcW(zzaT)) {
                case 1:
                    i = zzb.zzg(parcel, zzaT);
                    break;
                case 2:
                    str2 = zzb.zzq(parcel, zzaT);
                    break;
                case 3:
                    zzasz = (zzasz) zzb.zza(parcel, zzaT, zzasz.CREATOR);
                    break;
                case 4:
                    str = zzb.zzq(parcel, zzaT);
                    break;
                case 5:
                    j = zzb.zzi(parcel, zzaT);
                    break;
                default:
                    zzb.zzb(parcel, zzaT);
                    break;
            }
        }
        if (parcel.dataPosition() == zzaU) {
            return new zzatb(i, str2, zzasz, str, j);
        }
        throw new zza("Overread allowed size end=" + zzaU, parcel);
    }

    public zzatb[] zzlr(int i) {
        return new zzatb[i];
    }
}
