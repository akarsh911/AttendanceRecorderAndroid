package com.logictstics.attendancerecorder;

public class thread_verify_user_details {
    public static void verify_user(String name, String code,String start_date, String end_date,completed_user_verification comp)
    {

        if(!basic_functions.verify_name(name))
        {
            comp.on_complete(true,"Please Enter valid user name");
        }
        else if(code.equals(""))
        {
            comp.on_complete(true,"Please Select valid semester");
        }

        else
            comp.on_complete(false,"");
    }

}
interface completed_user_verification
{
    void on_complete(boolean err,String error);
}