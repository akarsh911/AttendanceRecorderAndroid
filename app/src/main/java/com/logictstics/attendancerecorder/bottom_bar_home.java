package com.logictstics.attendancerecorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link bottom_bar_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class bottom_bar_home extends Fragment {
    ArrayList<subject_handler> subject_cardsArrayList;
    public bottom_bar_home() {
        // Required empty public constructor
    }

    public static bottom_bar_home newInstance() {
        bottom_bar_home fragment = new bottom_bar_home();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_bottom_bar_home, container, false);
        TextView attendance_tv=v.findViewById(R.id.overall_attendance_tv);
        ProgressBar pg=v.findViewById(R.id.overall_attendance_progress);
        RecyclerView courseRV = v.findViewById(R.id.idRVCourse);
        TextView user_circle=v.findViewById(R.id.user_circle);
        database_manager dbms=new database_manager(getActivity());
        char firstLetter=dbms.get_user_name().charAt(0);
        user_circle.setText(Character.toString(firstLetter));
        user_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), add_subject.class);
                startActivity(intent);
            }
        });
      String overall_attendance=new database_manager(getActivity()).get_value_sub_string(database_manager.COLUMN_ATTENDANCE,"OVERALL");
      attendance_tv.setText(overall_attendance);
      pg.setProgress(Integer.parseInt(overall_attendance.substring(0,overall_attendance.indexOf('%'))));
        // Here, we have created new array list and added data to it
       subject_cardsArrayList = new ArrayList<subject_handler>();
       thread_get_all_subjects.get_list(getActivity(), new completed_get_subjects() {
           @Override
           public void print_list(ArrayList<subject_handler> list) {

              getActivity().runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                    //  Toast.makeText(getActivity(), "aa: "+list.get(1).toString(), Toast.LENGTH_SHORT).show();
                      subject_adapter_home_page adapter_home_page=new subject_adapter_home_page(v.getContext(), list);
                      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false);
                      courseRV.setLayoutManager(linearLayoutManager);
                      courseRV.setAdapter(adapter_home_page);
                  }
              });

               // below line is for setting a layout manager for our recycler view.
               // here we are creating vertical list so we will provide orientation as vertical

           }
       });

        int mScrollY = 0;

        // we are initializing our adapter class and passing our arraylist to it.

        // Inflate the layout for this fragment
        return v;
    }
}

