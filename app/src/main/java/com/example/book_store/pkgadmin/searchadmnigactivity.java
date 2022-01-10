package com.example.book_store.pkgadmin;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book_store.adabters.cls_adabter_rvbooks;
import com.example.book_store.classes.cls_bookinfo;
import com.example.book_store.databinding.ActivitySearchadmnigactivityBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class searchadmnigactivity extends AppCompatActivity {
    ActivitySearchadmnigactivityBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    ArrayList<cls_bookinfo> ALB;
    cls_adabter_rvbooks adabterbook;
    DatabaseReference database1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchadmnigactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        ALB = new ArrayList<>();
        storage = FirebaseStorage.getInstance();


//        database.getReference().child("Book").child("Books").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//            ALB.clear();
//                for (DataSnapshot snapshot1 :snapshot.getChildren()){
//                    cls_bookinfo cls_bookinfo = snapshot1.getValue(cls_bookinfo.class);
//                    ALB.add(cls_bookinfo);
//                }
//                adabterbook.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        database1 = FirebaseDatabase.getInstance().getReference();
        final ArrayAdapter<String> autoComplete = new ArrayAdapter<>(searchadmnigactivity.this,android.R.layout.simple_list_item_1);
         adabterbook = new cls_adabter_rvbooks(searchadmnigactivity.this,ALB);
        binding.rvBooks1.setAdapter(adabterbook);




        database1.child("Book").child("Books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    String location2 = ds.child("bookname").getValue(String.class);
                    autoComplete.add(location2);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(searchadmnigactivity.this, "Can not Find", Toast.LENGTH_SHORT).show();
            }
        });
        binding.txtsearch.setAdapter(autoComplete);
        binding.txtsearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String bookname= binding.txtsearch.getText().toString();
                searchlocation(bookname);
            }


        });

    }


    private void searchlocation(String bookname) {
        database1.child("Book").child("Books")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ALB.clear();
                        for (DataSnapshot snapshot1 :snapshot.getChildren()){
                            cls_bookinfo bookinfo = snapshot1.getValue(cls_bookinfo.class);
                            if (bookinfo.getBookname().equals(bookname)){
                                ALB.add(bookinfo);

                            }
                        }
                        adabterbook.notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(searchadmnigactivity.this,  error.getMessage() +"دیتابس مشکیل دارد", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}