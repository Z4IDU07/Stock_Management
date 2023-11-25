package com.example.signuploginfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class delete extends AppCompatActivity {
    private Button go_back, delete, search_btn;
    private TextView date, id, customer, units, rate, total;
    private EditText search_id;
    FirebaseDatabase db;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        search_id = findViewById(R.id.sal_search_delete);
        search_btn = findViewById(R.id.sal_searchbtn_delete);
        delete = findViewById(R.id.delete_rec_sal);

        date = findViewById(R.id.sal_dat_delete);
        id = findViewById(R.id.sal_id_delete);
        customer = findViewById(R.id.sal_cust_delete);
        units = findViewById(R.id.sal_unit_delete);
        rate = findViewById(R.id.sal_rat_delete);
        total = findViewById(R.id.sal_tot_delete);

        //Search button function
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_id = search_id.getText().toString().trim();
                db = FirebaseDatabase.getInstance();
                dbref = db.getReference("Sales");
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
                                String supp = snapshot.child(s_id).child("Customer").getValue(String.class);
                                Integer unit = snapshot.child(s_id).child("Units").getValue(Integer.class);
                                Integer tot = snapshot.child(s_id).child("Total").getValue(Integer.class);

                                String r = Integer.toString(rat);
                                String u = Integer.toString(unit);
                                String t = Integer.toString(tot);
                                date.setText(dat);
                                id.setText(s_id);
                                customer.setText(supp);
                                units.setText(u);
                                rate.setText(r);
                                total.setText(t);
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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_id = id.getText().toString().trim();
                db = FirebaseDatabase.getInstance();
                dbref = db.getReference("Sales");
                Query check = dbref.orderByChild("ID").equalTo(s_id);

                check.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            //search_id.setError(null);

                            String idfromDB = snapshot.child(s_id).child("ID").getValue(String.class);

                            assert idfromDB != null;
                            if(idfromDB.equals(s_id)){
                                dbref.child(s_id).removeValue();

                                search_id.setText("");
                                date.setText("");
                                id.setText("");
                                customer.setText("");
                                units.setText("");
                                rate.setText("");
                                total.setText("");

                                Toast.makeText(getApplicationContext(), "Record deleted successfully!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        go_back = (Button) findViewById(R.id.go_back3_sal);

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(delete.this, MainActivity.class);
                startActivity(i1);
            }
        });

    }
}