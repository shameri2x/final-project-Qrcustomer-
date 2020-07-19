package com.example.qrcustomer.customer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.qrcustomer.Info;
import com.example.qrcustomer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class Form extends AppCompatActivity {
    Bitmap bitmap;
    public final static int QRcodeWidth = 350;

    boolean generated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        final Button qr = findViewById(R.id.qr);
        final ImageView qrCode = findViewById(R.id.sun);

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
                            Intent a = new Intent(Form.this, Cprofile.class);
                            startActivity(a);

                        }

                        if (drawerItem.getIdentifier() == 2) {
                            Intent b = new Intent(Form.this, Store.class);
                            startActivity(b);

                        }

                        if (drawerItem.getIdentifier() == 3) {

                            Intent c = new Intent(Form.this, Terms.class);
                            startActivity(c);
                        }


                        if (drawerItem.getIdentifier() == 5) {

                            Intent e = new Intent(Form.this, Ask.class);
                            startActivity(e);
                        }

                        if (drawerItem.getIdentifier() == 6) {

                            Intent e = new Intent(Form.this, Support.class);
                            startActivity(e);
                        }

                        if (drawerItem.getIdentifier() == 7) {
                            FirebaseAuth.getInstance().signOut();
                            Intent f = new Intent(Form.this, Sign.class);
                            startActivity(f);
                            finish();
                        }
                        return false;
                    }
                })

                .build();

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (generated) {
                    Intent i = new Intent(Form.this, Store.class);
                    startActivity(i);
                }

                Bundle n = getIntent().getExtras();

                FirebaseAuth fAuth = FirebaseAuth.getInstance();
                FirebaseUser user = fAuth.getCurrentUser();
                String email = user.getEmail();
                String name = n.getString("name");
                String phone = n.getString("phone");
                String cid = n.getString("cid");

                String all = "QR#" + Info.store_name + "#" + email + "#" + name + "#" + phone + "#" + cid;

                if (!all.isEmpty()) {
                    try {
                        bitmap = TextToImageEncode(all);
                        qrCode.setImageBitmap(bitmap);
                        generated = true;
                        qr.setText("العودة");
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(Form.this, "الكود ليس بصحيح", Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {


            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null


            );

        } catch (IllegalArgumentException Illegalargumentexception) {


            return null;


        }


        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor) : getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 350, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;

    }

}
