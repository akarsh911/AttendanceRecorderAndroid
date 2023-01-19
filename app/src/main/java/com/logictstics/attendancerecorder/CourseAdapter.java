package com.logictstics.attendancerecorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<daily_subject_handler> subject_cardsArrayList;

    // Constructor
    public CourseAdapter(Context context, ArrayList<daily_subject_handler> subject_cardsArrayList) {
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



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        // to set data to textview and imageview of each card layout
        daily_subject_handler model = subject_cardsArrayList.get(position);
        holder.courseNameTV.setText(model.getName()+"-"+model.getLtp());
       holder.courseRatingTV.setText(model.getCode());
        holder.courseIV.setText(Integer.toString(model.getClass_left()));
        holder.percent.setText(model.getAttendance());
      holder.leaves.setText(Integer.toString(model.getLeaves_left()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(model.getDate(), formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd MMMM yyyy");
       String strDate = formatter2.format(date);
        holder.date.setText(strDate);
        holder.time.setText(model.getTime());
        holder.present.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
        holder.cancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
        private final TextView date;
        private final TextView time;
        private final Button present;
        private final Button absent;
        private final Button cancelled;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            courseNameTV = itemView.findViewById(R.id.subject_card_subject_name);
            courseRatingTV = itemView.findViewById(R.id.subject_card_subject_code);
            courseIV = itemView.findViewById(R.id.subject_card_total);
            percent = itemView.findViewById(R.id.subject_card_percent);
            leaves = itemView.findViewById(R.id.subject_card_leaves);
            date=itemView.findViewById(R.id.subject_card_date);
            time=itemView.findViewById(R.id.subject_card_time);
            present=itemView.findViewById(R.id.sbj_crd_present);
            absent=itemView.findViewById(R.id.sbj_crd_absent);
            cancelled=itemView.findViewById(R.id.sbj_crd_cancelled);
        }
    }


}
