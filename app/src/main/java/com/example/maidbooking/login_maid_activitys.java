package com.example.maidbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_maid_activitys extends AppCompatActivity {

    Button maidreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_maid_activitys);

        maidreg=findViewById(R.id.mbutton3);

        maidreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login_maid_activitys.this,maid_registrations.class);
                startActivity(intent);
            }
        });
    }
}