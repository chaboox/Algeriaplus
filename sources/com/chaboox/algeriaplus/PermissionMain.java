package com.chaboox.algeriaplus;

import android.content.Intent;
import android.os.Bundle;
import com.chaboox.algeriaplus.helper.RuntimePermission;

public class PermissionMain extends RuntimePermission {
    private static final int REQUEST_PERMISSION = 10;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0245R.layout.activity_main);
        requestAppPermission(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.WAKE_LOCK"}, C0245R.string.permission, 10);
    }

    public void onPermissionGranted(int requestCode) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
