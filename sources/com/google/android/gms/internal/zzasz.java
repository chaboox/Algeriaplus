package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.zzac;
import java.util.Iterator;

public class zzasz extends zza implements Iterable<String> {
    public static final Creator<zzasz> CREATOR = new zzata();
    public final int versionCode;
    private final Bundle zzbqM;

    /* renamed from: com.google.android.gms.internal.zzasz$1 */
    class C02901 implements Iterator<String> {
        Iterator<String> zzbqN = this.zzbqO.zzbqM.keySet().iterator();
        final /* synthetic */ zzasz zzbqO;

        C02901(zzasz zzasz) {
            this.zzbqO = zzasz;
        }

        public boolean hasNext() {
            return this.zzbqN.hasNext();
        }

        public String next() {
            return (String) this.zzbqN.next();
        }

        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }
    }

    zzasz(int i, Bundle bundle) {
        this.versionCode = i;
        this.zzbqM = bundle;
    }

    zzasz(Bundle bundle) {
        zzac.zzw(bundle);
        this.zzbqM = bundle;
        this.versionCode = 1;
    }

    Object get(String str) {
        return this.zzbqM.get(str);
    }

    public Iterator<String> iterator() {
        return new C02901(this);
    }

    public int size() {
        return this.zzbqM.size();
    }

    public String toString() {
        return this.zzbqM.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzata.zza(this, parcel, i);
    }

    public Bundle zzKY() {
        return new Bundle(this.zzbqM);
    }
}
