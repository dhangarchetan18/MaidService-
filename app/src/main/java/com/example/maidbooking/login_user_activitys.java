package com.example.maidbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_user_activitys extends AppCompatActivity {

Button userreg;
EditText user_email,user_pass;
DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_activitys);

        user_email=findViewById(R.id.editTextTextEmailAddress);
        user_pass=findViewById(R.id.editTextTextPassword2);

        helper=new DBHelper(this);

userreg=findViewById(R.id.registeruserbutton3);
        userreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(login_user_activitys.this,user_registrations.class);
                startActivity(intent);
            }
        });
    }

    public void userloginbutton(View view) {
        String user_email_s=user_email.getText().toString();
        String user_pass_s=user_pass.getText().toString();

        if (TextUtils.isEmpty(user_email_s) || TextUtils.isEmpty(user_pass_s))
        {
            Toast.makeText(this, "All Fields Required", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Boolean checkusps=helper.checkusernamepassword(user_email_s,user_pass_s);
            if (checkusps==true)
            {
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(login_user_activitys.this,user_dashboard.class);
                intent.putExtra("useremail",user_email_s);

                //------------------------------New Code---------------------------------------
                Cursor cv=helper.showData(user_email_s);
                String userfname_string=null;
                String userlname_string=null;
                String usercontact_string=null;
                String usermob_string=null;
                if (cv.moveToNext())
                {
                    userfname_string=cv.getString(1);
                    userlname_string=cv.getString(2);
                    usermob_string=cv.getString(3);
                    usercontact_string=cv.getString(4);
                }
                IntentValueHol intentValueHol=new IntentValueHol();
                intentValueHol.first_name_value=userfname_string;
                intentValueHol.last_name_value=userlname_string;
                intentValueHol.email_value=usercontact_string;
                intentValueHol.mob_value=usermob_string;
                //-----------------------------End New Code-------------------------------------
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this,"Invalid Email and Password",Toast.LENGTH_SHORT).show();

            }
        }
    }
}