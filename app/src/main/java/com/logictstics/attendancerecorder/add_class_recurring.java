package com.logictstics.attendancerecorder;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_class_recurring#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_class_recurring extends Fragment {
    boolean b;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public add_class_recurring() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_class_recurring.
     */
    // TODO: Rename and change types and number of parameters
    public static add_class_recurring newInstance(String param1, String param2) {
        add_class_recurring fragment = new add_class_recurring();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_add_class_recurring, container, false);
        EditText time=v.findViewById(R.id.cl_time);
        rd=(returnData)getActivity() ;
        time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText( selectedHour + ":" + selectedMinute);
                        rd.handel_data_recurring_time(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
        disableEditText(time);
            TextView tv1=v.findViewById(R.id.bt_days_1);
        TextView tv2=v.findViewById(R.id.bt_days_2);
        TextView tv3=v.findViewById(R.id.bt_days_3);
        TextView tv4=v.findViewById(R.id.bt_days_4);
        TextView tv5=v.findViewById(R.id.bt_days_5);
        TextView tv6=v.findViewById(R.id.bt_days_6);
        TextView tv7=v.findViewById(R.id.bt_days_7);
        textViewChange(tv1,0);
        textViewChange(tv2,1);
        textViewChange(tv3,2);
        textViewChange(tv4,3);
        textViewChange(tv5,4);
        textViewChange(tv6,5);
        textViewChange(tv7,6);
        // Inflate the layout for this fragment
        return v;
    }
    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
       // editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
        //editText.setBackgroundColor(Color.TRANSPARENT);
    }
    public returnData rd;
    private void textViewChange(TextView tv,int day)
    {
        b=false;
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tv.getTextSize()==54.0)
                {
                    tv.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.circle_text_view_blue));
                    tv.setTextColor(ContextCompat.getColor(getActivity(),R.color.white));
                    tv.setTextSize(19);
                    rd.handel_data_recurring_days(day,true);
                }
                else
                {
                    tv.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.circle_text_view_white));
                    tv.setTextColor(ContextCompat.getColor(getActivity(),R.color.gray));
                    tv.setTextSize(18);
                    rd.handel_data_recurring_days(day,false);
                }

            }
        });
    }
    public interface returnData {
        void handel_data_recurring_days(int data,boolean b);
        void handel_data_recurring_time(String time);
    }
}