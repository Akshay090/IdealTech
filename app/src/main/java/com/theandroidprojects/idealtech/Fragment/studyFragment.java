package com.theandroidprojects.idealtech.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
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
import com.theandroidprojects.idealtech.Activities.to_debug;
import com.theandroidprojects.idealtech.Adapters.ViewPagerAdapter;
import com.theandroidprojects.idealtech.Common.Common;
import com.theandroidprojects.idealtech.R;
import com.theandroidprojects.idealtech.ViewHolders.CategoryViewHolder;
import com.theandroidprojects.idealtech.model.Category;


public class StudyFragment extends Fragment {

    // private SwipeRefreshLayout refreshLayout;
    private DatabaseReference  categoryReference;
    private RecyclerView categoryRecyclerView;
    private FirebaseRecyclerAdapter<Category, CategoryViewHolder> categoryAdapter;


    public StudyFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.study_fragment, container, false);





        //INITIALIZE FB
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        categoryReference = database.getReference(Common.category_tbl);  //Common.category_tbl = "Category"


        //INITIALIZE RV
        categoryRecyclerView = rootView.findViewById(R.id.categoryRecyclerView);
        //categoryRecyclerView.setHasFixedSize(true);

        categoryRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));



        loadCategory();

        return rootView;
    }


    private void loadCategory() {


        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(categoryReference, Category.class)
                .build();
        categoryAdapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position, @NonNull final Category model) {
                holder.setName(model.getName());
                holder.setImage(getContext(), model.getImage());

                	holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = categoryAdapter.getRef(position).getKey();

                        Toast.makeText(getActivity(), ""+url, Toast.LENGTH_LONG).show();

                        Bundle bundle = new Bundle();
                        bundle.putString("CATEGORY_ID",url);

                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
                        SubCategoryFragment subCategoryFragment = new SubCategoryFragment();
                        subCategoryFragment.setArguments(bundle);

                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_mainStudy, subCategoryFragment)
                                .addToBackStack(null).commit();

                       /*
                        Intent intent = new Intent(getActivity(), to_debug.class);
                        intent.putExtra("CATEGORY_ID",url);
                        startActivity(intent);
                        */
                        }

                    });


            }

            @Override
            public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
                return new CategoryViewHolder(view);
            }

        };

            categoryAdapter.notifyDataSetChanged();     //Refresh data if data have changed
            categoryAdapter.startListening();
            categoryRecyclerView.setAdapter(categoryAdapter);

           // refreshLayout.setRefreshing(false);

    }



    @Override
    public void onStop() {
        super.onStop();
        categoryAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (categoryAdapter!=null)
            categoryAdapter.startListening();
    }


}





