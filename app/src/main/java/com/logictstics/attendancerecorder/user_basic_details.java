package com.logictstics.attendancerecorder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class user_basic_details extends AppCompatActivity {
    String selected_sem;String sel_name;String sel_date_start;String sel_date_end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_basic_details);
        EditText name=findViewById(R.id.editText_usr_name);
        Spinner semester=findViewById(R.id.sem_drop);
        EditText date_start=findViewById(R.id.cl_date_start);
        EditText date_end=findViewById(R.id.cl_date_end);
        ProgressBar loading=findViewById(R.id.progressBar);
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
       date_start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(user_basic_details.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        sel_date_start="" + selectedday + "/" + selectedmonth + "/" + selectedyear;
                        if(selectedmonth<10)
                        {
                            sel_date_start="" + selectedday + "/0" + selectedmonth + "/" + selectedyear;
                        }
                        if(selectedday<10)
                        {
                            sel_date_start="0" + selectedday + "/" + selectedmonth + "/" + selectedyear;
                        }
                        if (selectedday < 10 && selectedmonth < 10) {

                            sel_date_start="0" + selectedday + "/0" + selectedmonth + "/" + selectedyear;
                        }
                        date_start.setText(sel_date_start);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        date_end.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(user_basic_details.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        sel_date_end="" + selectedday + "/" + selectedmonth + "/" + selectedyear;
                        if(selectedmonth<10)
                        {
                            sel_date_end="" + selectedday + "/0" + selectedmonth + "/" + selectedyear;
                        }
                        if(selectedday<10)
                        {
                            sel_date_end="0" + selectedday + "/" + selectedmonth + "/" + selectedyear;
                        }
                        if (selectedday < 10 && selectedmonth < 10) {

                            sel_date_end="0" + selectedday + "/0" + selectedmonth + "/" + selectedyear;
                        }
                        date_end.setText(sel_date_end);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });
        disableEditText(date_start);
        disableEditText(date_end);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                loading.setVisibility(View.VISIBLE);
                sel_name=name.getText().toString();
                sel_date_start=date_start.getText().toString();
                sel_date_end=date_end.getText().toString();
                thread_verify_user_details.verify_user(sel_name, selected_sem, sel_date_start, sel_date_end, getApplicationContext(), new completed_user_verification() {
                    @Override
                    public void on_complete(boolean err, String error) {
                        runOnUiThread(() -> {
                            if(err) {
                                Toast.makeText(user_basic_details.this, error, Toast.LENGTH_LONG).show();
                                loading.setVisibility(View.INVISIBLE);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            }
                            else
                            {
                                Toast.makeText(user_basic_details.this, "User added successfully", Toast.LENGTH_LONG).show();
                                loading.setVisibility(View.INVISIBLE);
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                Intent intent = new Intent(user_basic_details.this, home_page.class);
                                startActivity(intent);
                                finish();
                            }});
                    }
                });
            }
        });
    }
    public void disableEditText(@NonNull EditText editText) {
        editText.setFocusable(false);
        // editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        // editText.setBackgroundColor(Color.TRANSPARENT);
    }
}