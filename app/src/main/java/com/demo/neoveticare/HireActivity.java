package com.demo.neoveticare;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HireActivity extends AppCompatActivity {

    EditText etMonday, etTuesday, etWednesday, etThursday, etFriday, etSaturday, etSunday;
    TextView tvName, tvEmail, tvRate, tvTotalPrice;
    Button btnCalculate, btnOffer;
    String name, email, rate;
    int mon, tue, wed, thu, fri, sat, sun;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire);

        etMonday = findViewById(R.id.etMonday);
        etTuesday = findViewById(R.id.etTuesday);
        etWednesday = findViewById(R.id.etWednesday);
        etThursday = findViewById(R.id.etThursday);
        etFriday = findViewById(R.id.etFriday);
        etSaturday = findViewById(R.id.etSaturday);
        etSunday = findViewById(R.id.etSunday);

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
        tvRate.setText(rate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(etMonday.getText().toString())) {
                    mon = 0;
                }
                if (TextUtils.isEmpty(etTuesday.getText().toString())) {
                    tue = 0;
                }
                if (TextUtils.isEmpty(etWednesday.getText().toString())) {
                    wed = 0;
                }
                if (TextUtils.isEmpty(etThursday.getText().toString())) {
                    thu = 0;
                }
                if (TextUtils.isEmpty(etFriday.getText().toString())) {
                    fri = 0;
                }
                if (TextUtils.isEmpty(etSaturday.getText().toString())) {
                    sat = 0;
                }
                if (TextUtils.isEmpty(etSunday.getText().toString())) {
                    sun = 0;
                }

                int TotalHours = mon + tue + wed + thu + fri + sat + sun;

                int TotalPrice = TotalHours * Integer.parseInt(rate);

                tvTotalPrice.setText("Total Price " + TotalPrice);

            }
        });

        btnOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
