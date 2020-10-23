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

public class MessageFragment extends Fragment {


    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    private List<Message> messageList;
    MessageAdapter adapter;
    RecyclerView recyclerView;
    String TAG = "MessageFragment";

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_message, container, false);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        messageList = new ArrayList<>();
        // setup recycler view
        recyclerView = root.findViewById(R.id.recyclerListView);
        recyclerView.setHasFixedSize(true);
        adapter = new MessageAdapter(getContext(), messageList, mAuth.getCurrentUser().getEmail());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        getMessages();
        return root;
    }

    private void getMessages() {
        messageList.clear();
        firebaseFirestore.collection("messages").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            List<Message> messages = documentSnapshots.toObjects(Message.class);
                            Log.e("Size", "" + messages.size());
                            for (int i = 0; i < messages.size(); i++) {
                                if (TextUtils.equals(messages.get(i).getEmailfrom(), mAuth.getCurrentUser().getEmail()) ||
                                        TextUtils.equals(messages.get(i).getEmailto(), mAuth.getCurrentUser().getEmail())) {

                                    Message message = new Message(messages.get(i).getId(), messages.get(i).getEmailfrom(),
                                            messages.get(i).getEmailto(),
                                            messages.get(i).getMessage(), messages.get(i).getDatetime());

                                    messageList.add(message);
                                }


                            }


                            adapter.notifyDataSetChanged();
                        }
                    }


                });

    }

}
