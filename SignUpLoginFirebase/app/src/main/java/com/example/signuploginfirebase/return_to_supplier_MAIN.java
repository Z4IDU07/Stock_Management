package com.example.signuploginfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class return_to_supplier_MAIN extends AppCompatActivity {

    private Button create_btn_sup;
    private Button update_btn_sup;
    private Button delete_btn_sup;
    private Button main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_to_supplier_main);

        create_btn_sup = (Button) findViewById(R.id.create_btn_sup);
        update_btn_sup = (Button) findViewById(R.id.update_btn_sup);
        delete_btn_sup = (Button) findViewById(R.id.delete_btn_sup);
        main = (Button) findViewById(R.id.main_rs);

        create_btn_sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(return_to_supplier_MAIN.this, return_to_supplier_CREATE.class);
                startActivity(i1);
            }
        });

        update_btn_sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(return_to_supplier_MAIN.this, return_to_supplier_UPDATE.class);
                startActivity(i1);
            }
        });

        delete_btn_sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(return_to_supplier_MAIN.this, return_to_supplier_DELETE.class);
                startActivity(i1);
            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(return_to_supplier_MAIN.this, main_menu.class);
                startActivity(i1);
            }
        });
    }
}