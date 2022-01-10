package com.example.book_store.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.book_store.R;
import com.example.book_store.pakage_activities.message_activity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class bottom_sheetfragment extends BottomSheetDialogFragment {
    ImageButton btncall,btnmsg;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public bottom_sheetfragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static bottom_sheetfragment newInstance(String param1, String param2) {
        bottom_sheetfragment fragment = new bottom_sheetfragment();
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
        View view =  inflater.inflate(R.layout.fragment_bottom_sheetfragment, container, false);

        btncall = view.findViewById(R.id.btncall);
        btnmsg = view.findViewById(R.id.btnmsg);

        btnmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = getArguments().getString("username");
                String adminid = getArguments().getString("userid");
                String bimg = getArguments().getString("bimg");



                Intent intent = new Intent(getActivity(), message_activity.class);
                intent.putExtra("userid",adminid);
                intent.putExtra("username",name);
                intent.putExtra("image",bimg);
                startActivity(intent);



            }
        });

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nu = "93799202021";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+nu));
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else
                {
                    startActivity(callIntent);
                }
            }
        });

        return view;

    }
}