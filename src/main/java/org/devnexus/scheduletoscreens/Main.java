package org.devnexus.scheduletoscreens;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.devnexus.scheduletoscreens.vo.Day;
import org.devnexus.scheduletoscreens.vo.RoomScheduleItem;
import org.devnexus.scheduletoscreens.vo.Schedule;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String OUTPUT_DATE_FORMAT = "MM/dd/yyyy hh:mm a";
    private static final SimpleDateFormat OUTPUT_FORMATTER = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
    public static void main (String... args) {
        Schedule schedule = getSchedule();
        var dayRoomItems = schedule.getConference().getDays().stream().flatMap(new Function<Day, Stream<List<RoomScheduleItem>>>() {
            @Override
            public Stream<List<RoomScheduleItem>> apply(Day day) {
                return day.getRooms().values().stream();
            }
        }).flatMap(new Function<List<RoomScheduleItem>, Stream<RoomScheduleItem>>() {
            @Override
            public Stream<RoomScheduleItem> apply(List<RoomScheduleItem> roomScheduleItems) {
                return roomScheduleItems.stream();
            }
        }).
                collect(Collectors.toList());
            System.out.println("\"Event\", \"Start\", \"End\",\"Room\"");
        dayRoomItems.forEach(roomItem-> {
                System.out.println(String.format("\"%s\", \"%s\", \"%s\",\"%s\"",
                        roomItem.getTitle().replace("\"","\"\""),
                        OUTPUT_FORMATTER.format(roomItem.getStartAsDate()),
                        OUTPUT_FORMATTER.format(roomItem.getEndAsDate()),
                        roomItem.getRoomWithGWCCName().replace("\"","\"\"")));

        });

    }

    private static Schedule getSchedule() {
        Gson gson = new Gson();
        var reader = gson.newJsonReader(new InputStreamReader(Main.class.getResourceAsStream("/schedule.json")));
        JsonElement rootElement = JsonParser.parseReader(reader);
        JsonObject scheduleObject = rootElement.getAsJsonObject().get("schedule").getAsJsonObject();
        return gson.fromJson(scheduleObject, Schedule.class);
    }

}
