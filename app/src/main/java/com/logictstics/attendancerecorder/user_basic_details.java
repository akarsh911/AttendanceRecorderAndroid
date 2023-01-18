package com.logictstics.attendancerecorder;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class user_basic_details extends AppCompatActivity {
    String selected_sem;String name;String date_start;String date_end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_basic_details);
        EditText name=findViewById(R.id.editText_usr_name);
        Spinner semester=findViewById(R.id.sem_drop);
        EditText date_start=findViewById(R.id.cl_date_start);
        EditText date_end=findViewById(R.id.cl_date_end);
        Button save=findViewById(R.id.bt_save);
        ArrayList<String> ltp=new ArrayList<>();
        ltp.add("Semester-I");
        ltp.add("Semester-II");
        ltp.add("Semester-III");
        ltp.add("Semester-IV");
        ltp.add("Semester-V");
        ltp.add("Semester-VI");
        ltp.add("Semester-VII");
        ltp.add("Semester-VIII");
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ltp);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semester.setAdapter(arrayAdapter);
        semester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_sem = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                selected_sem = parent.getItemAtPosition(0).toString();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}