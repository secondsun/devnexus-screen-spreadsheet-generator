package org.devnexus.scheduletoscreens.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RoomScheduleItem {

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX";

    private String date, room, title, track, duration;
    private static final Map<String, String> gwccMap = new HashMap<>();
    private static final Map<String, String> workshopMap = new HashMap<>();

    static {

        workshopMap.put("Room 1", "302");
        workshopMap.put("Room 2", "303");
        workshopMap.put("Room 3", "304");
        workshopMap.put("Room 4", "305");
        workshopMap.put("Room 5", "311");
        workshopMap.put("Room 6", "312");
        workshopMap.put("Room 7", "313");
        workshopMap.put("Room 8", "314");
        workshopMap.put("Room 9", "315/316");
        workshopMap.put("Room 10", "403");
        workshopMap.put("Room 11", "404");
        workshopMap.put("Room 12", "405");
        gwccMap.put("Room 1", "312");
        gwccMap.put("Room 2", "Sidney Marcus Auditorium");
        gwccMap.put("Room 3", "302");
        gwccMap.put("Room 4", "411");
        gwccMap.put("Room 5", "305");
        gwccMap.put("Room 6", "314");
        gwccMap.put("Room 7", "315/316");
        gwccMap.put("Room 8", "303");
        gwccMap.put("Room 9", "311");
        gwccMap.put("Room 10", "412");
        gwccMap.put("Room 11", "405");
        gwccMap.put("Room 12", "403");
        gwccMap.put("Room 13", "404");
        gwccMap.put("Room 14", "304");
        gwccMap.put("Room 15", "313");
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getRoomWithGWCCName() {
        if (getTrack().equalsIgnoreCase("Full day Workshops (Wednesday Only)")) {
            return workshopMap.getOrDefault(getRoom(), getRoom());
        } else {
            return gwccMap.getOrDefault(getRoom(), getRoom());
        }
    }

    public Date getStartAsDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat(DATE_FORMAT).parse(getDate());
            Calendar start = Calendar.getInstance();
            start.setTime(date);
            return start.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public Date getEndAsDate() {
        Date date = null;
        try {
            date = new SimpleDateFormat(DATE_FORMAT).parse(getDate());
            Calendar end = Calendar.getInstance();
            end.setTime(date);
            int hours = Integer.parseInt(duration.split(":")[0]);
            int minutes = Integer.parseInt(duration.split(":")[1]);
            end.add(Calendar.HOUR, hours);
            end.add(Calendar.MINUTE, minutes);

            return end.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
