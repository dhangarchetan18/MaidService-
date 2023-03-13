package com.example.maidbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_user_activitys extends AppCompatActivity {

Button userreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_activitys);

userreg=findViewById(R.id.userbutton3);
        userreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login_user_activitys.this,user_registrations.class);
                startActivity(intent);
            }
        });
    }
}