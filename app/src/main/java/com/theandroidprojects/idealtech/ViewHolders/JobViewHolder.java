package com.theandroidprojects.idealtech.ViewHolders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.theandroidprojects.idealtech.R;

public class JobViewHolder extends RecyclerView.ViewHolder {

    public View mView;

    public Button ShareBtn = (Button) itemView.findViewById(R.id.Job_shareBtn);
    public Button ApplyNowBtn = (Button) itemView.findViewById(R.id.Job_applyNowBtn);
    public Button SaveBtn = (Button) itemView.findViewById(R.id.Job_saveBtn);

    public CardView JobCard = (CardView) itemView.findViewById(R.id.job_view_h_card);


    public JobViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }



    public void setJobTitle(String jobTitle){
        TextView Job_title = (TextView) mView.findViewById(R.id.Jobs_title);
        Job_title.setText(jobTitle);
    }

    public void setJobLocation(String location){
        TextView Job_location = (TextView) mView.findViewById(R.id.Job_location);
        Job_location.setText(location);
    }

    public void setJobExperience(String experience){
        TextView Job_exp = (TextView) mView.findViewById(R.id.Job_experience);
        Job_exp.setText(experience);
    }

    public void setJobPostedOn(String postedOn){
        TextView Job_title = (TextView) mView.findViewById(R.id.Job_postedOn);
        Job_title.setText(postedOn);
    }

}
