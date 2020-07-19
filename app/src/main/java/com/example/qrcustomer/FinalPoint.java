package com.example.qrcustomer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.qrcustomer.customer.Store;
import com.example.qrcustomer.empolyee.Empolyee;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FinalPoint extends AppCompatActivity {
    FirebaseAuth fAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Button signout = findViewById(R.id.button10);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                Intent i = new Intent(FinalPoint.this, SignReg.class);
                startActivity(i);

                finish();

            }
        });
        Button home = findViewById(R.id.button11);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = fAuth.getCurrentUser();
                if (user.getEmail().endsWith("@qrcustomer.com")) {
                    Intent i = new Intent(FinalPoint.this, Empolyee.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(FinalPoint.this, Store.class);
                    startActivity(i);
                }
            }
        });
    }
}
