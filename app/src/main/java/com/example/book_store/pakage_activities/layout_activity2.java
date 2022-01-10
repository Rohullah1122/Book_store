package com.example.book_store.pakage_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.book_store.Fragment.fragment_layout;
import com.example.book_store.Fragment.fragment_search;
import com.example.book_store.Fragment.profie_pragment;
import com.example.book_store.R;
import com.example.book_store.databinding.ActivityLayoutActivity2Binding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class layout_activity2 extends AppCompatActivity {
    ActivityLayoutActivity2Binding binding;
    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLayoutActivity2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        getSupportActionBar().hide();

        fragment = new fragment_layout();
        loadfragment(fragment);


        binding.nbSettings.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.btn_orders:
                        fragment = new fragment_search();
                        loadfragment(fragment);
                        break;

                    case R.id.btn_Profile:
                        fragment = new profie_pragment();
                        loadfragment(fragment);
                        break;

                    case R.id.btn_layout:
                        fragment = new fragment_layout();
                        loadfragment(fragment);
                        break;

                    case R.id.btn_seting:
                        Intent intent = new Intent(layout_activity2.this,setting_activity.class);
                        startActivity(intent);
                        break;

                }
            }
        });



    }
    public void loadfragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout,fragment).commit();
    }
}