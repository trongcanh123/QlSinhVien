package com.example.qlsv.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.qlsv.helper.DateTimeHelper;
import com.example.qlsv.model.Classes;
import com.example.qlsv.model.student;

public class StudentDao {
    private SQLiteDatabase db;

    public StudentDao(SQLiteDatabase db) {
        this.db = db;
    }

    public StudentDao(Context context) {
        Dbhelper helper = new Dbhelper(context);
        this.db = helper.getWritableDatabase();
    }

    public StudentDao(View.OnClickListener onClickListener) {
    }

    public long insert( student emp) {
        ContentValues values = new ContentValues();
        values.put("id ", emp.getId());
        values.put("name", emp.getName());
        //values.put("dob", DateTimeHelper.tostring(emp.getDob()))
        return db.insert("students", null, values);

    }

    }
