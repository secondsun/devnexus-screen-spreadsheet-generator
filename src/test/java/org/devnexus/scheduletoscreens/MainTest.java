package org.devnexus.scheduletoscreens;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.devnexus.scheduletoscreens.vo.Schedule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.devnexus.scheduletoscreens.vo.RoomScheduleItem.DATE_FORMAT;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void loadSchedule() throws IOException {
        Gson gson = new Gson();
        var reader = gson.newJsonReader(new InputStreamReader(MainTest.class.getResourceAsStream("/schedule.json")));
        assertNotNull(reader);

        reader.beginObject();
        assertEquals("schedule",reader.nextName());

    }

    @Test
    public void testSchedule() {
        Gson gson = new Gson();
        var reader = gson.newJsonReader(new InputStreamReader(MainTest.class.getResourceAsStream("/schedule.json")));
        JsonElement rootElement = JsonParser.parseReader(reader);
        JsonObject scheduleObject = rootElement.getAsJsonObject().get("schedule").getAsJsonObject();
        Schedule schedule = gson.fromJson(scheduleObject, Schedule.class);
        assertEquals(3, schedule.getConference().getDaysCount());
    }

    @Test
    public void testRooms() {
        Gson gson = new Gson();
        var reader = gson.newJsonReader(new InputStreamReader(MainTest.class.getResourceAsStream("/schedule.json")));
        JsonElement rootElement = JsonParser.parseReader(reader);
        JsonObject scheduleObject = rootElement.getAsJsonObject().get("schedule").getAsJsonObject();
        Schedule schedule = gson.fromJson(scheduleObject, Schedule.class);
        assertEquals(3, schedule.getConference().getDaysCount());
        var workshopDay = schedule.getConference().getDays().get(0);
        assertEquals(0, workshopDay.getIndex());
        workshopDay.getRooms().containsKey("Room 1");

        var registrationDay = schedule.getConference().getDays().get(1);
        assertEquals(1, registrationDay.getIndex());
        registrationDay.getRooms().containsKey("Keynote Room");

        var keyNoteRoom = registrationDay.getRooms().get("Keynote Room");
        var firstKeyNoteRoomItem = keyNoteRoom.get(0);
        assertTrue(firstKeyNoteRoomItem.getDate().equalsIgnoreCase("2020-02-20T09:00:00-05:00"));

    }

    @Test
    public void parseDate() throws ParseException {
        Date d = new SimpleDateFormat(DATE_FORMAT).parse("2020-02-19T09:00:00-05:00");
        assertEquals(9, d.getHours());
    }

}
