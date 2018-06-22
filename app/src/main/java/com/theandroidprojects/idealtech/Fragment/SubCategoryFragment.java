package com.theandroidprojects.idealtech.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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

import com.theandroidprojects.idealtech.ViewHolders.subCategoryViewHolder;
import com.theandroidprojects.idealtech.model.subCategory;

public class SubCategoryFragment extends Fragment {

    // private SwipeRefreshLayout refreshLayout;
    private DatabaseReference subCategoryReference;
    private RecyclerView subCategoryRecyclerView;
    private FirebaseRecyclerAdapter<subCategory, subCategoryViewHolder> subCategoryAdapter;

    public SubCategoryFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.category_sub_layout, container, false);

        //INITIALIZE FB
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        subCategoryReference = database.getReference(Common.subCategory_tbl);  //Common.subCategory_tbl = "subCategory"

        //INITIALIZE RV
        subCategoryRecyclerView = rootView.findViewById(R.id.subRecyclerView);
        //categoryRecyclerView.setHasFixedSize(true);

        subCategoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        String categoryID = null;

        Bundle bundle = new Bundle();

      /*  if (bundle != null){
            categoryID = bundle.getString("CATEGORY_ID");
        } */

        assert getArguments() != null;
        categoryID = getArguments().getString("CATEGORY_ID");

        Toast.makeText(getActivity(),categoryID +"inSubC",Toast.LENGTH_LONG).show();

        loadSubCategory(categoryID);


        return rootView;
    }


    private void loadSubCategory(String categoryID) {
        //create query
        Query searchByName = subCategoryReference.orderByChild("catID").equalTo(categoryID);

        FirebaseRecyclerOptions<subCategory> options = new FirebaseRecyclerOptions.Builder<subCategory>()
                .setQuery(searchByName, subCategory.class)
                .build();


        subCategoryAdapter = new FirebaseRecyclerAdapter<subCategory, subCategoryViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull subCategoryViewHolder holder,
                                            final int position, @NonNull final subCategory model) {
                holder.setSubName(model.getSubName());
                holder.setSubDate(model.getDate());
                holder.setSubQuestions(model.getQuestions());
                holder.setSubDuration(model.getDuration());

                Toast.makeText(getActivity(), "itsMname"+model.getSubName(), Toast.LENGTH_SHORT).show();

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = subCategoryAdapter.getRef(position).getKey();
                        Bundle bundle = new Bundle();
                        bundle.putString("SUB_CATEGORY_ID",url);

                        AppCompatActivity activity = (AppCompatActivity) v.getContext();

                        MCQFragment MCQFrag = new MCQFragment();
                        MCQFrag.setArguments(bundle);
                        activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_subStudy, MCQFrag).addToBackStack(null).commit();
                    }

                });



            }

            @NonNull
            @Override
            public subCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_view_h_layout, parent, false);
                return new subCategoryViewHolder(view);
            }

        };

        subCategoryAdapter.notifyDataSetChanged();     //Refresh data if data have changed
        subCategoryAdapter.startListening();
        subCategoryRecyclerView.setAdapter(subCategoryAdapter);

        // refreshLayout.setRefreshing(false);


    }


    @Override
    public void onStop() {
        super.onStop();
        subCategoryAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (subCategoryAdapter!=null)
            subCategoryAdapter.startListening();
    }

}

