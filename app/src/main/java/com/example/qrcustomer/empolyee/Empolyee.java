package com.example.qrcustomer.empolyee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qrcustomer.R;
import com.example.qrcustomer.customer.Sign;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class Empolyee extends AppCompatActivity {

    DataSnapshot dataSnapshot ;
    FirebaseDatabase firebase ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empolyee);

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

                            Intent c = new Intent (Empolyee.this, Empolyee.class);
                            startActivity (c);
                        }

                        if (drawerItem.getIdentifier() == 7) {
                            FirebaseAuth.getInstance().signOut();
                            Intent f = new Intent(Empolyee.this, Sign.class);
                            startActivity(f);
                            finish();
                        }

                        return false;
                    }
                })

                .build ();

        Button btnScan = findViewById(R.id.scan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                IntentIntegrator integrator = new IntentIntegrator(Empolyee.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("اscan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();


            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null) {
            if(result.getContents() == null) {
                Log.e("Scan*******", "Cancelled scan");


            } else {
                Log.e("Scan", "Scanned");
                if (!result.getContents().startsWith("QR#")) {
                    Toast.makeText(
                            Empolyee.this, "االرمز خطا ", Toast.LENGTH_SHORT).show();
                    return;
                }

                String[] finalResult = result.getContents().split("#");
                Intent n = new Intent(Empolyee.this, Data.class);
                n.putExtra("store",finalResult[1]);
                n.putExtra("email",finalResult[2]);
                n.putExtra("name",finalResult[3]);
                n.putExtra("number",finalResult[4]);
                n.putExtra("cid",finalResult[5]);
                startActivity(n);
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference theRequest = reference.child("shops");


    }
}
