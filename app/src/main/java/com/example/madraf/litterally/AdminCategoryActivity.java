package com.example.madraf.litterally;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminCategoryActivity extends AppCompatActivity {

    ImageView botol,kresek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        botol = (ImageView) findViewById(R.id.botol);
        kresek = (ImageView) findViewById(R.id.kresek);

        botol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(AdminCategoryActivity.this, AdminAddNewProduct.class);
                a.putExtra("category","botol");
                startActivity(a);
            }
        });

        kresek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(AdminCategoryActivity.this, AdminAddNewProduct.class);
                a.putExtra("category","kresek");
                startActivity(a);
            }
        });

    }
}
