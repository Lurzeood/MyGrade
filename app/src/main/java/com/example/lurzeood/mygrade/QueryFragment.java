package com.example.lurzeood.mygrade;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.lurzeood.mygrade.GradeDbSchema.GradeTable;

/**
 * Created by Lurzeood on 2017/7/10 0010.
 */

public class QueryFragment extends Fragment {

    private static final String TAG = "QueryFragment";

    private Button query;
    private Button toInsert;
    private Spinner subjectSpinner;
    private TextView welcomeview;
    private TextView showtimeview;

    private List<Subject> lists;
    private List<String> subjectlist;
    private SQLiteDatabase database;
    String name;

    private Callbacks callbacks;



    public static QueryFragment newInstance(String name) {

        Bundle args = new Bundle();

        QueryFragment fragment = new QueryFragment();
        args.putString("name",name);
        fragment.setArguments(args);
        return fragment;
    }

    public interface Callbacks{
        void onQueryToInsert();
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

        View view = inflater.inflate(R.layout.search_fragment,container,false);

        query = (Button) view.findViewById(R.id.query);
        toInsert = (Button) view.findViewById(R.id.to_insert_fragment);
        subjectSpinner = (Spinner) view.findViewById(R.id.subject_spinner);
        welcomeview = (TextView) view.findViewById(R.id.textview_query_welcome);
        showtimeview = (TextView) view.findViewById(R.id.textview_time_show);

        name = getArguments().getString("name");

        lists = new ArrayList<>();
        subjectlist = new ArrayList<>();
        database = new GradeTableBaseHelper(getContext()).getWritableDatabase();

        subjectlist.clear();
        subjectlist.add("All");
        Cursor cursor = database.query(GradeTable.NAME,
                null,
                GradeTable.Cols.STUDENT_NAME+" = ?",
                new String[]{name},null,null,null);
        cursor.moveToFirst();
        Log.d(TAG,"to first");
        while (!cursor.isAfterLast()){
            String subject = cursor.getString(cursor.getColumnIndex(GradeTable.Cols.SUBJECT));
            System.out.println("subject:"+subject);

            subjectlist.add(subject);
            cursor.moveToNext();
        }
        Log.d(TAG,"科目查询完成");
        System.out.println(subjectlist);
        cursor.close();

        ArrayAdapter subjectadapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_dropdown_item,subjectlist);


        final Handler timehandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                System.out.println(msg.getData().getString("time"));
                showtimeview.setText("当前时间："+msg.getData().getString("time"));

            }
        };

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message time = new Message();
                long cur = System.currentTimeMillis();
                Date date = new Date(cur);
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EEE");

                Bundle bundle = new Bundle();
                bundle.putString("time",format.format(date));
                time.setData(bundle);
                timehandler.sendMessage(time);
            }
        },0,1000);
        welcomeview.setText(name);

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lists.clear();
                String type = subjectSpinner.getSelectedItem().toString();
                System.out.println("type-------->"+type);
                queryInfo(type);
                Intent intent = new Intent(getActivity(),SubjectListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("subjectlists", (ArrayList<? extends Parcelable>) lists);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        toInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onQueryToInsert();
            }
        });

        subjectSpinner.setAdapter(subjectadapter);



        return view;
    }

    public void queryInfo(String type){
        if (type.equals("All")){
            Cursor cursor = database.query(GradeTable.NAME,
                    null,
                    GradeTable.Cols.STUDENT_NAME+" = ?",
                    new String[]{name},null,null,null);
            cursor.moveToFirst();
            Log.d(TAG,"to first");
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(GradeTable.Cols.ID));
                System.out.println("id:"+id);
                String subject = cursor.getString(cursor.getColumnIndex(GradeTable.Cols.SUBJECT));
                System.out.println("subject:"+subject);
                String soc = cursor.getString(cursor.getColumnIndex(GradeTable.Cols.SCORE));
                System.out.println("score:"+soc);
                Subject mySubject = new Subject(id,name,subject,soc);
                lists.add(mySubject);
                cursor.moveToNext();
            }
            Log.d(TAG,"查询完成");
            System.out.println(lists);
            cursor.close();
        }else {
            Cursor cursor = database.query(GradeTable.NAME,
                    null,
                    GradeTable.Cols.STUDENT_NAME+" = ? AND "+ GradeTable.Cols.SUBJECT + " = ?",
                    new String[]{name,type},null,null,null);
            cursor.moveToFirst();
            Log.d(TAG,"to first");
            while (!cursor.isAfterLast()){
                int id = cursor.getInt(cursor.getColumnIndex(GradeTable.Cols.ID));
                System.out.println("id:"+id);
                String subject = cursor.getString(cursor.getColumnIndex(GradeTable.Cols.SUBJECT));
                System.out.println("subject:"+subject);
                String soc = cursor.getString(cursor.getColumnIndex(GradeTable.Cols.SCORE));
                System.out.println("score:"+soc);
                Subject mySubject = new Subject(id,name,subject,soc);
                lists.add(mySubject);
                cursor.moveToNext();
            }
            Log.d(TAG,"查询完成");
            System.out.println(lists);
            cursor.close();
        }

    }


}
