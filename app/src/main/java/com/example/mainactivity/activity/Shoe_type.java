package com.example.mainactivity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mainactivity.R;

public class Shoe_type extends AppCompatActivity {
    CardView tim_kiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_type);
        tim_kiem=findViewById(R.id.tim_kiem);
        tim_kiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Shoe_type.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}