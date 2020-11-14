package com.demo.neoveticare;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminProfileActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    String selectedGender = "Sexual Content";

    TextView tvName, tvAddress, tvCity;
    Button btnProfile, btnBlock, btnDelete;
    EditText etEmail;
    FirebaseFirestore firebaseFirestore;
    String table, documentId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_adminprofile);
        drawerLayout = findViewById(R.id.drawer_layout);
        mtoggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    Toast.makeText(AdminProfileActivity.this, "home Page:", Toast.LENGTH_SHORT).show();
                    Ho();
                }
                if (item.getItemId() == R.id.Admin) {
                    Toast.makeText(AdminProfileActivity.this, "Admin Login page:", Toast.LENGTH_SHORT).show();
                    jj();
                }
                if (item.getItemId() == R.id.security) {
                    Toast.makeText(AdminProfileActivity.this, "Privacy & security Page:", Toast.LENGTH_SHORT).show();
                    KK();
                }
                if (item.getItemId() == R.id.rating) {
                    Toast.makeText(AdminProfileActivity.this, "Rate this app:", Toast.LENGTH_SHORT).show();
                    k3();
                }
                if (item.getItemId() == R.id.share) {
                    Toast.makeText(AdminProfileActivity.this, "Share the link of app by:", Toast.LENGTH_SHORT).show();
                    k4();
                }
                if (item.getItemId() == R.id.report) {
                    Toast.makeText(AdminProfileActivity.this, "Report this app:", Toast.LENGTH_SHORT).show();
                    showOptionDialog();}
                if (item.getItemId() == R.id.About) {
                    Toast.makeText(AdminProfileActivity.this, "About Us:", Toast.LENGTH_SHORT).show();
                    k5();
                }
                if (item.getItemId() == R.id.contact) {
                    Intent contact = new Intent(AdminProfileActivity.this, ContactActivity.class);
                    startActivity(contact);}
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);


                return true;
            }
        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Help:
                Toast.makeText(this, "Help Page:", Toast.LENGTH_SHORT).show();
                h1();
                break;

        }
        {
            if (mtoggle.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    public void jj() {
        Intent intent = new Intent(AdminProfileActivity.this, Adminactivity.class);
        startActivity(intent);
    }

    public void KK() {
        Intent intent = new Intent(AdminProfileActivity.this, privaceandsecurity.class);
        startActivity(intent);
    }

    public void Ho() {
        Intent intent = new Intent(AdminProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void k3() {
        Intent intent = new Intent(AdminProfileActivity.this, Ratetheapp.class);
        startActivity(intent);
    }

    public void k4() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("Text");
        String sharebody = "Your Body Here";
        String sharesub = "Your subject here";
        intent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
        intent.putExtra(Intent.EXTRA_TEXT, sharebody);
        startActivity(Intent.createChooser(intent, "Share using"));
    }

    public void h1() {
        Intent intent = new Intent(AdminProfileActivity.this, Help.class);
        startActivity(intent);
    }

    public void k5() {
        Intent intent = new Intent(AdminProfileActivity.this, About_us.class);
        startActivity(intent);
    }
    public void showOptionDialog() {
        final String[] genders = {"Sexual Content", "Violent or repulsive Content", "Hateful or abusive Content","Harmful or dangerous Content","Spam or misleading"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminProfileActivity.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                selectedGender = genders[which];
                Toast.makeText(AdminProfileActivity.this, "your" + selectedGender, Toast.LENGTH_LONG).show();

            }
        });
        builder.setPositiveButton("Proceesd", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                go();
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
    public void go()
    {

        Toast.makeText(AdminProfileActivity.this, "You Report: " + selectedGender, Toast.LENGTH_LONG).show();

    }
}