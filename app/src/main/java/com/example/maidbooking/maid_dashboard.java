package com.example.maidbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class maid_dashboard extends AppCompatActivity {

    String nmae=null;    //new code after successful execution
    RecyclerView recyclerView_maid;
    ArrayList<String>fname_m,lname_m,contact_m,mob_m;
    MyAdapterMaid myAdapterMaid;
    DBHelperRequest dbHelperRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid_dashboard);

        dbHelperRequest=new DBHelperRequest(this);
        fname_m=new ArrayList<>();
        lname_m=new ArrayList<>();
        contact_m=new ArrayList<>();
        mob_m=new ArrayList<>();

        recyclerView_maid=findViewById(R.id.recycleview_maid);
        myAdapterMaid=new MyAdapterMaid(this,fname_m,lname_m,contact_m,mob_m);
        recyclerView_maid.setAdapter(myAdapterMaid);
        recyclerView_maid.setLayoutManager(new LinearLayoutManager(this));
        maid_profile();
        display_maidData();   //add call from maid_profile

    }

    private void maid_profile() {
        Intent intent=getIntent();
         nmae=intent.getStringExtra("user_maid");
        String nameofmaid = null;

        DBHelperMaid dbHelperMaid;
        dbHelperMaid=new DBHelperMaid(this);
        Cursor cursor=dbHelperMaid.getTheName(nmae);

        if (cursor.moveToNext())
        {
            nameofmaid=cursor.getString(1);
        }
        TextView textView=findViewById(R.id.texthellomaidname);
        textView.setText(nameofmaid.toString());

        //------
        //display_maidData();
        //==========
    }

    private void display_maidData() {
        Intent intent=getIntent();
        String nameofmaid=intent.getStringExtra("user_maid");
        Cursor cv= dbHelperRequest.getData(nameofmaid);
        //Cursor cv=dbHelperRequest.getAllData();

        if (cv.getCount()==0)
        {
            Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {

            while (cv.moveToNext())
            {
                fname_m.add(cv.getString(2));
                lname_m.add(cv.getString(3));
                contact_m.add(cv.getString(4 ));
                mob_m.add(cv.getString(5));
            }

        }

        //------------------
        //=====================
        cv.close();
    }
}