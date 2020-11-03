package com.demo.neoveticare;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdminContactActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    private List<AdminContact> adminContactList;
    AdminContactAdapter adapter;
    RecyclerView recyclerView;
    String TAG = "AdminContactActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_contacts);
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
}
