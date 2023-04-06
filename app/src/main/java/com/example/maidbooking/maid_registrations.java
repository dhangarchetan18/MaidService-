package com.example.maidbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class maid_registrations extends AppCompatActivity {

    EditText mfname,mlname,mcontact,mcity,memail,mpassword;
    DBHelperMaid helperMaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maid_registrations);

        mfname=findViewById(R.id.mreditextfirstname);
        mlname=findViewById(R.id.mreditextlasttname);
        mcontact=findViewById(R.id.mreditTextTextmob);
        mcity=findViewById(R.id.mreditTextTextCity);
        memail=findViewById(R.id.mreditTextTextEmailAddress);
        mpassword=findViewById(R.id.mreditTextTextPassword2);

        helperMaid=new DBHelperMaid(this);
    }

    public void maidsubmitbutton(View view) {
        String mfname_s=mfname.getText().toString();
        String mlname_s=mlname.getText().toString();
        String mcontact_s=mcontact.getText().toString();
        String mcity_s=mcity.getText().toString();
        String memail_s=memail.getText().toString();
        String mpass_s=mpassword.getText().toString();

        if (TextUtils.isEmpty(mfname_s) || TextUtils.isEmpty(mlname_s) || TextUtils.isEmpty(mcontact_s) || TextUtils.isEmpty(memail_s) || TextUtils.isEmpty(mpass_s) || TextUtils.isEmpty(mcity_s))
        {
            Toast.makeText(this, "All Fields Required", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Boolean checkmu=helperMaid.checkmaiduser(memail_s);
            if (checkmu==false)
            {
                Boolean insert = helperMaid.insertmaiduserdetails(mfname_s,mlname_s,mcontact_s,mcity_s,memail_s,mpass_s);
                if (insert==true)
                {
                    Toast.makeText(this, "Record Inserted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(this,login_maid_activitys.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Record not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(this, "Already user registerd", Toast.LENGTH_SHORT).show();
            }
        }
    }
}