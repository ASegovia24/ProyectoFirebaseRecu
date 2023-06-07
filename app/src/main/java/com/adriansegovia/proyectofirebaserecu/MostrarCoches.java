package com.adriansegovia.proyectofirebaserecu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MostrarCoches extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private RecyclerView rv_coches;
    private ArrayList<Coche>coches;
    private ListaCochesAdapter adaptadorCoche;
    private DatabaseReference myRefcoches = null;
    private Button bt_addcoche=null;
    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
        else{
            Toast.makeText(this, "debes autenticarte primero", Toast.LENGTH_SHORT).show();
            FirebaseUser user = mAuth.getCurrentUser();
            //updateUI(user);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_coches);
        rv_coches=(RecyclerView) findViewById(R.id.rv_coches);
        bt_addcoche = (Button)findViewById(R.id.bt_addcoche);
        bt_addcoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addCoche(bt_addcoche);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        coches = new ArrayList<Coche>();
        //-----------------------------------------------------------
        adaptadorCoche = new ListaCochesAdapter(this,coches);
        rv_coches.setAdapter(adaptadorCoche);
        DatabaseReference myRefcoches = FirebaseDatabase.getInstance().getReference("cocheshashmap");
        myRefcoches.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                adaptadorCoche.getCoches().clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Coche a = (Coche) dataSnapshot.getValue(Coche.class);
                    coches.add(a);
                }
                adaptadorCoche.setCoches(coches);
                Log.i( "firebase1", coches.toString());
                adaptadorCoche.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "firebase1", String.valueOf(error.toException()));
            }
        });

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            rv_coches.setLayoutManager(new GridLayoutManager(this,2));
        } else {
            // In portrait
            rv_coches.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public void addCoche(View view) {
        Intent intent = new Intent(this, AddCocheActivity.class);
        startActivity(intent);
    }
}