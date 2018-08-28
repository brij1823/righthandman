package com.righty.sanketpatel.righthandman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sanket Patel on 01-06-2018.
 */

public class Database extends SQLiteOpenHelper {
    private static final String Database_name="student.db";
    private String Table_name="student_table";
    private String col_1="Number";
    private String col_2="Message";
    public String col_3="Showdown";
    public String col_4="url";


    public Database(Context context) {
        super(context,Database_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("create table    "+Table_name+ " (NUMBER TEXT,MESSAGE TEXT,DAY INT,MONTH INT,YEAR INT)");
        sqLiteDatabase.execSQL("create table    "+Table_name+ " (NUMBER TEXT,MESSAGE TEXT,SHOWDOWN TEXT,URL TEXT)");
      //  ID INTEGER PRIMARY KEY AUTOINCREMENT,
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exist"+ Table_name);
        onCreate(sqLiteDatabase);
    }

    public boolean insetData(String num,String msg,String s,String url)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_1,num);
        contentValues.put(col_2,msg);
        contentValues.put(col_3,s);
        contentValues.put(col_4,url);
        long result=db.insert(Table_name,null,contentValues);

        if(result==-1) return false;
        else return true;

    }


    public Cursor getalldata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from "+Table_name,null);
        return res;
    }
}

