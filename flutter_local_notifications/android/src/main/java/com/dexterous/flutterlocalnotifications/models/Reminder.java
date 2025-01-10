package com.dexterous.flutterlocalnotifications.models;

public class Reminder {
    private Integer id;
    private Time remindTime;
    private WeekOfDays repeat;
    private Boolean isSoundAndVibration;
    private Boolean isActivity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(Time remindTime) {
        this.remindTime = remindTime;
    }

    public WeekOfDays getRepeat() {
        return repeat;
    }

    public void setRepeat(WeekOfDays repeat) {
        this.repeat = repeat;
    }

    public Boolean getIsSoundAndVibration() {
        return isSoundAndVibration;
    }

    public void setIsSoundAndVibration(Boolean isSoundAndVibration) {
        this.isSoundAndVibration = isSoundAndVibration;
    }

    public Boolean getIsActivity() {
        return isActivity;
    }

    public void setIsActivity(Boolean isActivity) {
        this.isActivity = isActivity;
    }
}
