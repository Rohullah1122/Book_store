package com.example.book_store.pakage_activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.book_store.databinding.ActivityCreateNewAcBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class create_new_ac extends AppCompatActivity {
    ActivityCreateNewAcBinding binding;
    FirebaseAuth auth;
    ProgressDialog dialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateNewAcBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(create_new_ac.this);


        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,mail,pass;
                name = binding.txtname.getText().toString();
                mail = binding.txtmail.getText().toString();
                pass = binding.txtpass.getText().toString();

                if (name.isEmpty()){
                    binding.txtname.setError("Please fill the Name");
                    binding.txtname.requestFocus();
                    return;
                }
                if (mail.isEmpty()){
                    binding.txtmail.setError("Please fill the Mail");
                    binding.txtmail.requestFocus();
                    return;
                }

                if (pass.isEmpty()){
                    binding.txtpass.setError("Please fill the Password");
                    binding.txtpass.requestFocus();
                    return;

                }
                dialog.show();






                auth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        dialog.dismiss();
                        Intent intent = new Intent(create_new_ac.this,userinfo_activity.class);
                        intent.putExtra("name",name);
                        intent.putExtra("Email",mail);
                        intent.putExtra("password",pass);
                        startActivity(intent);

                    }else{
                        dialog.dismiss();
                        Toast.makeText(create_new_ac.this, "error in database", Toast.LENGTH_SHORT).show();

                    }
                    }
                });



            }
        });


        binding.btnHasacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(create_new_ac.this,Login_activity.class);
                startActivity(intent);
            }
        });




    }
}