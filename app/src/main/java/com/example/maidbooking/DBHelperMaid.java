package com.example.maidbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelperMaid extends SQLiteOpenHelper {

    private final static String DBName="maiddata_first_maidapp.db";  //New code first maiddata_maidapp.db
    private final static int version=1;
    public DBHelperMaid(@Nullable Context context) {
        super(context, DBName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Info","Database is created");
        db.execSQL("create table maid_details(id integer primary key autoincrement,first_name text,last_name text,contact text,city text,email text,password text)");
        Log.d("Info","Table is created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public Boolean insertmaiduserdetails(String fname,String lname,String mob,String citym,String uemail,String pass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("first_name",fname);
        cv.put("last_name",lname);
        cv.put("contact",mob);
        cv.put("city",citym);
        cv.put("email",uemail);
        cv.put("password",pass);

        long row_id=db.insert("maid_details",null,cv);
        if (row_id>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkmaiduser(String uemail)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from maid_details where email=?",new String[]{uemail});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from maid_details where email=? and password=?",new String[]{username,password});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public Cursor getData()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from maid_details",null);
        return cursor;
    }
    public Cursor showData(String username)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from maid_details where email=?",new String[]{username});
        return cursor;
    }
    public Cursor getTheName(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from maid_details where email=?",new String[]{email});
        return cursor;
    }

}
