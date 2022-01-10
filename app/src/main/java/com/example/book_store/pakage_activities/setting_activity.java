
package com.example.book_store.pakage_activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.book_store.R;
import com.example.book_store.classes.cls_rating;
import com.example.book_store.databinding.ActivitySettingActivityBinding;
import com.example.book_store.pkgadmin.admin_layout;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class setting_activity extends AppCompatActivity {
    ActivitySettingActivityBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();


        binding.btnloginadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(setting_activity.this);
                View view = getLayoutInflater().inflate(R.layout.layout_adminlogin, null);
                alert.setView(view);
                alert.setCancelable(false);
                EditText Name = view.findViewById(R.id.txtadminname);
                EditText pass = view.findViewById(R.id.txtadmipass);
                Button btnlogin = view.findViewById(R.id.btnloginadmin2);
                btnlogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String adminname = Name.getText().toString();
                        String adminpass = pass.getText().toString();
                        if (adminname.isEmpty()) {
                            Name.setError("insert Name");
                            Name.requestFocus();
                            return;
                        }
                        if (adminpass.isEmpty()) {
                            pass.setError("insert password");
                            pass.requestFocus();
                            return;
                        }
                        if (adminname.equals("admin") && adminpass.equals("admin")) {
                            Intent intent = new Intent(setting_activity.this, admin_layout.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(setting_activity.this, "Wrong name or pass", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.setCancelable(true);
                    }
                });
                alert.show();


            }
        });

        binding.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                androidx.appcompat.app.AlertDialog.Builder alert = new androidx.appcompat.app.AlertDialog.Builder(setting_activity.this);
                alert.setTitle("Comment");
                View view = getLayoutInflater().inflate(R.layout.comment_layout, null);
                alert.setView(view);
                alert.setCancelable(false);
                EditText Name = view.findViewById(R.id.comment_user);
                Button btncomment = view.findViewById(R.id.btn_comment1);
                btncomment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String feedback = Name.getText().toString();
                        if (feedback.isEmpty()) {
                            Name.setError("لوطفن بنوسید");
                            Name.requestFocus();
                            return;
                        } else {
                            cls_rating cls_rating = new cls_rating();
                            cls_rating.setUid(auth.getUid());
                            cls_rating.setFeedback(feedback);
                            database.getReference().child("feedback").child("feedbacks").push()
                                    .setValue(cls_rating).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(setting_activity.this, "tnx for your comment", Toast.LENGTH_SHORT).show();
                                    Name.setText("");
                                    alert.setCancelable(true);
                                }
                            });
                        }


                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert.setCancelable(true);
                    }
                });

                alert.show();

            }
        });

        binding.btnRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                androidx.appcompat.app.AlertDialog.Builder alert1 = new androidx.appcompat.app.AlertDialog.Builder(setting_activity.this);
                alert1.setTitle("درج رات");
                View view1 = getLayoutInflater().inflate(R.layout.rating_bar, null);
                alert1.setView(view1);
                alert1.setCancelable(false);

                alert1.setPositiveButton("زخیره", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RatingBar ratingbar = view1.findViewById(R.id.ratingBar);
                        int rating = ratingbar.getNumStars();
                        if (rating == 0) {
                            Toast.makeText(setting_activity.this, "لوطفن رات نماید", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            cls_rating cls_rating = new cls_rating();
                            cls_rating.setUid(auth.getUid());
                            cls_rating.setUname(rating + "");
                            database.getReference().child("rating").child("rates").push()
                                    .setValue(cls_rating).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(setting_activity.this, "ممنون از نظریات شما", Toast.LENGTH_SHORT).show();
                                    alert1.setCancelable(true);
                                }
                            });
                        }
                    }
                });

                alert1.setNegativeButton("کنسل", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alert1.setCancelable(true);
                    }
                });

                alert1.show();


            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(setting_activity.this, MainActivity.class);
                startActivity(intent);
                setting_activity.this.finishAffinity();

            }
        });


    }
}

