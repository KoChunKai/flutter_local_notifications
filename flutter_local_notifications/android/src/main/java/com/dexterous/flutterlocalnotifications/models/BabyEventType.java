package com.dexterous.flutterlocalnotifications.models;

public enum BabyEventType {
    BOTTLE("bottle"),
    NURSING("nursing"),
    PUMPING("pumping"),
    DIAPER("diaper"),
    SLEEP("sleep"),
    SOLIDS("solids"),
    GROWTH("growth");

    private final String value;

    BabyEventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
