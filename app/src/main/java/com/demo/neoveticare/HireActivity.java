package com.demo.neoveticare;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HireActivity extends AppCompatActivity {

    EditText etMonday, etTuesday, etWednesday, etThursday, etFriday, etSaturday, etSunday, etFrom, etTo;
    TextView tvName, tvEmail, tvRate, tvTotalPrice;
    Button btnCalculate, btnOffer;
    String name, email, rate;
    int mon, tue, wed, thu, fri, sat, sun, totalPrice;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendarTo = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        etMonday = findViewById(R.id.etMonday);
        etTuesday = findViewById(R.id.etTuesday);
        etWednesday = findViewById(R.id.etWednesday);
        etThursday = findViewById(R.id.etThursday);
        etFriday = findViewById(R.id.etFriday);
        etSaturday = findViewById(R.id.etSaturday);
        etSunday = findViewById(R.id.etSunday);

        etFrom = findViewById(R.id.etFrom);
        etTo = findViewById(R.id.etTo);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvRate = findViewById(R.id.tvRate);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnOffer = findViewById(R.id.btnOffer);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        rate = getIntent().getStringExtra("rate");

        tvName.setText(name);
        tvEmail.setText(email);
        tvRate.setText(rate + " CAD/hr");

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(etMonday.getText().toString())) {
                    mon = 0;
                } else {
                    mon = Integer.parseInt(etMonday.getText().toString());
                }
                if (TextUtils.isEmpty(etTuesday.getText().toString())) {
                    tue = 0;
                } else {
                    tue = Integer.parseInt(etTuesday.getText().toString());
                }
                if (TextUtils.isEmpty(etWednesday.getText().toString())) {
                    wed = 0;
                } else {
                    wed = Integer.parseInt(etWednesday.getText().toString());
                }
                if (TextUtils.isEmpty(etThursday.getText().toString())) {
                    thu = 0;
                } else {
                    thu = Integer.parseInt(etThursday.getText().toString());
                }
                if (TextUtils.isEmpty(etFriday.getText().toString())) {
                    fri = 0;
                } else {
                    fri = Integer.parseInt(etFriday.getText().toString());
                }
                if (TextUtils.isEmpty(etSaturday.getText().toString())) {
                    sat = 0;
                } else {
                    sat = Integer.parseInt(etSaturday.getText().toString());
                }
                if (TextUtils.isEmpty(etSunday.getText().toString())) {
                    sun = 0;
                } else {
                    sun = Integer.parseInt(etSunday.getText().toString());
                }

                if (TextUtils.isEmpty(etFrom.getText().toString())) {
                    etFrom.setError("Invalid");
                    return;
                }

                if (TextUtils.isEmpty(etTo.getText().toString())) {
                    etTo.setError("Invalid");
                    return;
                }
                int TotalHours = mon + tue + wed + thu + fri + sat + sun;

                Log.e("TotalHours", "" + TotalHours);

                totalPrice = TotalHours * Integer.parseInt(rate);

                tvTotalPrice.setText("Total Price " + totalPrice + " CAD");

            }
        });

        btnOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String id = firebaseFirestore.collection("hires").document().getId();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date date = new Date();
                Map<String, Object> order = new HashMap<>();
                order.put("id", id);
                order.put("name", name);
                order.put("email", email);
                order.put("rate", rate);
                order.put("mon", String.valueOf(mon));
                order.put("tue", String.valueOf(tue));
                order.put("wed", String.valueOf(wed));
                order.put("thu", String.valueOf(thu));
                order.put("fri", String.valueOf(fri));
                order.put("sat", String.valueOf(sat));
                order.put("sun", String.valueOf(sun));
                order.put("total", String.valueOf(totalPrice));
                order.put("fromDate", etFrom.getText().toString());
                order.put("toDate", etTo.getText().toString());
                order.put("datetime", formatter.format(date));
                order.put("parentEmail", mAuth.getCurrentUser().getEmail());


                firebaseFirestore.collection("hires").document(id)
                        .set(order)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Intent i = new Intent(HireActivity.this, PaymentActivity.class);
                                i.putExtra("documentId", id);
                                startActivity(i);
                                // Toast.makeText(HireActivity.this, "Offer sent Successfully", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


            }
        });


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        etFrom.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(HireActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        final DatePickerDialog.OnDateSetListener dateTo = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarTo.set(Calendar.YEAR, year);
                myCalendarTo.set(Calendar.MONTH, monthOfYear);
                myCalendarTo.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateToLabel();
            }

        };

        etTo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(HireActivity.this, dateTo, myCalendarTo
                        .get(Calendar.YEAR), myCalendarTo.get(Calendar.MONTH),
                        myCalendarTo.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


    }

    private void updateLabel() {
        String myFormat = "MM-dd-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etFrom.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateToLabel() {
        String myFormat = "MM-dd-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etTo.setText(sdf.format(myCalendarTo.getTime()));
    }
}
