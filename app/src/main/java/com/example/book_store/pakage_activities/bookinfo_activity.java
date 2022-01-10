package com.example.book_store.pakage_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.book_store.Fragment.bottom_sheetfragment;
import com.example.book_store.databinding.ActivityBookinfoActivityBinding;

public class bookinfo_activity extends AppCompatActivity {
    ActivityBookinfoActivityBinding binding;
    String name;
    String bimg;
    String bid;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookinfoActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
//        String name = intent.getStringExtra("bname");
        name = intent.getStringExtra("bname");
        binding.btvpages.setText("pages____"+intent.getStringExtra("bpage"));
        binding.tvbdesc.setText(intent.getStringExtra("boverview"));
        binding.tvbname.setText(name);
        binding.tvbprice.setText("price____"+intent.getStringExtra("bprice"));
        binding.tvbrating.setText("rating____"+intent.getStringExtra("brating"));
        binding.tvauthor.setText("Author_____"+intent.getStringExtra("bauthor"));
        String adminid = intent.getStringExtra("adminid");

        bimg = intent.getStringExtra("bimage");
        bid = intent.getStringExtra("bid");
        String bdate = intent.getStringExtra("bdate");
        String bqty =intent.getStringExtra("bquantity");
        Glide.with(bookinfo_activity.this).load(bimg)
                .into(binding.bimage);



        binding.btnorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("userid", adminid);
                bundle.putString("username",name);
                bundle.putString("img",bimg);
                bottom_sheetfragment fragment = new bottom_sheetfragment();
                    fragment.show(getSupportFragmentManager(),fragment.getTag());
                fragment.setArguments(bundle);
            }
        });

//        getSupportActionBar().hide();



    }

//    public void loadfragment(Fragment fragment){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.replace(R.id.frmloutorder,fragment).commit();
//    }

}