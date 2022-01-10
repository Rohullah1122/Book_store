package com.example.book_store.pkgadmin;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book_store.classes.cls_bookinfo;
import com.example.book_store.databinding.ActivityBookinsertionBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;
import java.util.Date;

public class bookinsertion extends AppCompatActivity {
    ActivityBookinsertionBinding binding;
    Uri Image1;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;
    long hid = 0;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookinsertionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage  = FirebaseStorage.getInstance();
        dialog = new ProgressDialog(bookinsertion.this);
        dialog.setTitle("Book Insertion");

        database.getReference().child("Book").child("Books").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    hid = (snapshot.getChildrenCount());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.bimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
            }
        });

        binding.btninsertbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bname,bauthor,bprice,brating,boverview,bquantity,bpage,badmingid;
                bname = binding.tvbname.getText().toString();
                bauthor = binding.tvauthor.getText().toString();
                bprice = binding.tvbprice.getText().toString();
                brating = binding.tvbrating.getText().toString();
                boverview = binding.tvbdesc.getText().toString();
                bquantity = binding.tvqty.getText().toString();
                bpage = binding.btvpages.getText().toString();
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                String bdate = day + "/" + (month+1) + "/" + year;
                if (bname.isEmpty()){
                    binding.tvbname.setError("please fill the name");
                    binding.tvbname.requestFocus();
                    return;
                }
                if (bauthor.isEmpty()){
                    binding.tvauthor.setError("please fill the name");
                    binding.tvauthor.requestFocus();
                    return;
                }
                if (bpage.isEmpty()){
                    binding.btvpages.setError("please insert the pages");
                    binding.btvpages.requestFocus();
                    return;
                }
                if (bprice.isEmpty()){
                    binding.tvbprice.setError("please fill the name");
                    binding.tvbprice.requestFocus();
                    return;
                }
                if (brating.isEmpty()){
                    binding.tvbrating.setError("please fill the name");
                    binding.tvbrating.requestFocus();
                    return;
                }
                if (boverview.isEmpty()){
                    binding.tvbdesc.setError("please fill the name");
                    binding.tvbdesc.requestFocus();
                    return;
                }
                if (bquantity.isEmpty()){
                    binding.tvqty.setError("please fill the name");
                    binding.tvqty.requestFocus();
                    return;
                }
                dialog.show();
                Date date = new Date();
                StorageReference reference = storage.getReference().child("Bookimage").child(date.getTime() +"");
                reference.putFile(Image1).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageuri = uri.toString();
                                    cls_bookinfo binfo = new cls_bookinfo();
                                    binfo.setBookname(bname);
                                    binfo.setBookauthor(bauthor);
                                    binfo.setBookImage(imageuri);
                                    binfo.setBookid(hid +1 +"");
                                    binfo.setBookrating(brating);
                                    binfo.setBookoverview(boverview);
                                    binfo.setBookpages(bpage);
                                    binfo.setBquantity(bquantity);
                                    binfo.setInsertdate(bdate);
                                    binfo.setBookprice(bprice);
                                    binfo.setBadminid(auth.getUid());
                                    database.getReference()
                                            .child("Book")
                                            .child("Books")
                                            .push()
                                            .setValue(binfo)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @SuppressLint("ResourceType")
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(bookinsertion.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                                                    dialog.dismiss();
                                                    Intent intent = new Intent(bookinsertion.this,admin_layout.class);
                                                    startActivity(intent);
//                                                    binding.btvpages.setText("");
//                                                    binding.tvqty.setText("");
//                                                    binding.tvbrating.setText("");
//                                                    binding.tvbprice.setText("");
//                                                    binding.tvbdesc.setText("");
//                                                    binding.tvauthor.setText("");
//                                                    binding.tvbname.setText("");

                                                }
                                            });

                                    String key = database.getReference("Book").child("Books").push().getKey();
                                    Toast.makeText(bookinsertion.this, key, Toast.LENGTH_SHORT).show();

                                }
                            });




                        }
                    }
                });






            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            binding.bimage.setImageURI(data.getData());
            Image1 = data.getData();
        }

    }

}