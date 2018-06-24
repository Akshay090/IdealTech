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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theandroidprojects.idealtech.Fragment.DatePickerFragment;

import com.theandroidprojects.idealtech.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;




public class ProfileEditActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;

    String NameData;
    String GenderData;
    String MartialStatusData;
    String WhatsAppNoData;
    String PermanentAddressData;
    String CurrentAddressData;
    String DOBData;
    String LanguagesData;

    String CityData;
    String StateData;
    String CurrentCityData;
    String CurrentStateData;

    String name_recv;
    String phone_recv;
    String dob_recv;

    public ArrayAdapter<String> genderAdapter;
    public String[] strGender;
    List<String> genderList;

    public ArrayAdapter<String> martialAdapter;
    public String[] strMartial;
    List<String> martialList;

    EditText addressLine1;
    EditText addressLine2;
    EditText addressLine3;
    EditText addressLine4;
    EditText addressLine5;


    EditText addressCurrentLine1;
    EditText addressCurrentLine2;
    EditText addressCurrentLine3;
    EditText addressCurrentLine4;
    EditText addressCurrentLine5;

    EditText DOB;

    EditText LanguagesKnown;

    private DatabaseReference mDatabase;


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


                View IncludedMainCards = findViewById(R.id.included_main_cards);
                View PersonalPopForm = findViewById(R.id.included_personal_form);

                if (IncludedMainCards.getVisibility() == View.GONE && PersonalPopForm.getVisibility() == View.VISIBLE) {

                    IncludedMainCards.setVisibility(View.VISIBLE);
                    // Setting Visibility of Layouts
                    PersonalPopForm.setVisibility(View.GONE);

                } else {
                    finish();
                }


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




        CheckIfUserPersonalDetailsExistInFB();


    }


    private void showPersonalForm() {

        View IncludedMainCards = findViewById(R.id.included_main_cards);
        IncludedMainCards.setVisibility(View.GONE);
// Setting Visibility of Layouts
        View PersonalPopForm = findViewById(R.id.included_personal_form);
        PersonalPopForm.setVisibility(View.VISIBLE);


        Spinner genderSpinner = findViewById(R.id.genderSpinner);
        Spinner martialSpinner = findViewById(R.id.martialSpinner);

        dealWithGenderSpinner(genderSpinner);

        dealWithMartialSpinner(martialSpinner);


        final CheckBox sameAddressChkBX = findViewById(R.id.chk_bx);

        sameAddressChkBX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sameAddressChkBX.isChecked()) {
                    //Perform action when you touch on checkbox and it change to selected state

                    EditText addressLine1 = findViewById(R.id.address_text1);
                    EditText addressLine2 = findViewById(R.id.address_text2);
                    EditText addressLine3 = findViewById(R.id.address_text3);
                    EditText addressLine4 = findViewById(R.id.address_text4);
                    EditText addressLine5 = findViewById(R.id.address_text5);

                    EditText addressCurrentLine1 = findViewById(R.id.address_current_text1);
                    EditText addressCurrentLine2 = findViewById(R.id.address_current_text2);
                    EditText addressCurrentLine3 = findViewById(R.id.address_current_text3);
                    EditText addressCurrentLine4 = findViewById(R.id.address_current_text4);
                    EditText addressCurrentLine5 = findViewById(R.id.address_current_text5);


                    final String AddressL1 = addressLine1.getText().toString();
                    final String AddressL2 = addressLine2.getText().toString();
                    final String AddressL3 = addressLine3.getText().toString();
                    final String AddressL4 = addressLine4.getText().toString();
                    final String AddressL5 = addressLine5.getText().toString();

                    Toast.makeText(ProfileEditActivity.this, " " + AddressL1, Toast.LENGTH_SHORT).show();

                    addressCurrentLine1.setText(AddressL1);
                    addressCurrentLine2.setText(AddressL2);
                    addressCurrentLine3.setText(AddressL3);
                    addressCurrentLine4.setText(AddressL4);
                    addressCurrentLine5.setText(AddressL5);
                } else {
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
//                SharedPreferences.Editor editor = ProfileEditActivity.this.getSharedPreferences("USER_DTL", MODE_PRIVATE).edit();
//                editor.putString("DOB",DOB.getText().toString());
//                editor.apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {
//                SharedPreferences prefs = getSharedPreferences("USER_DTL", MODE_PRIVATE);
//                dob_recv = prefs.getString("DOB", "Date NA");
//                Toast.makeText(ProfileEditActivity.this,""+dob_recv,Toast.LENGTH_SHORT).show();

                DOB.setError(null);
            }
        });


