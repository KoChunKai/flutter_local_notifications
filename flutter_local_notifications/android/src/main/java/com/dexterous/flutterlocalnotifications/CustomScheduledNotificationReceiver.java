package com.dexterous.flutterlocalnotifications;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.Keep;
import androidx.core.app.NotificationManagerCompat;

import com.dexterous.flutterlocalnotifications.models.NotificationDetails;
import com.dexterous.flutterlocalnotifications.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by michaelbui on 24/3/18.
 */
@Keep
public class CustomScheduledNotificationReceiver extends BroadcastReceiver {

    private static final String TAG = "CustomScheduledNotificationReceiver";

    @Override
    @SuppressWarnings("deprecation")
    public void onReceive(final Context context, Intent intent) {
        Log.d(TAG, "onReceive trigger");
        String notificationDetailsJson =
                intent.getStringExtra(FlutterLocalNotificationsPlugin.NOTIFICATION_DETAILS);
        String payload = null;
        int notificationId = 0;

        if (StringUtils.isNullOrEmpty(notificationDetailsJson)) {
            // This logic is needed for apps that used the plugin prior to 0.3.4
            Notification notification;
            notificationId = intent.getIntExtra("notification_id", 0);
            payload = intent.getStringExtra("payload");

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                notification = intent.getParcelableExtra("notification", Notification.class);
            } else {
                notification = intent.getParcelableExtra("notification");
            }

            if (notification == null) {
                // This means the notification is corrupt
                FlutterLocalNotificationsPlugin.removeNotificationFromCache(context, notificationId);
                Log.e(TAG, "Failed to parse a notification from  Intent. ID: " + notificationId);
                return;
            }

            notification.when = System.currentTimeMillis();
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(notificationId, notification);
            boolean repeat = intent.getBooleanExtra("repeat", false);
            if (!repeat) {
                FlutterLocalNotificationsPlugin.removeNotificationFromCache(context, notificationId);
            }
        } else {
            Gson gson = FlutterLocalNotificationsPlugin.buildGson();
            Type type = new TypeToken<NotificationDetails>() {
            }.getType();
            NotificationDetails notificationDetails = gson.fromJson(notificationDetailsJson, type);

            payload = notificationDetails.payload;
            notificationId = notificationDetails.id;

            FlutterLocalNotificationsPlugin.showNotification(context, notificationDetails);
            FlutterLocalNotificationsPlugin.scheduleNextNotification(context, notificationDetails);
        }

        if (FlutterLocalNotificationsPlugin.methodNotificationChannel != null) {
            HashMap<String, Object> arguments = new HashMap<>();
            arguments.put("payload", payload);
            arguments.put("notificationId", notificationId);

            FlutterLocalNotificationsPlugin.methodNotificationChannel.invokeMethod(
                    "onNotificationReceived",
                    arguments
            );
        }
        updateSharedPreferences(context, notificationDetailsJson);
    }

    void updateSharedPreferences(Context context, String notificationDetailsJson) {
        Log.d(TAG, "notificationDetailsJson:" + notificationDetailsJson);
        SharedPreferences spf = context.getSharedPreferences("FlutterSharedPreferences", Context.MODE_PRIVATE);
        Log.d(TAG, "updateSharedPreferences:" + spf.getString("flutter.alarmDataKey", "null"));
    }
}