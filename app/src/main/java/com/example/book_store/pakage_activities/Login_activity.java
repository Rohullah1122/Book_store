package com.example.book_store.pakage_activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book_store.databinding.ActivityLoginActivityBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login_activity extends AppCompatActivity {
    ActivityLoginActivityBinding binding;
    FirebaseAuth auth;
    ProgressDialog dialog;

//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = auth.getCurrentUser();
//        if(currentUser != null){
//            Intent intent = new Intent(Login_activity.this,activity_layout.class);
//            startActivity(intent);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setTitle("loading");
//        getSupportActionBar().hide();

      if (auth.getCurrentUser() != null){
            startActivity(new Intent(Login_activity.this,layout_activity2.class));
            finishAffinity();
        }


        binding.btnReateacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_activity.this,create_new_ac.class);
                startActivity(intent);
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    String mail,pass;
                    mail = binding.txtemail.getText().toString();
                    pass = binding.txtpass.getText().toString();
                    if (mail.isEmpty()){
                        binding.txtemail.setError("please insert mail");
                        binding.txtemail.requestFocus();
                        return;
                    }
                    if (pass.isEmpty()){
                        binding.txtpass.setError("please insert mail");
                        binding.txtpass.requestFocus();
                        return;
                    }
                        dialog.show();



                        auth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    dialog.dismiss();
                                    Toast.makeText(Login_activity.this, "Successfully run", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Login_activity.this,layout_activity2.class));
                                }    else{
                                    dialog.dismiss();
                                    Toast.makeText(Login_activity.this, "Can not" +task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }

                });

            }


        });


    }

}