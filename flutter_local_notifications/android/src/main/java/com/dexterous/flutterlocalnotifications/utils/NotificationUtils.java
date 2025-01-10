package com.dexterous.flutterlocalnotifications.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.dexterous.flutterlocalnotifications.models.AlarmData;
import com.dexterous.flutterlocalnotifications.models.AlarmInterval;
import com.dexterous.flutterlocalnotifications.models.AlarmPayload;
import com.dexterous.flutterlocalnotifications.models.BabyEventTypeData;
import com.dexterous.flutterlocalnotifications.models.Reminder;
import com.dexterous.flutterlocalnotifications.models.WeekOfDays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.flutter.plugin.common.MethodChannel;

public class NotificationUtils {
    private static final String PREF_NAME = "FlutterSharedPreferences";
    private static final String ALARM_DATA_KEY = "flutter.alarmDataKey";
    private final Context context;
    private final MethodChannel notificationChannel;
    private final Gson gson;
    private final Handler mainHandler;

    public NotificationUtils(Context context, MethodChannel notificationChannel) {
        this.context = context;
        this.notificationChannel = notificationChannel;
        this.gson = new GsonBuilder()
                .serializeNulls()
                .create();
        this.mainHandler = new Handler(Looper.getMainLooper());
    }

    public void handleNotification(AlarmPayload alarmPayload) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String jsonString = preferences.getString(ALARM_DATA_KEY, null);

        if (jsonString != null) {
            try {
                AlarmData alarmData = gson.fromJson(jsonString, AlarmData.class);
                updateAlarmStatus(alarmData, alarmPayload);

                // Save updated data back to SharedPreferences
                String updatedJson = gson.toJson(alarmData);
                preferences.edit()
                        .putString(ALARM_DATA_KEY, updatedJson)
                        .apply();

                // Notify Flutter
                notifyFlutter(updatedJson);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void updateAlarmStatus(AlarmData alarmData, AlarmPayload payload) {
        if (payload.getInterval() != null && (payload.getInterval().getRepeat() == null || check(payload.getInterval().getRepeat()))) {
            updateIntervalStatus(alarmData, payload);
        }

        if (payload.getReminder() != null && (payload.getReminder().getRepeat() == null || check(payload.getReminder().getRepeat()))) {
            updateReminderStatus(alarmData, payload);
        }
    }

    private boolean check(WeekOfDays repeat) {
        return !repeat.getMonday() && !repeat.getTuesday() && !repeat.getWednesday() && !repeat.getThursday() && !repeat.getFriday() && !repeat.getSaturday() && !repeat.getSunday();
    }

    private void updateIntervalStatus(AlarmData alarmData, AlarmPayload payload) {
        BabyEventTypeData typeData = null;
        switch (payload.getType()) {
            case BOTTLE:
                typeData = alarmData.getBottle();
                break;
            case NURSING:
                typeData = alarmData.getNursing();
                break;
            case DIAPER:
                typeData = alarmData.getDiaper();
                break;
            case SLEEP:
                typeData = alarmData.getSleep();
                break;
            case PUMPING:
                typeData = alarmData.getPumping();
                break;
            case SOLIDS:
                typeData = alarmData.getSolids();
                break;
            case GROWTH:
                typeData = alarmData.getGrowth();
                break;
        }

        if (typeData != null && typeData.getInterval() != null) {
            for (AlarmInterval interval : typeData.getInterval()) {
                if (interval.getId() != null &&
                        interval.getId().equals(payload.getInterval().getId())) {
                    interval.setIsActivity(false);
                    break;
                }
            }
        }
    }

    private void updateReminderStatus(AlarmData alarmData, AlarmPayload payload) {
        BabyEventTypeData typeData = null;
        switch (payload.getType()) {
            case BOTTLE:
                typeData = alarmData.getBottle();
                break;
            case NURSING:
                typeData = alarmData.getNursing();
                break;
            case DIAPER:
                typeData = alarmData.getDiaper();
                break;
            case SLEEP:
                typeData = alarmData.getSleep();
                break;
            case PUMPING:
                typeData = alarmData.getPumping();
                break;
            case SOLIDS:
                typeData = alarmData.getSolids();
                break;
            case GROWTH:
                typeData = alarmData.getGrowth();
                break;
        }

        if (typeData != null && typeData.getReminder() != null) {
            for (Reminder reminder : typeData.getReminder()) {
                if (reminder.getId() != null &&
                        reminder.getId().equals(payload.getReminder().getId())) {
                    reminder.setIsActivity(false);
                    break;
                }
            }
        }
    }

    private void notifyFlutter(final String updatedJson) {
        mainHandler.post(() -> {
            notificationChannel.invokeMethod("onNotificationReceived", updatedJson);
        });
    }

    public void saveAlarmData(AlarmData alarmData) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String jsonString = gson.toJson(alarmData);
        preferences.edit()
                .putString(ALARM_DATA_KEY, jsonString)
                .apply();
    }

    public AlarmData getAlarmData() {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String jsonString = preferences.getString(ALARM_DATA_KEY, null);
        if (jsonString != null) {
            try {
                return gson.fromJson(jsonString, AlarmData.class);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void clearAlarmData() {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        preferences.edit()
                .remove(ALARM_DATA_KEY)
                .apply();
    }
}
