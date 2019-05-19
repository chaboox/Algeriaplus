package com.google.android.gms.internal;

import java.io.IOException;

public interface zzaug {

    public static final class zza extends zzbut {
        private static volatile zza[] zzbvH;
        public String name;
        public Boolean zzbvI;
        public Boolean zzbvJ;

        public zza() {
            zzMu();
        }

        public static zza[] zzMt() {
            if (zzbvH == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbvH == null) {
                        zzbvH = new zza[0];
                    }
                }
            }
            return zzbvH;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            if (this.name == null) {
                if (zza.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zza.name)) {
                return false;
            }
            if (this.zzbvI == null) {
                if (zza.zzbvI != null) {
                    return false;
                }
            } else if (!this.zzbvI.equals(zza.zzbvI)) {
                return false;
            }
            return this.zzbvJ == null ? zza.zzbvJ == null : this.zzbvJ.equals(zza.zzbvJ);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbvI == null ? 0 : this.zzbvI.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzbvJ != null) {
                i = this.zzbvJ.hashCode();
            }
            return hashCode + i;
        }

        public zza zzM(zzbul zzbul) throws IOException {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        this.name = zzbul.readString();
                        continue;
                    case 16:
                        this.zzbvI = Boolean.valueOf(zzbul.zzacA());
                        continue;
                    case 24:
                        this.zzbvJ = Boolean.valueOf(zzbul.zzacA());
                        continue;
                    default:
                        if (!zzbuw.zzb(zzbul, zzacu)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public zza zzMu() {
            this.name = null;
            this.zzbvI = null;
            this.zzbvJ = null;
            this.zzcsg = -1;
            return this;
        }

        public void zza(zzbum zzbum) throws IOException {
            if (this.name != null) {
                zzbum.zzq(1, this.name);
            }
            if (this.zzbvI != null) {
                zzbum.zzg(2, this.zzbvI.booleanValue());
            }
            if (this.zzbvJ != null) {
                zzbum.zzg(3, this.zzbvJ.booleanValue());
            }
            super.zza(zzbum);
        }

        public /* synthetic */ zzbut zzb(zzbul zzbul) throws IOException {
            return zzM(zzbul);
        }

        protected int zzv() {
            int zzv = super.zzv();
            if (this.name != null) {
                zzv += zzbum.zzr(1, this.name);
            }
            if (this.zzbvI != null) {
                zzv += zzbum.zzh(2, this.zzbvI.booleanValue());
            }
            return this.zzbvJ != null ? zzv + zzbum.zzh(3, this.zzbvJ.booleanValue()) : zzv;
        }
    }

    public static final class zzb extends zzbut {
        public String zzbqf;
        public Long zzbvK;
        public Integer zzbvL;
        public zzc[] zzbvM;
        public zza[] zzbvN;
        public com.google.android.gms.internal.zzauf.zza[] zzbvO;

        public zzb() {
            zzMv();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) obj;
            if (this.zzbvK == null) {
                if (zzb.zzbvK != null) {
                    return false;
                }
            } else if (!this.zzbvK.equals(zzb.zzbvK)) {
                return false;
            }
            if (this.zzbqf == null) {
                if (zzb.zzbqf != null) {
                    return false;
                }
            } else if (!this.zzbqf.equals(zzb.zzbqf)) {
                return false;
            }
            if (this.zzbvL == null) {
                if (zzb.zzbvL != null) {
                    return false;
                }
            } else if (!this.zzbvL.equals(zzb.zzbvL)) {
                return false;
            }
            return !zzbur.equals(this.zzbvM, zzb.zzbvM) ? false : !zzbur.equals(this.zzbvN, zzb.zzbvN) ? false : zzbur.equals(this.zzbvO, zzb.zzbvO);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzbqf == null ? 0 : this.zzbqf.hashCode()) + (((this.zzbvK == null ? 0 : this.zzbvK.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (this.zzbvL != null) {
                i = this.zzbvL.hashCode();
            }
            return ((((((hashCode + i) * 31) + zzbur.hashCode(this.zzbvM)) * 31) + zzbur.hashCode(this.zzbvN)) * 31) + zzbur.hashCode(this.zzbvO);
        }

        public zzb zzMv() {
            this.zzbvK = null;
            this.zzbqf = null;
            this.zzbvL = null;
            this.zzbvM = zzc.zzMw();
            this.zzbvN = zza.zzMt();
            this.zzbvO = com.google.android.gms.internal.zzauf.zza.zzMj();
            this.zzcsg = -1;
            return this;
        }

        public zzb zzN(zzbul zzbul) throws IOException {
            while (true) {
                int zzacu = zzbul.zzacu();
                int zzc;
                Object obj;
                switch (zzacu) {
                    case 0:
                        break;
                    case 8:
                        this.zzbvK = Long.valueOf(zzbul.zzacx());
                        continue;
                    case 18:
                        this.zzbqf = zzbul.readString();
                        continue;
                    case 24:
                        this.zzbvL = Integer.valueOf(zzbul.zzacy());
                        continue;
                    case 34:
                        zzc = zzbuw.zzc(zzbul, 34);
                        zzacu = this.zzbvM == null ? 0 : this.zzbvM.length;
                        obj = new zzc[(zzc + zzacu)];
                        if (zzacu != 0) {
                            System.arraycopy(this.zzbvM, 0, obj, 0, zzacu);
                        }
                        while (zzacu < obj.length - 1) {
                            obj[zzacu] = new zzc();
                            zzbul.zza(obj[zzacu]);
                            zzbul.zzacu();
                            zzacu++;
                        }
                        obj[zzacu] = new zzc();
                        zzbul.zza(obj[zzacu]);
                        this.zzbvM = obj;
                        continue;
                    case 42:
                        zzc = zzbuw.zzc(zzbul, 42);
                        zzacu = this.zzbvN == null ? 0 : this.zzbvN.length;
                        obj = new zza[(zzc + zzacu)];
                        if (zzacu != 0) {
                            System.arraycopy(this.zzbvN, 0, obj, 0, zzacu);
                        }
                        while (zzacu < obj.length - 1) {
                            obj[zzacu] = new zza();
                            zzbul.zza(obj[zzacu]);
                            zzbul.zzacu();
                            zzacu++;
                        }
                        obj[zzacu] = new zza();
                        zzbul.zza(obj[zzacu]);
                        this.zzbvN = obj;
                        continue;
                    case 50:
                        zzc = zzbuw.zzc(zzbul, 50);
                        zzacu = this.zzbvO == null ? 0 : this.zzbvO.length;
                        obj = new com.google.android.gms.internal.zzauf.zza[(zzc + zzacu)];
                        if (zzacu != 0) {
                            System.arraycopy(this.zzbvO, 0, obj, 0, zzacu);
                        }
                        while (zzacu < obj.length - 1) {
                            obj[zzacu] = new com.google.android.gms.internal.zzauf.zza();
                            zzbul.zza(obj[zzacu]);
                            zzbul.zzacu();
                            zzacu++;
                        }
                        obj[zzacu] = new com.google.android.gms.internal.zzauf.zza();
                        zzbul.zza(obj[zzacu]);
                        this.zzbvO = obj;
                        continue;
                    default:
                        if (!zzbuw.zzb(zzbul, zzacu)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public void zza(zzbum zzbum) throws IOException {
            int i = 0;
            if (this.zzbvK != null) {
                zzbum.zzb(1, this.zzbvK.longValue());
            }
            if (this.zzbqf != null) {
                zzbum.zzq(2, this.zzbqf);
            }
            if (this.zzbvL != null) {
                zzbum.zzF(3, this.zzbvL.intValue());
            }
            if (this.zzbvM != null && this.zzbvM.length > 0) {
                for (zzbut zzbut : this.zzbvM) {
                    if (zzbut != null) {
                        zzbum.zza(4, zzbut);
                    }
                }
            }
            if (this.zzbvN != null && this.zzbvN.length > 0) {
                for (zzbut zzbut2 : this.zzbvN) {
                    if (zzbut2 != null) {
                        zzbum.zza(5, zzbut2);
                    }
                }
            }
            if (this.zzbvO != null && this.zzbvO.length > 0) {
                while (i < this.zzbvO.length) {
                    zzbut zzbut3 = this.zzbvO[i];
                    if (zzbut3 != null) {
                        zzbum.zza(6, zzbut3);
                    }
                    i++;
                }
            }
            super.zza(zzbum);
        }

        public /* synthetic */ zzbut zzb(zzbul zzbul) throws IOException {
            return zzN(zzbul);
        }

        protected int zzv() {
            int i;
            int i2 = 0;
            int zzv = super.zzv();
            if (this.zzbvK != null) {
                zzv += zzbum.zzf(1, this.zzbvK.longValue());
            }
            if (this.zzbqf != null) {
                zzv += zzbum.zzr(2, this.zzbqf);
            }
            if (this.zzbvL != null) {
                zzv += zzbum.zzH(3, this.zzbvL.intValue());
            }
            if (this.zzbvM != null && this.zzbvM.length > 0) {
                i = zzv;
                for (zzbut zzbut : this.zzbvM) {
                    if (zzbut != null) {
                        i += zzbum.zzc(4, zzbut);
                    }
                }
                zzv = i;
            }
            if (this.zzbvN != null && this.zzbvN.length > 0) {
                i = zzv;
                for (zzbut zzbut2 : this.zzbvN) {
                    if (zzbut2 != null) {
                        i += zzbum.zzc(5, zzbut2);
                    }
                }
                zzv = i;
            }
            if (this.zzbvO != null && this.zzbvO.length > 0) {
                while (i2 < this.zzbvO.length) {
                    zzbut zzbut3 = this.zzbvO[i2];
                    if (zzbut3 != null) {
                        zzv += zzbum.zzc(6, zzbut3);
                    }
                    i2++;
                }
            }
            return zzv;
        }
    }

    public static final class zzc extends zzbut {
        private static volatile zzc[] zzbvP;
        public String value;
        public String zzaA;

        public zzc() {
            zzMx();
        }

        public static zzc[] zzMw() {
            if (zzbvP == null) {
                synchronized (zzbur.zzcsf) {
                    if (zzbvP == null) {
                        zzbvP = new zzc[0];
                    }
                }
            }
            return zzbvP;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) obj;
            if (this.zzaA == null) {
                if (zzc.zzaA != null) {
                    return false;
                }
            } else if (!this.zzaA.equals(zzc.zzaA)) {
                return false;
            }
            return this.value == null ? zzc.value == null : this.value.equals(zzc.value);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzaA == null ? 0 : this.zzaA.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31;
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode + i;
        }

        public zzc zzMx() {
            this.zzaA = null;
            this.value = null;
            this.zzcsg = -1;
            return this;
        }

        public zzc zzO(zzbul zzbul) throws IOException {
            while (true) {
                int zzacu = zzbul.zzacu();
                switch (zzacu) {
                    case 0:
                        break;
                    case 10:
                        this.zzaA = zzbul.readString();
                        continue;
                    case 18:
                        this.value = zzbul.readString();
                        continue;
                    default:
                        if (!zzbuw.zzb(zzbul, zzacu)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public void zza(zzbum zzbum) throws IOException {
            if (this.zzaA != null) {
                zzbum.zzq(1, this.zzaA);
            }
            if (this.value != null) {
                zzbum.zzq(2, this.value);
            }
            super.zza(zzbum);
        }

        public /* synthetic */ zzbut zzb(zzbul zzbul) throws IOException {
            return zzO(zzbul);
        }

        protected int zzv() {
            int zzv = super.zzv();
            if (this.zzaA != null) {
                zzv += zzbum.zzr(1, this.zzaA);
            }
            return this.value != null ? zzv + zzbum.zzr(2, this.value) : zzv;
        }
    }
}
