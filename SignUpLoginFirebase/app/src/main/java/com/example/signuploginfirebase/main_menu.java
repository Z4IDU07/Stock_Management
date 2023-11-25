package com.example.signuploginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_menu extends AppCompatActivity {
    Button purchase;
    Button sales;
    Button return_cust;
    Button return_supp;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        purchase = (Button) findViewById(R.id.purchase);
        sales = (Button) findViewById(R.id.sales);
        //return_cust = (Button) findViewById(R.id.return_cust);
        //return_supp = (Button) findViewById(R.id.return_supp);
        logout = (Button) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(main_menu.this, LoginActivity.class);
                startActivity(in);
            }
        });

        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(main_menu.this, purchase_main.class);
                startActivity(in);
            }
        });

        sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(main_menu.this, MainActivity.class);
                startActivity(in);
            }
        });
/*
        return_cust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(main_menu.this, return_by_customer_MAIN.class);
                startActivity(in);
            }
        });

        return_supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(main_menu.this, return_to_supplier_MAIN.class);
                startActivity(in);
            }
        });*/
    }

}