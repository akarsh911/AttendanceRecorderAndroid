package com.logictstics.attendancerecorder;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class add_subject extends AppCompatActivity {
    public EditText sub_name;
    public EditText sub_code;
    public EditText sub_attend;
    boolean pressedUp = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        sub_name=findViewById(R.id.editText_sb_name);
        sub_code=findViewById(R.id.editText_sb_code);
        sub_attend =findViewById(R.id.editText_sb_atn_req);
        ImageButton plus=findViewById(R.id.btn_sub_atn_plus);
        ImageButton minus=findViewById(R.id.btn_sb_atn_minus);
        Button save=findViewById(R.id.bt_save);
        ProgressBar loading=findViewById(R.id.progressBar);
        sub_attend.setText("75");

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int atten=Integer.parseInt(sub_attend.getText().toString());
                atten++;
                if(atten<100)
                sub_attend.setText(Integer.toString(atten));
                else
                {
                    Toast.makeText(add_subject.this,"Cannot be greater than 100%",Toast.LENGTH_SHORT).show();
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int atten=Integer.parseInt(sub_attend.getText().toString());
                atten--;
                if(atten>0)
                    sub_attend.setText(Integer.toString(atten));
                else
                {
                    Toast.makeText(add_subject.this,"Cannot be smaller than 0%",Toast.LENGTH_SHORT).show();
                }
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                loading.setVisibility(View.VISIBLE);
                String name = sub_name.getText().toString();
                String code = (sub_code.getText().toString()).toUpperCase();
                int atten = Integer.parseInt(sub_attend.getText().toString());
                thread_verify_new_subject.verify_subject(name, code, atten, new completed_verification() {
                    @Override
                    public void on_complete(boolean err, String error) {
                        if(err) {
                            Toast.makeText(add_subject.this, error, Toast.LENGTH_LONG).show();
                            loading.setVisibility(View.INVISIBLE);
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        }
                        else
                        {
                            database_manager dbms=new database_manager(add_subject.this);
                            thread_verify_new_subject.database_store_subject(name, code,atten, dbms, new completed_verification() {
                                @Override
                                public void on_complete(boolean err, String error) {
                                }
                                @Override
                                public void on_complete_database_new_subject( boolean success) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(success)
                                        {
                                            Toast.makeText(add_subject.this, "Subject added successfully", Toast.LENGTH_LONG).show();
                                            loading.setVisibility(View.INVISIBLE);
                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        }
                                        else
                                        {
                                            loading.setVisibility(View.INVISIBLE);
                                            Toast.makeText(add_subject.this, "Subject could not be added", Toast.LENGTH_LONG).show();
                                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                        }
                                    }
                                });
                                }
                            });
                        }

                    }
                    @Override
                    public void on_complete_database_new_subject( boolean success) {

                    }
                }, add_subject.this);

            }
        });
    }
}