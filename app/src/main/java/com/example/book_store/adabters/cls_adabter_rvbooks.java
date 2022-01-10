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
import com.example.book_store.classes.cls_bookinfo;
import com.example.book_store.databinding.LayoutUpdatebookBinding;
import com.example.book_store.pkgadmin.update_activity;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class cls_adabter_rvbooks extends RecyclerView.Adapter<cls_adabter_rvbooks.rvadabter> {
    Context context;
    ArrayList<cls_bookinfo> ALB;
    FirebaseAuth auth;


    public cls_adabter_rvbooks(Context context, ArrayList<cls_bookinfo> ALB) {
        this.context = context;
        this.ALB = ALB;

    }


    @NonNull
    @Override
    public rvadabter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_updatebook,parent,false);
        return new rvadabter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rvadabter holder, int position) {
        cls_bookinfo book_atterbiutes = ALB.get(position);
        holder.binding.tvbname.setText(book_atterbiutes.getBookname());
        holder.binding.tbdate.setText(book_atterbiutes.getInsertdate());
        holder.binding.tvbprice.setText(book_atterbiutes.getBookprice());
        Glide.with(context).load(book_atterbiutes.getBookImage())
                .placeholder(R.drawable.profilepic)
                .into(holder.binding.bimage);
        holder.binding.btnMoreinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, update_activity.class);
                intent.putExtra("bid",book_atterbiutes.getBookid());
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

    public class rvadabter extends RecyclerView.ViewHolder {
        LayoutUpdatebookBinding binding;
        public rvadabter(@NonNull View itemView) {
            super(itemView);
            binding = LayoutUpdatebookBinding.bind(itemView);
        }
    }

}
