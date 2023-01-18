package com.logictstics.attendancerecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<subject_cards> subject_cardsArrayList;

    // Constructor
    public CourseAdapter(Context context, ArrayList<subject_cards> subject_cardsArrayList) {
        this.context = context;
        this.subject_cardsArrayList = subject_cardsArrayList;
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_card_view, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        subject_cards model = subject_cardsArrayList.get(position);
        holder.courseNameTV.setText(model.getCourse_name());
       holder.courseRatingTV.setText(model.getCourse_code());
        holder.courseIV.setText(model.getCourse_left());
        holder.percent.setText(model.getCourse_attendance());
      holder.leaves.setText(model.getCourse_leaves());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number of card items in recycler view
        return subject_cardsArrayList.size();
    }

    // View holder class for initializing of your views such as TextView and Imageview
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseIV;
        private final TextView courseNameTV;
        private final TextView courseRatingTV;
        private final TextView percent;
        private final TextView leaves;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseNameTV = itemView.findViewById(R.id.subject_card_subject_name);
            courseRatingTV = itemView.findViewById(R.id.subject_card_subject_code);
            courseIV = itemView.findViewById(R.id.subject_card_total);
            percent = itemView.findViewById(R.id.subject_card_percent);
            leaves = itemView.findViewById(R.id.subject_card_leaves);
        }
    }


}
