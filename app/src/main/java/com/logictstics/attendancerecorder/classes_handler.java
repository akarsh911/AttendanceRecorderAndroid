package com.logictstics.attendancerecorder;

public class classes_handler {
    private String name;
    private String code;
    private String time;
    private String day;
    private String repeat;
    private String date;
    private String ltp;

    public classes_handler(String name, String code, String time, String day, String repeat, String date, String ltp) {
        this.name = name;
        this.code = code;
        this.time = time;
        this.day = day;
        this.repeat = repeat;
        this.date = date;
        this.ltp = ltp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLtp() {
        return ltp;
    }

    public void setLtp(String ltp) {
        this.ltp = ltp;
    }

    @Override
    public String toString() {
        return "classes_handler{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", time='" + time + '\'' +
                ", day='" + day + '\'' +
                ", repeat='" + repeat + '\'' +
                ", date='" + date + '\'' +
                ", ltp='" + ltp + '\'' +
                '}';
    }
}