//        SharedPreferences.Editor editor = ProfileEditActivity.this.getSharedPreferences("USER_DTL", MODE_PRIVATE).edit();
//        editor.putString("LANGUAGES",LanguagesKnown.getText().toString());
//        editor.apply();


        Button ProfileDetailsSubmitBtn = (Button) findViewById(R.id.pofileDtlSubmitBtn);

        ProfileDetailsSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addAllDataAndAddToFB();
            }
        });


    }

    private void dealWithGenderSpinner(Spinner genderSpinner) {


        strGender = new String[]{"Select your gender...", "Male", "Female"};

        genderList = new ArrayList<>(Arrays.asList(strGender));
        genderAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, genderList) {

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
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
                if (position > 0) {
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();

                    GenderData = selectedItemText;

//                    SharedPreferences.Editor editor = ProfileEditActivity.this.getSharedPreferences("USER_DTL", MODE_PRIVATE).edit();
//                    editor.putString("GENDER",selectedItemText);
//                    editor.apply();
                }
                if (position == 1) {
                    ImageView GenderIMG = findViewById(R.id.profile_image);
                    GenderIMG.setImageResource(R.drawable.man);
                }
                if (position == 2) {
                    ImageView GenderIMG = findViewById(R.id.profile_image);
                    GenderIMG.setImageResource(R.drawable.woman);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Toast.makeText(ProfileEditActivity.this, "Select Your Gender", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void dealWithMartialSpinner(Spinner martialSpinner) {


        strMartial = new String[]{"Select your martial status...", "Married", "Unmarried"};

        martialList = new ArrayList<>(Arrays.asList(strMartial));
        martialAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, martialList) {

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
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
                if (position > 0) {
                    // Notify the selected item text
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                            .show();

//                    SharedPreferences.Editor editor = ProfileEditActivity.this.getSharedPreferences("USER_DTL", MODE_PRIVATE).edit();
//                    editor.putString("MARTIAL_STATUS",selectedItemText);
//                    editor.apply();

                    MartialStatusData = selectedItemText;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                Toast.makeText(ProfileEditActivity.this, "Select Your Martial Status", Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void addAllDataAndAddToFB() {

        EditText Name = findViewById(R.id.pd_name);
        EditText WhatsAppNo = findViewById(R.id.pd_whatsApp);

        EditText addressLine1 = findViewById(R.id.address_text1);
        EditText addressLine2 = findViewById(R.id.address_text2);
        EditText addressLine3 = findViewById(R.id.address_text3);
        EditText addressLine4 = findViewById(R.id.address_text4);
        EditText addressLine5 = findViewById(R.id.address_text5);


        EditText addressCurrentLine1 = findViewById(R.id.address_current_text1);
        EditText addressCurrentLine2 = findViewById(R.id.address_current_text2);
        EditText addressCurrentLine3 = findViewById(R.id.address_current_text3);
        EditText addressCurrentLine4 = findViewById(R.id.address_current_text4);
        EditText addressCurrentLine5 = findViewById(R.id.address_current_text5);

        EditText DOB = findViewById(R.id.date);

        EditText LanguagesKnown = findViewById(R.id.languages_known_edTxt);

        if (Name.getText().toString().trim().equals("")) {
            Name.setError("Please enter Name");
            return;
        }
        if (WhatsAppNo.getText().toString().equals("")
                || WhatsAppNo.getText().toString().length() < 10) {
            WhatsAppNo.setError("Please enter WhatsApp no");
            return;
        }
        if (GenderData.isEmpty()) {
            Toast.makeText(ProfileEditActivity.this, "Select Gender", Toast.LENGTH_SHORT).show();
            return;
        }
        if (MartialStatusData.isEmpty()) {
            Toast.makeText(ProfileEditActivity.this, "Select Martial Status", Toast.LENGTH_SHORT).show();
            return;
        }


        if (addressLine1.getText().toString().equals("")) {
            addressLine1.setError("Please enter details");
            return;
        }
        if (addressLine2.getText().toString().equals("")) {
            addressLine2.setError("Please enter details");
            return;
        }
        if (addressLine3.getText().toString().equals("")) {
            addressLine3.setError("Please enter details");
            return;
        }
        if (addressLine4.getText().toString().equals("")) {
            addressLine4.setError("Please enter details");
            return;
        }
        if (addressLine5.getText().toString().equals("")) {
            addressLine5.setError("Please enter details");
            return;
        }


        if (addressCurrentLine1.getText().toString().equals("")) {
            addressCurrentLine1.setError("Please enter details");
            return;
        }
        if (addressCurrentLine2.getText().toString().equals("")) {
            addressCurrentLine2.setError("Please enter details");
            return;
        }
        if (addressCurrentLine3.getText().toString().equals("")) {
            addressCurrentLine3.setError("Please enter details");
            return;
        }
        if (addressCurrentLine4.getText().toString().equals("")) {
            addressCurrentLine4.setError("Please enter details");
            return;
        }
        if (addressCurrentLine5.getText().toString().equals("")) {
            addressCurrentLine5.setError("Please enter details");
            return;
        }

        if (DOB.getText().toString().equals("")) {
            DOB.setError("Please enter birth date");
            return;
        }
        if (LanguagesKnown.getText().toString().equals("")) {
            LanguagesKnown.setError("Please enter details");
            return;
        }


        Toast.makeText(ProfileEditActivity.this, "Btn Clicked", Toast.LENGTH_SHORT).show();


        NameData = Name.getText().toString().trim();
        WhatsAppNoData = WhatsAppNo.getText().toString().trim();

        PermanentAddressData = (addressLine1.getText().toString().trim()
                + "," + addressLine2.getText().toString().trim() + ","
                + addressLine3.getText().toString().trim()
                + "," + addressLine4.getText().toString().trim()
                + "," + addressLine5.getText().toString().trim());

        CurrentAddressData = (addressCurrentLine1.getText().toString().trim()
                + "," + addressCurrentLine2.getText().toString().trim() + ","
                + addressCurrentLine3.getText().toString().trim()
                + "," + addressCurrentLine4.getText().toString().trim()
                + "," + addressCurrentLine5.getText().toString().trim());


        CityData = addressLine4.getText().toString().trim();
        StateData = addressLine5.getText().toString().trim();

        CurrentCityData = addressCurrentLine4.getText().toString().trim();
        CurrentStateData = addressCurrentLine5.getText().toString().trim();

        DOBData = DOB.getText().toString().trim();
        LanguagesData = LanguagesKnown.getText().toString().trim();


        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        assert current_user != null;
        String uid = current_user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("name", NameData);
        userMap.put("WhatsApp no", WhatsAppNoData);

        userMap.put("Gender", GenderData);
        userMap.put("Martial Status", MartialStatusData);

        userMap.put("Permanent Address", PermanentAddressData);
        userMap.put("Current Address", CurrentAddressData);

        userMap.put("City From", CityData);
        userMap.put("State From", StateData);

        userMap.put("City Current", CurrentCityData);
        userMap.put("State Current", CurrentStateData);


        userMap.put("DOB", DOBData);
        userMap.put("Languages Known", LanguagesData);


        mDatabase.updateChildren(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(ProfileEditActivity.this, "Details Added", Toast.LENGTH_SHORT).show();

                    View IncludedMainCards = findViewById(R.id.included_main_cards);
                    IncludedMainCards.setVisibility(View.VISIBLE);
                    // Setting Visibility of Layouts
                    View PersonalPopForm = findViewById(R.id.included_personal_form);
                    PersonalPopForm.setVisibility(View.GONE);


                    View PersonalDInitial = findViewById(R.id.card_view_personal_d);
                    PersonalDInitial.setVisibility(View.GONE);
                    View PersonalDFilled = findViewById(R.id.personal_d_filled_included);
                    PersonalDFilled.setVisibility(View.VISIBLE);


                    TextView setDate = findViewById(R.id.cal_data);
                    setDate.setText(DOBData);

                    TextView setGender = findViewById(R.id.gender_data);
                    setGender.setText(GenderData);

                    TextView setFromCityDate = findViewById(R.id.from_city_state);
                    setFromCityDate.setText(CityData + " , " + StateData);

                    TextView setLivingCityState = findViewById(R.id.Living_city_state);
                    setLivingCityState.setText(CurrentCityData + " , " + CurrentStateData);

                    TextView setWhatsAppNo = findViewById(R.id.WhatsApp_no);
                    setWhatsAppNo.setText(WhatsAppNoData);

                    TextView setLanguages = findViewById(R.id.Languages_set);
                    setLanguages.setText(LanguagesData);

                } else {
                    Toast.makeText(ProfileEditActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();

                }
            }

        });


    }


    private void CheckIfUserPersonalDetailsExistInFB() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference user_dtl_ref = ref.child("Users").child(user.getUid());
        user_dtl_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild("WhatsApp no")) {
                    // run some code

                    View PersonalDInitial = findViewById(R.id.card_view_personal_d);
                    PersonalDInitial.setVisibility(View.GONE);
                    View PersonalDFilled = findViewById(R.id.personal_d_filled_included);
                    PersonalDFilled.setVisibility(View.VISIBLE);


                    String WhatsAppFB = dataSnapshot.child("WhatsApp no").getValue().toString();
                    String DoBFB = dataSnapshot.child("DOB").getValue().toString();
                    String GenderFB = dataSnapshot.child("Gender").getValue().toString();
                    String FromCityFB = dataSnapshot.child("City From").getValue().toString();
                    String FromStateFB = dataSnapshot.child("State From").getValue().toString();
                    String LivingCityFB = dataSnapshot.child("City Current").getValue().toString();
                    String LivingStateFB = dataSnapshot.child("State Current").getValue().toString();
                    String LanguageFB = dataSnapshot.child("Languages Known").getValue().toString();




                    TextView setDate = findViewById(R.id.cal_data);
                    setDate.setText(DoBFB);

                    TextView setGender = findViewById(R.id.gender_data);
                    setGender.setText(GenderFB);

                    TextView setFromCityDate = findViewById(R.id.from_city_state);
                    setFromCityDate.setText(FromCityFB + " , " + FromStateFB);

                    TextView setLivingCityState = findViewById(R.id.Living_city_state);
                    setLivingCityState.setText(LivingCityFB + " , " + LivingStateFB);

                    TextView setWhatsAppNo = findViewById(R.id.WhatsApp_no);
                    setWhatsAppNo.setText(WhatsAppFB);

                    TextView setLanguages = findViewById(R.id.Languages_set);
                    setLanguages.setText(LanguageFB);


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


        }



    @Override
    public void onBackPressed ()
    {

        View IncludedMainCards = findViewById(R.id.included_main_cards);
        View PersonalPopForm = findViewById(R.id.included_personal_form);

        if (IncludedMainCards.getVisibility() == View.GONE && PersonalPopForm.getVisibility() == View.VISIBLE) {

            IncludedMainCards.setVisibility(View.VISIBLE);
            // Setting Visibility of Layouts
            PersonalPopForm.setVisibility(View.GONE);
        } else {
            super.onBackPressed();//This will finish the activity
        }

    }

}
