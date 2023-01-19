package com.logictstics.attendancerecorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    ArrayList<daily_subject_handler> subject_cardsArrayList;
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
        // Here, we have created new array list and added data to it
       subject_cardsArrayList = new ArrayList<daily_subject_handler>();
        thread_get_todays_class.get_list(getActivity(), new thread_get_todays_class.thread_today_class_on_complete() {
            @Override
            public void print_list(ArrayList<daily_subject_handler> list) {
                subject_cardsArrayList=list;
                //subject_cardsArrayList.add(list);
                CourseAdapter courseAdapter = new CourseAdapter(v.getContext(), subject_cardsArrayList);

                // below line is for setting a layout manager for our recycler view.
                // here we are creating vertical list so we will provide orientation as vertical
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false);

                // in below two lines we are setting layoutmanager and adapter to our recycler view.
                courseRV.setLayoutManager(linearLayoutManager);
                courseRV.setAdapter(courseAdapter);
            }
        });

        int mScrollY = 0;

        // we are initializing our adapter class and passing our arraylist to it.

        // Inflate the layout for this fragment
        return v;
    }
}

