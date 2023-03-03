package com.example.aseels;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Button btnLogInMain, btnSignUpMain, btnAddResMAIN, btnAllRestMAIN;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        func();
    }

    public void func() {

        btnLogInMain = getView().findViewById(R.id.btnLogInMain);
        btnSignUpMain = getView().findViewById(R.id.btnSignUpMain);
        btnSignUpMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpFragment SignUpFragment = new SignUpFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.FrameLayout, SignUpFragment, SignUpFragment.getTag()).commit();
            }
        });
        btnLogInMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LogInFragment LogInFragment = new LogInFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.FrameLayout, LogInFragment, LogInFragment.getTag()).commit();
            }
        });
    }
}