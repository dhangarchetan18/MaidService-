package com.example.maidbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class user_dashboard extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String>fname_a,lname_a,contact_a,email_a,city_a;
    MyAdapter adapter;
    DBHelperMaid helperMaid;
    DBHelper helper;
    String name=" ";
    TextView textView_name_h;



    EditText sear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_user_dashboard);

            sear = findViewById(R.id.search_view_edit_text);

            helperMaid = new DBHelperMaid(this);
            helper = new DBHelper(this);

            fname_a = new ArrayList<>();
            lname_a = new ArrayList<>();
            contact_a = new ArrayList<>();
            email_a = new ArrayList<>();
            city_a = new ArrayList<>();

            recyclerView = findViewById(R.id.recycleview);

            //-----new code second updated
            List<MyModel> data = getDataFromDatabase();
            adapter = new MyAdapter(data);
            //-------end of second code update
            //   adapter=new MyAdapter(this,fname_a,lname_a,contact_a,email_a,city_a);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            //----------------------------new code second update----------------
            sear.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    String searchQuery = charSequence.toString().toLowerCase();
                    List<MyModel> filteredData = filterData(searchQuery);
                    adapter = new MyAdapter(filteredData);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            //----------------------------end of code second update-------------
            //--------------------------------------------------
            // displayData();
            //-----------------UserProfile----------------------------
            Intent intent = getIntent();
            String username_p = intent.getStringExtra("useremail");
            Cursor cv = helper.showData(username_p);
            if (cv.moveToNext()) {
                name = cv.getString(1);
            }
            textView_name_h = findViewById(R.id.texthellousername);
            textView_name_h.setText(name);

        }




    // -------------new code second code update-----------
    private List<MyModel> getDataFromDatabase() {
        List<MyModel> data = new ArrayList<>();

        Cursor cursor = helperMaid.getData();

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String contact=cursor.getString(3);
                String email=cursor.getString(4);
                String city=cursor.getString(5);
                MyModel item = new MyModel(id, fname,lname, contact,email,city);
                data.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return data;
    }
    private List<MyModel> filterData(String query) {
        List<MyModel> filteredData = new ArrayList<>();

        DBHelperMaid helpe=new DBHelperMaid(this);
        SQLiteDatabase db=helpe.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM maid_details WHERE LOWER(city) LIKE ?", new String[]{"%" + query + "%"});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String fname = cursor.getString(1);
                String lname=cursor.getString(2);
                String contact=cursor.getString(3);
                String email=cursor.getString(5);//third 27/03/23   change 5 to 4
                String city=cursor.getString(4);//27/03/23
                MyModel item = new MyModel(id, fname,lname,contact,city,email);//27.03
                filteredData.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return filteredData;
    }
//--------------------end second code update-------------------------
    private void displayData() {
        Cursor cursor=helperMaid.getData();

        if (cursor.getCount()==0)
        {
            Toast.makeText(this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            while (cursor.moveToNext())
            {
                fname_a.add(cursor.getString(1));
                lname_a.add(cursor.getString(2));
                contact_a.add(cursor.getString(3));

                email_a.add(cursor.getString(5));//4 to 5 27..03
                city_a.add(cursor.getString(4)); //new first update//second update
            }
        }
    }

    public void navigatetoprofile(View view) {
        Intent intent=new Intent(user_dashboard.this,UserProfileActivity.class);
        startActivity(intent);
    }
}