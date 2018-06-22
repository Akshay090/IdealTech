package com.theandroidprojects.idealtech.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.theandroidprojects.idealtech.R;


public class mcqViewHolder extends RecyclerView.ViewHolder {
    public View mView;


    public LinearLayout MCQ_opt_A = (LinearLayout)itemView.findViewById(R.id.mcq_opt_1_ll);
    public LinearLayout MCQ_opt_B = (LinearLayout)itemView.findViewById(R.id.mcq_opt_2_ll);
    public LinearLayout MCQ_opt_C = (LinearLayout)itemView.findViewById(R.id.mcq_opt_3_ll);
    public LinearLayout MCQ_opt_D = (LinearLayout)itemView.findViewById(R.id.mcq_opt_4_ll);

    public mcqViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setQuestion(String question){
        TextView mcq_question = (TextView) mView.findViewById(R.id.mcq_question);
        mcq_question.setText(question);
    }

    public void setA(String a){
        TextView mcq_optionA = (TextView) mView.findViewById(R.id.mcq_opt_1_txt);
        mcq_optionA.setText(a);
    }

    public void setB(String b){
        TextView mcq_optionB = (TextView) mView.findViewById(R.id.mcq_opt_2_txt);
        mcq_optionB.setText(b);
    }

    public void setC(String c){
        TextView mcq_optionC = (TextView) mView.findViewById(R.id.mcq_opt_3_txt);
        mcq_optionC.setText(c);
    }

    public void setD(String d){
        TextView mcq_optionD = (TextView) mView.findViewById(R.id.mcq_opt_4_txt);
        mcq_optionD.setText(d);
    }
}
