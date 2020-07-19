package com.example.qrcustomer.customer;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qrcustomer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Create extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);

        final EditText name, last, number, email, pass, pass2;
        Button create;
        final FirebaseAuth fAuth;
        final CheckBox checkBox;

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        pass2 = findViewById(R.id.pass2);
        create = findViewById(R.id.next);
        checkBox = findViewById(R.id.checkBox);
        fAuth = FirebaseAuth.getInstance();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String password = pass.getText().toString();

                if (TextUtils.isEmpty(Email)) {
                    email.setError(" البريد الاكتروني مطلوب ");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    pass.setError("الرقم السري مطلوب ");
                    return;
                }

                if (password.length() < (3)) {
                    pass.setError("يجب ان تكون اكثر من 3");
                    return;
                }

                if (!password.equals(pass2.getText().toString())) {
                    Toast.makeText(Create.this, "  الرقم السري غير متطابق ", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!checkBox.isChecked()) {
                    checkBox.setError("يجب عليك الموافقة على شروط الاستخدام");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(Email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Create.this, "يرجى تفعيل الحساب", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Sign.class));
                            finish();

                            FirebaseUser user = fAuth.getCurrentUser();

                            user.sendEmailVerification()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                            }
                                        }
                                    });


                        } else {
                            Toast.makeText(Create.this, "حدث خطا ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }


        });

    }
}
