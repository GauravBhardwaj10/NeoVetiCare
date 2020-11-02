package com.demo.neoveticare;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

public class FilterActivity extends AppCompatActivity {

    AppCompatButton btnFilter;
    private RadioGroup radioGenderGroup;
    private RadioButton radioGenderButton;
    AppCompatEditText etMin, etMax, etCity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Intent in = getIntent();
        final String category = in.getStringExtra("category");

        radioGenderGroup = findViewById(R.id.radioGroup);

        etMin = findViewById(R.id.etMinPrice);
        etMax = findViewById(R.id.etMaxPrice);
        etCity = findViewById(R.id.etCity);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGenderGroup.getCheckedRadioButtonId();
                radioGenderButton = (RadioButton) findViewById(selectedId);
                Intent i = new Intent(FilterActivity.this, RetreivedataActivity.class);
                i.putExtra("category", category);
                i.putExtra("min", etMin.getText().toString());
                i.putExtra("max", etMax.getText().toString());
                i.putExtra("city", etCity.getText().toString());
                i.putExtra("gender", radioGenderButton.getText());
                startActivity(i);
            }
        });

    }
}
