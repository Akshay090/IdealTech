package com.theandroidprojects.idealtech.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.theandroidprojects.idealtech.R;

public class Contact_us extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us_activiity_scrolling);
        Toolbar toolbar1= (Toolbar)findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);



    }
}
