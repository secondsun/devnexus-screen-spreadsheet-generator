package org.devnexus.scheduletoscreens.vo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day implements Comparable<Day>{
    private int index;
    private  String date;
    private  String day_start;
    private  String day_end;
    private Map<String, List<RoomScheduleItem>> rooms;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay_start() {
        return day_start;
    }

    public void setDay_start(String day_start) {
        this.day_start = day_start;
    }

    public String getDay_end() {
        return day_end;
    }

    public void setDay_end(String day_end) {
        this.day_end = day_end;
    }

    public Map<String, List<RoomScheduleItem>> getRooms() {
        return rooms;
    }

    public void setRooms(Map<String, List<RoomScheduleItem>> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return index == day.index &&
                Objects.equals(date, day.date) &&
                Objects.equals(day_start, day.day_start) &&
                Objects.equals(day_end, day.day_end) &&
                Objects.equals(rooms, day.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, date, day_start, day_end, rooms);
    }

    @Override
    public int compareTo(Day o) {
        return o.index - index;
    }
}
