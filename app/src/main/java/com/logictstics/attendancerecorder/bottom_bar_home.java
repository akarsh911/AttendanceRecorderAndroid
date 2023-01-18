package com.logictstics.attendancerecorder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        // Here, we have created new array list and added data to it
        ArrayList<subject_cards> subject_cardsArrayList = new ArrayList<subject_cards>();
        subject_cardsArrayList.add(new subject_cards("Computer Programming", "4","75","15","5"));
        subject_cardsArrayList.add(new subject_cards("Computer Programming", "4","75","15","5"));
        subject_cardsArrayList.add(new subject_cards("Computer Programming", "4","75","15","5"));
        subject_cardsArrayList.add(new subject_cards("Computer Programming", "4","75","15","5"));
        subject_cardsArrayList.add(new subject_cards("Computer Programming", "4","75","15","5"));
        subject_cardsArrayList.add(new subject_cards("Computer Programming", "4","75","15","5"));
        subject_cardsArrayList.add(new subject_cards("Computer Programming", "4","75","15","5"));

        int mScrollY = 0;

        // we are initializing our adapter class and passing our arraylist to it.
        CourseAdapter courseAdapter = new CourseAdapter(v.getContext(), subject_cardsArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(v.getContext(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(courseAdapter);
        // Inflate the layout for this fragment
        return v;
    }
}

