package com.demo.neoveticare;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RetreivedataActivity extends AppCompatActivity {
    ListofnanniesAdapter listnanies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreivedata);

        final RecyclerView recyclerview=findViewById(R.id.recyclerListView);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        String abc="";

        if(abc.equals("childernparttime")) {

            CollectionReference CTCollectionReference = rootRef.collection(abc);
            CTCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        ArrayList<Upload> CTList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Upload caretaker = document.toObject(Upload.class);
                            CTList.add(caretaker);
                        }

                        listnanies =new ListofnanniesAdapter(getApplicationContext(),CTList);

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
        }else if(abc.equals("seniorfulltime")){
            CollectionReference CTCollectionReference = rootRef.collection(abc);
            CTCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        List<Upload> CTList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Upload caretaker = document.toObject(Upload.class);
                            CTList.add(caretaker);
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
        }else if(abc.equals("seniorparttime")){
            CollectionReference CTCollectionReference = rootRef.collection(abc);
            CTCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        List<Upload> CTList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            Upload Caretaker = document.toObject(Upload.class);
                            CTList.add(Caretaker);
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
        }else{
            CollectionReference CTCollectionReference = rootRef.collection("uploadchildernfulltime");
            CTCollectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        ArrayList<Upload> CTList = new ArrayList<>();
                        for (DocumentSnapshot document : task.getResult()) {
                            try {
                                Upload caretaker = document.toObject(Upload.class);
                                CTList.add(caretaker);
                            }catch (Exception ee){
                                ee.toString();
                                listnanies =new ListofnanniesAdapter(getApplicationContext(),CTList);

                                recyclerview.setAdapter(listnanies);
                            }
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
    }
}