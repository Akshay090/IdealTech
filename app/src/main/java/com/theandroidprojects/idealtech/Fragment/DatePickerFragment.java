package com.theandroidprojects.idealtech.Fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.theandroidprojects.idealtech.R;

import java.util.Calendar;

import static android.content.Context.MODE_PRIVATE;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user

      EditText DOB = (EditText) getActivity().findViewById(R.id.date);

        String year1 = String.valueOf(year);
        String month1 = String.valueOf(month + 1);

        String day1 = String.valueOf(day);

      //  NameAndDateTextView.setText(day1 + "/" + month1 + "/" + year1);

        DOB.setText(day1 + "/" + month1 + "/" + year1);
        String Date = (day1 + "/" + month1 + "/" + year1);



        }



}