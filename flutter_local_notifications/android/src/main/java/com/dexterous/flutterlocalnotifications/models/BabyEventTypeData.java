package com.dexterous.flutterlocalnotifications.models;

import java.util.List;

public class BabyEventTypeData {
    private List<AlarmInterval> interval;
    private List<Reminder> reminder;

    public List<AlarmInterval> getInterval() {
        return interval;
    }

    public void setInterval(List<AlarmInterval> interval) {
        this.interval = interval;
    }

    public List<Reminder> getReminder() {
        return reminder;
    }

    public void setReminder(List<Reminder> reminder) {
        this.reminder = reminder;
    }
}
