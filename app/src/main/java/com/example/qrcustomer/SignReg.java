package com.example.qrcustomer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qrcustomer.customer.Create;
import com.example.qrcustomer.customer.Sign;

public class SignReg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_reg);

        Button create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignReg.this, Create.class);
                startActivity(intent);
            }
        });
        Button S = findViewById(R.id.sign);
        S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(SignReg.this, Sign.class);
                startActivity(next);
            }
        });
    }
}