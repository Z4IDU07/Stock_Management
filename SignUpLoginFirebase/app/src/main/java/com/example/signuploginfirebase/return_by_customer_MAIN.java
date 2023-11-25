package com.example.signuploginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class return_by_customer_MAIN extends AppCompatActivity {
    private Button create;
    private Button delete;
    private Button update;
    private Button main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_by_customer_main);
        create = (Button) findViewById(R.id.create_btn_rc);
        update = (Button) findViewById(R.id.update_btn_rc);
        delete = (Button) findViewById(R.id.delete_btn_rc);
        main = (Button) findViewById(R.id.main_rc);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(return_by_customer_MAIN.this, return_by_customer_CREATE.class);
                startActivity(i1);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(return_by_customer_MAIN.this, return_by_customer_UPDATE.class);
                startActivity(i1);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(return_by_customer_MAIN.this, return_by_customer_DELETE.class);
                startActivity(i1);
            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(return_by_customer_MAIN.this, main_menu.class);
                startActivity(i1);
            }
        });
    }
}