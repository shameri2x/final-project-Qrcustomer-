package com.example.qrcustomer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                Intent i = new Intent(MainActivity.this, SignReg.class);
                startActivity(i);

                finish();
            }
        };

        handler.postDelayed(r, 1500);


    }
}
