package com.example.lurzeood.mygrade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class MyGradeActivity extends AppCompatActivity
        implements QueryFragment.Callbacks ,InsertFragment.Callbacks{

    String name;

    /**
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grade);

        name = getIntent().getStringExtra("name");

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.mygrade_activity);

        if (fragment == null){
            fragment = QueryFragment.newInstance(name);
            fragmentManager.beginTransaction()
                    .add(R.id.mygrade_activity,fragment)
                    .commit();
        }
    }

    @Override
    public void onQueryToInsert() {
        Fragment newFragment = InsertFragment.newInstance(name);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mygrade_activity,newFragment)
                .commit();
    }

    @Override
    public void onInsertToQuery() {
        Fragment newFragment = QueryFragment.newInstance(name);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mygrade_activity,newFragment)
                .commit();
    }
}
