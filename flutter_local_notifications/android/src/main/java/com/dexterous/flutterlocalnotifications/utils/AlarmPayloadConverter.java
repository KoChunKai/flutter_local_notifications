package com.dexterous.flutterlocalnotifications.utils;

import com.dexterous.flutterlocalnotifications.models.AlarmPayload;
import com.dexterous.flutterlocalnotifications.models.BabyEventType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

public class AlarmPayloadConverter {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(BabyEventType.class, (JsonDeserializer<BabyEventType>) (json, typeOfT, context) -> {
                String value = json.getAsString();
                for (BabyEventType type : BabyEventType.values()) {
                    if (type.getValue().equals(value)) {
                        return type;
                    }
                }
                throw new IllegalArgumentException("Unknown BabyEventType: " + value);
            })
            .create();

    public static AlarmPayload fromJson(String payload) {
        try {
            return gson.fromJson(payload, AlarmPayload.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}