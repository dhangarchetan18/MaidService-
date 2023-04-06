package com.example.maidbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_maid_activitys extends AppCompatActivity {

    EditText musername,mpassword;
    Button maidreg;
    DBHelperMaid helperMaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_maid_activitys);

        musername=findViewById(R.id.meditTextTextEmailAddress);
        mpassword=findViewById(R.id.meditTextTextPassword2);
        maidreg=findViewById(R.id.mbutton3);

        helperMaid=new DBHelperMaid(this);

        maidreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login_maid_activitys.this,maid_registrations.class);
                startActivity(intent);
            }
        });
    }

    public void maidloginbutton(View view) {
        String musername_s=musername.getText().toString();
        String mpassword_s=mpassword.getText().toString();

        if (TextUtils.isEmpty(musername_s) || TextUtils.isEmpty(mpassword_s))
        {
            Toast.makeText(this, "All Fields Required", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Boolean checkmup=helperMaid.checkusernamepassword(musername_s,mpassword_s);
            if (checkmup==true)
            {
                Toast.makeText(this, "Successfully login", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,maid_dashboard.class);
                intent.putExtra("user_maid",musername_s);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Invalid Email and Password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}