package com.example.qrcustomer.empolyee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qrcustomer.FinalPoint;
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

public class Data extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        TextView store = findViewById(R.id.textView5);
        TextView email = findViewById(R.id.textView10);
        TextView name = findViewById(R.id.textView6);
        TextView cid = findViewById(R.id.textView7);
        TextView number = findViewById(R.id.textView9);

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

                            Intent c = new Intent (Data.this, Empolyee.class);
                            startActivity (c);
                        }


                        if (drawerItem.getIdentifier() == 7) {
                            FirebaseAuth.getInstance().signOut();
                            Intent f = new Intent(Data.this, Sign.class);
                            startActivity(f);
                            finish();
                        }
                        return false;
                    }
                })

                .build ();

        Bundle w = getIntent().getExtras();
        name.setText(w.getString("name"));
        number.setText(w.getString("number"));
        cid.setText(w.getString("cid"));
        email.setText(w.getString("email"));
        store.setText(w.getString("store"));

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Data.this, FinalPoint.class);
                startActivity(intent);
            }
        });

    }
}
