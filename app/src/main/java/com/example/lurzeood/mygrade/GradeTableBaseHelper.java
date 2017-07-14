package com.example.lurzeood.mygrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.lurzeood.mygrade.GradeDbSchema.*;

/**
 * Created by Lurzeood on 2017/7/11 0011.
 */

public class GradeTableBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "gradeBase.db";

    public GradeTableBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ GradeTable.NAME + "("+
        GradeTable.Cols.ID +  " integer primary key autoincrement,"+
                GradeTable.Cols.STUDENT_NAME + "," +
                GradeTable.Cols.SUBJECT + "," +
                GradeTable.Cols.SCORE + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
