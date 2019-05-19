package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzac;
import com.google.android.gms.internal.zzauf.zza;
import com.google.android.gms.internal.zzauf.zzb;
import com.google.android.gms.internal.zzauf.zzc;
import com.google.android.gms.internal.zzauf.zzd;
import com.google.android.gms.internal.zzauf.zze;
import com.google.android.gms.internal.zzauf.zzf;
import com.google.android.gms.internal.zzauh.zzg;
import com.google.android.gms.measurement.AppMeasurement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

class zzass extends zzats {
    zzass(zzatp zzatp) {
        super(zzatp);
    }

    private Boolean zza(zzb zzb, zzauh.zzb zzb2, long j) {
        Boolean zza;
        if (zzb.zzbvp != null) {
            zza = zza(j, zzb.zzbvp);
            if (zza == null) {
                return null;
            }
            if (!zza.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }
        Set hashSet = new HashSet();
        for (zzc zzc : zzb.zzbvn) {
            if (TextUtils.isEmpty(zzc.zzbvu)) {
                zzJt().zzLc().zzj("null or empty param name in filter. event", zzb2.name);
                return null;
            }
            hashSet.add(zzc.zzbvu);
        }
        Map arrayMap = new ArrayMap();
        for (zzauh.zzc zzc2 : zzb2.zzbvV) {
            if (hashSet.contains(zzc2.name)) {
                if (zzc2.zzbvZ != null) {
                    arrayMap.put(zzc2.name, zzc2.zzbvZ);
                } else if (zzc2.zzbvc != null) {
                    arrayMap.put(zzc2.name, zzc2.zzbvc);
                } else if (zzc2.zzaFy != null) {
                    arrayMap.put(zzc2.name, zzc2.zzaFy);
                } else {
                    zzJt().zzLc().zze("Unknown value for param. event, param", zzb2.name, zzc2.name);
                    return null;
                }
            }
        }
        for (zzc zzc3 : zzb.zzbvn) {
            boolean equals = Boolean.TRUE.equals(zzc3.zzbvt);
            CharSequence charSequence = zzc3.zzbvu;
            if (TextUtils.isEmpty(charSequence)) {
                zzJt().zzLc().zzj("Event has empty param name. event", zzb2.name);
                return null;
            }
            Object obj = arrayMap.get(charSequence);
            if (obj instanceof Long) {
                if (zzc3.zzbvs == null) {
                    zzJt().zzLc().zze("No number filter for long param. event, param", zzb2.name, charSequence);
                    return null;
                }
                zza = zza(((Long) obj).longValue(), zzc3.zzbvs);
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof Double) {
                if (zzc3.zzbvs == null) {
                    zzJt().zzLc().zze("No number filter for double param. event, param", zzb2.name, charSequence);
                    return null;
                }
                zza = zza(((Double) obj).doubleValue(), zzc3.zzbvs);
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof String) {
                if (zzc3.zzbvr == null) {
                    zzJt().zzLc().zze("No string filter for String param. event, param", zzb2.name, charSequence);
                    return null;
                }
                zza = zza((String) obj, zzc3.zzbvr);
                if (zza == null) {
                    return null;
                }
                if (((!zza.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj == null) {
                zzJt().zzLg().zze("Missing param for filter. event, param", zzb2.name, charSequence);
                return Boolean.valueOf(false);
            } else {
                zzJt().zzLc().zze("Unknown param type. event, param", zzb2.name, charSequence);
                return null;
            }
        }
        return Boolean.valueOf(true);
    }

    private Boolean zza(zze zze, zzg zzg) {
        zzc zzc = zze.zzbvC;
        if (zzc == null) {
            zzJt().zzLc().zzj("Missing property filter. property", zzg.name);
            return null;
        }
        boolean equals = Boolean.TRUE.equals(zzc.zzbvt);
        if (zzg.zzbvZ != null) {
            if (zzc.zzbvs != null) {
                return zza(zza(zzg.zzbvZ.longValue(), zzc.zzbvs), equals);
            }
            zzJt().zzLc().zzj("No number filter for long property. property", zzg.name);
            return null;
        } else if (zzg.zzbvc != null) {
            if (zzc.zzbvs != null) {
                return zza(zza(zzg.zzbvc.doubleValue(), zzc.zzbvs), equals);
            }
            zzJt().zzLc().zzj("No number filter for double property. property", zzg.name);
            return null;
        } else if (zzg.zzaFy == null) {
            zzJt().zzLc().zzj("User property has no value, property", zzg.name);
            return null;
        } else if (zzc.zzbvr != null) {
            return zza(zza(zzg.zzaFy, zzc.zzbvr), equals);
        } else {
            if (zzc.zzbvs == null) {
                zzJt().zzLc().zzj("No string or number filter defined. property", zzg.name);
                return null;
            } else if (zzaue.zzgi(zzg.zzaFy)) {
                return zza(zza(zzg.zzaFy, zzc.zzbvs), equals);
            } else {
                zzJt().zzLc().zze("Invalid user property value for Numeric number filter. property, value", zzg.name, zzg.zzaFy);
                return null;
            }
        }
    }

    static Boolean zza(Boolean bool, boolean z) {
        return bool == null ? null : Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!(z || i == 1)) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private Boolean zza(BigDecimal bigDecimal, int i, BigDecimal bigDecimal2, BigDecimal bigDecimal3, BigDecimal bigDecimal4, double d) {
        boolean z = true;
        if (bigDecimal == null) {
            return null;
        }
        if (i == 4) {
            if (bigDecimal3 == null || bigDecimal4 == null) {
                return null;
            }
        } else if (bigDecimal2 == null) {
            return null;
        }
        switch (i) {
            case 1:
                if (bigDecimal.compareTo(bigDecimal2) != -1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 2:
                if (bigDecimal.compareTo(bigDecimal2) != 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 3:
                if (d != 0.0d) {
                    if (!(bigDecimal.compareTo(bigDecimal2.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) == 1 && bigDecimal.compareTo(bigDecimal2.add(new BigDecimal(d).multiply(new BigDecimal(2)))) == -1)) {
                        z = false;
                    }
                    return Boolean.valueOf(z);
                }
                if (bigDecimal.compareTo(bigDecimal2) != 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 4:
                if (bigDecimal.compareTo(bigDecimal3) == -1 || bigDecimal.compareTo(bigDecimal4) == 1) {
                    z = false;
                }
                return Boolean.valueOf(z);
            default:
                return null;
        }
    }

    private List<String> zza(String[] strArr, boolean z) {
        if (z) {
            return Arrays.asList(strArr);
        }
        List<String> arrayList = new ArrayList();
        for (String toUpperCase : strArr) {
            arrayList.add(toUpperCase.toUpperCase(Locale.ENGLISH));
        }
        return arrayList;
    }

    public Boolean zza(double d, zzd zzd) {
        try {
            return zza(new BigDecimal(d), zzd, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Boolean zza(long j, zzd zzd) {
        try {
            return zza(new BigDecimal(j), zzd, 0.0d);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public Boolean zza(String str, zzd zzd) {
        Boolean bool = null;
        if (zzaue.zzgi(str)) {
            try {
                bool = zza(new BigDecimal(str), zzd, 0.0d);
            } catch (NumberFormatException e) {
            }
        }
        return bool;
    }

    Boolean zza(String str, zzf zzf) {
        String str2 = null;
        zzac.zzw(zzf);
        if (str == null || zzf.zzbvD == null || zzf.zzbvD.intValue() == 0) {
            return null;
        }
        if (zzf.zzbvD.intValue() == 6) {
            if (zzf.zzbvG == null || zzf.zzbvG.length == 0) {
                return null;
            }
        } else if (zzf.zzbvE == null) {
            return null;
        }
        int intValue = zzf.zzbvD.intValue();
        boolean z = zzf.zzbvF != null && zzf.zzbvF.booleanValue();
        String toUpperCase = (z || intValue == 1 || intValue == 6) ? zzf.zzbvE : zzf.zzbvE.toUpperCase(Locale.ENGLISH);
        List zza = zzf.zzbvG == null ? null : zza(zzf.zzbvG, z);
        if (intValue == 1) {
            str2 = toUpperCase;
        }
        return zza(str, intValue, z, toUpperCase, zza, str2);
    }

    Boolean zza(BigDecimal bigDecimal, zzd zzd, double d) {
        zzac.zzw(zzd);
        if (zzd.zzbvv == null || zzd.zzbvv.intValue() == 0) {
            return null;
        }
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        if (zzd.zzbvv.intValue() == 4) {
            if (zzd.zzbvy == null || zzd.zzbvz == null) {
                return null;
            }
        } else if (zzd.zzbvx == null) {
            return null;
        }
        int intValue = zzd.zzbvv.intValue();
        if (zzd.zzbvv.intValue() == 4) {
            if (!zzaue.zzgi(zzd.zzbvy) || !zzaue.zzgi(zzd.zzbvz)) {
                return null;
            }
            try {
                bigDecimal2 = new BigDecimal(zzd.zzbvy);
                bigDecimal3 = new BigDecimal(zzd.zzbvz);
                bigDecimal4 = null;
            } catch (NumberFormatException e) {
                return null;
            }
        } else if (!zzaue.zzgi(zzd.zzbvx)) {
            return null;
        } else {
            try {
                bigDecimal4 = new BigDecimal(zzd.zzbvx);
                bigDecimal3 = null;
                bigDecimal2 = null;
            } catch (NumberFormatException e2) {
                return null;
            }
        }
        return zza(bigDecimal, intValue, bigDecimal4, bigDecimal2, bigDecimal3, d);
    }

    @WorkerThread
    void zza(String str, zza[] zzaArr) {
        zzac.zzw(zzaArr);
        for (zza zza : zzaArr) {
            for (zzb zzb : zza.zzbvj) {
                String str2 = (String) AppMeasurement.zza.zzbpx.get(zzb.zzbvm);
                if (str2 != null) {
                    zzb.zzbvm = str2;
                }
                for (zzc zzc : zzb.zzbvn) {
                    str2 = (String) AppMeasurement.zze.zzbpy.get(zzc.zzbvu);
                    if (str2 != null) {
                        zzc.zzbvu = str2;
                    }
                }
            }
            for (zze zze : zza.zzbvi) {
                str2 = (String) AppMeasurement.zzg.zzbpC.get(zze.zzbvB);
                if (str2 != null) {
                    zze.zzbvB = str2;
                }
            }
        }
        zzJo().zzb(str, zzaArr);
    }

    @WorkerThread
    zzauh.zza[] zza(String str, zzauh.zzb[] zzbArr, zzg[] zzgArr) {
        int intValue;
        BitSet bitSet;
        BitSet bitSet2;
        Map map;
        Map map2;
        Boolean zza;
        Object obj;
        zzac.zzdv(str);
        Set hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        Map arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        Map zzfC = zzJo().zzfC(str);
        if (zzfC != null) {
            for (Integer intValue2 : zzfC.keySet()) {
                intValue = intValue2.intValue();
                zzauh.zzf zzf = (zzauh.zzf) zzfC.get(Integer.valueOf(intValue));
                bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue));
                bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue));
                if (bitSet == null) {
                    bitSet = new BitSet();
                    arrayMap2.put(Integer.valueOf(intValue), bitSet);
                    bitSet2 = new BitSet();
                    arrayMap3.put(Integer.valueOf(intValue), bitSet2);
                }
                for (int i = 0; i < zzf.zzbwC.length * 64; i++) {
                    if (zzaue.zza(zzf.zzbwC, i)) {
                        zzJt().zzLg().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue), Integer.valueOf(i));
                        bitSet2.set(i);
                        if (zzaue.zza(zzf.zzbwD, i)) {
                            bitSet.set(i);
                        }
                    }
                }
                zzauh.zza zza2 = new zzauh.zza();
                arrayMap.put(Integer.valueOf(intValue), zza2);
                zza2.zzbvT = Boolean.valueOf(false);
                zza2.zzbvS = zzf;
                zza2.zzbvR = new zzauh.zzf();
                zza2.zzbvR.zzbwD = zzaue.zza(bitSet);
                zza2.zzbvR.zzbwC = zzaue.zza(bitSet2);
            }
        }
        if (zzbArr != null) {
            ArrayMap arrayMap4 = new ArrayMap();
            for (zzauh.zzb zzb : zzbArr) {
                zzasy zzasy;
                zzasy zzP = zzJo().zzP(str, zzb.name);
                if (zzP == null) {
                    zzJt().zzLc().zze("Event aggregate wasn't created during raw event logging. appId, event", zzati.zzfI(str), zzb.name);
                    zzasy = new zzasy(str, zzb.name, 1, 1, zzb.zzbvW.longValue());
                } else {
                    zzasy = zzP.zzKX();
                }
                zzJo().zza(zzasy);
                long j = zzasy.zzbqJ;
                map = (Map) arrayMap4.get(zzb.name);
                if (map == null) {
                    map = zzJo().zzS(str, zzb.name);
                    if (map == null) {
                        map = new ArrayMap();
                    }
                    arrayMap4.put(zzb.name, map);
                    map2 = map;
                } else {
                    map2 = map;
                }
                for (Integer intValue22 : r7.keySet()) {
                    int intValue3 = intValue22.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue3))) {
                        zzJt().zzLg().zzj("Skipping failed audience ID", Integer.valueOf(intValue3));
                    } else {
                        bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue3));
                        bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue3));
                        if (((zzauh.zza) arrayMap.get(Integer.valueOf(intValue3))) == null) {
                            zzauh.zza zza3 = new zzauh.zza();
                            arrayMap.put(Integer.valueOf(intValue3), zza3);
                            zza3.zzbvT = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue3), bitSet);
                            bitSet2 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue3), bitSet2);
                        }
                        for (zzb zzb2 : (List) r7.get(Integer.valueOf(intValue3))) {
                            if (zzJt().zzai(2)) {
                                zzJt().zzLg().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(intValue3), zzb2.zzbvl, zzb2.zzbvm);
                                zzJt().zzLg().zzj("Filter definition", zzaue.zza(zzb2));
                            }
                            if (zzb2.zzbvl == null || zzb2.zzbvl.intValue() > 256) {
                                zzJt().zzLc().zze("Invalid event filter ID. appId, id", zzati.zzfI(str), String.valueOf(zzb2.zzbvl));
                            } else if (bitSet.get(zzb2.zzbvl.intValue())) {
                                zzJt().zzLg().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue3), zzb2.zzbvl);
                            } else {
                                zza = zza(zzb2, zzb, j);
                                zzati.zza zzLg = zzJt().zzLg();
                                String str2 = "Event filter result";
                                if (zza == null) {
                                    obj = "null";
                                } else {
                                    Boolean bool = zza;
                                }
                                zzLg.zzj(str2, obj);
                                if (zza == null) {
                                    hashSet.add(Integer.valueOf(intValue3));
                                } else {
                                    bitSet2.set(zzb2.zzbvl.intValue());
                                    if (zza.booleanValue()) {
                                        bitSet.set(zzb2.zzbvl.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (zzgArr != null) {
            Map arrayMap5 = new ArrayMap();
            for (zzg zzg : zzgArr) {
                map = (Map) arrayMap5.get(zzg.name);
                if (map == null) {
                    map = zzJo().zzT(str, zzg.name);
                    if (map == null) {
                        map = new ArrayMap();
                    }
                    arrayMap5.put(zzg.name, map);
                    map2 = map;
                } else {
                    map2 = map;
                }
                for (Integer intValue222 : r7.keySet()) {
                    int intValue4 = intValue222.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue4))) {
                        zzJt().zzLg().zzj("Skipping failed audience ID", Integer.valueOf(intValue4));
                    } else {
                        bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue4));
                        bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue4));
                        if (((zzauh.zza) arrayMap.get(Integer.valueOf(intValue4))) == null) {
                            zza3 = new zzauh.zza();
                            arrayMap.put(Integer.valueOf(intValue4), zza3);
                            zza3.zzbvT = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue4), bitSet);
                            bitSet2 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue4), bitSet2);
                        }
                        for (zze zze : (List) r7.get(Integer.valueOf(intValue4))) {
                            if (zzJt().zzai(2)) {
                                zzJt().zzLg().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue4), zze.zzbvl, zze.zzbvB);
                                zzJt().zzLg().zzj("Filter definition", zzaue.zza(zze));
                            }
                            if (zze.zzbvl == null || zze.zzbvl.intValue() > 256) {
                                zzJt().zzLc().zze("Invalid property filter ID. appId, id", zzati.zzfI(str), String.valueOf(zze.zzbvl));
                                hashSet.add(Integer.valueOf(intValue4));
                                break;
                            } else if (bitSet.get(zze.zzbvl.intValue())) {
                                zzJt().zzLg().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue4), zze.zzbvl);
                            } else {
                                zza = zza(zze, zzg);
                                zzati.zza zzLg2 = zzJt().zzLg();
                                String str3 = "Property filter result";
                                if (zza == null) {
                                    obj = "null";
                                } else {
                                    bool = zza;
                                }
                                zzLg2.zzj(str3, obj);
                                if (zza == null) {
                                    hashSet.add(Integer.valueOf(intValue4));
                                } else {
                                    bitSet2.set(zze.zzbvl.intValue());
                                    if (zza.booleanValue()) {
                                        bitSet.set(zze.zzbvl.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        zzauh.zza[] zzaArr = new zzauh.zza[arrayMap2.size()];
        int i2 = 0;
        for (Integer intValue2222 : arrayMap2.keySet()) {
            intValue = intValue2222.intValue();
            if (!hashSet.contains(Integer.valueOf(intValue))) {
                zza3 = (zzauh.zza) arrayMap.get(Integer.valueOf(intValue));
                zza2 = zza3 == null ? new zzauh.zza() : zza3;
                int i3 = i2 + 1;
                zzaArr[i2] = zza2;
                zza2.zzbvh = Integer.valueOf(intValue);
                zza2.zzbvR = new zzauh.zzf();
                zza2.zzbvR.zzbwD = zzaue.zza((BitSet) arrayMap2.get(Integer.valueOf(intValue)));
                zza2.zzbvR.zzbwC = zzaue.zza((BitSet) arrayMap3.get(Integer.valueOf(intValue)));
                zzJo().zza(str, intValue, zza2.zzbvR);
                i2 = i3;
            }
        }
        return (zzauh.zza[]) Arrays.copyOf(zzaArr, i2);
    }

    protected void zzmr() {
    }
}
