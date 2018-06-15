package com.theandroidprojects.idealtech.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.theandroidprojects.idealtech.Activities.ProfileEditActivity;
import com.theandroidprojects.idealtech.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment  {

    public ProfileFragment() {
        // Required empty public constructor
    }

    public ArrayAdapter<String> genderAdapter;
    public String[] strGender;
    List<String> genderList;

    public ArrayAdapter<String> martialAdapter;
    public String[] strMartial;
    List<String> martialList;

    String name_recv;
    String phone_recv;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.profile, container, false);


//------------gender spinner start

/*        Spinner genderSpinner = (Spinner) view.findViewById(R.id.genderSpinner);

        strGender = new String[]{"Select your gender...", "Male", "Female"};

        genderList = new ArrayList<>(Arrays.asList(strGender));
        genderAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()).getApplicationContext(), android.R.layout.simple_spinner_item, genderList);

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genderSpinner.setAdapter(genderAdapter);

        genderSpinner.setOnItemSelectedListener(new SpinnerGenderSelectedListener());

//------------gender spinner end





//--------- martial spinner start
        Spinner martialSpinner = (Spinner) view.findViewById(R.id.martialSpinner);

        strMartial = new String[]{"Select your martial status...", "Married", "Unmarried"};

        martialList = new ArrayList<>(Arrays.asList(strMartial));
        martialAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()).getApplicationContext(),
                android.R.layout.simple_spinner_item, martialList);

        martialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        martialSpinner.setAdapter(martialAdapter);

        martialSpinner.setOnItemSelectedListener(new SpinnerMartialSelectedListener());
//------------martial spinner end


        EditText DOB = (EditText) view.findViewById(R.id.date);

        DOB.setRawInputType(InputType.TYPE_NULL);

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });


*/
        SharedPreferences prefs = this.getActivity().getSharedPreferences("USER_DTL", MODE_PRIVATE);
        name_recv = prefs.getString("NAME_FB", "No name defined");//"No name defined" is the default value.
        phone_recv = prefs.getString("PHONE_FB", "Not defined"); //0 is the default value.



        TextView nameTxtView = view.findViewById(R.id.username_in_profile);
        nameTxtView.setText(name_recv);

        Toast.makeText(getActivity(),"PF" +name_recv,Toast.LENGTH_LONG).show();


        LinearLayout layer = (LinearLayout) view.findViewById (R.id.ProfilePgLL);
        layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), ProfileEditActivity.class);
                startActivity(intent);
            }
        });



// Inflate the layout for this fragment
        return view;
    }



  /*  private class SpinnerGenderSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            Toast.makeText(parent.getContext(), "Item selected is " + strGender[position], Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(parent.getContext(), "No Item selected", Toast.LENGTH_SHORT).show();
        }
    }


    private class SpinnerMartialSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int position, long id) {
            Toast.makeText(parent.getContext(), "Item selected is " + strMartial[position], Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            Toast.makeText(parent.getContext(), "No Item selected", Toast.LENGTH_SHORT).show();
        }
    }

*/



}
