package com.logictstics.attendancerecorder;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class add_classes_timetable extends AppCompatActivity  implements  add_class_recurring.returnData, add_class_non_recurring.returnData_non  {
    String selected_sub,selected_ltp;ArrayList <Integer> days_list;String class_time="08:00";boolean recurring=false;String class_date="01/01/2023";
    classes_handler new_class;boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_classes_timetable);
        Switch fragment=findViewById(R.id.recurring);
       FragmentContainerView frag_holder=findViewById(R.id.fragment_form);
        Spinner subjects = findViewById(R.id.spinner);
        Spinner ltp_drop = findViewById(R.id.ltp_drop);
        database_manager dbms=new database_manager(add_classes_timetable.this);
        days_list=new ArrayList<>();
        ArrayList<String> subs=dbms.subject_name();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, subs);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjects.setAdapter(arrayAdapter);
        subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_sub = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                selected_sub = parent.getItemAtPosition(0).toString();
            }
        });
        ArrayList<String> ltp=new ArrayList<>();
        ltp.add("Lecture");
        ltp.add("Tutorial");
        ltp.add("Lab");
         arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ltp);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ltp_drop.setAdapter(arrayAdapter);
        ltp_drop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_ltp = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                selected_ltp = parent.getItemAtPosition(0).toString();
            }
        });



        fragment.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(fragment.isChecked())
                {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_form, new add_class_recurring());
                    ft.commit();
                    days_list.clear();
                    class_date="";
                    recurring=true;
                }
                else
                {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment_form, new add_class_non_recurring());
                    ft.commit();
                  //  class_date="";
                    days_list.clear();
                    recurring=false;
                }
            }
        });
        Button save=findViewById(R.id.bt_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=selected_sub.indexOf('-');
                String subject_name=selected_sub.substring(0,i);
                String subject_code=selected_sub.substring(i+1);

              create_class(subject_name,subject_code,class_time,days_list,recurring,class_date,selected_ltp);
               // Toast.makeText(getApplicationContext(),"saving as "+selected_ltp +selected_sub,Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override public void handel_data_recurring_days(int input,boolean b)
    {
      ///  mInput = input;
        if(b)
            days_list.add(input);
        else
            days_list.remove(input);
    }
    @Override public void handel_data_recurring_time(String time)
    {
        ///  mInput = input;
       class_time=time;

    }
@Override public void handel_data_non_recurring_date(String date)
        {
        ///  mInput = input;
        class_date=date;

        }
        private void create_class(String name,String code,String time,ArrayList<Integer> day,boolean repeat,String date,String ltp)
        {
            //TODO:call database function to invoke storing a new class
            if(repeat)
            {
                int count=day.size();
                while(count>0)
                {
                    thread_verify_class_timetable.verify_subject(name, code, time, Integer.toString(day.get(--count)+1), repeat, ltp, "", add_classes_timetable.this, new completed_verification_class() {

                        @Override
                        public void on_complete(boolean err, String error) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    if(err)
                                    {
                                        Toast.makeText(getBaseContext(), "Error "+error, Toast.LENGTH_SHORT).show();
                                        flag=false;
                                    }

                                }
                            });
                        }
                    });
                }
                if(flag)
                    Toast.makeText(getBaseContext(),"Success",Toast.LENGTH_SHORT).show();
                return;
            }
            thread_verify_class_timetable.verify_subject(name, code, time, "", repeat, ltp, date, add_classes_timetable.this, new completed_verification_class() {
                @Override
                public void on_complete(boolean err, String error) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(err)
                            {
                                Toast.makeText(getBaseContext(), "Error "+error, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(getBaseContext(),"Success",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
        }
}