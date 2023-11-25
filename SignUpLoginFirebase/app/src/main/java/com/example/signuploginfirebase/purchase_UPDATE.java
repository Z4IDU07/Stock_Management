package com.example.signuploginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class purchase_UPDATE extends AppCompatActivity {
    private Button go_back, update, search_btn;
    private EditText date, id, supplier, units, rate;
    private EditText search_id;
    FirebaseDatabase db;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_update);

        search_id = findViewById(R.id.pur_search_update);
        search_btn = findViewById(R.id.pur_searchbtn_update);
        update = findViewById(R.id.update_rec_pur);

        date = findViewById(R.id.pur_dat_update);
        id = findViewById(R.id.pur_id_update);
        supplier = findViewById(R.id.pur_supp_update);
        units = findViewById(R.id.pur_unit_update);
        rate = findViewById(R.id.pur_rat_update);

        //Search button function (same as in purchase_main.java)
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_id = search_id.getText().toString().trim();
                db = FirebaseDatabase.getInstance();
                dbref = db.getReference("Purchase");
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
                                String dat = snapshot.child(s_id).child("Date").getValue(String.class);
                                Integer rat = snapshot.child(s_id).child("Rate").getValue(Integer.class);
                                String supp = snapshot.child(s_id).child("Supplier").getValue(String.class);
                                Integer unit = snapshot.child(s_id).child("Units").getValue(Integer.class);

                                String r = Integer.toString(rat);
                                String u = Integer.toString(unit);
                                date.setText(dat);
                                id.setText(s_id);
                                supplier.setText(supp);
                                units.setText(u);
                                rate.setText(r);
                            }
                            //search_id.setError("Invalid ID");
                            //search_id.requestFocus();

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_id = id.getText().toString().trim();
                db = FirebaseDatabase.getInstance();
                dbref = db.getReference("Purchase");
                Query check = dbref.orderByChild("ID").equalTo(s_id);

                check.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            //search_id.setError(null);
                            String idfromDB = snapshot.child(s_id).child("ID").getValue(String.class);

                            assert idfromDB != null;
                            if(idfromDB.equals(s_id)){
                                //delete original product details
                                dbref.child(s_id).removeValue();

                                String dat = date.getText().toString();
                                String pur_id = id.getText().toString();
                                String supp = supplier.getText().toString();
                                int unit = Integer.parseInt(units.getText().toString());
                                int rat = Integer.parseInt(rate.getText().toString());
                                int tot = unit*rat;

                                //replace with new details (ID cannot be changed by user)
                                dbref = db.getReference("Purchase").child(s_id);

                                dbref.child("Date").setValue(dat);
                                dbref.child("ID").setValue(pur_id);
                                dbref.child("Supplier").setValue(supp);
                                dbref.child("Units").setValue(unit);
                                dbref.child("Rate").setValue(rat);
                                dbref.child("Total").setValue(tot);

                                //dbref2 = db.getReference("Purchase_IDs");
                                //dbref2.push().setValue(pur_id);
                                search_id.setText("");
                                date.setText("");
                                id.setText("");
                                supplier.setText("");
                                units.setText("");
                                rate.setText("");
                                Toast.makeText(purchase_UPDATE.this, "Record updated successfully!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        go_back = (Button) findViewById(R.id.go_back2_pur);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(purchase_UPDATE.this, purchase_main.class);
                startActivity(i1);
            }
        });
    }
}