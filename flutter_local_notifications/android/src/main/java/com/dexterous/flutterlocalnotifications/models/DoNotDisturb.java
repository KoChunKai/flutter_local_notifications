package com.dexterous.flutterlocalnotifications.models;

public class DoNotDisturb {
    private Boolean enabled;
    private CustomTime startTime;
    private CustomTime endTime;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public CustomTime getStartTime() {
        return startTime;
    }

    public void setStartTime(CustomTime startTime) {
        this.startTime = startTime;
    }

    public CustomTime getEndTime() {
        return endTime;
    }

    public void setEndTime(CustomTime endTime) {
        this.endTime = endTime;
    }
}
