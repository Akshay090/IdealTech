package com.theandroidprojects.idealtech.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

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
import com.google.firebase.database.Query;
import com.theandroidprojects.idealtech.Common.Common;
import com.theandroidprojects.idealtech.R;

import com.theandroidprojects.idealtech.ViewHolders.mcqViewHolder;

import com.theandroidprojects.idealtech.model.mcq;


public class MCQFragment extends Fragment {

    // private SwipeRefreshLayout refreshLayout;
    private DatabaseReference mcqReference;
    private RecyclerView mcqRecyclerView;
    private FirebaseRecyclerAdapter<mcq, mcqViewHolder> mcqAdapter;

    int Score = 0;

    public MCQFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.mcq_layout, container, false);

        //INITIALIZE FB
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mcqReference = database.getReference(Common.MCQ_tbl);  //Common.MCQ_tbl = "MCQ"

        //INITIALIZE RV
        mcqRecyclerView = rootView.findViewById(R.id.mcqRecyclerView);
        //categoryRecyclerView.setHasFixedSize(true);

        mcqRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        String subCategoryID = null;

        Bundle bundle = new Bundle();

      /*  if (bundle != null){
            categoryID = bundle.getString("CATEGORY_ID");
        } */

        assert getArguments() != null;
        subCategoryID = getArguments().getString("SUB_CATEGORY_ID");

        Toast.makeText(getActivity(),subCategoryID +"inMCQ-F",Toast.LENGTH_LONG).show();

        loadSubCategory(subCategoryID);


        return rootView;
    }


    private void loadSubCategory(String subCategoryID) {
        //create query
        Query searchByName = mcqReference.orderByChild("subCatID").equalTo(subCategoryID);

        FirebaseRecyclerOptions<mcq> options = new FirebaseRecyclerOptions.Builder<mcq>()
                .setQuery(searchByName, mcq.class)
                .build();


        mcqAdapter = new FirebaseRecyclerAdapter<mcq, mcqViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull final mcqViewHolder holder,
                                            final int position, @NonNull final mcq model) {
                holder.setQuestion(model.getQuestion());
                holder.setA(model.getA());
                holder.setB(model.getB());
                holder.setC(model.getC());
                holder.setD(model.getD());



                holder.MCQ_opt_A.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(holder.MCQ_opt_A.getBackground() != null){
                            holder.MCQ_opt_A.setBackground(null);
                            }else {
                            holder.MCQ_opt_A.setBackgroundResource(R.drawable.custom_mcq_clicked);

                            if(model.getA().equals(model.getAns())){
                                Score = Score + 1;


                                String Result = String.valueOf(Score);
                                Toast.makeText(getActivity(), Result,Toast.LENGTH_SHORT).show();
                            }

                            holder.MCQ_opt_B.setEnabled(false);
                        }


                    }
                });


                holder.MCQ_opt_B.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(holder.MCQ_opt_B.getBackground() != null){
                            holder.MCQ_opt_B.setBackground(null);
                        }else {
                            holder.MCQ_opt_B.setBackgroundResource(R.drawable.custom_mcq_clicked);

                            if(model.getB().equals(model.getAns())){
                                Score = Score + 1;

                                String Result = String.valueOf(Score);
                                Toast.makeText(getActivity(), Result,Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


                holder.MCQ_opt_C.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(holder.MCQ_opt_C.getBackground() != null){
                            holder.MCQ_opt_C.setBackground(null);
                        }else {
                            holder.MCQ_opt_C.setBackgroundResource(R.drawable.custom_mcq_clicked);

                            if(model.getC().equals(model.getAns())){
                                Score = Score + 1;


                                String Result = String.valueOf(Score);
                                Toast.makeText(getActivity(), Result,Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });

                holder.MCQ_opt_D.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(holder.MCQ_opt_D.getBackground() != null){
                            holder.MCQ_opt_D.setBackground(null);
                        }else {
                            holder.MCQ_opt_D.setBackgroundResource(R.drawable.custom_mcq_clicked);

                            if(model.getD().equals(model.getAns())){
                                Score = Score + 1;


                                String Result = String.valueOf(Score);
                                Toast.makeText(getActivity(), Result,Toast.LENGTH_SHORT).show();
                            }
                        }

                }
                });



                //Toast.makeText(getActivity(), "itsMname"+model.getSubName(), Toast.LENGTH_SHORT).show();
               /* holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = subCategoryAdapter.getRef(position).getKey();
                        Bundle bundle = new Bundle();
                        bundle.putString("categoryId",url);
                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        SubCategoryFragment SubCategoryFragment = new SubCategoryFragment();
                        SubCategoryFragment.setArguments(bundle);
                        activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.view_pager, SubCategoryFragment).addToBackStack(null).commit();
                    }

                });
                */


            }

            @NonNull
            @Override
            public mcqViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mcq_view_holder, parent, false);
                return new mcqViewHolder(view);
            }

        };

        mcqAdapter.notifyDataSetChanged();     //Refresh data if data have changed
        mcqAdapter.startListening();
        mcqRecyclerView.setAdapter(mcqAdapter);

        // refreshLayout.setRefreshing(false);


    }


    @Override
    public void onStop() {
        super.onStop();
        mcqAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mcqAdapter!=null)
            mcqAdapter.startListening();
    }

}

