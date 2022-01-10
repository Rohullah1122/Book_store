package com.example.book_store.Fragment;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.book_store.R;
import com.example.book_store.classes.cls_user;
import com.example.book_store.databinding.FragmentProfiePragmentBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class profie_pragment extends Fragment {

    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseStorage storage;
    String userid;
    Uri selectedImage;
    cls_user user;
    ProgressDialog dialog;
    ArrayList<cls_user> ALU;
    private FirebaseUser currenuser;
    String name,phone;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public profie_pragment() {
        // Required empty public constructor
    }


    public static profie_pragment newInstance(String param1, String param2) {
        profie_pragment fragment = new profie_pragment();
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
        FragmentProfiePragmentBinding binding =  FragmentProfiePragmentBinding.inflate(inflater, container, false);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(getContext());
        dialog.setCancelable(false);
        dialog.setMessage("loading profile");
        dialog.show();
        database.getReference("Users").child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cls_user loginuser = dataSnapshot.getValue(cls_user.class);
                if (loginuser != null){
                    dialog.dismiss();
                    String name = loginuser.getName();
                    String phon  = loginuser.getPhone();
                    userid = loginuser.getId();
                    String img = loginuser.getUserimage();
                    binding.txtname.setText(name+"");
                    binding.txtphone.setText(phon);

                    if (img.equals("no Image")){
                        Glide.with(getActivity())
                                .load(img)
                                .placeholder(R.drawable.ic_profile)
                                .into(binding.userimage);
                    }else{
                        Glide.with(getActivity())
                                .load(loginuser.getUserimage())
                                .into(binding.userimage);

                    }
                }else {
                    Toast.makeText(getContext(), "لود نشد", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


//        binding.btnupdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                name = binding.txtname.getText().toString();
//                phone = binding.txtphone.getText().toString();
//                if (name.isEmpty()){
//                    binding.txtname.setError("please fill the name");
//                    binding.txtname.requestFocus();
//                    return;
//                }if (phone.isEmpty()){
//                    binding.txtphone.setError("please fill the phone");
//                    binding.txtphone.requestFocus();
//                    return;
//                }
//
//
//
//
//
//
//
//
//            }
//        });

        return binding.getRoot();
    }




    }



