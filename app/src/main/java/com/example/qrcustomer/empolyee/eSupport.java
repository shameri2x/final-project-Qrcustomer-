package com.example.qrcustomer.empolyee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qrcustomer.R;
import com.example.qrcustomer.customer.Sign;
import com.google.firebase.auth.FirebaseAuth;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class eSupport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_support);

        @SuppressLint("ResourceAsColor") AccountHeader hr = new AccountHeaderBuilder()
                .withActivity (this)
                .withHeaderBackground (R.drawable.header)
                .withTextColorRes (R.color.colorAccent)
                .build ();


        PrimaryDrawerItem item3 = new PrimaryDrawerItem() .withIdentifier(3) .withName ("حسابي");

        SecondaryDrawerItem item7 = new SecondaryDrawerItem () .withIdentifier(7) .withName ("تسجيل خروج");


        Toolbar toolBar = findViewById (R.id.toolbar);



        new DrawerBuilder()
                .withActivity (this)
                .withToolbar (toolBar)
                .withAccountHeader (hr)
                .addDrawerItems(item3)
                .addDrawerItems().addStickyDrawerItems()
                .addDrawerItems(new DividerDrawerItem())
                .addDrawerItems(item7)
                .addDrawerItems(new DividerDrawerItem())


                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        if(drawerItem.getIdentifier ()==3) {

                            Intent c = new Intent (eSupport.this, Empolyee.class);
                            startActivity (c);
                        }


                        if (drawerItem.getIdentifier() == 7) {
                            FirebaseAuth.getInstance().signOut();
                            Intent f = new Intent(eSupport.this, Sign.class);
                            startActivity(f);
                            finish();
                        }

                        return false;
                    }
                })

                .build ();

    }
}
