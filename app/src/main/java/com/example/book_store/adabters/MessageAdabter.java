package com.example.book_store.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.book_store.R;
import com.example.book_store.classes.cls_msgs;
import com.example.book_store.databinding.ItemReceiveBinding;
import com.example.book_store.databinding.ItemSentBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdabter extends RecyclerView.Adapter{

    Context context;
    ArrayList<cls_msgs> messages;

    final int ITEM_SENT = 1;
    final int ITEM_RECEIVE = 2;

    String senderRoom;
    String receiverRoom;

    public MessageAdabter(Context context, ArrayList<cls_msgs> messages, String senderRoom, String receiverRoom) {
        this.context = context;
        this.messages = messages;
        this.senderRoom = senderRoom;
        this.receiverRoom = receiverRoom;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == ITEM_SENT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_sent, parent, false);
            return new SentViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_receive, parent, false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        cls_msgs message = messages.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(message.getSenderID())) {
            return ITEM_SENT;
        } else {
            return ITEM_RECEIVE;
        }
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        cls_msgs message = messages.get(position);

            if(holder.getClass() == SentViewHolder.class) {
                SentViewHolder viewHolder = (SentViewHolder)holder;
            } else {
                ReceiverViewHolder viewHolder = (ReceiverViewHolder)holder;
            }
        if(holder.getClass() == SentViewHolder.class) {
            SentViewHolder viewHolder = (SentViewHolder)holder;

            if(message.getMsgs().equals("photo")) {
                viewHolder.binding.image.setVisibility(View.VISIBLE);
                viewHolder.binding.message.setVisibility(View.GONE);
                Glide.with(context)
                        .load(message.getImageUrl())
                        .placeholder(R.drawable.profilepic)
                        .into(viewHolder.binding.image);
            }

            viewHolder.binding.message.setText(message.getMsgs());


        } else {
            ReceiverViewHolder viewHolder = (ReceiverViewHolder)holder;
            if(message.getMsgs().equals("photo")) {
                viewHolder.binding.image.setVisibility(View.VISIBLE);
                viewHolder.binding.message.setVisibility(View.GONE);
                Glide.with(context)
                        .load(message.getImageUrl())
                        .placeholder(R.drawable.profilepic)
                        .into(viewHolder.binding.image);
            }
            viewHolder.binding.message.setText(message.getMsgs());


        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }



    public class SentViewHolder extends RecyclerView.ViewHolder {

        ItemSentBinding binding;
        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSentBinding.bind(itemView);
        }
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {

        ItemReceiveBinding binding;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemReceiveBinding.bind(itemView);
        }
    }

}
