package com.example.book_store.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.book_store.R;
import com.example.book_store.adabters.adabter_books;
import com.example.book_store.classes.cls_bookinfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class fragment_search extends Fragment {
    AutoCompleteTextView txtsearch;
    RecyclerView recyclerView;
    ArrayList<cls_bookinfo> ALB;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_search() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static fragment_search newInstance(String param1, String param2) {
        fragment_search fragment = new fragment_search();
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
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        txtsearch = view.findViewById(R.id.txtsearch);
        recyclerView = view.findViewById(R.id.rv_books2);
        ALB = new ArrayList<>();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        final ArrayAdapter<String> autoComplete = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1);
        adabter_books  adabter = new adabter_books(getContext(),ALB);
        recyclerView.setAdapter(adabter);




        database.child("Book").child("Books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String location2 = ds.child("bookname").getValue(String.class);
                    autoComplete.add(location2);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Can not Find", Toast.LENGTH_SHORT).show();
            }
        });
        txtsearch.setAdapter(autoComplete);
        txtsearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String location1= txtsearch.getText().toString();
                searchlocation(location1);
            }

            private void searchlocation(String bookname) {
                database.child("Book").child("Books")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                ALB.clear();
                                for (DataSnapshot snapshot1 :snapshot.getChildren()){
                                    cls_bookinfo bookinfo = snapshot1.getValue(cls_bookinfo.class);
                                    if (bookinfo.getBookname().equals(bookname)){
                                        ALB.add(bookinfo);
                                        adabter.notifyDataSetChanged();
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(getContext(), "دیتابس مشکیل دارد", Toast.LENGTH_SHORT).show();
                            }
                        });


            }

        });






        return view;
    }
}