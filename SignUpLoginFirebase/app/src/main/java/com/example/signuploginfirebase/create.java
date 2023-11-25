package com.example.signuploginfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class create extends AppCompatActivity {
    private Button go_back, create, search_btn;
    private EditText date, id, customer, units, rate;
    FirebaseDatabase db;
    DatabaseReference dbref1, dbref2;
    private EditText search_id;
    private String r, u, s_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        search_id = findViewById(R.id.sal_search_create);
        search_btn = findViewById(R.id.sal_searchbtn_create);

        date = findViewById(R.id.sal_dat_create);
        id = findViewById(R.id.sal_id_create);
        customer = findViewById(R.id.sal_cust_create);
        units = findViewById(R.id.sal_unit_create);
        rate = findViewById(R.id.sal_rat_create);

        //Searching ID that matches User's input ID
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_id = search_id.getText().toString().trim();
                db = FirebaseDatabase.getInstance();
                dbref1 = db.getReference("Purchase");
                //Querying
                Query check = dbref1.orderByChild("ID").equalTo(s_id);

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
                                Integer rat = snapshot.child(s_id).child("Rate").getValue(Integer.class);
                                Integer unit = snapshot.child(s_id).child("Units").getValue(Integer.class);

                                r = Integer.toString(rat);
                                u = Integer.toString(unit);

                                //setting data
                                id.setText(s_id);
                                units.setText(u);
                                rate.setText(r);
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

        create = findViewById(R.id.create_rec_sal);

        //on clicking CREATE
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance();

                //getting values from user
                String dat = date.getText().toString();
                String sal_id = id.getText().toString();
                String cust = customer.getText().toString();
                int unit = Integer.parseInt(units.getText().toString());
                int rat = Integer.parseInt(rate.getText().toString());
                int tot = unit*rat;

                if(unit > Integer.parseInt(u)) {
                    Toast.makeText(getApplicationContext(), "Not enough stock!", Toast.LENGTH_SHORT).show();
                }
                else {
                    //PurchaseDB -> ID given by user -> Product details
                    dbref2 = db.getReference("Sales").child(sal_id);


                    dbref2.child("Date").setValue(dat);
                    dbref2.child("ID").setValue(sal_id);
                    dbref2.child("Customer").setValue(cust);
                    dbref2.child("Units").setValue(unit);
                    dbref2.child("Rate").setValue(rat);
                    dbref2.child("Total").setValue(tot);

                    Toast.makeText(getApplicationContext(), "Record created successfully!", Toast.LENGTH_SHORT).show();
                    date.setText("");
                    id.setText("");
                    customer.setText("");
                    units.setText("");
                    rate.setText("");
                }
            }
        });
        go_back = (Button) findViewById(R.id.go_back1_sal);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(create.this, MainActivity.class);
                startActivity(i1);
            }
        });
    }
}