package com.example.book_store.adabters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.book_store.R;
import com.example.book_store.classes.cls_user;
import com.example.book_store.databinding.UserslayoutBinding;
import com.example.book_store.pakage_activities.messageact2;

import java.util.ArrayList;

public class dabter_user extends RecyclerView.Adapter<dabter_user.rvclass> {
    Context context;
    ArrayList<cls_user>ALU;

    public dabter_user(Context context, ArrayList<cls_user> ALU) {
        this.context = context;
        this.ALU = ALU;
    }

    @NonNull
    @Override
    public rvclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.userslayout,parent,false);
        return new rvclass(view);

    }

    @Override
    public void onBindViewHolder(@NonNull rvclass holder, int position) {
        cls_user user = ALU.get(position);
        holder.binding.userName.setText(user.getName());
        Glide.with(context).load(user.getUserimage())
                .placeholder(R.drawable.profilepic)
                .into(holder.binding.imguser);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, messageact2.class);
                intent.putExtra("Uid",user.getId());
                intent.putExtra("Username",user.getName());
                intent.putExtra("image1",user.getUserimage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ALU.size();
    }

    public class rvclass extends RecyclerView.ViewHolder {
        UserslayoutBinding binding;
        public rvclass(@NonNull View itemView) {
            super(itemView);
            binding = UserslayoutBinding.bind(itemView);
        }
    }




}
