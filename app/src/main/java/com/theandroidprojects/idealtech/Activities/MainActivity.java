package com.theandroidprojects.idealtech.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


import com.theandroidprojects.idealtech.Adapters.ViewPagerAdapter;

import com.theandroidprojects.idealtech.Fragment.homeFragment;
import com.theandroidprojects.idealtech.Fragment.notificationFragment;
import com.theandroidprojects.idealtech.Fragment.profileFragment;
import com.theandroidprojects.idealtech.Fragment.studyFragment;

import com.theandroidprojects.idealtech.Helpers.BottomNavigationViewHelper;
import com.theandroidprojects.idealtech.R;

public class MainActivity extends AppCompatActivity {


    homeFragment homeFragment;
    studyFragment studyFragment;
    notificationFragment notificationFragment;
    profileFragment profileFragment;

    MenuItem prevMenuItem;
    //Initializing viewPager



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     /*   String KeyValue = "Yea Btn Pressed";
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("Sign Up btn") == KeyValue){
            viewPager.setCurrentItem(3);
        }
*/


   //getting the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);

        //setting the title
        toolbar.setTitle("ToolBar");

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);



        //Initializing the bottomNavigationView and disabling shifting
        final BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        final  ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        String KeyValue = "Yea Btn Pressed";
        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("Sign Up btn") == KeyValue) {
            viewPager.setCurrentItem(3);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_study:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.navigation_notifications:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.navigation_profile:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

       /*  //Disable ViewPager Swipe
       viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
        */

        setupViewPager(viewPager);
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment=new homeFragment();
        notificationFragment=new notificationFragment();
        profileFragment=new profileFragment();
        studyFragment=new studyFragment();

        adapter.addFragment(homeFragment);
        adapter.addFragment(studyFragment);
        adapter.addFragment(notificationFragment);
        adapter.addFragment(profileFragment);

        viewPager.setAdapter(adapter);
    }
}