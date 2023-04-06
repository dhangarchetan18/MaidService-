package com.example.maidbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class user_registrations extends AppCompatActivity {

    EditText fname,lname,contactu,email,pass;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registrations);

        fname=findViewById(R.id.ureditextfirstname);
        lname=findViewById(R.id.ureditextlasttname);
        contactu=findViewById(R.id.ureditTextTContactAddress);
        email=findViewById(R.id.ureditTextTextEmailAddress);
        pass=findViewById(R.id.ureditTextTextPassword2);

        helper=new DBHelper(this);
    }

    public void userresubmit(View view) {
        String fname_s=fname.getText().toString();
        String lname_s=lname.getText().toString();
        String contactu_s=contactu.getText().toString();
        String email_s=email.getText().toString();
        String pass_s=pass.getText().toString();

        if (TextUtils.isEmpty(fname_s) || TextUtils.isEmpty(lname_s) || TextUtils.isEmpty(email_s) || TextUtils.isEmpty(pass_s) || TextUtils.isEmpty(contactu_s))
        {
            Toast.makeText(this, "All Fields Required", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Boolean checku=helper.checkuser(email_s);
            if (checku==false)
            {
                Boolean insert=helper.insertuserdetails(fname_s,lname_s,contactu_s,email_s,pass_s);
                if (insert==true)
                {
                    Toast.makeText(this, "Record inserted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this,login_user_activitys.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(this,"Record not inserted",Toast.LENGTH_SHORT).show();
                }

            }
            else {
                Toast.makeText(this,"Already user Registered",Toast.LENGTH_SHORT).show();
            }
        }
    }
}