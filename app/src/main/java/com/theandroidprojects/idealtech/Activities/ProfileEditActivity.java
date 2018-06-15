package com.theandroidprojects.idealtech.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.theandroidprojects.idealtech.Fragment.DatePickerFragment;

import com.theandroidprojects.idealtech.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;




public class ProfileEditActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;

    String name_recv;
    String phone_recv;
    String dob_recv;

    public ArrayAdapter<String> genderAdapter;
    public String[] strGender;
    List<String> genderList;

    public ArrayAdapter<String> martialAdapter;
    public String[] strMartial;
    List<String> martialList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);


        SharedPreferences prefs = getSharedPreferences("USER_DTL", MODE_PRIVATE);
        name_recv = prefs.getString("NAME_FB", "No name defined");//"No name defined" is the default value.
        phone_recv = prefs.getString("PHONE_FB", "Not defined"); //0 is the default value.



        setContentView(R.layout.profile_edit);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.inflateMenu(R.menu.menu_main);

        toolbar.setTitle(name_recv);



        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(" ");
        collapsingToolbarLayout.setTitleEnabled(false);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorForToolBar));
        collapsingToolbarLayout.setStatusBarScrimColor(getResources().getColor(R.color.bg_screen2));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        TextView emailInEditText = findViewById(R.id.email_in_edit_profile);
        emailInEditText.setText(name_recv);

        TextView phoneInEditText = findViewById(R.id.phone_in_edit_profile);
        phoneInEditText.setText(phone_recv);


        View PersonalPopForm = findViewById(R.id.included_personal_form);
        PersonalPopForm.setVisibility(View.GONE);



        Button PersonalAddNowBtn = findViewById(R.id.personal_btn);
        PersonalAddNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showPersonalForm();
            }
        });



    }

    private void showPersonalForm() {

        View IncludedMainCards = findViewById(R.id.included_main_cards);
        IncludedMainCards.setVisibility(View.GONE);

        View PersonalPopForm = findViewById(R.id.included_personal_form);
        PersonalPopForm.setVisibility(View.VISIBLE);

        EditText Name = findViewById(R.id.pd_name);
        EditText WhatsAppNo = findViewById(R.id.pd_whatsApp);

        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        Spinner martialSpinner = findViewById(R.id.martialSpinner);

            dealWithGenderSpinner(genderSpinner);
            dealWithMartialSpinner(martialSpinner);


        final EditText addressLine1 = findViewById(R.id.address_text1);
        final EditText addressLine2 = findViewById(R.id.address_text2);
        final EditText addressLine3 = findViewById(R.id.address_text3);
        final EditText addressLine4 = findViewById(R.id.address_text4);
        final EditText addressLine5 = findViewById(R.id.address_text5);

        final CheckBox sameAddressChkBX = findViewById(R.id.chk_bx);

        sameAddressChkBX.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (sameAddressChkBX.isChecked())
                {
                    //Perform action when you touch on checkbox and it change to selected state

                    final String AddressL1 = addressLine1.getText().toString();
                    final String AddressL2 = addressLine2.getText().toString();
                    final String AddressL3 = addressLine3.getText().toString();
                    final String AddressL4 = addressLine4.getText().toString();
                    final String AddressL5 = addressLine5.getText().toString();

                    Toast.makeText(ProfileEditActivity.this," "+ AddressL1,Toast.LENGTH_SHORT).show();

                    EditText addressCurrentLine1 = findViewById(R.id.address_current_text1);
                    EditText addressCurrentLine2 = findViewById(R.id.address_current_text2);
                    EditText addressCurrentLine3 = findViewById(R.id.address_current_text3);
                    EditText addressCurrentLine4 = findViewById(R.id.address_current_text4);
                    EditText addressCurrentLine5 = findViewById(R.id.address_current_text5);

                    addressCurrentLine1.setText(AddressL1);
                    addressCurrentLine2.setText(AddressL2);
                    addressCurrentLine3.setText(AddressL3);
                    addressCurrentLine4.setText(AddressL4);
                    addressCurrentLine5.setText(AddressL5);
                }
                else
                {
                    //Perform action when you touch on checkbox and it change to unselected state

                    EditText addressCurrentLine1 = findViewById(R.id.address_current_text1);
                    EditText addressCurrentLine2 = findViewById(R.id.address_current_text2);
                    EditText addressCurrentLine3 = findViewById(R.id.address_current_text3);
                    EditText addressCurrentLine4 = findViewById(R.id.address_current_text4);
                    EditText addressCurrentLine5 = findViewById(R.id.address_current_text5);

                    addressCurrentLine1.setText("");
                    addressCurrentLine2.setText("");
                    addressCurrentLine3.setText("");
                    addressCurrentLine4.setText("");
                    addressCurrentLine5.setText("");
                }
            }
        });


        final EditText DOB = (EditText) findViewById(R.id.date);
        DOB.setRawInputType(InputType.TYPE_NULL);
        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(ProfileEditActivity.this.getSupportFragmentManager(), "datePicker");


            }
        });

        DOB.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferences.Editor editor = ProfileEditActivity.this.getSharedPreferences("USER_DTL", MODE_PRIVATE).edit();
                editor.putString("DOB",DOB.getText().toString());
                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                SharedPreferences prefs = getSharedPreferences("USER_DTL", MODE_PRIVATE);
                dob_recv = prefs.getString("DOB", "Date NA");
                Toast.makeText(ProfileEditActivity.this,""+dob_recv,Toast.LENGTH_SHORT).show();

            }
        });



        EditText LanguagesKnown = findViewById(R.id.languages_known_edTxt);
        SharedPreferences.Editor editor = ProfileEditActivity.this.getSharedPreferences("USER_DTL", MODE_PRIVATE).edit();
        editor.putString("LANGUAGES",LanguagesKnown.getText().toString());
        editor.apply();



    }

    private void dealWithGenderSpinner(Spinner genderSpinner) {




        strGender = new String[]{"Select your gender...", "Male", "Female"};

        genderList = new ArrayList<>(Arrays.asList(strGender));
        genderAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, genderList){

            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpinner.setAdapter(genderAdapter);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();

                    SharedPreferences.Editor editor = ProfileEditActivity.this.getSharedPreferences("USER_DTL", MODE_PRIVATE).edit();
                    editor.putString("GENDER",selectedItemText);
                    editor.apply();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void dealWithMartialSpinner(Spinner martialSpinner) {




        strMartial = new String[]{"Select your martial status...", "Married", "Unmarried"};

        martialList = new ArrayList<>(Arrays.asList(strMartial));
        martialAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, martialList){

            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        martialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        martialSpinner.setAdapter(martialAdapter);

        martialSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position > 0){
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();

                    SharedPreferences.Editor editor = ProfileEditActivity.this.getSharedPreferences("USER_DTL", MODE_PRIVATE).edit();
                    editor.putString("MARTIAL_STATUS",selectedItemText);
                    editor.apply();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
