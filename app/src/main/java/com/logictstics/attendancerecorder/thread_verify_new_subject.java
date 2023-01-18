package com.logictstics.attendancerecorder;

import android.content.Context;

public class thread_verify_new_subject {
    public static void verify_subject(String name, String code, int atten, completed_verification comp, Context context)
    {

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
         comp.on_complete(false,"");
    }

}
interface completed_verification
{
    void on_complete(boolean err,String error);
}