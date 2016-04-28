package com.zexus.next.base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NextSqliteDataBase extends SQLiteOpenHelper {
    public final String ITEM_DETAIL = "itemDetail";
    public final String ITEM_LIST = "itemList";

    public NextSqliteDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table itemDetail (id integer primary key autoincrement, name varchar(20), url varchar(20), link varchar(20)) ");
        sqLiteDatabase.execSQL("create table itemList (id integer primary key autoincrement, type varchar(20), name varchar(20), url varchar(40))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
