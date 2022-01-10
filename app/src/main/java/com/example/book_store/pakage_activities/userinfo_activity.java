package com.example.book_store.pakage_activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book_store.classes.cls_user;
import com.example.book_store.databinding.ActivityUserinfoActivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class userinfo_activity extends AppCompatActivity {
    ActivityUserinfoActivityBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Uri Image;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserinfoActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        dialog = new ProgressDialog(userinfo_activity.this);
        dialog.setMessage("درجریان");

        binding.userimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "عکس خودرا انتخاب نماید"), 45);
            }
        });



        String name,mail,pass;
        Intent intent = getIntent();
        mail = intent.getStringExtra("Email");
        name = intent.getStringExtra("name");
        pass = intent.getStringExtra("password");
        binding.ptxtmail.setText(mail);
        binding.txtnamee.setText(name);
        binding.btnSaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lastname,phone;
                lastname = binding.txtlnmae.getText().toString();
                phone = binding.ptxtphone.getText().toString();


                if (lastname.isEmpty()){
                    binding.txtlnmae.setError("Please insert lastname");
                    binding.txtlnmae.requestFocus();
                    return;
                }
                if (phone.isEmpty()){
                    binding.ptxtphone.setError("Please insert Phone");
                    binding.ptxtphone.requestFocus();
                    return;
                }
                dialog.show();

                if (Image != null){
                    StorageReference reference = storage.getReference().child("userimg").child(auth.getUid());
                    reference.putFile(Image).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()){
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        String imageuri = uri.toString();
                                        String uid = auth.getUid();

                                        cls_user user = new cls_user(uid,name,lastname,mail,phone,imageuri,pass);
                                        database.getReference()
                                                .child("Users")
                                                .child(uid)
                                                .setValue(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        dialog.dismiss();
                                                        Intent intent = new Intent(userinfo_activity.this,layout_activity2.class);
                                                        startActivity(intent);
                                                        finishAffinity();
                                                    }
                                                });


                                    }
                                });

                            }
                        }
                    });

                }
                else{
                    String uid = auth.getUid();
                    cls_user user = new cls_user(uid,name,lastname,mail,phone,"No_Image",pass);
                    database.getReference()
                            .child("Users")
                            .child(uid)
                            .setValue(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(userinfo_activity.this,layout_activity2.class);
                                    startActivity(intent);
                                    finishAffinity();

                                }
                            });

                }






            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            binding.userimage.setImageURI(data.getData());
            Image = data.getData();
        }

    }

}