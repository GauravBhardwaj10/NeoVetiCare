package com.demo.neoveticare;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class ContactActivity extends AppCompatActivity {

    EditText etName, etEmail, etPhone, etMessage;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnContact;
    String name, email, phone, message, service;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        firebaseFirestore = FirebaseFirestore.getInstance();
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etMessage = findViewById(R.id.etMessage);
        radioGroup = findViewById(R.id.rdGroup);
        btnContact = findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                service = radioButton.getText().toString();
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                phone = etPhone.getText().toString();
                message = etMessage.getText().toString();


                if (TextUtils.isEmpty(name)) {
                    etName.setError("Invalid");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    etEmail.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    etPhone.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(message)) {
                    etMessage.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(service)) {

                    return;
                }


                String id = firebaseFirestore.collection("contacts").document().getId();

                Map<String, Object> order = new HashMap<>();
                order.put("id", id);
                order.put("name", name);
                order.put("email", email);
                order.put("phone", phone);
                order.put("message", message);
                order.put("service", service);


                firebaseFirestore.collection("contacts").document(id)
                        .set(order)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(ContactActivity.this, "Successfully Sent", Toast.LENGTH_SHORT).show();
                                etName.setText("");
                                etEmail.setText("");
                                etMessage.setText("");
                                etPhone.setText("");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


            }
        });
    }
}
