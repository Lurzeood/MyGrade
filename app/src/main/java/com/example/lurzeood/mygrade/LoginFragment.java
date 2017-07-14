package com.example.lurzeood.mygrade;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Lurzeood on 2017/7/13 0013.
 */

public class LoginFragment extends Fragment {

    private EditText nameinputedit;
    private Button login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login_fragment,container,false);

        nameinputedit = (EditText) view.findViewById(R.id.login_name_input_edit);
        login = (Button) view.findViewById(R.id.login_to_query);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MyGradeActivity.class);
                intent.putExtra("name",nameinputedit.getText().toString());
                startActivity(intent);
            }
        });

        return view;

    }
}
