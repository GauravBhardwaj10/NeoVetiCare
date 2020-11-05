package com.demo.neoveticare;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class RatingActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    private List<Rating> ratingList;
    RatingAdapter adapter;
    RecyclerView recyclerView;
    String TAG = "RatingActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        ratingList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new RatingAdapter(RatingActivity.this, ratingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(RatingActivity.this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        getRatings();


    }
    private void getRatings() {
        ratingList.clear();
        firebaseFirestore.collection("ratings").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            List<Rating> ratings = documentSnapshots.toObjects(Rating.class);

                            for (int i = 0; i < ratings.size(); i++) {

                                if (TextUtils.equals(ratings.get(i).getEmail(), mAuth.getCurrentUser().getEmail())) {

                                    Rating rating=ratings.get(i);
                                    ratingList.add(rating);
                                }


                            }


                            adapter.notifyDataSetChanged();
                        }
                    }


                });

    }

}

