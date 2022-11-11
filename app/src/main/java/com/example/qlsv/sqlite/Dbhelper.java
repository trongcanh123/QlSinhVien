package com.example.qlsv.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    private static final String DB_NAME= "polysys";
    private static final int DB_VERSION= 1;
    public Dbhelper(@Nullable Context context) {
        super(context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String classesSql = "CREATE TABLE classes (id integer primary key autoincrement, "+" name text not null )";
       // String studentsSql = "CREATE TABLE students (id text primary key, "+"name text not null, classid  integer , dob text,"+"FOREIGN KEY (classid) REFERENCES classes(id))";
        sqLiteDatabase.execSQL(classesSql);
        //sqLiteDatabase.execSQL(studentsSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String classesSql = " DROP TABLE IF EXISTS classes";
        String  studentsSql = " DROP TABLE IF EXISTS classes";
        sqLiteDatabase.execSQL(classesSql);
        sqLiteDatabase.execSQL(studentsSql);
        onCreate(sqLiteDatabase);

    }
}
