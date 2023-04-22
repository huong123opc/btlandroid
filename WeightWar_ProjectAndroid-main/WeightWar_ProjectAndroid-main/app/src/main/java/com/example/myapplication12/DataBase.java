package com.example.myapplication12;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    //taocsdl
    public  void  QueryData(String SQL)
    {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(SQL);
    }

    //thực hiện các câu truy vấn
    public Cursor Getdata(String SQl)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(SQl,null);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
