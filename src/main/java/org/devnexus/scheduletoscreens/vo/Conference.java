package org.devnexus.scheduletoscreens.vo;

import java.util.List;

public class Conference {

    private String        title;
    private String        start;
    private String        end;
    private int        daysCount;
    private String timeslot_duration;
    private List<Day> days;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getDaysCount() {
        return daysCount;
    }

    public void setDaysCount(int daysCount) {
        this.daysCount = daysCount;
    }

    public String getTimeslot_duration() {
        return timeslot_duration;
    }

    public void setTimeslot_duration(String timeslot_duration) {
        this.timeslot_duration = timeslot_duration;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}
