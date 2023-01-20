package com.logictstics.attendancerecorder;

import android.content.Context;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class thread_get_all_subjects {
    static ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void get_list(Context context, completed_get_subjects tv)
    {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<subject_handler> list;
                database_manager dbms=new database_manager(context);
                list=dbms.subject_card_home();
              //  list.add(new subject_handler("err - no subs","",0,0,0,0));
                tv.print_list(list);
            }
        });
    }
}
interface completed_get_subjects{
    void print_list(ArrayList<subject_handler> list);
}