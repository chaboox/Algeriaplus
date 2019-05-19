package com.chaboox.algeriaplus.helper;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private static final String REG_TOKEN = "REG_TOKEN";

    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d(REG_TOKEN, FirebaseInstanceId.getInstance().getToken());
    }
}
