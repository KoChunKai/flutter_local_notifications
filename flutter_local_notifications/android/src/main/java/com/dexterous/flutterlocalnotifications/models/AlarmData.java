package com.dexterous.flutterlocalnotifications.models;

import java.util.List;

public class AlarmData {
    private Integer currentBabyEventTypeIndex;
    private Integer currentSettingTypeIndex;
    private BabyEventTypeData bottle;
    private BabyEventTypeData nursing;
    private BabyEventTypeData diaper;
    private BabyEventTypeData sleep;
    private BabyEventTypeData pumping;
    private BabyEventTypeData solids;
    private BabyEventTypeData growth;

    public Integer getCurrentBabyEventTypeIndex() {
        return currentBabyEventTypeIndex;
    }

    public void setCurrentBabyEventTypeIndex(Integer currentBabyEventTypeIndex) {
        this.currentBabyEventTypeIndex = currentBabyEventTypeIndex;
    }

    public Integer getCurrentSettingTypeIndex() {
        return currentSettingTypeIndex;
    }

    public void setCurrentSettingTypeIndex(Integer currentSettingTypeIndex) {
        this.currentSettingTypeIndex = currentSettingTypeIndex;
    }

    public BabyEventTypeData getBottle() {
        return bottle;
    }

    public void setBottle(BabyEventTypeData bottle) {
        this.bottle = bottle;
    }

    public BabyEventTypeData getNursing() {
        return nursing;
    }

    public void setNursing(BabyEventTypeData nursing) {
        this.nursing = nursing;
    }

    public BabyEventTypeData getDiaper() {
        return diaper;
    }

    public void setDiaper(BabyEventTypeData diaper) {
        this.diaper = diaper;
    }

    public BabyEventTypeData getSleep() {
        return sleep;
    }

    public void setSleep(BabyEventTypeData sleep) {
        this.sleep = sleep;
    }

    public BabyEventTypeData getPumping() {
        return pumping;
    }

    public void setPumping(BabyEventTypeData pumping) {
        this.pumping = pumping;
    }

    public BabyEventTypeData getSolids() {
        return solids;
    }

    public void setSolids(BabyEventTypeData solids) {
        this.solids = solids;
    }

    public BabyEventTypeData getGrowth() {
        return growth;
    }

    public void setGrowth(BabyEventTypeData growth) {
        this.growth = growth;
    }
}



