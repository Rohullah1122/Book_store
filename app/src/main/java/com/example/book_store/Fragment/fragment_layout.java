package com.example.book_store.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book_store.R;
import com.example.book_store.adabters.adabter_books;
import com.example.book_store.classes.cls_bookinfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;


public class fragment_layout extends Fragment {
    ArrayList<cls_bookinfo>ALB;
    FirebaseDatabase database;
    RecyclerView rvbook;
    FirebaseStorage storage;
    FirebaseAuth auth;
    AutoCompleteTextView txt1;
    RecyclerView rv1;
    DatabaseReference database1;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_layout() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static fragment_layout newInstance(String param1, String param2) {
        fragment_layout fragment = new fragment_layout();
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
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        rvbook = (RecyclerView) view.findViewById(R.id.rv_books1);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        ALB = new ArrayList<>();
        txt1 = view.findViewById(R.id.txtsearch);
        storage = FirebaseStorage.getInstance();
        database1 = FirebaseDatabase.getInstance().getReference("Book");

        adabter_books adabterbook = new adabter_books(getContext(),ALB);
        rvbook.setAdapter(adabterbook);
        database.getReference().child("Book").child("Books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ALB.clear();
                for (DataSnapshot snapshot1 :snapshot.getChildren()){
                    cls_bookinfo cls_bookinfo = snapshot1.getValue(cls_bookinfo.class);
                    ALB.add(cls_bookinfo);
                }
                adabterbook.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });















        return view;
    }









    }


