package com.dexterous.flutterlocalnotifications.utils;

import com.dexterous.flutterlocalnotifications.models.AlarmData;
import com.dexterous.flutterlocalnotifications.models.AlarmInterval;
import com.dexterous.flutterlocalnotifications.models.AlarmPayload;
import com.dexterous.flutterlocalnotifications.models.BabyEventType;
import com.dexterous.flutterlocalnotifications.models.BabyEventTypeData;
import com.dexterous.flutterlocalnotifications.models.CustomTime;
import com.dexterous.flutterlocalnotifications.models.DoNotDisturb;
import com.dexterous.flutterlocalnotifications.models.Reminder;
import com.dexterous.flutterlocalnotifications.models.WeekOfDays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AlarmPayloadConverter {
    private static final Gson gson = createGson();

    private static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(AlarmPayload.class, new TypeAdapter<AlarmPayload>() {
                    @Override
                    public void write(JsonWriter out, AlarmPayload value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                            return;
                        }
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
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
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
                        if (value == null) {
                            out.nullValue();
                            return;
                        }
                        out.beginObject();

                        if (value.getCurrentBabyEventTypeIndex() != null) {
                            out.name("currentBabyEventTypeIndex");
                            out.value(value.getCurrentBabyEventTypeIndex());
                        }
                        if (value.getCurrentSettingTypeIndex() != null) {
                            out.name("currentSettingTypeIndex");
                            out.value(value.getCurrentSettingTypeIndex());
                        }

                        if (value.getBottle() != null) {
                            out.name("bottle");
                            gson.toJson(value.getBottle(), BabyEventTypeData.class, out);
                        }
                        if (value.getNursing() != null) {
                            out.name("nursing");
                            gson.toJson(value.getNursing(), BabyEventTypeData.class, out);
                        }
                        if (value.getDiaper() != null) {
                            out.name("diaper");
                            gson.toJson(value.getDiaper(), BabyEventTypeData.class, out);
                        }
                        if (value.getSleep() != null) {
                            out.name("sleep");
                            gson.toJson(value.getSleep(), BabyEventTypeData.class, out);
                        }
                        if (value.getPumping() != null) {
                            out.name("pumping");
                            gson.toJson(value.getPumping(), BabyEventTypeData.class, out);
                        }
                        if (value.getSolids() != null) {
                            out.name("solids");
                            gson.toJson(value.getSolids(), BabyEventTypeData.class, out);
                        }
                        if (value.getGrowth() != null) {
                            out.name("growth");
                            gson.toJson(value.getGrowth(), BabyEventTypeData.class, out);
                        }

                        out.endObject();
                    }

                    @Override
                    public AlarmData read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
                        AlarmData data = new AlarmData();
                        in.beginObject();
                        while (in.hasNext()) {
                            String name = in.nextName();
                            switch (name) {
                                case "currentBabyEventTypeIndex":
                                    if (in.peek() != JsonToken.NULL) {
                                        data.setCurrentBabyEventTypeIndex(in.nextInt());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "currentSettingTypeIndex":
                                    if (in.peek() != JsonToken.NULL) {
                                        data.setCurrentSettingTypeIndex(in.nextInt());
                                    } else {
                                        in.nextNull();
                                    }
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
                .registerTypeAdapter(BabyEventTypeData.class, new TypeAdapter<BabyEventTypeData>() {
                    @Override
                    public void write(JsonWriter out, BabyEventTypeData value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                            return;
                        }
                        out.beginObject();
                        if (value.getInterval() != null) {
                            out.name("interval");
                            out.beginArray();
                            for (AlarmInterval interval : value.getInterval()) {
                                gson.toJson(interval, AlarmInterval.class, out);
                            }
                            out.endArray();
                        }
                        if (value.getReminder() != null) {
                            out.name("reminder");
                            out.beginArray();
                            for (Reminder reminder : value.getReminder()) {
                                gson.toJson(reminder, Reminder.class, out);
                            }
                            out.endArray();
                        }
                        out.endObject();
                    }

                    @Override
                    public BabyEventTypeData read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
                        BabyEventTypeData data = new BabyEventTypeData();
                        in.beginObject();
                        while (in.hasNext()) {
                            String name = in.nextName();
                            switch (name) {
                                case "interval":
                                    List<AlarmInterval> intervals = new ArrayList<>();
                                    in.beginArray();
                                    while (in.hasNext()) {
                                        intervals.add(gson.fromJson(in, AlarmInterval.class));
                                    }
                                    in.endArray();
                                    data.setInterval(intervals);
                                    break;
                                case "reminder":
                                    List<Reminder> reminders = new ArrayList<>();
                                    in.beginArray();
                                    while (in.hasNext()) {
                                        reminders.add(gson.fromJson(in, Reminder.class));
                                    }
                                    in.endArray();
                                    data.setReminder(reminders);
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
                .registerTypeAdapter(AlarmInterval.class, new TypeAdapter<AlarmInterval>() {
                    @Override
                    public void write(JsonWriter out, AlarmInterval value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                            return;
                        }
                        out.beginObject();
                        if (value.getId() != null) {
                            out.name("id");
                            out.value(value.getId());
                        }
                        if (value.getFrequency() != null) {
                            out.name("frequency");
                            gson.toJson(value.getFrequency(), CustomTime.class, out);
                        }
                        if (value.getRepeat() != null) {
                            out.name("repeat");
                            gson.toJson(value.getRepeat(), WeekOfDays.class, out);
                        }
                        if (value.getDoNotDisturb() != null) {
                            out.name("doNotDisturb");
                            gson.toJson(value.getDoNotDisturb(), DoNotDisturb.class, out);
                        }
                        if (value.getIsSoundAndVibration() != null) {
                            out.name("isSoundAndVibration");
                            out.value(value.getIsSoundAndVibration());
                        }
                        if (value.getIsActivity() != null) {
                            out.name("isActivity");
                            out.value(value.getIsActivity());
                        }
                        out.endObject();
                    }

                    @Override
                    public AlarmInterval read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
                        AlarmInterval interval = new AlarmInterval();
                        in.beginObject();
                        while (in.hasNext()) {
                            String name = in.nextName();
                            switch (name) {
                                case "id":
                                    if (in.peek() != JsonToken.NULL) {
                                        interval.setId(in.nextLong());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "frequency":
                                    interval.setFrequency(gson.fromJson(in, CustomTime.class));
                                    break;
                                case "repeat":
                                    interval.setRepeat(gson.fromJson(in, WeekOfDays.class));
                                    break;
                                case "doNotDisturb":
                                    interval.setDoNotDisturb(gson.fromJson(in, DoNotDisturb.class));
                                    break;
                                case "isSoundAndVibration":
                                    if (in.peek() != JsonToken.NULL) {
                                        interval.setIsSoundAndVibration(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "isActivity":
                                    if (in.peek() != JsonToken.NULL) {
                                        interval.setIsActivity(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                default:
                                    in.skipValue();
                                    break;
                            }
                        }
                        in.endObject();
                        return interval;
                    }
                })
                .registerTypeAdapter(Reminder.class, new TypeAdapter<Reminder>() {
                    @Override
                    public void write(JsonWriter out, Reminder value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                            return;
                        }
                        out.beginObject();
                        if (value.getId() != null) {
                            out.name("id");
                            out.value(value.getId());
                        }
                        if (value.getRemindTime() != null) {
                            out.name("remindTime");
                            gson.toJson(value.getRemindTime(), CustomTime.class, out);
                        }
                        if (value.getRepeat() != null) {
                            out.name("repeat");
                            gson.toJson(value.getRepeat(), WeekOfDays.class, out);
                        }
                        if (value.getIsSoundAndVibration() != null) {
                            out.name("isSoundAndVibration");
                            out.value(value.getIsSoundAndVibration());
                        }
                        if (value.getIsActivity() != null) {
                            out.name("isActivity");
                            out.value(value.getIsActivity());
                        }
                        out.endObject();
                    }

                    @Override
                    public Reminder read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
                        Reminder reminder = new Reminder();
                        in.beginObject();
                        while (in.hasNext()) {
                            String name = in.nextName();
                            switch (name) {
                                case "id":
                                    if (in.peek() != JsonToken.NULL) {
                                        reminder.setId(in.nextLong());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "remindTime":
                                    reminder.setRemindTime(gson.fromJson(in, CustomTime.class));
                                    break;
                                case "repeat":
                                    reminder.setRepeat(gson.fromJson(in, WeekOfDays.class));
                                    break;
                                case "isSoundAndVibration":
                                    if (in.peek() != JsonToken.NULL) {
                                        reminder.setIsSoundAndVibration(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "isActivity":
                                    if (in.peek() != JsonToken.NULL) {
                                        reminder.setIsActivity(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                default:
                                    in.skipValue();
                                    break;
                            }
                        }
                        in.endObject();
                        return reminder;
                    }
                })
                .registerTypeAdapter(CustomTime.class, new TypeAdapter<CustomTime>() {
                    @Override
                    public void write(JsonWriter out, CustomTime value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                            return;
                        }
                        out.beginObject();
                        if (value.getHour() != null) {
                            out.name("hour");
                            out.value(value.getHour());
                        }
                        if (value.getMinute() != null) {
                            out.name("minute");
                            out.value(value.getMinute());
                        }
                        if (value.getIsAM() != null) {
                            out.name("isAM");
                            out.value(value.getIsAM());
                        }
                        out.endObject();
                    }

                    @Override
                    public CustomTime read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
                        CustomTime time = new CustomTime();
                        in.beginObject();
                        while (in.hasNext()) {
                            String name = in.nextName();
                            switch (name) {
                                case "hour":
                                    if (in.peek() != JsonToken.NULL) {
                                        time.setHour(in.nextInt());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "minute":
                                    if (in.peek() != JsonToken.NULL) {
                                        time.setMinute(in.nextInt());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "isAM":
                                    if (in.peek() != JsonToken.NULL) {
                                        time.setIsAM(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                default:
                                    in.skipValue();
                                    break;
                            }
                        }
                        in.endObject();
                        return time;
                    }
                })
                .registerTypeAdapter(DoNotDisturb.class, new TypeAdapter<DoNotDisturb>() {
                    @Override
                    public void write(JsonWriter out, DoNotDisturb value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                            return;
                        }
                        out.beginObject();
                        if (value.getEnabled() != null) {
                            out.name("enabled");
                            out.value(value.getEnabled());
                        }
                        if (value.getStartTime() != null) {
                            out.name("startTime");
                            gson.toJson(value.getStartTime(), CustomTime.class, out);
                        }
                        if (value.getEndTime() != null) {
                            out.name("endTime");
                            gson.toJson(value.getEndTime(), CustomTime.class, out);
                        }
                        out.endObject();
                    }

                    @Override
                    public DoNotDisturb read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
                        DoNotDisturb dnd = new DoNotDisturb();
                        in.beginObject();
                        while (in.hasNext()) {
                            String name = in.nextName();
                            switch (name) {
                                case "enabled":
                                    if (in.peek() != JsonToken.NULL) {
                                        dnd.setEnabled(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "startTime":
                                    dnd.setStartTime(gson.fromJson(in, CustomTime.class));
                                    break;
                                case "endTime":
                                    dnd.setEndTime(gson.fromJson(in, CustomTime.class));
                                    break;
                                default:
                                    in.skipValue();
                                    break;
                            }
                        }
                        in.endObject();
                        return dnd;
                    }
                })
                .registerTypeAdapter(WeekOfDays.class, new TypeAdapter<WeekOfDays>() {
                    @Override
                    public void write(JsonWriter out, WeekOfDays value) throws IOException {
                        if (value == null) {
                            out.nullValue();
                            return;
                        }
                        out.beginObject();
                        if (value.getMonday() != null) {
                            out.name("monday");
                            out.value(value.getMonday());
                        }
                        if (value.getTuesday() != null) {
                            out.name("tuesday");
                            out.value(value.getTuesday());
                        }
                        if (value.getWednesday() != null) {
                            out.name("wednesday");
                            out.value(value.getWednesday());
                        }
                        if (value.getThursday() != null) {
                            out.name("thursday");
                            out.value(value.getThursday());
                        }
                        if (value.getFriday() != null) {
                            out.name("friday");
                            out.value(value.getFriday());
                        }
                        if (value.getSaturday() != null) {
                            out.name("saturday");
                            out.value(value.getSaturday());
                        }
                        if (value.getSunday() != null) {
                            out.name("sunday");
                            out.value(value.getSunday());
                        }
                        out.endObject();
                    }

                    @Override
                    public WeekOfDays read(JsonReader in) throws IOException {
                        if (in.peek() == JsonToken.NULL) {
                            in.nextNull();
                            return null;
                        }
                        WeekOfDays weekOfDays = new WeekOfDays();
                        in.beginObject();
                        while (in.hasNext()) {
                            String name = in.nextName();
                            switch (name) {
                                case "monday":
                                    if (in.peek() != JsonToken.NULL) {
                                        weekOfDays.setMonday(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "tuesday":
                                    if (in.peek() != JsonToken.NULL) {
                                        weekOfDays.setTuesday(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "wednesday":
                                    if (in.peek() != JsonToken.NULL) {
                                        weekOfDays.setWednesday(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "thursday":
                                    if (in.peek() != JsonToken.NULL) {
                                        weekOfDays.setThursday(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "friday":
                                    if (in.peek() != JsonToken.NULL) {
                                        weekOfDays.setFriday(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "saturday":
                                    if (in.peek() != JsonToken.NULL) {
                                        weekOfDays.setSaturday(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                case "sunday":
                                    if (in.peek() != JsonToken.NULL) {
                                        weekOfDays.setSunday(in.nextBoolean());
                                    } else {
                                        in.nextNull();
                                    }
                                    break;
                                default:
                                    in.skipValue();
                                    break;
                            }
                        }
                        in.endObject();
                        return weekOfDays;
                    }
                })
                .create();
    }

    public static AlarmPayload fromJson(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return gson.fromJson(json, AlarmPayload.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(AlarmPayload payload) {
        if (payload == null) {
            return null;
        }
        try {
            return gson.toJson(payload);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static AlarmData alarmDataFromJson(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return gson.fromJson(json, AlarmData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String alarmDataToJson(AlarmData data) {
        if (data == null) {
            return null;
        }
        try {
            return gson.toJson(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}