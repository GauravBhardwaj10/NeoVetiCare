package com.demo.neoveticare;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ChatActivity extends AppCompatActivity {

    EditText etMessage;
    ImageView ivSend;
    String email;
    FirebaseAuth mAuth;

    private RecyclerView recyclerView;
    private List<Chat> chatList;
    private ChatAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mAuth = FirebaseAuth.getInstance();
        email = getIntent().getStringExtra("email");
        etMessage = findViewById(R.id.etMessage);
        ivSend = findViewById(R.id.ivSend);

        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = etMessage.getText().toString();
                if (TextUtils.isEmpty(msg)) {
                    etMessage.setError("");
                    return;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date date = new Date();
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("chats");
                String chatId = mDatabase.push().getKey();
                Chat chat = new Chat(chatId, mAuth.getCurrentUser().getEmail(), email, msg, formatter.format(date));
                mDatabase.child(Objects.requireNonNull(chatId)).setValue(chat);
                etMessage.setText("");
            }
        });
        chatList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerListView);
        recyclerView.setHasFixedSize(true);
        adapter = new ChatAdapter(ChatActivity.this, chatList, mAuth.getCurrentUser().getEmail());
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("chats");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                chatList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Chat chat = dataSnapshot.getValue(Chat.class);


                    if ((TextUtils.equals(chat.getMsgFrom(), mAuth.getCurrentUser().getEmail()) && TextUtils.equals(chat.getMsgTo(), email)) ||
                            (TextUtils.equals(chat.getMsgTo(), mAuth.getCurrentUser().getEmail()) && TextUtils.equals(chat.getMsgFrom(), email)))
                        chatList.add(chat);


                }


                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
    }
}
