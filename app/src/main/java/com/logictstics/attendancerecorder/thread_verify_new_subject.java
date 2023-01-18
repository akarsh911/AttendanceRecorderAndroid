package com.logictstics.attendancerecorder;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class thread_verify_new_subject {
    static ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void verify_subject(String name, String code, int atten,completed_verification_subject comp, Context context)
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if(!basic_functions.verify_name(name))
                {
                    comp.on_complete(true,"Please Enter valid subject name");
                }
                else if(code.equals(""))
                {
                    comp.on_complete(true,"Please Enter valid subject code (cannot be blank)");
                }
                else if(new database_manager(context).check_duplicate_code(code))
                {
                    comp.on_complete(true,"Same Subject Code exists!");
                }
                else if(atten>100||atten<0)
                {
                    comp.on_complete(true,"Please Enter Attendance between 0-100%");
                }
                else
                    database_store_subject(name,code,atten,context,comp);
            }
        });


    }
    public static  void database_store_subject(String name,String code,int atten,Context context,completed_verification_subject cv)
    {
        database_manager dbms=new database_manager(context);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                subject_handler sub=new subject_handler(name,code,0,0,0,0,0,atten,0,0,0,0,0,0,0,0,0);
                boolean b = dbms.add_new_subject(sub);
                if(b)
                cv.on_complete(false,"");
                else
                    cv.on_complete(true,"Subject could not be added");
            }
        });
    }

}
interface completed_verification_subject
{
    void on_complete(boolean err,String error);
}