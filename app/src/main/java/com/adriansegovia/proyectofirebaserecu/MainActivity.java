package com.adriansegovia.proyectofirebaserecu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText edt_login_email;
    private EditText edt_login_clave;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        //
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        edt_login_email = (EditText) findViewById(R.id.edt_login_email);
        edt_login_clave = (EditText) findViewById(R.id.edt_login_clave);
    }
    public void loguearAlumno(View view) {
        String email = String.valueOf(edt_login_email.getText());
        String password = String.valueOf(edt_login_clave.getText());
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("firebasedb", "logueado correctamente");
                            Toast.makeText(MainActivity.this, "logueado correctamente", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent intent = new Intent(MainActivity.this, MostrarCoches.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("firebasedb", "error al loguearse", task.getException());
                            Toast.makeText(MainActivity.this, "error al loguearse", Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }

    public void registrarAlumno(View view) {
        String email = String.valueOf(edt_login_email.getText()).trim();
        String password = String.valueOf(edt_login_clave.getText());
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("firebase1", "usuario registrado correctamente");
                            Toast.makeText(MainActivity.this, "usuario registrado correctamente", Toast.LENGTH_SHORT).show();
//                            // updateUI(user);
                            Intent intent = new Intent(MainActivity.this, MostrarCoches.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("firebase1", "no se pudo registrar al usuario", task.getException());
                            Toast.makeText(MainActivity.this, "no se pudo registrar al usuario", Toast.LENGTH_SHORT).show();
                            //  updateUI(null);
                        }
                    }
                });

    }

    public void cerrarAlumno(View view) {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(MainActivity.this, "se cerró la sesión correctamente", Toast.LENGTH_SHORT).show();
    }
}