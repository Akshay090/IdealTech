package com.theandroidprojects.idealtech.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.theandroidprojects.idealtech.R;

import java.util.HashMap;

public class LoginRegister extends Activity {


    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    //ProgressDialog
    private ProgressDialog ProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginRegister.this, MainActivity.class));
            finish();
        }


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

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();



        setContentView(R.layout.main_login_register);


        Button login_btn = findViewById(R.id.signin_btn);
        Button register_btn = findViewById(R.id.register_btn);

        //adding haptic feedback
        login_btn.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        register_btn.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);

    }


    public void SignInButtonClicked(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.login_layout, null);
        final EditText email = alertLayout.findViewById(R.id.login_email);
        final EditText password = alertLayout.findViewById(R.id.login_password);




        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("SIGN IN");
        alert.setMessage("PLEASE USE EMAIL TO SIGN IN");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.setPositiveButton("SIGN IN", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String emailData = email.getText().toString().trim();
                String passData = password.getText().toString().trim();

                //Toast.makeText(getBaseContext(), "email: " + emailData + " pass: " + passData, Toast.LENGTH_SHORT).show();

                if (TextUtils.isEmpty(emailData)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passData)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }


                LoginFirebaseAndProgressBar(emailData, passData);


            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }




    public void RegisterBtnClicked(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.register_layout, null);

        final EditText email = alertLayout.findViewById(R.id.register_email);
        final EditText password = alertLayout.findViewById(R.id.register_password);
        final EditText name = alertLayout.findViewById(R.id.register_name);
        final EditText phone = alertLayout.findViewById(R.id.register_phone);

        //ProgressDialog
        final ProgressDialog mRegProgress;


        AlertDialog.Builder alert = new AlertDialog.Builder(LoginRegister.this);
        alert.setTitle("REGISTER");
        alert.setMessage("PLEASE USE EMAIL TO REGISTER");
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String emailData = email.getText().toString().trim();
                String passData = password.getText().toString().trim();
                String nameData = name.getText().toString().trim();
                String phoneData = phone.getText().toString().trim();

               // Toast.makeText(getBaseContext(), "email: " + emailData
                //                + " pass: " + passData + " name_FB: " + nameData + " phone: " + phoneData
                //        , Toast.LENGTH_SHORT).show();


                if (TextUtils.isEmpty(emailData)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passData)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passData.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(nameData)) {
                    Toast.makeText(getApplicationContext(), "Enter name_FB!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phoneData)) {
                    Toast.makeText(getApplicationContext(), "Enter phone no!", Toast.LENGTH_SHORT).show();
                    return;
                }



                RegisterFirebaseAndProgressBar(emailData,passData,nameData,phoneData);

                AddToSharedPreference(emailData,nameData,phoneData);


                //Intent myIntent = new Intent(LoginRegister.this, MainActivity.class);
               // myIntent.putExtra("key", value); //Optional parameters
               // LoginRegister.this.startActivity(myIntent);

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }




    public void ForgotPasswordClick(View view) {
        LayoutInflater inflater = getLayoutInflater();
    View alertLayout = inflater.inflate(R.layout.forgot_password_layout, null);

    final EditText email = alertLayout.findViewById(R.id.forgot_email);


    AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("RESET PASSWORD");
        alert.setMessage("PLEASE USE EMAIL TO RESET PASSWORD");
    // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
    // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    });

        alert.setPositiveButton("RESET", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            String emailData = email.getText().toString().trim();


            //Toast.makeText(getBaseContext(), "email: " + emailData, Toast.LENGTH_SHORT).show();

            if (TextUtils.isEmpty(emailData)) {
                Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                return;
            }

            forgotFirebaseAndProgressBar(emailData);

        }
    });
    AlertDialog dialog = alert.create();
        dialog.show();
}




    private void RegisterFirebaseAndProgressBar(String emailData, String passData, String nameData, String phoneData) {

        ProgressDialog = new ProgressDialog(LoginRegister.this);

        // Firebase Auth

        mAuth = FirebaseAuth.getInstance();


        ProgressDialog.setTitle("Registering User");
        ProgressDialog.setMessage("Please wait while we create your account !");
        ProgressDialog.setCanceledOnTouchOutside(false);
        ProgressDialog.show();

        register_user(emailData, passData, nameData, phoneData);







    }

    private void register_user(String emailData,
                               String passData, final String nameData, final String phoneData)  {

        mAuth.createUserWithEmailAndPassword(emailData, passData).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){


                    FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                    assert current_user != null;
                    String uid = current_user.getUid();

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    //String device_token = FirebaseInstanceId.getInstance().getToken();

                    HashMap<String, Object> userMap = new HashMap<>();
                    userMap.put("name", nameData);
                    userMap.put("phone", phoneData);


                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){

                                ProgressDialog.dismiss();

                                Intent mainIntent = new Intent(LoginRegister.this, MainActivity.class);
                               // mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainIntent);
                                finish();

                            }

                        }
                    });


                } else {

                    ProgressDialog.hide();
                    Toast.makeText(LoginRegister.this, "Cannot Sign in. Please check the form and try again.",
                            Toast.LENGTH_LONG).show();

                }

            }
        });

    }


    private void LoginFirebaseAndProgressBar(String emailData, String passData) {

        ProgressDialog = new ProgressDialog(this);

        // Firebase Auth

        mAuth = FirebaseAuth.getInstance();


        ProgressDialog.setTitle("Logging In");
        ProgressDialog.setMessage("Please wait while we check your credentials.");
        ProgressDialog.setCanceledOnTouchOutside(false);
        ProgressDialog.show();

        loginUser(emailData, passData);

    }

    private void loginUser(String emailData, String passData) {


        mAuth.signInWithEmailAndPassword(emailData, passData).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    ProgressDialog.dismiss();

                            Intent mainIntent = new Intent(LoginRegister.this, MainActivity.class);
                           // mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainIntent);
                            finish();


                } else {

                    ProgressDialog.hide();

                    String task_result = task.getException().getMessage().toString();

                    Toast.makeText(LoginRegister.this, "Error : " + task_result, Toast.LENGTH_LONG).show();

                }


            }
        });


    }

    private void forgotFirebaseAndProgressBar(String emailData) {

        ProgressDialog = new ProgressDialog(this);

        // Firebase Auth

        mAuth = FirebaseAuth.getInstance();


        ProgressDialog.setTitle("Please check mail");
        ProgressDialog.setMessage("We have sent you instructions to reset your password!");
        ProgressDialog.setCanceledOnTouchOutside(false);
        ProgressDialog.show();

        forgot_user(emailData);

    }

    private void forgot_user(String emailData) {

        mAuth.sendPasswordResetEmail(emailData)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            ProgressDialog.dismiss();


                        } else {
                            Toast.makeText(LoginRegister.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            ProgressDialog.dismiss();
                             }


                    }
                });
    }


    private void AddToSharedPreference(String emailData, String nameData, String phoneData) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Email",emailData);
        editor.putString("Name",nameData);
        editor.putString("Phone",phoneData);
        editor.apply();
    }

}




