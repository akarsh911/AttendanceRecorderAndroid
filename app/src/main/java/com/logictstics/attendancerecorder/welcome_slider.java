package com.logictstics.attendancerecorder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class welcome_slider extends AppCompatActivity {
    int current=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_slider);
        Button bt_prev= findViewById(R.id.wlcm_sldr_prev);
       Button bt_next= findViewById(R.id.wlcm_sldr_next);
        ImageView wlcm_img= findViewById(R.id.wlcm_sldr_img);
        Bitmap bmp2 = MyCache.getInstance().retrieveBitmapFromCache("wlcm_bitmap1");
        wlcm_img.setImageBitmap(bmp2);

        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current++;
                if(current<1||current>3)
                {
                    current=1;
                }
                else if(current==3)
                {
                    bt_next.setText("Start");
                    bt_next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //the activity is complete and now load the home page
                            startActivity(new Intent(welcome_slider.this, home_page.class));
                        }
                    });
                }
                Bitmap bmp = MyCache.getInstance().retrieveBitmapFromCache("wlcm_bitmap"+current);
                wlcm_img.setImageBitmap(bmp);
            }
        });
        bt_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                current--;
                if(current<1||current>3)
                {
                    current=1;
                }
                Bitmap bmp = MyCache.getInstance().retrieveBitmapFromCache("wlcm_bitmap"+current);
                wlcm_img.setImageBitmap(bmp);
            }
        });
        SharedPreferences.Editor sharedPreferencesEditor =
                PreferenceManager.getDefaultSharedPreferences(this).edit();
        sharedPreferencesEditor.putBoolean(
                "COMPLETED_ONBOARDING_PREF_NAME", true);
        sharedPreferencesEditor.apply();
        Context applicationContext= getApplicationContext();
    }

}