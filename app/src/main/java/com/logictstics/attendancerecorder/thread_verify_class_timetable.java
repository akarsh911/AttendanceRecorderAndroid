package com.logictstics.attendancerecorder;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class thread_verify_class_timetable {
    static ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void verify_subject(String name, String code, String time,String day,boolean repeat,String ltp,String date ,Context context,completed_verification_class comp)
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if(!basic_functions.verify_name(name))
                {
                    comp.on_complete(true,"Please Enter valid subject name");
                }
                else if(code.equals("")||code==null)
                {
                    comp.on_complete(true,"Please Enter valid subject code (cannot be blank)");
                }
                else if(time.equals("")||time==null)
                {
                    comp.on_complete(true,"Enter valid time");
                }
                else if((repeat&&(day.equals("")||day==null))||(!repeat&&(date.equals("")||date==null)))
                {
                    comp.on_complete(true,"Day / Date is rrequired!");
                }else if(ltp.equals("")||ltp==null)
                {
                    comp.on_complete(true,"Enter valid time");
                }
                else
                    database_store_subject(name,code,time,day,repeat,ltp,date,context,comp);
            }
        });


    }
    public static  void database_store_subject(String name, String code, String time,String day,boolean repeat,String ltp,String date ,Context context,completed_verification_class cv)
    {
        database_manager dbms=new database_manager(context);
        classes_handler new_class=new classes_handler(name,code,time,day,Boolean.toString(repeat),date,ltp);
        boolean b = dbms.add_new_class_time_table(new_class);
        if(!b)
            cv.on_complete(true,"Failed adding class!");
        else
            cv.on_complete(false,"Success adding class");
    }

}
interface completed_verification_class
{
    void on_complete(boolean err,String error);
}
