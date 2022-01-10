package com.example.book_store.adabters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.book_store.R;
import com.example.book_store.classes.cls_bookinfo;
import com.example.book_store.databinding.LayoutPurchaselistBinding;

import java.util.ArrayList;

public class adabter_sale extends RecyclerView.Adapter<adabter_sale.rv1> {
    Context context;
    ArrayList<cls_bookinfo> ALU;

    public adabter_sale(Context context, ArrayList<cls_bookinfo> ALU) {
        this.context = context;
        this.ALU = ALU;
    }


    @NonNull
    @Override
    public rv1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_purchaselist, parent, false);
        return new rv1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rv1 holder, int position) {
        cls_bookinfo binfo = ALU.get(position);
        holder.binding.bname.setText(binfo.getBookname());
        Glide.with(context).load(binfo.getBookImage())
                .placeholder(R.drawable.profilepic)
                .into(holder.binding.bimge);
    }


    @Override
    public int getItemCount() {
        return ALU.size();
    }
    public class rv1 extends RecyclerView.ViewHolder {
        LayoutPurchaselistBinding binding;

        public rv1(@NonNull View itemView) {
            super(itemView);
            binding = LayoutPurchaselistBinding.bind(itemView);
        }
    }
}

