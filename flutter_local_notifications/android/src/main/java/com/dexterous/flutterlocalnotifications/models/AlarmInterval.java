package com.dexterous.flutterlocalnotifications.models;

public class AlarmInterval {
    private Integer id;
    private CustomTime frequency;
    private WeekOfDays repeat;
    private DoNotDisturb doNotDisturb;
    private Boolean isSoundAndVibration;
    private Boolean isActivity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomTime getFrequency() {
        return frequency;
    }

    public void setFrequency(CustomTime frequency) {
        this.frequency = frequency;
    }

    public WeekOfDays getRepeat() {
        return repeat;
    }

    public void setRepeat(WeekOfDays repeat) {
        this.repeat = repeat;
    }

    public DoNotDisturb getDoNotDisturb() {
        return doNotDisturb;
    }

    public void setDoNotDisturb(DoNotDisturb doNotDisturb) {
        this.doNotDisturb = doNotDisturb;
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
