package com.demo.neoveticare;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class HireDetailsActivity extends AppCompatActivity {

    TextView tvParentEmail, tvMonday, tvTuesday, tvWednesday, tvThursday, tvFriday, tvSaturday, tvSunday, tvTotalPayment, tvPaymentStatus;
    FirebaseFirestore firebaseFirestore;
    String documentId, hireId;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    String selectedGender = "Sexual Content";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_hire_activity);
        documentId = getIntent().getStringExtra("documentId");
        firebaseFirestore = FirebaseFirestore.getInstance();
        tvParentEmail = findViewById(R.id.tvParentEmail);
        tvMonday = findViewById(R.id.tvMonday);
        tvTuesday = findViewById(R.id.tvTuesday);
        tvWednesday = findViewById(R.id.tvWednesday);
        tvThursday = findViewById(R.id.tvThursday);
        tvFriday = findViewById(R.id.tvFriday);
        tvSaturday = findViewById(R.id.tvSaturday);
        tvSunday = findViewById(R.id.tvSunday);
        tvTotalPayment = findViewById(R.id.tvTotalPayment);
        tvPaymentStatus = findViewById(R.id.tvPaymentStatus);
        getHireDetails();
        drawerLayout = findViewById(R.id.drawer_layout);
        mtoggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    Toast.makeText(HireDetailsActivity.this, "home Page:", Toast.LENGTH_SHORT).show();
                    c6();
                }
                if (item.getItemId() == R.id.Admin) {
                    Toast.makeText(HireDetailsActivity.this, "Admin Login page:", Toast.LENGTH_SHORT).show();
                    c7();
                }
                if (item.getItemId() == R.id.security) {
                    Toast.makeText(HireDetailsActivity.this, "Privacy & security Page:", Toast.LENGTH_SHORT).show();
                    c8();
                }
                if (item.getItemId() == R.id.rating) {
                    Toast.makeText(HireDetailsActivity.this, "Rate this app:", Toast.LENGTH_SHORT).show();
                    c9();
                }
                if (item.getItemId() == R.id.share) {
                    Toast.makeText(HireDetailsActivity.this, "Share the link of app by:", Toast.LENGTH_SHORT).show();
                    c10();
                }
                if (item.getItemId() == R.id.About) {
                    Toast.makeText(HireDetailsActivity.this, "Share the link of app by:", Toast.LENGTH_SHORT).show();
                    c11();
                }
                if (item.getItemId() == R.id.report) {
                    Toast.makeText(HireDetailsActivity.this, "Report this app:", Toast.LENGTH_SHORT).show();
                    showOptionDialog();
                }
                if (item.getItemId() == R.id.contact) {
                    Intent contact = new Intent(HireDetailsActivity.this, ContactActivity.class);
                    startActivity(contact);

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void getHireDetails() {

        firebaseFirestore.collection("hires").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {

                            return;
                        } else {
                            List<Hire> hires = documentSnapshots.toObjects(Hire.class);

                            for (int i = 0; i < hires.size(); i++) {

                                if (TextUtils.equals(documentSnapshots.getDocuments().get(i).getId(), documentId)) {

                                    Hire hire = hires.get(i);
                                    tvParentEmail.setText(hire.getParentEmail());
                                    tvMonday.setText(hire.getMon() + " Hours");
                                    tvTuesday.setText(hire.getTue() + " Hours");
                                    tvWednesday.setText(hire.getWed() + " Hours");
                                    tvThursday.setText(hire.getThu()+ " Hours");
                                    tvFriday.setText(hire.getFri()+ " Hours");
                                    tvSaturday.setText(hire.getSat()+ " Hours");
                                    tvSunday.setText(hire.getSun()+ " Hours");
                                    tvTotalPayment.setText(hire.getTotal() + " CAD");
                                    hireId = hire.getId();
                                    checkPaymentStaus();

                                }


                            }


                        }
                    }


                });

    }

    private void checkPaymentStaus() {
        tvPaymentStatus.setText("payment done");

        firebaseFirestore.collection("payments").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {

                            return;
                        } else {
                            List<Payment> payments = documentSnapshots.toObjects(Payment.class);

                            for (int i = 0; i < payments.size(); i++) {

                                if (TextUtils.equals(documentSnapshots.getDocuments().get(i).getId(), hireId)) {
                                    tvPaymentStatus.setText("Paid");



                                }


                            }


                        }
                    }


                });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Help:
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                hi();
                break;
        }
        {
            if (mtoggle.onOptionsItemSelected(item)) {
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    public void c7() {
        Intent intent = new Intent(HireDetailsActivity.this, Adminactivity.class);
        startActivity(intent);
    }

    public void c8() {
        Intent intent = new Intent(HireDetailsActivity.this, privaceandsecurity.class);
        startActivity(intent);
    }

    public void c6() {
        Intent intent = new Intent(HireDetailsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void c9() {
        Intent intent = new Intent(HireDetailsActivity.this, Ratetheapp.class);
        startActivity(intent);
    }

    public void c11() {
        Intent intent = new Intent(HireDetailsActivity.this, About_us.class);
        startActivity(intent);
    }

    public void c10() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("Text");
        String sharebody = "Your Body Here";
        String sharesub = "Your subject here";
        intent.putExtra(Intent.EXTRA_SUBJECT, sharesub);
        intent.putExtra(Intent.EXTRA_TEXT, sharebody);
        startActivity(Intent.createChooser(intent, "Share using"));
    }

    public void hi() {
        Intent intent = new Intent(HireDetailsActivity.this, Help.class);
        startActivity(intent);
    }

    public void showOptionDialog() {
        final String[] genders = {"Sexual Content", "Violent or repulsive Content", "Hateful or abusive Content", "Harmful or dangerous Content", "Spam or misleading"};
        AlertDialog.Builder builder = new AlertDialog.Builder(HireDetailsActivity.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(genders, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                selectedGender = genders[which];
                Toast.makeText(HireDetailsActivity.this, "your" + selectedGender, Toast.LENGTH_LONG).show();

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

    public void go() {

        Toast.makeText(HireDetailsActivity.this, "You Report: " + selectedGender, Toast.LENGTH_LONG).show();

    }
}
