package com.zexus.next.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zexus.next.base.NextSqliteDataBase;

public class ItemListSqliteOpenHelper {
    public NextSqliteDataBase mNextSqliteDataBase;

    public ItemListSqliteOpenHelper(Context context) {
        mNextSqliteDataBase = new NextSqliteDataBase(context, "com.zexus.next", null, 1);
    }

    public long addItem(String name, String url) {
        SQLiteDatabase mSQLiteDatabase = mNextSqliteDataBase.getWritableDatabase();
        Cursor mCursor = mSQLiteDatabase.query(mNextSqliteDataBase.ITEM_DETAIL, null, "name=?", new String[]{name}, null, null, null);
        if (mCursor.getCount() == 0) {
            ContentValues mContentValues = new ContentValues();
            mContentValues.put("name", name);
            mContentValues.put("url", url);
            return mSQLiteDatabase.insert(mNextSqliteDataBase.ITEM_DETAIL, null, mContentValues);
        } else {
            mCursor.close();
            mSQLiteDatabase.close();
            return -1;
        }

    }

    public int deleteItem(String name) {
        SQLiteDatabase mSQLiteDatabase = mNextSqliteDataBase.getReadableDatabase();
        int mResult = mSQLiteDatabase.delete(mNextSqliteDataBase.ITEM_DETAIL, "name=?", new String[]{name});
        mSQLiteDatabase.close();
        return mResult;
    }


}
