package com.demo.neoveticare;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HireFragment extends Fragment {


    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    private List<Hire> hireList;
    HireAdapter adapter;
    RecyclerView recyclerView;
    String TAG = "HireFragment";

    public HireFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_hire, container, false);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        hireList = new ArrayList<>();
        recyclerView = root.findViewById(R.id.recyclerListView);
        recyclerView.setHasFixedSize(true);
        adapter = new HireAdapter(getContext(), hireList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        getMessages();
        return root;
    }

    private void getMessages() {
        hireList.clear();
        firebaseFirestore.collection("hires").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            List<Hire> hires = documentSnapshots.toObjects(Hire.class);

                            for (int i = 0; i < hires.size(); i++) {

                                if (TextUtils.equals(hires.get(i).getEmail(), mAuth.getCurrentUser().getEmail())) {

                                  Hire hire=hires.get(i);
                                    hireList.add(hire);
                                }


                            }


                            adapter.notifyDataSetChanged();
                        }
                    }


                });

    }

}
