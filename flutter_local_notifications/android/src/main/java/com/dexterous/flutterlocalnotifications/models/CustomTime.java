package com.dexterous.flutterlocalnotifications.models;

public class CustomTime {
    private Integer hour;
    private Integer minute;
    private Boolean isAM;

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Boolean getIsAM() {
        return isAM;
    }

    public void setIsAM(Boolean isAM) {
        this.isAM = isAM;
    }

    public Integer getHour24() {
        if (hour == null || isAM == null) return hour;
        if (hour == 12) {
            return isAM ? 0 : 12;
        }
        return isAM ? hour : hour + 12;
    }
}
