package com.example.qlsv.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseSQL extends SQLiteOpenHelper {
    public DatabaseSQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //Truy vấn ko trả kết quả:CREATE, DELETE, INSERT,...
    public void Database(String sql){
        SQLiteDatabase data = getWritableDatabase();
        data.execSQL(sql);
    }
    //Truy vấn có trả kết quả: SELECT
    public Cursor GetDatabase(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
