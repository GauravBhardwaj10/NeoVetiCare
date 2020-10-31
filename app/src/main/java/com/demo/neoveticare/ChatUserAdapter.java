package com.demo.neoveticare;


import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatUserAdapter extends RecyclerView.Adapter<ChatUserAdapter.ViewHolder> {

    private Chat chat;
    private Context context;
    private List<Chat> chatList;
    String email;


    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView tvUser;

        ViewHolder(View view) {
            super(view);
            tvUser = view.findViewById(R.id.tvFromUser);
            tvUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chat = chatList.get(getAdapterPosition());
                    Intent i = new Intent(context, ChatActivity.class);
                    i.putExtra("email", chat.msgFrom);
                    context.startActivity(i);
                }
            });


        }


    }

    public ChatUserAdapter(Context mContext, List<Chat> chatList, String email) {
        this.context = mContext;
        this.chatList = chatList;
        this.email = email;


    }

    @NonNull
    @Override
    public ChatUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_user, parent, false);
        return new ChatUserAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ChatUserAdapter.ViewHolder holder, final int position) {

        chat = chatList.get(position);


        holder.tvUser.setText(chat.getMsgFrom());


    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }


}