package com.chaboox.algeriaplus.helper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class RuntimePermission extends Activity {
    private SparseIntArray mErrorString;

    /* renamed from: com.chaboox.algeriaplus.helper.RuntimePermission$2 */
    class C02532 implements OnClickListener {
        C02532() {
        }

        public void onClick(View view) {
            Intent i = new Intent();
            i.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            i.setData(Uri.parse("package:" + RuntimePermission.this.getPackageName()));
            i.addCategory("android.intent.category.DEFAULT");
            i.addFlags(268435456);
            i.addFlags(1073741824);
            i.addFlags(8388608);
            RuntimePermission.this.startActivity(i);
        }
    }

    public abstract void onPermissionGranted(int i);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mErrorString = new SparseIntArray();
    }

    public void requestAppPermission(final String[] requestedPermissions, int stringId, final int requestCode) {
        this.mErrorString.put(requestCode, stringId);
        int permissionCheck = 0;
        boolean showRequestPermission = false;
        for (String permission : requestedPermissions) {
            permissionCheck += ContextCompat.checkSelfPermission(this, permission);
            if (showRequestPermission || ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                showRequestPermission = true;
            } else {
                showRequestPermission = false;
            }
        }
        if (permissionCheck == 0) {
            onPermissionGranted(requestCode);
        } else if (showRequestPermission) {
            Snackbar.make(findViewById(16908290), stringId, -2).setAction((CharSequence) "GRANT", new OnClickListener() {
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(RuntimePermission.this, requestedPermissions, requestCode);
                }
            }).show();
        } else {
            ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = 0;
        for (int permission : grantResults) {
            permissionCheck += permission;
        }
        if (grantResults.length <= 0 || permissionCheck != 0) {
            Snackbar.make(findViewById(16908290), this.mErrorString.get(requestCode), -2).setAction((CharSequence) "ENABLE", new C02532()).show();
        } else {
            onPermissionGranted(requestCode);
        }
    }
}
