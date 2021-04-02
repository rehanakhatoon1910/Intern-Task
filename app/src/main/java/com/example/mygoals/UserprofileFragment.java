package com.example.mygoals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UserprofileFragment extends Fragment {
    private ImageView imageView;
    TextView textView;
    private EditText Abt,Share,Settings;
    Button btnlogout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userprofile, container, false);

        imageView=view.findViewById(R.id.img_profile);
        textView=view.findViewById(R.id.txt_view);
        Abt=view.findViewById(R.id.ed_abt);
        Share=view.findViewById(R.id.ed_share);
        Settings=view.findViewById(R.id.ed_settings);
        btnlogout=view.findViewById(R.id.btn_logout);

        return view;
    }
}