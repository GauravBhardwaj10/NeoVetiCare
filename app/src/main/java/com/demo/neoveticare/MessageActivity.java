package com.demo.neoveticare;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MessageActivity extends AppCompatActivity {

    String email;
    AppCompatEditText etMessage;
    AppCompatButton btnSend;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);
        email = getIntent().getStringExtra("email");
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        Log.e("email", email);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = etMessage.getText().toString();
                if (TextUtils.isEmpty(msg)) {
                    etMessage.setError("");
                    return;
                }

                String id = firebaseFirestore.collection("messages").document().getId();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date date = new Date();
                Map<String, Object> order = new HashMap<>();
                order.put("id", id);
                order.put("emailfrom", mAuth.getCurrentUser().getEmail());
                order.put("emailto", email);
                order.put("message", msg);
                order.put("datetime", formatter.format(date));



                firebaseFirestore.collection("messages").document(id)
                        .set(order)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(MessageActivity.this, "Message sent Successfully", Toast.LENGTH_SHORT).show();

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
