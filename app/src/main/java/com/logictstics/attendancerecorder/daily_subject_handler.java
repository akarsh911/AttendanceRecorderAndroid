package com.logictstics.attendancerecorder;

public class daily_subject_handler {
    private String name;
    private String code;
    private String time;
    private String ltp;
    private String date;
    private String attendance;
    private int class_left;
    private int leaves_left;

    public daily_subject_handler(String name, String code, String time, String ltp, String date, String attendance, int class_left, int leaves_left) {
        this.name = name;
        this.code = code;
        this.time = time;
        this.ltp = ltp;
        this.date = date;
        this.attendance = attendance;
        this.class_left = class_left;
        this.leaves_left = leaves_left;
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

    public String getLtp() {
        return ltp;
    }

    public void setLtp(String ltp) {
        this.ltp = ltp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public int getClass_left() {
        return class_left;
    }

    public void setClass_left(int class_left) {
        this.class_left = class_left;
    }

    public int getLeaves_left() {
        return leaves_left;
    }

    public void setLeaves_left(int leaves_left) {
        this.leaves_left = leaves_left;
    }

    @Override
    public String toString() {
        return "daily_subject_handler{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", time='" + time + '\'' +
                ", ltp='" + ltp + '\'' +
                ", date='" + date + '\'' +
                ", attendance='" + attendance + '\'' +
                ", class_left=" + class_left +
                ", leaves_left=" + leaves_left +
                '}';
    }
}
