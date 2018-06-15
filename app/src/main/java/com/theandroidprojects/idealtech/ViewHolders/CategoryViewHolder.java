package com.theandroidprojects.idealtech.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.theandroidprojects.idealtech.R;


public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public View mView;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
    }

    public void setName(String name){
        TextView categoryName = (TextView) mView.findViewById(R.id.categoryName);
        categoryName.setText(name);
    }

    public void setImage(Context context, String image){
        ImageView categoryImage = (ImageView) mView.findViewById(R.id.categoryImage);
        //Picasso.with(context).load(image).into(categoryImage);
        Picasso.get().load(image).into(categoryImage);
    }

}
