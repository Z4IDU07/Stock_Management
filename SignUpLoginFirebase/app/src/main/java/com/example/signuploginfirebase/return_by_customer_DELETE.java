package com.example.signuploginfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class return_by_customer_DELETE extends AppCompatActivity {
    private Button go_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_by_customer_delete);
        go_back = (Button) findViewById(R.id.go_back3_rc);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(return_by_customer_DELETE.this, return_by_customer_MAIN.class);
                startActivity(i1);
            }
        });
    }
}