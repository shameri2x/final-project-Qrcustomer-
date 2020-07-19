package com.example.qrcustomer.customer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qrcustomer.R;
import com.example.qrcustomer.empolyee.Empolyee;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign extends AppCompatActivity {

    int errorTime = 0;
    Boolean errorAct = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);


        final TextView forget = findViewById(R.id.forget);
        forget.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Sign.this, Forget.class);
                startActivity(intent);
            }
        });

        final EditText email, pass;
        final FirebaseAuth fAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);

        Button signIn = findViewById(R.id.next);
        signIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (errorAct) {
                    Toast.makeText(Sign.this, "لقد تجاوزت العدد المسموح يرجى الانتظار...", Toast.LENGTH_LONG).show();
                }
                final String email2 = email.getText().toString();
                String password = pass.getText().toString();

                if (email2.isEmpty()) {
                    email.setError("البريد الاكتروني مطلوب  ");
                    return;
                }

                if (password.isEmpty()) {
                    pass.setError("جب ان تكون اكثر من 3 ");
                    return;
                }

                if (password.length() < 3) {
                    pass.setError("ايجب ان يكون الرقم السري اكثر نت 3");
                    return;
                }

                fAuth.signInWithEmailAndPassword(email2, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {


                            if (email2.endsWith("@qrcustomer.com")) {

                                startActivity(new Intent(getApplicationContext(), Empolyee.class));
                                Toast.makeText(Sign.this, "تم الدخول ", Toast.LENGTH_LONG).show();
                                finish();
                                return;
                            }
                            if (fAuth.getCurrentUser().isEmailVerified()) {
                                startActivity(new Intent(getApplicationContext(), Store.class));
                                Toast.makeText(Sign.this, "تم الدخول ", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(Sign.this, "بريدك الاكتروني غير مفعل", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            if (++errorTime == 5) {
                                errorAct = true;
                                final Handler handler = new Handler();

                                final Runnable r = new Runnable() {
                                    public void run() {
                                        errorAct = false;
                                        errorTime = 0;
                                    }
                                };

                                handler.postDelayed(r, 1000 * 20);
                                return;
                            }
                            Toast.makeText(Sign.this, "ERROR " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();


                        }


                    }
                });

            }
        });
    }
}