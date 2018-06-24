package com.theandroidprojects.idealtech.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.theandroidprojects.idealtech.Common.Common;
import com.theandroidprojects.idealtech.Fragment.MapFragment;
import com.theandroidprojects.idealtech.R;
import com.theandroidprojects.idealtech.ViewHolders.subCategoryViewHolder;
import com.theandroidprojects.idealtech.model.subCategory;

public class to_debug extends AppCompatActivity {


    public to_debug() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_debug);


        MapFragment mapFragment = new MapFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.to_debug_frame, mapFragment);
            ft.commit();


    }

}