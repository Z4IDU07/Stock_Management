package com.example.signuploginfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class return_to_supplier_DELETE extends AppCompatActivity {

    Button go_back_sup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_to_supplier_delete);

        go_back_sup = (Button) findViewById(R.id.go_back3_sup);

        go_back_sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(return_to_supplier_DELETE.this, return_to_supplier_MAIN.class);
                startActivity(i1);
            }
        });
    }
}