package com.chaboox.algeriaplus.helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat.Builder;
import com.chaboox.algeriaplus.C0245R;
import com.chaboox.algeriaplus.Home;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Intent intent = new Intent(this, Home.class);
        intent.setFlags(67108864);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 1073741824);
        Builder notificationBuilder = new Builder(this);
        notificationBuilder.setContentTitle(getResources().getString(C0245R.string.app_name));
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSmallIcon(C0245R.mipmap.algeriapluslogo);
        notificationBuilder.setContentIntent(pendingIntent);
        ((NotificationManager) getSystemService("notification")).notify(0, notificationBuilder.build());
    }
}
