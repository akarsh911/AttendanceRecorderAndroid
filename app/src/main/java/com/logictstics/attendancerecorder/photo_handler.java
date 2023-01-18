package com.logictstics.attendancerecorder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class photo_handler
{

    public static void download_photo(String urls[], completed caller)
    {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String imageURL="https://miro.medium.com/max/1400/1*I9JDxWRbbxoo4W7F60NiRw.png";
                Bitmap bimage=null;
                for(int i=0;i<urls.length;i++)
                {
                    imageURL=urls[i];
                    try {
                        InputStream in=new java.net.URL(imageURL).openStream();
                        bimage= BitmapFactory.decodeStream(in);
                    } catch (Exception e) {
                        Log.e("Error Message", e.getMessage());
                        e.printStackTrace();
                    }
                    MyCache.getInstance().saveBitmapToCahche("wlcm_bitmap"+(i+1), bimage);
                }

                caller.on_complete(bimage);

                //download and save a photo
                //or multiple download photos
            }
        });



    }

}

interface completed
{
    void on_complete(Bitmap bmp);
}