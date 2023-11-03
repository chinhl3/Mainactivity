package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mainactivity.activity.Welcome;

import java.util.Timer;
import java.util.TimerTask;

public class Screen_saver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_saver);
        // Tạo một timer để chuyển sang activity chính sau 5 giây
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Chuyển sang activity chính
                Intent intent = new Intent(Screen_saver.this, Welcome.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}