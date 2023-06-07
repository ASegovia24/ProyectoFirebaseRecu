package com.adriansegovia.proyectofirebaserecu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ListaCochesAdapter extends RecyclerView.Adapter<CocheViewHolder> {
    private Context contexto = null;
    private ArrayList<Coche> coches = null;
    private LayoutInflater inflate = null;

    private FirebaseAuth mAuth;

    public ListaCochesAdapter(Context contexto, ArrayList<Coche> coches ) {
        this.contexto = contexto;
        this.coches = coches;
        inflate =  LayoutInflater.from(this.contexto);
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public ArrayList<Coche> getCoches() {
        return coches;
    }

    public void setCoches(ArrayList<Coche> coches) {
        this.coches = coches;
        notifyDataSetChanged();
    }

    public LayoutInflater getInflate() {
        return inflate;
    }

    public void setInflate(LayoutInflater inflate) {
        this.inflate = inflate;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }

    @NonNull
    @Override
    public CocheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflate.inflate(R.layout.card_coche,parent,false);
        CocheViewHolder cvh = new CocheViewHolder(mItemView,this);
        Log.i( "firebase1", String.valueOf(this.coches));

        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CocheViewHolder holder, int position) {
        Coche p = this.getCoches().get(position);
        Log.i( "firebase1", p.toString());
        //        cargo la imagen desde la base de datos
        //-----------------------------------------------------------------
        //String carpeta = p.getNombre();
        //ImageView imagen = holder.getImg_item_alumno() ;
       //ImagenesFirebase.descargarFoto(carpeta,p.getNombre(),imagen);
        //ImageView imagen1 = imagen;
        //holder.setImg_item_alumno(imagen1);
        //----------------------------------------------------------------------
        holder.getTxt_item_modelo().setText("nombre: " + p.getModelo());
        holder.getTxt_item_color().setText("curso " + String.valueOf(p.getColor()));


    }

    @Override
    public int getItemCount() {
        Log.i( "firebase1", String.valueOf(this.coches.size()));
        return this.coches.size();
    }
}
