package com.theandroidprojects.idealtech.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theandroidprojects.idealtech.R;


public class studyFragment extends Fragment {

        public studyFragment() {
            // Required empty public constructor
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.study_fragment, container, false);
        }
    }
