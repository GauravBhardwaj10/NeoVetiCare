package com.demo.neoveticare;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import static java.security.AccessController.getContext;

public class RetreivedataActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    String selectedGender = "Sexual Content";
    ListofnanniesAdapter listnanies;
    AppCompatEditText etSearch;
    AppCompatImageView ivLocation;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_ret);
        etSearch = findViewById(R.id.etSearch);
        ivLocation = findViewById(R.id.ivLocation);
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
                    Toast.makeText(RetreivedataActivity.this, "home Page:", Toast.LENGTH_SHORT).show();
                    Ho();
                }
                if (item.getItemId() == R.id.Admin) {
                    Toast.makeText(RetreivedataActivity.this, "Admin Login page:", Toast.LENGTH_SHORT).show();
                    jj();
                }
                if (item.getItemId() == R.id.security) {
                    Toast.makeText(RetreivedataActivity.this, "Privacy & security Page:", Toast.LENGTH_SHORT).show();
                    KK();
                }
                if (item.getItemId() == R.id.rating) {
                    Toast.makeText(RetreivedataActivity.this, "Rate this app:", Toast.LENGTH_SHORT).show();
                    k3();
                }
                if (item.getItemId() == R.id.share) {
                    Toast.makeText(RetreivedataActivity.this, "Share the link of app by:", Toast.LENGTH_SHORT).show();
                    k4();
                }
                if (item.getItemId() == R.id.report) {
                    Toast.makeText(RetreivedataActivity.this, "Report this app:", Toast.LENGTH_SHORT).show();
                    showOptionDialog();}
                if (item.getItemId() == R.id.About) {
                    Toast.makeText(RetreivedataActivity.this, "About Us:", Toast.LENGTH_SHORT).show();
                    k5();
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
        final RecyclerView recyclerview = findViewById(R.id.recyclerListView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        Intent in = getIntent();
        String abc = in.getStringExtra("category");

        if (abc.equals("childernparttime")) {

            CollectionReference CTCollectionReference = rootRef.collection(abc);
            CTCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        ArrayList<Upload> CTList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Upload Caretaker = document.toObject(Upload.class);
                            CTList.add(Caretaker);
                        }

                        listnanies = new ListofnanniesAdapter(getApplicationContext(), CTList);

                        recyclerview.setAdapter(listnanies);
                        int CTListSize = CTList.size();
                        List<Upload> randomCTList = new ArrayList<>();
                        for (int i = 0; i < CTListSize; i++) {
                            Upload randomCaretaker = CTList.get(new Random().nextInt(CTListSize));
                            if (!randomCTList.contains(randomCaretaker)) {
                                randomCTList.add(randomCaretaker);
                                if (randomCTList.size() == 10) {
                                    break;
                                }
                            }
                        }
                    } else {
                        Log.d("AG", "Error getting documents: ", task.getException());
                    }
                }
            });
        } else if (abc.equals("seniorfulltime")) {
            CollectionReference CTCollectionReference = rootRef.collection(abc);
            CTCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        ArrayList<Upload> CTList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Upload caretaker = document.toObject(Upload.class);
                            CTList.add(caretaker);

                            listnanies = new ListofnanniesAdapter(getApplicationContext(), CTList);

                            recyclerview.setAdapter(listnanies);
                        }

                        int CTListSize = CTList.size();
                        List<Upload> randomCTList = new ArrayList<>();
                        for (int i = 0; i < CTListSize; i++) {
                            Upload randomCaretaker = CTList.get(new Random().nextInt(CTListSize));
                            if (!randomCTList.contains(randomCaretaker)) {
                                randomCTList.add(randomCaretaker);
                                if (randomCTList.size() == 10) {
                                    break;
                                }
                            }
                        }
                    } else {
                        Log.d("AG", "Error getting documents: ", task.getException());
                    }
                }
            });
        } else if (abc.equals("seniorparttime")) {
            CollectionReference CTCollectionReference = rootRef.collection(abc);
            CTCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        ArrayList<Upload> CTList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Upload Caretaker = document.toObject(Upload.class);
                            CTList.add(Caretaker);

                            listnanies = new ListofnanniesAdapter(getApplicationContext(), CTList);

                            recyclerview.setAdapter(listnanies);
                        }

                        int CTListSize = CTList.size();
                        List<Upload> randomCTList = new ArrayList<>();
                        for (int i = 0; i < CTListSize; i++) {
                            Upload randomCaretaker = CTList.get(new Random().nextInt(CTListSize));
                            if (!randomCTList.contains(randomCaretaker)) {
                                randomCTList.add(randomCaretaker);
                                if (randomCTList.size() == 10) {
                                    break;
                                }
                            }
                        }
                    } else {
                        Log.d("AG", "Error getting documents: ", task.getException());
                    }
                }
            });
        } else {
            CollectionReference CTCollectionReference = rootRef.collection("uploadchildernfulltime");
            CTCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        ArrayList<Upload> CTList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {

                            Upload caretaker = document.toObject(Upload.class);
                            CTList.add(caretaker);

                            listnanies = new ListofnanniesAdapter(getApplicationContext(), CTList);

                            recyclerview.setAdapter(listnanies);

                        }


                        int CTListSize = CTList.size();
                        List<Upload> randomCTList = new ArrayList<>();

                        for (int i = 0; i < CTListSize; i++) {
                            Upload randomCaretaker = CTList.get(new Random().nextInt(CTListSize));
                            if (!randomCTList.contains(randomCaretaker)) {
                                randomCTList.add(randomCaretaker);
                                if (randomCTList.size() == 10) {
                                    break;
                                }
                            }
                        }
                    } else {
                        Log.d("AG", "Error getting documents: ", task.getException());
                    }
                }
            });
        }
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                listnanies.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ivLocation.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(RetreivedataActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);


                }else{
                    Intent i = new Intent(RetreivedataActivity.this, MapsActivity.class);
                    startActivityForResult(i, 2);
                }

            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION) {// If request is cancelled, the result arrays are empty.
            if (grantResults.length == 0
                    || grantResults[0] !=
                    PackageManager.PERMISSION_GRANTED) {

                //   Toast.makeText(PGImageActivity.this, "You have to grant CAMERA and SD card Write permission for image upload", Toast.LENGTH_SHORT).show();

            } else {
                Intent i = new Intent(RetreivedataActivity.this, MapsActivity.class);
                startActivityForResult(i, 2);
            }

            // other 'case' lines to check for other permissions this app might request.
            //You can add here other case statements according to your requirement.
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 2) {
            String message = data.getStringExtra("address");
            etSearch.setText(message);
            latitude = Double.parseDouble(Objects.requireNonNull(data.getStringExtra("latitude")));
            longitude = Double.parseDouble(Objects.requireNonNull(data.getStringExtra("longitude")));

        }
    }

    private void getAddress() {

        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(RetreivedataActivity.this, Locale.getDefault());

        try {
            // Log.e("lat", latitude + "");
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (addresses != null) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();
                etSearch.setText(city);
            }
        } catch (IndexOutOfBoundsException i) {

        }
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
        Intent intent = new Intent(RetreivedataActivity.this, Adminactivity.class);
        startActivity(intent);
    }

    public void KK() {
        Intent intent = new Intent(RetreivedataActivity.this, privaceandsecurity.class);
        startActivity(intent);
    }

    public void Ho() {
        Intent intent = new Intent(RetreivedataActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void k3() {
        Intent intent = new Intent(RetreivedataActivity.this, Ratetheapp.class);
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
        Intent intent = new Intent(RetreivedataActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void k5() {
        Intent intent = new Intent(RetreivedataActivity.this, About_us.class);
        startActivity(intent);
    }
    public void showOptionDialog() {
        final String[] genders = {"Sexual Content", "Violent or repulsive Content", "Hateful or abusive Content","Harmful or dangerous Content","Spam or misleading"};
        AlertDialog.Builder builder = new AlertDialog.Builder(RetreivedataActivity.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                selectedGender = genders[which];
                Toast.makeText(RetreivedataActivity.this, "your" + selectedGender, Toast.LENGTH_LONG).show();

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

        Toast.makeText(RetreivedataActivity.this, "You Report: " + selectedGender, Toast.LENGTH_LONG).show();

    }
}