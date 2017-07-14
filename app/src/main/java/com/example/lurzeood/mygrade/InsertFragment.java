package com.example.lurzeood.mygrade;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Lurzeood on 2017/7/12 0012.
 */

public class InsertFragment extends Fragment {

    private Button toQuery;
    private EditText subjectEdit;
    private EditText scoreEdit;
    private Button insert;

    private Callbacks callbacks;

    private ContentValues values;
    private SQLiteDatabase database;

    public static InsertFragment newInstance(String name) {

        Bundle args = new Bundle();

        args.putString("name",name);
        InsertFragment fragment = new InsertFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public interface Callbacks{
        public void onInsertToQuery();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callbacks = (Callbacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callbacks = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.insert_fragment,container,false);

        toQuery = (Button) view.findViewById(R.id.to_query_fragment);
        subjectEdit = (EditText) view.findViewById(R.id.subject_input_edit);
        scoreEdit = (EditText) view.findViewById(R.id.score_input_edit);
        insert = (Button) view.findViewById(R.id.insert);

        values = new ContentValues();
        database = new GradeTableBaseHelper(getContext()).getWritableDatabase();

        final String name = getArguments().getString("name");

        toQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onInsertToQuery();
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = subjectEdit.getText().toString();
                String score = scoreEdit.getText().toString();
                insertInfo(subject,score,name);
                subjectEdit.setText("");
                scoreEdit.setText("");
                subjectEdit.requestFocus();
            }
        });

        return view;
    }

    private void insertInfo(String subject,String score,String name){
        values.put(GradeDbSchema.GradeTable.Cols.STUDENT_NAME,name);
        values.put(GradeDbSchema.GradeTable.Cols.SUBJECT,subject);
        values.put(GradeDbSchema.GradeTable.Cols.SCORE,score);
        database.insert(GradeDbSchema.GradeTable.NAME,null,values);
        Toast.makeText(getContext(),"数据插入成功！",Toast.LENGTH_SHORT).show();
    }

}
