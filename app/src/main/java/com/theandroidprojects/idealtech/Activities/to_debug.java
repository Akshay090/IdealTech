package com.theandroidprojects.idealtech.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class to_debug extends AppCompatActivity {


    // private SwipeRefreshLayout refreshLayout;
    private DatabaseReference subCategoryReference;
    private RecyclerView subCategoryRecyclerView;
    private FirebaseRecyclerAdapter<subCategory, subCategoryViewHolder> subCategoryAdapter;

    public to_debug() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_debug);



        //INITIALIZE FB
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        subCategoryReference = database.getReference(Common.subCategory_tbl);  //Common.subCategory_tbl = "SubCategoryFragment"

        //INITIALIZE RV
        subCategoryRecyclerView = findViewById(R.id.subRecyclerView);
        //categoryRecyclerView.setHasFixedSize(true);

        subCategoryRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        String categoryID = null;

       /* Bundle bundle = new Bundle();

        if (bundle != null){
            categoryID = bundle.getString("CATEGORY_ID");
        }
*/
       // Intent intent = getIntent();
        //categoryID = intent.getStringExtra("CATEGORY_ID");


        //Important step to use bundle with getIntent and get extra
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){

            categoryID = bundle.getString("CATEGORY_ID");
        }



        loadSubCategory(categoryID);

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
                Toast.makeText(to_debug.this, ""+model.getSubName(), Toast.LENGTH_LONG).show();
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
            public subCategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View viewdebug = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_view_h_layout, parent, false);
                return new subCategoryViewHolder(viewdebug);
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
