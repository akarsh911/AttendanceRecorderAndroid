package com.logictstics.attendancerecorder;

import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class thread_verify_user_details {
    static ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void verify_user(String name, String sem, String start_date, String end_date, Context context, completed_user_verification comp)
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if(!basic_functions.verify_name(name))
                {
                    comp.on_complete(true,"Please Enter valid user name");
                }
                else if(sem.equals("")||sem==null)
                {
                    comp.on_complete(true,"Please Select valid semester");
                }

                else
                    save_user_database(name,sem,start_date,end_date,context,comp);
            }
        });

    }
    public static void save_user_database(String name, String sem, String start_date, String end_date, Context context, completed_user_verification comp)
    {
        //TODO: call database to user
        user_detail_handler user=new user_detail_handler(name,sem,start_date,end_date);
        database_manager dbms=new database_manager(context);
        boolean b= dbms.add_new_user(user);
        if(b)
            comp.on_complete(false,"");
        else
            comp.on_complete(true,"Could not add user");
    }

}
interface completed_user_verification
{
    void on_complete(boolean err,String error);
}