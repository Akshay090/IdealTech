package com.theandroidprojects.idealtech.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.google.firebase.auth.FirebaseAuth;
import com.theandroidprojects.idealtech.R;

public class Splash_Screen extends Activity {
    private static final int SPLASH_DISPLAY_TIME = 3000; // splash screen delay time

    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent intent = new Intent();
                intent.setClass(Splash_Screen.this, WelcomeActivity.class);

                Splash_Screen.this.startActivity(intent);
                Splash_Screen.this.finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);


            }
        }, SPLASH_DISPLAY_TIME);
    }
}
