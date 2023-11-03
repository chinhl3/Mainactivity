package com.example.mainactivity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mainactivity.R;

public class Welcome extends AppCompatActivity {
    Button bat_dau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        bat_dau=findViewById(R.id.bat_dau);
        bat_dau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome.this, Shoe_type.class);
                startActivity(intent);
                finish();
            }
        });
    }
}