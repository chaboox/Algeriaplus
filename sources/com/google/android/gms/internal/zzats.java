package com.google.android.gms.internal;

abstract class zzats extends zzatr {
    private boolean zzacO;

    zzats(zzatp zzatp) {
        super(zzatp);
        this.zzbpw.zzb(this);
    }

    public final void initialize() {
        if (this.zzacO) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzmr();
        this.zzbpw.zzLK();
        this.zzacO = true;
    }

    boolean isInitialized() {
        return this.zzacO;
    }

    protected abstract void zzmr();

    protected void zznA() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}
