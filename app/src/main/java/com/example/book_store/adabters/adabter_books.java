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
import com.example.book_store.pakage_activities.bookinfo_activity;
import com.example.book_store.classes.cls_bookinfo;
import com.example.book_store.databinding.LayoutBooksBinding;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class adabter_books extends RecyclerView.Adapter<adabter_books.bookviewhoder>{
    FirebaseDatabase database;
    Context context;
    ArrayList<cls_bookinfo> ALB;
//    FirebaseAuth auth;

    public adabter_books(Context context, ArrayList<cls_bookinfo> ALB) {
        this.context = context;
        this.ALB = ALB;
//        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public bookviewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_books,parent,false);
        return new bookviewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bookviewhoder holder, int position) {
        cls_bookinfo book_atterbiutes = ALB.get(position);
        holder.binding.tvbname.setText(book_atterbiutes.getBookname());
        holder.binding.tvrating.setText(book_atterbiutes.getBookrating());
        Glide.with(context).load(book_atterbiutes.getBookImage())
                .placeholder(R.drawable.profilepic)
                .into(holder.binding.imgbookuser);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, bookinfo_activity.class);
                intent.putExtra("bname",book_atterbiutes.getBookname());
                intent.putExtra("bauthor",book_atterbiutes.getBookauthor());
                intent.putExtra("brating",book_atterbiutes.getBookrating());
                intent.putExtra("bimage",book_atterbiutes.getBookImage());
                intent.putExtra("bpage",book_atterbiutes.getBookpages());
                intent.putExtra("boverview",book_atterbiutes.getBookoverview());
                intent.putExtra("bquantity",book_atterbiutes.getBquantity());
                intent.putExtra("bdate",book_atterbiutes.getInsertdate());
                intent.putExtra("bimage",book_atterbiutes.getBookImage());
                intent.putExtra("bprice",book_atterbiutes.getBookprice());
                intent.putExtra("adminid",book_atterbiutes.getBadminid());
                context.startActivity(intent);
            }
        });

        holder.binding.btnMoreinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, bookinfo_activity.class);
                intent.putExtra("bname",book_atterbiutes.getBookname());
                intent.putExtra("bauthor",book_atterbiutes.getBookauthor());
                intent.putExtra("brating",book_atterbiutes.getBookrating());
                intent.putExtra("bimage",book_atterbiutes.getBookImage());
                intent.putExtra("bpage",book_atterbiutes.getBookpages());
                intent.putExtra("boverview",book_atterbiutes.getBookoverview());
                intent.putExtra("bquantity",book_atterbiutes.getBquantity());
                intent.putExtra("bdate",book_atterbiutes.getInsertdate());
                intent.putExtra("bimage",book_atterbiutes.getBookImage());
                intent.putExtra("bprice",book_atterbiutes.getBookprice());
                intent.putExtra("adminid",book_atterbiutes.getBadminid());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return ALB.size();
    }

    public class bookviewhoder extends RecyclerView.ViewHolder {
        LayoutBooksBinding binding;
        public bookviewhoder(@NonNull View itemView) {
            super(itemView);
            binding = LayoutBooksBinding.bind(itemView);
        }


    }
}
