package com.theandroidprojects.idealtech.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.theandroidprojects.idealtech.R;

public class subCategoryViewHolder extends RecyclerView.ViewHolder {

    public View mView;

    public subCategoryViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setSubName(String name){
        TextView subCategoryName = (TextView) mView.findViewById(R.id.sub_cat_name);
        subCategoryName.setText(name);
    }

    public void setSubDate(String date){
        TextView subCategoryDate = (TextView) mView.findViewById(R.id.sub_cat_date);
        subCategoryDate.setText(date);
    }

    public void setSubQuestions(String questions){
        TextView subCategoryQuestions = (TextView) mView.findViewById(R.id.questions_no);
        subCategoryQuestions.setText(questions);
    }

    public void setSubDuration(String duration){
        TextView subCategoryduration = (TextView) mView.findViewById(R.id.duration_no);
        subCategoryduration.setText(duration);
    }
}
