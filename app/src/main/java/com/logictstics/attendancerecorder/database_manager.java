package com.logictstics.attendancerecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public  class database_manager  extends SQLiteOpenHelper {

    public static final String COLUMN_SUBJECT_NAME = "SUBJECT_NAME";
    public static final String COLUMN_SUBJECT_CODE = "SUBJECT_CODE";
    public static final String COLUMN_ATTENDANCE = "ATTENDANCE";
    public static final String COLUMN_TOTAL_NOW = "TOTAL_NOW";
    public static final String COLUMN_ATTENDED_NOW = "ATTENDED_NOW";
    public static final String COLUMN_CLASSES_LEFT = "CLASSES_LEFT";
    public static final String COLUMN_LEAVES_AVAILAIBLE = "LEAVES_AVAILAIBLE";
    public static final String COLUMN_ATTENDANCE_REQUIREMENT = "ATTENDANCE_REQUIREMENT";
    public static final String COLUMN_LECTURES_NOW = "LECTURES_NOW";
    public static final String COLUMN_LECTURES_LEFT = "LECTURES_LEFT";
    public static final String COLUMN_LECTURES_PRESENT = "LECTURES_PRESENT";
    public static final String COLUMN_LAB_NOW = "LAB_NOW";
    public static final String COLUMN_LAB_LEFT = "LAB_LEFT";
    public static final String COLUMN_LAB_PRESENT = "LAB_PRESENT";
    public static final String COLUMN_TUT_NOW = "TUT_NOW";
    public static final String COLUMN_TUT_LEFT = "TUT_LEFT";
    public static final String COLUMN_TUT_PRESENT = "TUT_PRESENT";
    public static final String COLUMN_TIME = "TIME";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_STATE = "STATE";
    public static final String COLUMN_DAY = "DAY";
    public static final String COLUMN_LTP = "LTP";
    public static final String COLUMN_REPEAT = "REPEAT";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_SEMESTER_CODE = "SEMESTER_CODE";
    public static final String COLUMN_SEMESTER_START_DATE = "SEMESTER_START_DATE";
    public static final String COLUMN_SEMESTER_END_DATE = "SEMESTER_END_DATE";
    public static final String COLUMN_LOCATION = "LOCATION";

    public database_manager(@Nullable Context context) {
        super(context, "attendance.db", null, 1);
    }

    //this is implemented for first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE SUBJECTS (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SUBJECT_NAME + " TEXT," + COLUMN_SUBJECT_CODE + " TEXT," + COLUMN_ATTENDANCE + " TEXT," + COLUMN_TOTAL_NOW + " INTEGER," + COLUMN_ATTENDED_NOW + " INTEGER," + COLUMN_CLASSES_LEFT + " INTEGER," + COLUMN_LEAVES_AVAILAIBLE + " INTEGER," + COLUMN_ATTENDANCE_REQUIREMENT + " INTEGER," + COLUMN_LECTURES_NOW + " INTEGER," + COLUMN_LECTURES_LEFT + " INTEGER," + COLUMN_LECTURES_PRESENT + " INTEGER," + COLUMN_LAB_NOW + " INTEGER," + COLUMN_LAB_LEFT + " INTEGER," + COLUMN_LAB_PRESENT + " INTEGER," + COLUMN_TUT_NOW + " INTEGER," + COLUMN_TUT_LEFT + " INTEGER," + COLUMN_TUT_PRESENT + " INTEGER)";
        db.execSQL(create_table);
        create_table = "CREATE TABLE HISTORY (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SUBJECT_NAME + " TEXT," + COLUMN_SUBJECT_CODE + " TEXT," + COLUMN_TIME + " TEXT," + COLUMN_DATE + " TEXT," + COLUMN_STATE + " TEXT)";
        db.execSQL(create_table);
        create_table = "CREATE TABLE CLASSES (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SUBJECT_NAME + " TEXT," + COLUMN_SUBJECT_CODE + " TEXT," + COLUMN_TIME + " TEXT," + COLUMN_DAY + " TEXT," + COLUMN_LTP + " TEXT, " + COLUMN_REPEAT + " TEXT," + COLUMN_LOCATION + "  TEXT," + COLUMN_DATE + " TEXT )";
        db.execSQL(create_table);
        create_table = "CREATE TABLE USER (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT," + COLUMN_SEMESTER_CODE + " TEXT," + COLUMN_SEMESTER_START_DATE + " TEXT," + COLUMN_SEMESTER_END_DATE + " TEXT)";
        db.execSQL(create_table);
    }
    //work on new version of app
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean add_new_subject(subject_handler subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SUBJECT_NAME, subject.getName());
        cv.put(COLUMN_SUBJECT_CODE, subject.getCode());
        cv.put(COLUMN_ATTENDANCE, subject.getAttendance()+"%");
        cv.put(COLUMN_TOTAL_NOW, subject.getTotal_now());
        cv.put(COLUMN_ATTENDED_NOW, subject.getAttended_now());
        cv.put(COLUMN_CLASSES_LEFT, subject.getClasses_left());
        cv.put(COLUMN_LEAVES_AVAILAIBLE, subject.getLeaves_available());
        cv.put(COLUMN_ATTENDANCE_REQUIREMENT, subject.getAttendance_requirement());
        cv.put(COLUMN_LECTURES_NOW, subject.getLecture_now());
        cv.put(COLUMN_LECTURES_LEFT, subject.getLectures_left());
        cv.put(COLUMN_LECTURES_PRESENT, subject.getLectures_attendance());
        cv.put(COLUMN_LAB_LEFT, subject.getLabs_left());
        cv.put(COLUMN_LAB_NOW, subject.getLabs_now());
        cv.put(COLUMN_LAB_PRESENT, subject.getLabs_attendance());
        cv.put(COLUMN_TUT_NOW, subject.getTut_now());
        cv.put(COLUMN_TUT_LEFT, subject.getTut_left());
        cv.put(COLUMN_TUT_PRESENT, subject.getTut_attendance());
        long subjects = db.insert("SUBJECTS", null, cv);
        return subjects != -1;
    }

    public boolean add_new_class(classes_handler classes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SUBJECT_NAME, classes.getName());
        cv.put(COLUMN_SUBJECT_CODE, classes.getCode());
        cv.put(COLUMN_TIME, classes.getTime());
        cv.put(COLUMN_DAY, classes.getDay());
        cv.put(COLUMN_REPEAT, classes.getRepeat());
        cv.put(COLUMN_DATE, classes.getDate());
        cv.put(COLUMN_LTP, classes.getLtp());
        long cls = db.insert("CLASSES", null, cv);
        return cls != -1;
    }

    public ArrayList<daily_subject_handler> todays_classes() {

        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String today = dtf.format(now);
        ArrayList<daily_subject_handler> subjectList = new ArrayList<>();
      //  Log.e(basic_functions.getTag(),"The day of week is ="+dayOfWeek);
        String query1 = "SELECT * FROM CLASSES WHERE ( " + COLUMN_DAY + " = '" + dayOfWeek + "'" + " AND " + COLUMN_REPEAT + " = 'true' ) OR ( " + COLUMN_DATE + " = '" + today + "' )";
        SQLiteDatabase db1 = this.getReadableDatabase();
        Cursor cursor1 = db1.rawQuery(query1, null);
        if (cursor1.moveToFirst()) {
            do {

                String name = cursor1.getString(1);
                String subj = cursor1.getString(2);
                String time = cursor1.getString(3);
                String type = cursor1.getString(5);
                String date = today;
                String query = "SELECT * FROM SUBJECTS WHERE " + COLUMN_SUBJECT_CODE + "='" + subj+"'" +
                        "";
                SQLiteDatabase db = this.getReadableDatabase();
                Cursor cursor = db.rawQuery(query, null);
                if (cursor.moveToFirst()) {
                    do {

                        String attendance = cursor.getString(3);
                        int classes_left = cursor.getInt(6);
                        int leaves_left = cursor.getInt(7);
                        daily_subject_handler sub = new daily_subject_handler(name, subj, time, type, date, attendance, classes_left, leaves_left);
                        subjectList.add(sub);
                    } while (cursor.moveToNext());
                } else {
                    subjectList.add(new daily_subject_handler("Query Err","","00:00","inner","12/12/2023","",5,5));
                }
                cursor.close();
            } while (cursor1.moveToNext());
        } else {
             subjectList.add(new daily_subject_handler("Query Err","","00:00","outer","12/12/2023","",5,5));
        }
        cursor1.close();
        db1.close();
        return subjectList;
    }

    public boolean check_duplicate_code(String code) {
        int count = 0;
        String query = "SELECT * FROM SUBJECTS WHERE " + COLUMN_SUBJECT_CODE + "='" + code + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    count++;
                } while (cursor.moveToNext());
            } else {
                return false;
            }
            cursor.close();
            return count != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public ArrayList<String> subject_name() {
        int count = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> subs = new ArrayList<>();
        String query = "SELECT * FROM SUBJECTS";
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        count = 0;
        if (cursor.moveToFirst()) {
            do {
                if (count == 0) {
                    count++;
                    continue;
                }
                subs.add(cursor.getString(1) + "-" + cursor.getString(2));
            } while (cursor.moveToNext());
        } else {

        }
        cursor.close();
        return subs;
    }
    public ArrayList<subject_handler> subject_card_home() {
        int count = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<subject_handler> subs = new ArrayList<>();
        String query = "SELECT * FROM SUBJECTS";
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        count = 0;
        if (cursor.moveToFirst()) {
            do {
                if (count == 0) {
                    count++;
                    continue;
                }
                int overall_attendance=Integer.parseInt( cursor.getString(3).substring(0, (cursor.getString(3).indexOf('%'))));

                subject_handler sbj=new subject_handler(cursor.getString(1),cursor.getString(2),
                        overall_attendance,cursor.getInt(4),cursor.getInt(6),cursor.getInt(7));
                subs.add(sbj);
            } while (cursor.moveToNext());
        } else {
           // subs.add(new subject_handler("err - no subs","",0,0,0,0));
        }
        cursor.close();
        return subs;
    }
    public boolean add_new_user(user_detail_handler user) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (db == null)
            return false;
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NAME, user.getName());
        cv.put(COLUMN_SEMESTER_CODE, user.getSem());
        cv.put(COLUMN_SEMESTER_START_DATE, user.getStart_date());
        cv.put(COLUMN_SEMESTER_END_DATE, user.getEnd_date());
        long cls = db.insert("USER", null, cv);
        return cls != -1;
    }

    public boolean add_new_class_time_table(classes_handler cls) {
        update_subject_overall(cls,Boolean.parseBoolean(cls.getRepeat()), cls.getCode());
        update_subject_overall(cls,Boolean.parseBoolean(cls.getRepeat()), "OVERALL");
        return add_new_class(cls);
    }

    public boolean check_user_exists() {
        int count = 0;
        String query = "SELECT * FROM USER";
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    count++;
                } while (cursor.moveToNext());
            } else {
                return false;
            }
            cursor.close();
            return count != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public String get_user_name() {
        String name;
        String query = "SELECT " + COLUMN_USER_NAME + " FROM USER";
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    name = cursor.getString(0).toString().toUpperCase(Locale.ROOT);
                } while (cursor.moveToNext());
            } else {
                return "false";
            }
            cursor.close();
            return name;
        } catch (Exception e) {
            return "false";
        }
    }

    public void update_subject_overall(classes_handler cls,boolean flag,String code) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date_start = LocalDate.parse(get_sem_start(), formatter);
        formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date_end = LocalDate.parse(get_sem_end(), formatter);
        int reps=1;
        if(flag)
        reps = (int) get_days_between(date_start, date_end, Integer.parseInt(cls.getDay()));
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (cls.getLtp().equals("Lecture"))
            contentValues.put(COLUMN_LECTURES_LEFT, reps);
        else if (cls.getLtp().equals("Lab"))
            contentValues.put(COLUMN_LAB_LEFT, reps);
        else if (cls.getLtp().equals("Tutorial"))
            contentValues.put(COLUMN_TUT_LEFT, reps);
        int remain=get_value_sub(COLUMN_CLASSES_LEFT,code);
        contentValues.put(COLUMN_CLASSES_LEFT, remain+reps);
        int attended=get_value_sub(COLUMN_ATTENDED_NOW, code);
        int attendance_req=get_value_sub(COLUMN_ATTENDANCE_REQUIREMENT, code);
        int total_now=get_value_sub(COLUMN_TOTAL_NOW, code);
        if(total_now==attended)
        {
            contentValues.put(COLUMN_ATTENDANCE, "100%");
        }
           int lvs=total_now-attended;
           int allw_lvs=(total_now+reps+remain)*attendance_req/100;
           int avl_lvs=allw_lvs-lvs;
           if(avl_lvs<0)
               avl_lvs=0;
        contentValues.put(COLUMN_LEAVES_AVAILAIBLE, avl_lvs);
        db.update("SUBJECTS", contentValues, COLUMN_SUBJECT_CODE + " = ?", new String[]{code});
    }

    public String get_sem_end() {
        String name;
        String query = "SELECT " + COLUMN_SEMESTER_END_DATE + " FROM USER";
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    name = cursor.getString(0).toString().toUpperCase(Locale.ROOT);
                } while (cursor.moveToNext());
            } else {
                return "false";
            }
            cursor.close();
            return name;
        }
    }

    public String get_sem_start() {
        String name;
        String query = "SELECT " + COLUMN_SEMESTER_START_DATE + " FROM USER";
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    name = cursor.getString(0).toString().toUpperCase(Locale.ROOT);
                } while (cursor.moveToNext());
            } else {
                return "false";
            }
            cursor.close();
            return name;
        }
    }

    public static long get_days_between(LocalDate first, LocalDate last, int day) {
        if (last.isBefore(first)) {
            throw new IllegalArgumentException("first " + first + " was after last " + last);
        }
        // find first Monday in interval
        LocalDate firstMonday = first.with(TemporalAdjusters.next(DayOfWeek.of(day)));
        // similarly find last Monday
        LocalDate lastMonday = last.with(TemporalAdjusters.previous(DayOfWeek.of(day)));
        // count
        long number = ChronoUnit.WEEKS.between(firstMonday, lastMonday);
        // add one to count both first Monday and last Monday in
        return number + 1;
    }

    public int get_value_sub(String search,String code) {
        int total;
        String query = "SELECT " + search+ " FROM SUBJECTS WHERE " + COLUMN_SUBJECT_CODE + "='" + code + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    total = cursor.getInt(0);
                } while (cursor.moveToNext());
            } else {
                return 0;
            }
            cursor.close();
            return total;
        }
    }
    public String get_value_sub_string(String search,String code) {
        String total="";
        String query = "SELECT " + search+ " FROM SUBJECTS WHERE " + COLUMN_SUBJECT_CODE + "='" + code + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(query, null)) {
            if (cursor.moveToFirst()) {
                do {
                    total = cursor.getString(0);
                } while (cursor.moveToNext());
            } else {

            }
            cursor.close();
            return total;
        }
    }
    public void mark_present()
    {

    }

}
