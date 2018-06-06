package com.theandroidprojects.idealtech.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.theandroidprojects.idealtech.R;

public class LogIn extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }


        // Making status bar transparent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.login);


        TextView signUP = findViewById(R.id.signup_txt_v_in_login);

        signUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // launch sign up activity

                Intent intent = new Intent();
                intent.setClass(LogIn.this, SignUp.class);

                LogIn.this.startActivity(intent);
                LogIn.this.finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });





    }

}
