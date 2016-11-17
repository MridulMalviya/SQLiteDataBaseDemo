package com.malviya.sqlitedatabasedemo.utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.malviya.sqlitedatabasedemo.constant.Constant;

/**
 * Created by Prafulla on 11/16/2016.
 */

public class DBCreateClass extends SQLiteOpenHelper {

    private static final String DBNAME="USERDB.db";
    private static final int VERSION=3;


    private static final String CREATE_TABLE="Create Table "+ Constant.TABLE_NAME+"("+Constant.ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+Constant.NAME+" Text NOT NULL,"+Constant.SURNAME+" Text NOT NULL)";

    public DBCreateClass(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Constant.TABLE_NAME);
        onCreate(db);
    }
}
