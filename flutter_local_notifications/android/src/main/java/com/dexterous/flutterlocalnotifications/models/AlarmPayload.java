package com.dexterous.flutterlocalnotifications.models;

public class AlarmPayload {
    private AlarmInterval interval;
    private Reminder reminder;
    private BabyEventType type;

    public AlarmInterval getInterval() {
        return interval;
    }

    public void setInterval(AlarmInterval interval) {
        this.interval = interval;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    public BabyEventType getType() {
        return type;
    }

    public void setType(BabyEventType type) {
        this.type = type;
    }
}
