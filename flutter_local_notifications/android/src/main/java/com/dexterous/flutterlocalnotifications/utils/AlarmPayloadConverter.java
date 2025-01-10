package com.dexterous.flutterlocalnotifications.utils;

import com.dexterous.flutterlocalnotifications.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

public class AlarmPayloadConverter {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(AlarmPayload.class, new TypeAdapter<AlarmPayload>() {
                @Override
                public void write(JsonWriter out, AlarmPayload value) throws IOException {
                    out.beginObject();

                    if (value.getInterval() != null) {
                        out.name("interval");
                        gson.toJson(value.getInterval(), AlarmInterval.class, out);
                    }

                    if (value.getReminder() != null) {
                        out.name("reminder");
                        gson.toJson(value.getReminder(), Reminder.class, out);
                    }

                    if (value.getType() != null) {
                        out.name("type");
                        out.value(value.getType().getValue());
                    }

                    out.endObject();
                }

                @Override
                public AlarmPayload read(JsonReader in) throws IOException {
                    AlarmPayload payload = new AlarmPayload();
                    in.beginObject();

                    while (in.hasNext()) {
                        String name = in.nextName();
                        switch (name) {
                            case "interval":
                                payload.setInterval(gson.fromJson(in, AlarmInterval.class));
                                break;
                            case "reminder":
                                payload.setReminder(gson.fromJson(in, Reminder.class));
                                break;
                            case "type":
                                String typeValue = in.nextString();
                                for (BabyEventType type : BabyEventType.values()) {
                                    if (type.getValue().equals(typeValue)) {
                                        payload.setType(type);
                                        break;
                                    }
                                }
                                break;
                            default:
                                in.skipValue();
                                break;
                        }
                    }

                    in.endObject();
                    return payload;
                }
            })
            .registerTypeAdapter(AlarmData.class, new TypeAdapter<AlarmData>() {
                @Override
                public void write(JsonWriter out, AlarmData value) throws IOException {
                    out.beginObject();

                    if (value.getCurrentBabyEventTypeIndex() != null) {
                        out.name("currentBabyEventTypeIndex");
                        out.value(value.getCurrentBabyEventTypeIndex());
                    }

                    if (value.getCurrentSettingTypeIndex() != null) {
                        out.name("currentSettingTypeIndex");
                        out.value(value.getCurrentSettingTypeIndex());
                    }

                    writeEventTypeData(out, "bottle", value.getBottle());
                    writeEventTypeData(out, "nursing", value.getNursing());
                    writeEventTypeData(out, "diaper", value.getDiaper());
                    writeEventTypeData(out, "sleep", value.getSleep());
                    writeEventTypeData(out, "pumping", value.getPumping());
                    writeEventTypeData(out, "solids", value.getSolids());
                    writeEventTypeData(out, "growth", value.getGrowth());

                    out.endObject();
                }

                private void writeEventTypeData(JsonWriter out, String name, BabyEventTypeData data) throws IOException {
                    if (data != null) {
                        out.name(name);
                        gson.toJson(data, BabyEventTypeData.class, out);
                    }
                }

                @Override
                public AlarmData read(JsonReader in) throws IOException {
                    AlarmData data = new AlarmData();
                    in.beginObject();

                    while (in.hasNext()) {
                        String name = in.nextName();
                        switch (name) {
                            case "currentBabyEventTypeIndex":
                                data.setCurrentBabyEventTypeIndex(in.nextInt());
                                break;
                            case "currentSettingTypeIndex":
                                data.setCurrentSettingTypeIndex(in.nextInt());
                                break;
                            case "bottle":
                                data.setBottle(gson.fromJson(in, BabyEventTypeData.class));
                                break;
                            case "nursing":
                                data.setNursing(gson.fromJson(in, BabyEventTypeData.class));
                                break;
                            case "diaper":
                                data.setDiaper(gson.fromJson(in, BabyEventTypeData.class));
                                break;
                            case "sleep":
                                data.setSleep(gson.fromJson(in, BabyEventTypeData.class));
                                break;
                            case "pumping":
                                data.setPumping(gson.fromJson(in, BabyEventTypeData.class));
                                break;
                            case "solids":
                                data.setSolids(gson.fromJson(in, BabyEventTypeData.class));
                                break;
                            case "growth":
                                data.setGrowth(gson.fromJson(in, BabyEventTypeData.class));
                                break;
                            default:
                                in.skipValue();
                                break;
                        }
                    }

                    in.endObject();
                    return data;
                }
            })
            .create();

    public static AlarmPayload fromJson(String json) {
        try {
            return gson.fromJson(json, AlarmPayload.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(AlarmPayload payload) {
        return gson.toJson(payload);
    }

    public static AlarmData alarmDataFromJson(String json) {
        try {
            return gson.fromJson(json, AlarmData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String alarmDataToJson(AlarmData data) {
        return gson.toJson(data);
    }
}