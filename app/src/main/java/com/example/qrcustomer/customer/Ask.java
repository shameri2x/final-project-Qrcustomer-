package com.example.qrcustomer.customer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qrcustomer.JavaMailAPI;
import com.example.qrcustomer.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class Ask extends AppCompatActivity {


    public EditText mEmail;
    public EditText mSubject;
    public EditText mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);


        @SuppressLint("ResourceAsColor") AccountHeader hr = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground (R.drawable.header)
                .withTextColorRes(R.color.colorAccent)
                .build();

        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("حسابي");

        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("المحلات");

        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("الشروط");

        SecondaryDrawerItem item5 = new SecondaryDrawerItem().withIdentifier(5).withName("الاسئلة");

        SecondaryDrawerItem item6 = new SecondaryDrawerItem().withIdentifier(6).withName("التواصل");

        SecondaryDrawerItem item7 = new SecondaryDrawerItem().withIdentifier(7).withName("تسجيل خروج");


        Toolbar toolBar = findViewById(R.id.toolbar);


        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolBar)
                .withAccountHeader(hr)
                .addDrawerItems(item1)
                .addDrawerItems(item2)
                .addDrawerItems(item3)
                .addDrawerItems().addStickyDrawerItems()
                .addDrawerItems(new DividerDrawerItem())
                .addDrawerItems(item5)
                .addDrawerItems(item6)
                .addDrawerItems(item7)
                .addDrawerItems(new DividerDrawerItem())


                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem.getIdentifier() == 1) {
                            Intent a = new Intent(Ask.this, Cprofile.class);
                            startActivity(a);

                        }

                        if (drawerItem.getIdentifier() == 2) {
                            Intent b = new Intent(Ask.this, Store.class);
                            startActivity(b);

                        }

                        if (drawerItem.getIdentifier() == 3) {

                            Intent c = new Intent(Ask.this, Terms.class);
                            startActivity(c);
                        }


                        if (drawerItem.getIdentifier() == 5) {

                            Intent e = new Intent(Ask.this, Ask.class);
                            startActivity(e);
                        }

                        if (drawerItem.getIdentifier() == 6) {

                            Intent e = new Intent(Ask.this, Support.class);
                            startActivity(e);
                        }


                        if (drawerItem.getIdentifier() == 7) {
                            FirebaseAuth.getInstance().signOut();
                            Intent f = new Intent(Ask.this, Sign.class);
                            startActivity(f);
                            finish();
                        }

                        return false;
                    }
                })

                .build();



        mEmail = (EditText) findViewById(R.id.mailID);
        mMessage = (EditText) findViewById(R.id.messageID);
        mSubject = (EditText) findViewById(R.id.subjectID);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    private void sendMail() {

        String mail = mEmail.getText().toString().trim();
        String message = mMessage.getText().toString();
        String subject = mSubject.getText().toString().trim();

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(this, "qrcustomer.kw@gmail.com", subject, mail + " | " + message);

        javaMailAPI.execute();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}