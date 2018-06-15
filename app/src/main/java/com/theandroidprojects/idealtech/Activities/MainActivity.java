package com.theandroidprojects.idealtech.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theandroidprojects.idealtech.Fragment.HomeFragment;
import com.theandroidprojects.idealtech.Fragment.NotificationFragment;
import com.theandroidprojects.idealtech.Fragment.ProfileFragment;
import com.theandroidprojects.idealtech.Fragment.StudyFragment;

import com.theandroidprojects.idealtech.Helpers.BottomNavigationViewHelper;
import com.theandroidprojects.idealtech.R;

public class MainActivity extends AppCompatActivity {

    Toolbar mainToolbar;

    private BottomNavigationView mainbottomNav;

    HomeFragment homeFragment;
    StudyFragment studyFragment;
    NotificationFragment notificationFragment;
    ProfileFragment profileFragment;

    DatabaseReference ref;
     String name_FB;
     String phone_FB;
     String email_FB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        // String name_FB = user.getDisplayName();
         email_FB = user.getEmail();

        String UID = user.getUid();
        //Toast.makeText(MainActivity.this,""+ email_FB + UID, Toast.LENGTH_LONG).show();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference user_dtl_ref = ref.child("Users").child(UID);
        user_dtl_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name_FB = dataSnapshot.child("name").getValue().toString();
                phone_FB = dataSnapshot.child("phone").getValue().toString();

                Toast.makeText(MainActivity.this,"MAc"+name_FB+phone_FB+email_FB,Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = getSharedPreferences("USER_DTL", MODE_PRIVATE).edit();
                editor.putString("EMAIL_FB",email_FB);
                editor.putString("NAME_FB", name_FB);
                editor.putString("PHONE_FB",phone_FB);
                editor.apply();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

/*
        DatabaseReference phone_ref = ref.child("Users").child(UID).child("phone");
        phone_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                phone_FB = dataSnapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
        //Toast.makeText(MainActivity.this,"2nd"+name_FB+phone_FB,Toast.LENGTH_SHORT).show();


        //Toast.makeText(MainActivity.this,"" +phone_FB, Toast.LENGTH_LONG).show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("EMAIL_FB",email_FB);
        editor.putString("NAME_FB", name_FB);
        editor.putString("PHONE_FB",phone_FB);
        editor.apply();



        mainToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("Title");

        mainbottomNav = findViewById(R.id.mainBottomNav);
        BottomNavigationViewHelper.disableShiftMode(mainbottomNav);

        // FRAGMENTS
        homeFragment = new HomeFragment();
        studyFragment = new StudyFragment();
        notificationFragment = new NotificationFragment();
        profileFragment = new ProfileFragment();

        initializeFragment();


        mainbottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                android.support.v4.app.Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);

                switch (item.getItemId()) {

                    case R.id.navigation_home:

                        replaceFragment(homeFragment, currentFragment);
                        return true;

                    case R.id.navigation_study:

                        replaceFragment(studyFragment, currentFragment);
                        return true;

                    case R.id.navigation_notifications:

                        replaceFragment(notificationFragment, currentFragment);
                        return true;

                    case R.id.navigation_profile:

                        replaceFragment(profileFragment, currentFragment);
                        return true;

                    default:
                        return false;


                }

            }
        });


    }


    private void initializeFragment(){

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.main_container, homeFragment);
        fragmentTransaction.add(R.id.main_container, studyFragment);
        fragmentTransaction.add(R.id.main_container, notificationFragment);
        fragmentTransaction.add(R.id.main_container, profileFragment);

        fragmentTransaction.hide(studyFragment);
        fragmentTransaction.hide(notificationFragment);
        fragmentTransaction.hide(profileFragment);

        fragmentTransaction.commit();

    }


    private void replaceFragment(android.support.v4.app.Fragment fragment, android.support.v4.app.Fragment currentFragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if(fragment == homeFragment){
            fragmentTransaction.hide(studyFragment);
            fragmentTransaction.hide(notificationFragment);
            fragmentTransaction.hide(profileFragment);
            }

        if(fragment == studyFragment){
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(notificationFragment);
            fragmentTransaction.hide(profileFragment);
            }

        if(fragment == notificationFragment){
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(studyFragment);
            fragmentTransaction.hide(profileFragment);
            }

        if(fragment == profileFragment){
            fragmentTransaction.hide(homeFragment);
            fragmentTransaction.hide(studyFragment);
            fragmentTransaction.hide(notificationFragment);
        }
        fragmentTransaction.show(fragment);

        //fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();

    }

}