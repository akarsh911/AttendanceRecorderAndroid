package com.logictstics.attendancerecorder;


public class subject_cards {

    private String course_name;
    private String course_code;
    private String course_attendance;
    private String course_left;
    private String course_leaves;
    // Constructor
    public subject_cards(String course_name, String course_code, String course_attendance,String course_left,String course_leaves) {
        this.course_name = course_name;
        this.course_code = course_code;
        this.course_attendance = course_attendance;
        this.course_left=course_left;
        this.course_leaves=course_leaves;
    }

    // Getter and Setter
    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }


    public String getCourse_left() {
        return course_left;
    }

    public void setCourse_left(String course_left) {
        this.course_left = course_left;
    }

    public String getCourse_attendance() {
        return course_attendance;
    }

    public void setCourse_attendance(String course_attendance) {
        this.course_attendance = course_attendance;
    }
    public String getCourse_leaves() {
        return course_leaves;
    }

    public void setCourse_leaves(String course_leaves) {
        this.course_leaves = course_leaves;
    }
}