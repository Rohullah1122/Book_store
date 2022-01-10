package com.example.book_store.pkgadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.book_store.databinding.ActivityUpdateActivityBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class update_activity extends AppCompatActivity {
        ActivityUpdateActivityBinding binding;
        String bid,name,bimage,price,adminid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        name = intent.getStringExtra("bname");
        adminid = intent.getStringExtra("adminid");
        binding.btvpages.setText("pages____"+intent.getStringExtra("bpage"));
        binding.tvbdesc.setText(intent.getStringExtra("boverview"));
        binding.tvbname.setText(name);
        binding.tvbprice.setText("price____"+intent.getStringExtra("bprice"));
        binding.tvbrating.setText("rating____"+intent.getStringExtra("brating"));
        binding.tvauthor.setText("Author_____"+intent.getStringExtra("bauthor"));
        String adminid = intent.getStringExtra("adminid");

        bimage = intent.getStringExtra("bimage");
        bid = intent.getStringExtra("bid");
        String bdate = intent.getStringExtra("bdate");
        String bqty =intent.getStringExtra("bquantity");




        Glide.with(update_activity.this).load(bimage)
                .into(binding.bimage);


        binding.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletebook(bid);



            }
        });

    }

    public void deletebook(String bookid){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Book").child("Books").orderByChild("bookid").equalTo(bookid);
        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                    Intent in = new Intent(update_activity.this,admin_layout.class);
                    update_activity.this.startActivity(in);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}