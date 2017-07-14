package com.example.lurzeood.mygrade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lurzeood on 2017/7/13 0013.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.login_activity);

        if (fragment == null){
            fragment = new LoginFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.login_activity,fragment)
                    .commit();
        }
    }
}
