package com.example.book_store.pkgadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.book_store.R;
import com.example.book_store.classes.cls_user;
import com.example.book_store.databinding.ActivityAdminLayoutBinding;
import com.example.book_store.pkgadmin.frgment_admin.fragment_sales;
import com.example.book_store.pkgadmin.frgment_admin.fragment_users;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class admin_layout extends AppCompatActivity {
    ActivityAdminLayoutBinding binding;
    Fragment fragment;
    FirebaseDatabase database;
    FirebaseAuth auth;
    cls_user user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        database.getReference("Users").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(cls_user.class);
                if (user != null){
                    View header = binding.drawer.getHeaderView(0);
                    TextView tv1 = (TextView) header.findViewById(R.id.txtname1);
                    ImageView im1 = (ImageView) header.findViewById(R.id.userimage);
                    tv1.setText(user.getName());
                    Glide.with(admin_layout.this).load(user.getUserimage()).into(im1);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });




        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, binding.drawerlayout, binding.toolbar, R.string.open_navigation, R.string.close_navigation);
        binding.drawerlayout.addDrawerListener(toggle);
        toggle.syncState();


        fragment = new fragment_users();

        loadfragment(fragment);
        binding.drawerlayout.closeDrawer(GravityCompat.START);
        binding.drawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.btn_users:
                        fragment = new fragment_users();
                        loadfragment(fragment);
                        binding.drawerlayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.btn_sales:
                        fragment = new fragment_sales();
                        loadfragment(fragment);
                        binding.drawerlayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.btn_insertbook:
                        Intent intent = new Intent(admin_layout.this,bookinsertion.class);
                        startActivity(intent);
                        break;

                    case R.id.bookinfo:
                        Intent intent1 = new Intent(admin_layout.this,searchadmnigactivity.class);
                        startActivity(intent1);
                        break;
                }
                return true;
            }
        });



    }

    public void loadfragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.framelayout,fragment).commit();
        binding.drawerlayout.closeDrawer(GravityCompat.START);
    }


    public void headerlayout(String name,String imge){
        View view = getLayoutInflater().inflate(R.layout.header_layout,null);
        ImageView img = (ImageView) view.findViewById(R.id.userimage);
        TextView tv = (TextView) view.findViewById(R.id.txtname1);
        Glide.with(admin_layout.this)
                .load(imge)
                .into(img);
                tv.setText(name);

    }

}