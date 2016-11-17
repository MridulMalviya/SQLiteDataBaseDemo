package com.malviya.sqlitedatabasedemo.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.EditText;

import com.malviya.sqlitedatabasedemo.constant.Constant;
import com.malviya.sqlitedatabasedemo.model.UserNameDataModel;

/**
 * Created by Prafulla on 11/16/2016.
 */

public class SQLiteOperation  {
  private SQLiteOperation(){}
    private static SQLiteOperation instance;
    public static SQLiteOperation getInstance(){
        if(instance==null){
            synchronized (SQLiteOperation.class)
            {
                if(instance==null){
                    instance=new SQLiteOperation();
                }
            }
        }
        return instance;
    }
   public boolean insertData(Context pContext, EditText pName, EditText pSurName) {
        DBCreateClass dbCreateClass = new DBCreateClass(pContext);
        SQLiteDatabase sqLiteDatabase = dbCreateClass.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Constant.NAME, pName.getText().toString());
        values.put(Constant.SURNAME, pSurName.getText().toString());
        sqLiteDatabase.insert(Constant.TABLE_NAME, null, values);
     return true;
    }
    public boolean updateData(Context pContext, EditText pName, EditText pSurName,int pId) {
        Log.i("DB++","updateData : "+pId);
        DBCreateClass dbCreateClass = new DBCreateClass(pContext);
        SQLiteDatabase sqLiteDatabase = dbCreateClass.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constant.NAME, pName.getText().toString());
        values.put(Constant.SURNAME, pSurName.getText().toString());
        sqLiteDatabase.update(Constant.TABLE_NAME, values,Constant.ID+"=?",new String[] {String.valueOf(pId)});
        return true;
    }
    public void getDeatils(Context pContext) {
        DBCreateClass dbCreateClass = new DBCreateClass(pContext);
        SQLiteDatabase sqLiteDatabase = dbCreateClass.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * from "+Constant.TABLE_NAME,null);
        if(cursor.moveToFirst())
            do {
                Log.i("DB++"," "+cursor.getInt(0)+" "+cursor.getString(1)+" "+cursor.getString(2));
                new UserNameDataModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2));

            } while (cursor.moveToNext());
        cursor.close();
    }
    public boolean deleteData(Context pContext, int  pID) {
        Log.i("DB++","deleteData : "+pID);
        DBCreateClass dbCreateClass = new DBCreateClass(pContext);
        SQLiteDatabase sqLiteDatabase = dbCreateClass.getWritableDatabase();
        sqLiteDatabase.delete(Constant.TABLE_NAME,Constant.ID+"=?",new String[] {String.valueOf(pID)});
        return true;
    }


}
