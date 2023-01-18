package com.logictstics.attendancerecorder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class splash_screen_logo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen_logo);
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //this code is marked for thread splash screen
        basic_functions basicFunctions = new basic_functions();
        if (basicFunctions.isInternetAvailable()) {
            Context applicationContext = getApplicationContext();

            //internet connected load app data from internet

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            if (!sharedPreferences.getBoolean("COMPLETED_ONBOARDING_PREF_NAME", false)) {
                // The user hasn't seen the OnboardingSupportFragment yet, so show it
                //download the data images from server
                database_manager dbms=new database_manager(splash_screen_logo.this);
                subject_handler subject=new subject_handler("Overall","OVERALL",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
                dbms.add_new_subject(subject);
                Toast.makeText(applicationContext, "Preparing for first time startup!", Toast.LENGTH_SHORT).show();
                String urls[]={"https://images.pexels.com/photos/321552/pexels-photo-321552.jpeg?cs=srgb&dl=pexels-oleksandr-pidvalnyi-321552.jpg&fm=jpg","https://thumbs.dreamstime.com/b/funny-face-baby-27701492.jpg","https://thumbs.dreamstime.com/b/funny-face-12963753.jpg"};
                photo_handler.download_photo(urls, new completed() {
                    @Override
                    public void on_complete(Bitmap bmp) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(splash_screen_logo.this, welcome_slider.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                    }
                });

            }
            else
            {
                database_manager dbms=new database_manager(getApplicationContext());
                if(dbms.check_user_exists())
                {
                    Intent intent = new Intent(splash_screen_logo.this, home_page.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent = new Intent(splash_screen_logo.this, user_basic_details.class);
                    startActivity(intent);
                    finish();
                }

            }

        } else {
            Context applicationContext = getApplicationContext();
            Toast.makeText(applicationContext, "Server not availaible!", Toast.LENGTH_SHORT).show();
        }


    }


}