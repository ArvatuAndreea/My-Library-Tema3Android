package com.example.mylibrary.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.mylibrary.R;
import com.example.mylibrary.fragments.WelcomeFragment;
import com.example.mylibrary.interfaces.OnFragmentActivityCommunication;

public class WelcomeActivity extends AppCompatActivity implements OnFragmentActivityCommunication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        onAddWelcomeFragment();
    }

    private void onAddWelcomeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fly_container, WelcomeFragment.newInstance(), WelcomeFragment.TAG_FRAGMENT_WELCOME);
        fragmentTransaction.commit();
    }

    @Override
    public void onReplaceFragment(String TAG) {

    }
}
