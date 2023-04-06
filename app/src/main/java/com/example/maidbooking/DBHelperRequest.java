package com.example.maidbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelperRequest extends SQLiteOpenHelper {

    private final static String DB_name="user_first_request.db";//New Code First update userrequest.db
    private final static int version=1;

    public DBHelperRequest(@Nullable Context context) {
        super(context, DB_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Info","Database is Created");
        db.execSQL("create table user_request_details(id integer primary key autoincrement,maid_username text,first_name_u text,last_name_u,user_username_e text,contact_u text)");
        Log.d("Info","Table is Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public Boolean insertuserdetails(String m_username,String fname,String lname,String user_username,String user_contact)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put("maid_username",m_username);
        cv.put("first_name_u",fname);
        cv.put("last_name_u",lname);
        cv.put("user_username_e",user_username);
        cv.put("contact_u",user_contact);

        long row_id=db.insert("user_request_details",null,cv);
        if (row_id>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Boolean checkuser(String m_username,String user_username)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user_request_details where maid_username=? and user_username_e=?",new String[]{m_username,user_username});
        if (cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Cursor getData(String usermail)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cv=db.rawQuery("select * from user_request_details where maid_username=?",new String[]{usermail});
        return cv;
    }

    public Cursor getAllData()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cv=db.rawQuery("select * from user_request_details ",null);
        return cv;
    }
}
