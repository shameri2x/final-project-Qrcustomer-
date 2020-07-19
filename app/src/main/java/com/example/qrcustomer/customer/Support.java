package com.example.qrcustomer.customer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qrcustomer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class Support extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

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
                            Intent a = new Intent(Support.this, Cprofile.class);
                            startActivity(a);

                        }

                        if (drawerItem.getIdentifier() == 2) {
                            Intent b = new Intent(Support.this, Store.class);
                            startActivity(b);

                        }

                        if (drawerItem.getIdentifier() == 3) {

                            Intent c = new Intent(Support.this, Terms.class);
                            startActivity(c);
                        }


                        if (drawerItem.getIdentifier() == 5) {

                            Intent e = new Intent(Support.this, Ask.class);
                            startActivity(e);
                        }

                        if (drawerItem.getIdentifier() == 6) {

                            Intent e = new Intent(Support.this, Support.class);
                            startActivity(e);
                        }


                        if (drawerItem.getIdentifier() == 7) {
                            FirebaseAuth.getInstance().signOut();
                            Intent f = new Intent(Support.this, Sign.class);
                            startActivity(f);
                            finish();
                        }
                        return false;
                    }
                })

                .build();


    }
}
