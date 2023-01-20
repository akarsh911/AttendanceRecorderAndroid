package com.logictstics.attendancerecorder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class subject_adapter_home_page  extends RecyclerView.Adapter<subject_adapter_home_page.ViewHolder>
{
    private final ArrayList<subject_handler> subject_cardsArrayList;
    private final Context context;private boolean b=true;
    // Constructor
    public subject_adapter_home_page(Context context, ArrayList<subject_handler> subject_cardsArrayList) {
        this.context = context;
        this.subject_cardsArrayList = subject_cardsArrayList;
    }

    @NonNull
    @Override
    public subject_adapter_home_page.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subjects_cards_home_page, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull subject_adapter_home_page.ViewHolder holder, int position) {
        subject_handler subj=subject_cardsArrayList.get(position);
        holder.subj_name.setText(subj.getName());
        holder.subj_code.setText(subj.getCode());
        holder.percent.setText(Integer.toString(subj.getAttendance())+"%");
        holder.subj_till_now.setText(Integer.toString(subj.getTotal_now()));
        holder.subj_class_left.setText(Integer.toString(subj.getClasses_left()));
        holder.leaves.setText(Integer.toString(subj.getLeaves_available()));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b)
                {
                    holder.drop.setVisibility(View.VISIBLE);
                    b=false;
                }
                else
                {
                    holder.drop.setVisibility(View.GONE);
                    b=true;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return subject_cardsArrayList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView subj_name;
        private final TextView subj_code;
        private final TextView subj_till_now;
        private final TextView subj_class_left;
        private final TextView percent;
        private final TextView leaves;
        private final LinearLayout layout;
        private final LinearLayout drop;
        private final Button present;
        private final Button absent;
        private final Button cancelled;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subj_name= itemView.findViewById(R.id.subject_card_subject_name);
            subj_code = itemView.findViewById(R.id.subject_card_subject_code);
            percent = itemView.findViewById(R.id.subject_card_subject_attendance);
            leaves = itemView.findViewById(R.id.subject_card_leaves);
            subj_till_now=itemView.findViewById(R.id.subject_card_classes_total);
            subj_class_left=itemView.findViewById(R.id.subject_card_classes_remain);
            layout=itemView.findViewById(R.id.subject_card_subject_details);
            drop=itemView.findViewById(R.id.subject_card_drop);
            present=itemView.findViewById(R.id.sbj_crd_present);
            absent=itemView.findViewById(R.id.sbj_crd_absent);
            cancelled=itemView.findViewById(R.id.sbj_crd_cancelled);
        }
    }
}
