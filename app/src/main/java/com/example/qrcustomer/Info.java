package com.example.qrcustomer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qrcustomer.customer.Form;

public class Info extends AppCompatActivity {
    public static String store_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        final EditText name = findViewById(R.id.name);
        final EditText number = findViewById(R.id.pass);
        final EditText civil = findViewById(R.id.civil);


        Button next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Info.this, Form.class);

                intent.putExtra("name", name.getText().toString());
                intent.putExtra("phone", number.getText().toString());
                intent.putExtra("cid", civil.getText().toString());
                startActivity(intent);
            }
        });

    }
}
