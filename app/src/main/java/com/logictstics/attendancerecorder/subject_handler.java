package com.logictstics.attendancerecorder;

public class subject_handler {
private String name;
private String code;
private int attendance;
private int total_now;
private int attended_now;
private int classes_left;
private int leaves_available;
private int attendance_requirement;
private int lecture_now;
private int lectures_left;
private int lectures_attendance;
private int labs_now;
private int labs_left;
private int labs_attendance;
    private int tut_now;
    private int tut_left;
    private int tut_attendance;

    public subject_handler(String name, String code, int attendance, int total_now, int attended_now, int classes_left, int leaves_available, int attendance_requirement, int lecture_now, int lectures_left, int lectures_attendance, int labs_now, int labs_left, int labs_attendance, int tut_now, int tut_left, int tut_attendance) {
        this.name = name;
        this.code = code;
        this.attendance = attendance;
        this.total_now = total_now;
        this.attended_now = attended_now;
        this.classes_left = classes_left;
        this.leaves_available = leaves_available;
        this.attendance_requirement = attendance_requirement;
        this.lecture_now = lecture_now;
        this.lectures_left = lectures_left;
        this.lectures_attendance = lectures_attendance;
        this.labs_now = labs_now;
        this.labs_left = labs_left;
        this.labs_attendance = labs_attendance;
        this.tut_now = tut_now;
        this.tut_left = tut_left;
        this.tut_attendance = tut_attendance;
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

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getTotal_now() {
        return total_now;
    }

    public void setTotal_now(int total_now) {
        this.total_now = total_now;
    }

    public int getAttended_now() {
        return attended_now;
    }

    public void setAttended_now(int attended_now) {
        this.attended_now = attended_now;
    }

    public int getClasses_left() {
        return classes_left;
    }

    public void setClasses_left(int classes_left) {
        this.classes_left = classes_left;
    }

    public int getLeaves_available() {
        return leaves_available;
    }

    public void setLeaves_available(int leaves_available) {
        this.leaves_available = leaves_available;
    }

    public int getAttendance_requirement() {
        return attendance_requirement;
    }

    public void setAttendance_requirement(int attendance_requirement) {
        this.attendance_requirement = attendance_requirement;
    }

    public int getLecture_now() {
        return lecture_now;
    }

    public void setLecture_now(int lecture_now) {
        this.lecture_now = lecture_now;
    }

    public int getLectures_left() {
        return lectures_left;
    }

    public void setLectures_left(int lectures_left) {
        this.lectures_left = lectures_left;
    }

    public int getLectures_attendance() {
        return lectures_attendance;
    }

    public void setLectures_attendance(int lectures_attendance) {
        this.lectures_attendance = lectures_attendance;
    }

    public int getLabs_now() {
        return labs_now;
    }

    public void setLabs_now(int labs_now) {
        this.labs_now = labs_now;
    }

    public int getLabs_left() {
        return labs_left;
    }

    public void setLabs_left(int labs_left) {
        this.labs_left = labs_left;
    }

    public int getLabs_attendance() {
        return labs_attendance;
    }

    public void setLabs_attendance(int labs_attendance) {
        this.labs_attendance = labs_attendance;
    }

    public int getTut_now() {
        return tut_now;
    }

    public void setTut_now(int tut_now) {
        this.tut_now = tut_now;
    }

    public int getTut_left() {
        return tut_left;
    }

    public void setTut_left(int tut_left) {
        this.tut_left = tut_left;
    }

    public int getTut_attendance() {
        return tut_attendance;
    }

    public void setTut_attendance(int tut_attendance) {
        this.tut_attendance = tut_attendance;
    }

    @Override
    public String toString() {
        return "subject_handler{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", attendance=" + attendance +
                ", total_now=" + total_now +
                ", attended_now=" + attended_now +
                ", classes_left=" + classes_left +
                ", leaves_available=" + leaves_available +
                ", attendance_requirement=" + attendance_requirement +
                ", lecture_now=" + lecture_now +
                ", lectures_left=" + lectures_left +
                ", lectures_attendance=" + lectures_attendance +
                ", labs_now=" + labs_now +
                ", labs_left=" + labs_left +
                ", labs_attendance=" + labs_attendance +
                ", tut_now=" + tut_now +
                ", tut_left=" + tut_left +
                ", tut_attendance=" + tut_attendance +
                '}';
    }
}
