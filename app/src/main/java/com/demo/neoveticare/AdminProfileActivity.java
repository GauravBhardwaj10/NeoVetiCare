package com.demo.neoveticare;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminProfileActivity extends AppCompatActivity {

    TextView tvName, tvAddress, tvCity;
    Button btnProfile, btnBlock, btnDelete;
    EditText etEmail;
    FirebaseFirestore firebaseFirestore;
    String table, documentId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        firebaseFirestore = FirebaseFirestore.getInstance();
        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvCity = findViewById(R.id.tvCity);

        etEmail = findViewById(R.id.etEmail);
        btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etEmail.getText().toString())) {
                    etEmail.setError("invalid");
                } else {
                    getProfile();
                }
            }
        });

        btnBlock = findViewById(R.id.btnBlock);
        btnDelete = findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(documentId)) {

                    Toast.makeText(AdminProfileActivity.this, "Firstly get profile", Toast.LENGTH_LONG).show();

                } else {
                    firebaseFirestore.collection(table).document(documentId)
                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(AdminProfileActivity.this, "User Profile deleted Successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        btnBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etEmail.getText().toString())) {
                    etEmail.setError("invalid");
                } else {
                    String id = firebaseFirestore.collection("blocks").document().getId();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    Date date = new Date();
                    Map<String, Object> order = new HashMap<>();
                    order.put("id", id);
                    order.put("email", etEmail.getText().toString());
                    order.put("datetime", formatter.format(date));

                    firebaseFirestore.collection("blocks").document(id)
                            .set(order)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(AdminProfileActivity.this, "User Blocked Successfully", Toast.LENGTH_SHORT).show();

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


    }

    private void getProfile() {

        firebaseFirestore.collection("uploadchildernfulltime").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            return;
                        } else {

                            List<Upload> uploads = documentSnapshots.toObjects(Upload.class);
                            for (int i = 0; i < uploads.size(); i++) {

                                Upload upload = uploads.get(i);

                                if (TextUtils.equals(upload.getEmailaddress(), etEmail.getText().toString())) {
                                    documentId = documentSnapshots.getDocuments().get(i).getId();
                                    table = "uploadchildernfulltime";
                                    tvName.setText(upload.getName());
                                    tvAddress.setText(upload.getPhone());
                                    tvCity.setText(upload.getCity());


                                    return;
                                }


                            }
                            getSeniorfulltime();

                        }
                    }


                });


    }

    public void getSeniorfulltime() {
        firebaseFirestore.collection("seniorfulltime").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {

                            return;
                        } else {

                            List<Upload> uploads = documentSnapshots.toObjects(Upload.class);
                            for (int i = 0; i < uploads.size(); i++) {

                                Upload upload = uploads.get(i);
                                if (TextUtils.equals(upload.getEmailaddress(), etEmail.getText().toString())) {
                                    documentId = documentSnapshots.getDocuments().get(i).getId();
                                    table = "seniorfulltime";
                                    tvName.setText(upload.getName());
                                    tvAddress.setText(upload.getPhone());
                                    tvCity.setText(upload.getCity());


                                    return;
                                }


                            }
                            getSeniorParttime();

                        }
                    }


                });
    }

    public void getSeniorParttime() {
        firebaseFirestore.collection("seniorparttime").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            return;
                        } else {

                            List<Upload> uploads = documentSnapshots.toObjects(Upload.class);
                            for (int i = 0; i < uploads.size(); i++) {

                                Upload upload = uploads.get(i);
                                if (TextUtils.equals(upload.getEmailaddress(), etEmail.getText().toString())) {
                                    documentId = documentSnapshots.getDocuments().get(i).getId();
                                    table = "seniorparttime";
                                    tvName.setText(upload.getName());
                                    tvAddress.setText(upload.getPhone());
                                    tvCity.setText(upload.getCity());


                                    return;
                                }


                            }
                            getChildParttime();
                        }
                    }


                });
    }

    public void getChildParttime() {
        firebaseFirestore.collection("childernparttime").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {

                            return;
                        } else {

                            List<Upload> uploads = documentSnapshots.toObjects(Upload.class);
                            for (int i = 0; i < uploads.size(); i++) {

                                Upload upload = uploads.get(i);

                                if (TextUtils.equals(upload.getEmailaddress(), etEmail.getText().toString())) {
                                    documentId = documentSnapshots.getDocuments().get(i).getId();
                                    table = "childernparttime";
                                    tvName.setText(upload.getName());
                                    tvAddress.setText(upload.getPhone());
                                    tvCity.setText(upload.getCity());

                                    return;
                                }


                            }

                        }
                    }


                });
    }

}
