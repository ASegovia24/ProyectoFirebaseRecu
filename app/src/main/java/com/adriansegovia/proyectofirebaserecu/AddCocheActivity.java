package com.adriansegovia.proyectofirebaserecu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.adriansegovia.proyectofirebaserecu.utilidades.ImagenesFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class AddCocheActivity extends AppCompatActivity {

    private EditText edt_add_modelo;
    private EditText edt_add_color;

    private FirebaseAuth mAuth;
    public static final int NUEVA_IMAGEN = 1;
    Uri imagen_seleccionada = null;

    ImageView img_add_coche;

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
        setContentView(R.layout.activity_add_coche);
        //------------------------------------------------
        edt_add_modelo = (EditText) findViewById(R.id.edt_detalles_modelo);
        edt_add_color = (EditText) findViewById(R.id.edt_detalles_color);
        img_add_coche = (ImageView) findViewById(R.id.img_add_coche);

    }

    public void add_coche_realtime(View view) {
        String modelo = String.valueOf(edt_add_modelo.getText());
        String color = String.valueOf(edt_add_color.getText());
        Coche a = new Coche(modelo, color);
        //--------------------------------------------
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
        myRef.child("cocheshashmap").child(a.getModelo()).setValue(a);
        Toast.makeText(this,"coche añadido correctamente",Toast.LENGTH_LONG).show();
        // codigo para guardar la imagen del usuario en firebase store
        if(imagen_seleccionada != null) {
            String carpeta = a.getModelo();
            ImagenesFirebase.subirFoto(carpeta,a.getModelo(), img_add_coche);
        }

    }

    public void cambiar_imagen(View view) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Selecciona una imagen");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, NUEVA_IMAGEN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NUEVA_IMAGEN && resultCode == Activity.RESULT_OK) {
            imagen_seleccionada = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagen_seleccionada);
                img_add_coche.setImageBitmap(bitmap);

                //---------------------------------------------

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}