package com.logictstics.attendancerecorder;

public class user_detail_handler {
    private String name;
    private  String sem;
    private String start_date;
    private String end_date;

    public user_detail_handler(String name, String sem, String start_date, String end_date) {
        this.name = name;
        this.sem = sem;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "user_detail_handler{" +
                "name='" + name + '\'' +
                ", sem='" + sem + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                '}';
    }
}
