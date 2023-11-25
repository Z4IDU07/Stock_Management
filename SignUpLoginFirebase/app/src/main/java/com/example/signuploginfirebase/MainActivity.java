package com.example.signuploginfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button main, create_btn, update_btn, delete_btn, search_btn;
    private TextView date, id, customer, units, rate, total;
    private EditText search_id;
    FirebaseDatabase db;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_id = findViewById(R.id.sal_search_main);
        search_btn = findViewById(R.id.sal_searchbtn_main);

        date = findViewById(R.id.sal_dat_main);
        id = findViewById(R.id.sal_id_main);
        customer = findViewById(R.id.sal_cust_main);
        units = findViewById(R.id.sal_unit_main);
        rate = findViewById(R.id.sal_rat_main);
        total = findViewById(R.id.sal_tot_main);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_id = search_id.getText().toString().trim();
                db = FirebaseDatabase.getInstance();
                dbref = db.getReference("Sales");
                //Querying
                Query check = dbref.orderByChild("ID").equalTo(s_id);

                check.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String idfromDB = snapshot.child(s_id).child("ID").getValue(String.class);

                            assert idfromDB != null;
                            if(idfromDB.equals(s_id)){
                                search_id.setError(null);
                                //getting data of the respective Product ID
                                String dat = snapshot.child(s_id).child("Date").getValue(String.class);
                                Integer rat = snapshot.child(s_id).child("Rate").getValue(Integer.class);
                                String supp = snapshot.child(s_id).child("Customer").getValue(String.class);
                                Integer tot = snapshot.child(s_id).child("Total").getValue(Integer.class);
                                Integer unit = snapshot.child(s_id).child("Units").getValue(Integer.class);

                                String r = Integer.toString(rat);
                                String t = Integer.toString(tot);
                                String u = Integer.toString(unit);

                                //setting data
                                date.setText(dat);
                                id.setText(s_id);
                                customer.setText(supp);
                                units.setText(u);
                                rate.setText(r);
                                total.setText(t);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        create_btn = (Button) findViewById(R.id.create_btn_sal);
        update_btn = (Button) findViewById(R.id.update_btn_sal);
        delete_btn = (Button) findViewById(R.id.delete_btn_sal);
        main = (Button) findViewById(R.id.main_s);

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, create.class);
                startActivity(i1);
            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, update.class);
                startActivity(i1);
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, delete.class);
                startActivity(i1);
            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, main_menu.class);
                startActivity(i1);
            }
        });
    }
}