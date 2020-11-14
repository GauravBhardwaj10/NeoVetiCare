package com.demo.neoveticare;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminContactActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    String selectedGender = "Sexual Content";

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    private List<AdminContact> adminContactList;
    AdminContactAdapter adapter;
    RecyclerView recyclerView;
    String TAG = "AdminContactActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_admincontact);
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
                    Toast.makeText(AdminContactActivity.this, "home Page:", Toast.LENGTH_SHORT).show();
                    Ho();
                }
                if (item.getItemId() == R.id.Admin) {
                    Toast.makeText(AdminContactActivity.this, "Admin Login page:", Toast.LENGTH_SHORT).show();
                    jj();
                }
                if (item.getItemId() == R.id.security) {
                    Toast.makeText(AdminContactActivity.this, "Terms & Conditions Page:", Toast.LENGTH_SHORT).show();
                    KK();
                }
                if (item.getItemId() == R.id.rating) {
                    Toast.makeText(AdminContactActivity.this, "Rate this app:", Toast.LENGTH_SHORT).show();
                    k3();
                }
                if (item.getItemId() == R.id.share) {
                    Toast.makeText(AdminContactActivity.this, "Share the link of app by:", Toast.LENGTH_SHORT).show();
                    k4();
                }
                if (item.getItemId() == R.id.report) {
                    Toast.makeText(AdminContactActivity.this, "Report this app:", Toast.LENGTH_SHORT).show();
                    showOptionDialog();}
                if (item.getItemId() == R.id.About) {
                    Toast.makeText(AdminContactActivity.this, "About Us:", Toast.LENGTH_SHORT).show();
                    k5();
                }if (item.getItemId() == R.id.contact) {
                    Intent contact = new Intent(AdminContactActivity.this, ContactActivity.class);
                    startActivity(contact);}
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        adminContactList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new AdminContactAdapter(AdminContactActivity.this, adminContactList);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminContactActivity.this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        getMessages();

    }


    private void getMessages() {
        adminContactList.clear();
        firebaseFirestore.collection("contacts").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            List<AdminContact> contacts = documentSnapshots.toObjects(AdminContact.class);
                            adminContactList.addAll(contacts);


                        }


                        adapter.notifyDataSetChanged();
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
        Intent intent = new Intent(AdminContactActivity.this, Adminactivity.class);
        startActivity(intent);
    }

    public void KK() {
        Intent intent = new Intent(AdminContactActivity.this, privaceandsecurity.class);
        startActivity(intent);
    }

    public void Ho() {
        Intent intent = new Intent(AdminContactActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void k3() {
        Intent intent = new Intent(AdminContactActivity.this, Ratetheapp.class);
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
        Intent intent = new Intent(AdminContactActivity.this, Help.class);
        startActivity(intent);
    }

    public void k5() {
        Intent intent = new Intent(AdminContactActivity.this, About_us.class);
        startActivity(intent);
    }
    public void showOptionDialog() {
        final String[] genders = {"Sexual Content", "Violent or repulsive Content", "Hateful or abusive Content","Harmful or dangerous Content","Spam or misleading"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AdminContactActivity.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                selectedGender = genders[which];
                Toast.makeText(AdminContactActivity.this, "your" + selectedGender, Toast.LENGTH_LONG).show();

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

        Toast.makeText(AdminContactActivity.this, "You Report: " + selectedGender, Toast.LENGTH_LONG).show();

    }
}
