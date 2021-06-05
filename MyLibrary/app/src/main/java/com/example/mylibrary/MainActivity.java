package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onAddWelcomeFragment();
    }

    private void onAddWelcomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       // fragmentTransaction.add(R.id.fly_container, WelcomeFragment.newInstance(), WelcomeFragment.TAG_FRAGMENT_WELCOME);
        fragmentTransaction.commit();
    }
}