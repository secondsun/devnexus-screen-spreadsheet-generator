package org.devnexus.scheduletoscreens;

import com.google.gson.stream.JsonReader;
import org.devnexus.scheduletoscreens.vo.Conference;
import org.devnexus.scheduletoscreens.vo.Schedule;

import java.io.IOException;

public class Consumer {
    public static Schedule schedule(JsonReader reader) throws IOException {
        reader.beginObject();
        Schedule schedule = new Schedule();
        schedule.setConference(conference(reader));
        reader.endObject();
        return schedule;
    }

    private static Conference conference(JsonReader reader) {
        return null;
    }
}
