package com.logictstics.attendancerecorder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class basic_functions {

    public boolean isInternetAvailable() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean verify_name(String name)
    {
        String regex = "^[\\p{L} .'-]+$";
        Pattern p = Pattern.compile(regex);
        if (name == null) {
            return false;
        }
        Matcher m = p.matcher(name);
        return m.matches();
    }

}
