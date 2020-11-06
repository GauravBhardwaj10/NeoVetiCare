package com.demo.neoveticare;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RatingActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    private List<Rating> ratingList;
    RatingAdapter adapter;
    RecyclerView recyclerView;
    String TAG = "RatingActivity";
    Button btnRating;
    String email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratings);
        email = getIntent().getStringExtra("email");
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        ratingList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new RatingAdapter(RatingActivity.this, ratingList);
        recyclerView.setLayoutManager(new LinearLayoutManager(RatingActivity.this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        btnRating = findViewById(R.id.btnRating);
        btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ratingPopUp();
            }
        });

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
                            Log.e("size", ratings.size() + "");
                            for (int i = 0; i < ratings.size(); i++) {

                                if (TextUtils.equals(ratings.get(i).getEmail(), email)) {

                                    Rating rating = ratings.get(i);
                                    ratingList.add(rating);
                                }


                            }


                            adapter.notifyDataSetChanged();
                        }
                    }


                });

    }

    private void ratingPopUp() {

        final Dialog dialog = new Dialog(RatingActivity.this);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(R.layout.dialog_rating);
        dialog.setCancelable(true);

        final TextView tvLater = dialog.findViewById(R.id.tvLater);
        final TextView tvSubmit = dialog.findViewById(R.id.tvSubmit);
        final RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
        final EditText etFeedback = dialog.findViewById(R.id.etFeedback);


        tvLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();


            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating = ratingBar.getRating();
                if (rating == 0) {
                    Toast.makeText(RatingActivity.this, "Please select Stars", Toast.LENGTH_LONG).show();
                } else {

                    String id = firebaseFirestore.collection("ratings").document().getId();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    Date date = new Date();
                    Map<String, Object> order = new HashMap<>();
                    order.put("id", id);
                    order.put("message", etFeedback.getText().toString());
                    order.put("star", String.valueOf(rating));
                    order.put("email", email);
                    order.put("givenUserEmail", mAuth.getCurrentUser().getEmail());


                    firebaseFirestore.collection("ratings").document(id)
                            .set(order)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    dialog.cancel();
                                    getRatings();
                                    Toast.makeText(RatingActivity.this, "Rated Successfully!", Toast.LENGTH_LONG).show();

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });


                }
            }
        });
        dialog.show();


    }


}

