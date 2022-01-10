package com.example.book_store.pkgadmin.frgment_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book_store.R;
import com.example.book_store.adabters.dabter_user;
import com.example.book_store.classes.cls_user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class fragment_users extends Fragment {
    FirebaseDatabase database;
    FirebaseAuth auth;
    ArrayList ALU;
    RecyclerView recyclerView;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_users() {

    }


    public static fragment_users newInstance(String param1, String param2) {
        fragment_users fragment = new fragment_users();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        database = FirebaseDatabase.getInstance();
        ALU = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rvchat);
        dabter_user adabter = new dabter_user(getContext(),ALU);
        recyclerView.setAdapter(adabter);
        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ALU.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    cls_user user = snapshot1.getValue(cls_user.class);
                    if (!user.getId().equals(FirebaseAuth.getInstance().getUid()))
                        ALU.add(user);
                }
                adabter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return view;
    }
}