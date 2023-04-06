package com.example.maidbooking;

import android.accessibilityservice.GestureDescription;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME="userdata_first_maidapp.db";  //change name userdata_maidapp.db
    private final static int version=1;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Info","Database is Created");
        db.execSQL("create table userdetails_user(id integer primary key autoincrement,first_name text,last_name text,contact text,email text,password text)");
        Log.d("Info","Table is created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public Boolean insertuserdetails(String fname,String lname,String ucontact,String uemail,String pass)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("first_name",fname);
        cv.put("last_name",lname);
        cv.put("contact",ucontact);
        cv.put("email",uemail);
        cv.put("password",pass);

        long row_id=db.insert("userdetails_user",null,cv);
        if (row_id>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Boolean checkuser(String uemail)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from userdetails_user where email=?",new String[]{uemail});
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
        Cursor cursor=db.rawQuery("select * from userdetails_user where email=? and password=?",new String[]{username,password});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else {
            return false;
        }
    }
    public Cursor showData(String useremail)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from userdetails_user where email=?",new String[]{useremail});
        return cursor;

    }

}
