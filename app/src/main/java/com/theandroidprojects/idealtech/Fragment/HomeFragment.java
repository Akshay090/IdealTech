package com.theandroidprojects.idealtech.Fragment;

import android.os.Build;
import android.support.annotation.NonNull;

import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.theandroidprojects.idealtech.Common.Common;
import com.theandroidprojects.idealtech.R;

import com.theandroidprojects.idealtech.ViewHolders.JobViewHolder;

import com.theandroidprojects.idealtech.model.Job;

public class HomeFragment extends Fragment {

    // private SwipeRefreshLayout refreshLayout;
    private DatabaseReference JobReference;
    private RecyclerView JobRecyclerView;
    private FirebaseRecyclerAdapter<Job, JobViewHolder> JobAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.home_fragment, container, false);

        //INITIALIZE FB
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        JobReference = database.getReference(Common.Job_tbl);  //Common.Job_tbl = "Job"

        //INITIALIZE RV
        JobRecyclerView = (RecyclerView) rootView.findViewById(R.id.JobRecyclerView);
        //categoryRecyclerView.setHasFixedSize(true);

        JobRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        JobRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
//        above commented line adds a line below every field

        loadJob();



        return rootView;
    }

    private void loadJob() {


        FirebaseRecyclerOptions<Job> options = new FirebaseRecyclerOptions.Builder<Job>()
                .setQuery(JobReference, Job.class)
                .build();
        JobAdapter = new FirebaseRecyclerAdapter<Job, JobViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull JobViewHolder holder, final int position, @NonNull final Job model) {

                //Toast.makeText(getActivity(),model.getTITLE(),Toast.LENGTH_SHORT).show();

                holder.setJobTitle(model.getTITLE());
                holder.setJobExperience(model.getEXPERIENCE());
                holder.setJobLocation(model.getLOCATION());
                holder.setJobPostedOn(model.getPOSTED_ON());

                holder.ShareBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(),"Shared" + model.getTITLE(),Toast.LENGTH_SHORT).show();
                    }
                });

                holder.ApplyNowBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(),"Applied for"+model.getTITLE(),Toast.LENGTH_SHORT).show();
                    }
                });

                holder.SaveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(),"Saved"+model.getTITLE(),Toast.LENGTH_SHORT).show();
                    }
                });


                holder.JobCard.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onFocusChange(View view, boolean b) {

                        view.setElevation(10);
                    }
                });


//                holder.mView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        final String url = categoryAdapter.getRef(position).getKey();
//
//                        Toast.makeText(getActivity(), ""+url, Toast.LENGTH_LONG).show();
//
//                        Bundle bundle = new Bundle();
//                        bundle.putString("CATEGORY_ID",url);
//
//                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                        SubCategoryFragment subCategoryFragment = new SubCategoryFragment();
//                        subCategoryFragment.setArguments(bundle);
//
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_mainStudy, subCategoryFragment)
//                                .addToBackStack(null).commit();
//
//                       /*
//                        Intent intent = new Intent(getActivity(), to_debug.class);
//                        intent.putExtra("CATEGORY_ID",url);
//                        startActivity(intent);
//                        */
//                    }
//
//                });

            }

            @NonNull
            @Override
            public JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_view_h_layout, parent, false);
                return new JobViewHolder(view);
            }

        };

        JobAdapter.notifyDataSetChanged();     //Refresh data if data have changed
        JobAdapter.startListening();
        JobRecyclerView.setAdapter(JobAdapter);

        // refreshLayout.setRefreshing(false);

    }

    @Override
    public void onStop() {
        super.onStop();
        JobAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (JobAdapter!=null)
            JobAdapter.startListening();
    }

}
