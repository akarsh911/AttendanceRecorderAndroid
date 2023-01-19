package com.logictstics.attendancerecorder;

import android.content.Context;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class thread_get_todays_class {

    static ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void get_list(Context context,thread_today_class_on_complete tv)
    {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                 ArrayList<daily_subject_handler> list;
                database_manager dbms=new database_manager(context);
                list=dbms.todays_classes();
                tv.print_list(list);
            }
        });
    }
    interface thread_today_class_on_complete{
        void print_list(ArrayList<daily_subject_handler> list);
    }

}
