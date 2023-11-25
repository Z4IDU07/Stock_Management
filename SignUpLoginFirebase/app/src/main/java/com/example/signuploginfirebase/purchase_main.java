package com.example.signuploginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class purchase_main extends AppCompatActivity {
    private Button main, create_btn, update_btn, delete_btn, search_btn;
    private TextView date, id, supplier, units, rate, total;
    private EditText search_id;
    FirebaseDatabase db;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_main);

        search_id = findViewById(R.id.pur_search_main);
        search_btn = findViewById(R.id.pur_searchbtn_main);

        date = findViewById(R.id.pur_dat_main);
        id = findViewById(R.id.pur_id_main);
        supplier = findViewById(R.id.pur_supp_main);
        units = findViewById(R.id.pur_unit_main);
        rate = findViewById(R.id.pur_rate_main);
        total = findViewById(R.id.pur_tot_main);

        //Searching ID that matches User's input ID
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_id = search_id.getText().toString().trim();
                db = FirebaseDatabase.getInstance();
                dbref = db.getReference("Purchase");
                //Querying
                Query check = dbref.orderByChild("ID").equalTo(s_id);

               check.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       if(snapshot.exists()){
                           //search_id.setError(null);
                           String idfromDB = snapshot.child(s_id).child("ID").getValue(String.class);

                           assert idfromDB != null;
                           if(idfromDB.equals(s_id)){
                               search_id.setError(null);
                               //getting data of the respective Product ID
                               String dat = snapshot.child(s_id).child("Date").getValue(String.class);
                               Integer rat = snapshot.child(s_id).child("Rate").getValue(Integer.class);
                               String supp = snapshot.child(s_id).child("Supplier").getValue(String.class);
                               Integer tot = snapshot.child(s_id).child("Total").getValue(Integer.class);
                               Integer unit = snapshot.child(s_id).child("Units").getValue(Integer.class);

                               String r = Integer.toString(rat);
                               String t = Integer.toString(tot);
                               String u = Integer.toString(unit);

                               //setting data
                               date.setText(dat);
                               id.setText(s_id);
                               supplier.setText(supp);
                               units.setText(u);
                               rate.setText(r);
                               total.setText(t);
                           }
                           /*else{
                               search_id.setError("Invalid ID");
                               search_id.requestFocus();
                           }*/
                       }
                   }
                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
            }
        });

        create_btn = (Button) findViewById(R.id.create_btn_pur);
        update_btn = (Button) findViewById(R.id.update_btn_pur);
        delete_btn = (Button) findViewById(R.id.delete_btn_pur);
        main = (Button) findViewById(R.id.main_p);

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(purchase_main.this, purchase_CREATE.class);
                startActivity(i1);
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(purchase_main.this, purchase_UPDATE.class);
                startActivity(i1);
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(purchase_main.this, purchase_DELETE.class);
                startActivity(i1);
            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(purchase_main.this, main_menu.class);
                startActivity(in);
            }
        });
    }

}