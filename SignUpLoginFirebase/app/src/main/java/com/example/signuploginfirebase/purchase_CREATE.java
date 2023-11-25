package com.example.signuploginfirebase;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class purchase_CREATE extends AppCompatActivity {
    private Button go_back, create;
    private EditText date, id, supplier, units, rate;
    FirebaseDatabase db;
    DatabaseReference dbref1, dbref2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_create);


        date = findViewById(R.id.pur_dat_create);
        id = findViewById(R.id.pur_id_create);
        supplier = findViewById(R.id.pur_supp_create);
        units = findViewById(R.id.pur_units_create);
        rate = findViewById(R.id.pur_rate_create);

        create = findViewById(R.id.create_rec_pur);

        //on clicking CREATE
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance();
                //dbref = db.getReference("Purchase");

                //getting values from user
                String dat = date.getText().toString();
                String pur_id = id.getText().toString();
                String supp = supplier.getText().toString();
                int unit = Integer.parseInt(units.getText().toString());
                int rat = Integer.parseInt(rate.getText().toString());
                int tot = unit*rat;

                //HelperClass hp = new HelperClass(pur_id, supp, unit, rat, tot);

                //PurchaseDB -> ID given by user -> Product details
                dbref2 = db.getReference("Purchase").child(pur_id);


                dbref2.child("Date").setValue(dat);
                dbref2.child("ID").setValue(pur_id);
                dbref2.child("Supplier").setValue(supp);
                dbref2.child("Units").setValue(unit);
                dbref2.child("Rate").setValue(rat);
                dbref2.child("Total").setValue(tot);
                //dbref1 = db.getReference("Purchase_IDs");
                //dbref1.push().setValue(pur_id);

                Toast.makeText(purchase_CREATE.this, "Record created successfully!", Toast.LENGTH_SHORT).show();
                date.setText("");
                id.setText("");
                supplier.setText("");
                units.setText("");
                rate.setText("");
                //Intent in = new Intent(purchase_CREATE.this, Log.class);
                //startActivity(in);
            }
        });

        go_back = (Button) findViewById(R.id.go_back1_pur);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(purchase_CREATE.this, purchase_main.class);
                startActivity(i1);
            }
        });
    }
}