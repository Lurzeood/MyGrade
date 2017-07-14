package com.example.lurzeood.mygrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by Lurzeood on 2017/7/12 0012.
 */

public class SubjectListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        List<Subject> subjectLists = bundle.getParcelableArrayList("subjectlists");

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.activity_subject_list);

        if (fragment == null){
            fragment = SubjectListFragment.newInstance(subjectLists);
            fragmentManager.beginTransaction()
                    .add(R.id.activity_subject_list,fragment)
                    .commit();
        }

    }
}
