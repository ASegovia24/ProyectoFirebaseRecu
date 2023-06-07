package com.adriansegovia.proyectofirebaserecu;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CocheViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView txt_item_modelo;
    private TextView txt_item_color;
    private ImageView img_item_coche;
    private ListaCochesAdapter listaCochesAdapter;

    public TextView getTxt_item_modelo() {
        return txt_item_modelo;
    }

    public void setTxt_item_modelo(TextView txt_item_modelo) {
        this.txt_item_modelo = txt_item_modelo;
    }

    public TextView getTxt_item_color() {
        return txt_item_color;
    }

    public void setTxt_item_color(TextView txt_item_color) {
        this.txt_item_color = txt_item_color;
    }

    public ImageView getImg_item_coche() {
        return img_item_coche;
    }

    public void setImg_item_coche(ImageView img_item_coche) {
        this.img_item_coche = img_item_coche;
    }

    //-------------------------------------
    public CocheViewHolder(@NonNull View itemView, ListaCochesAdapter listaCochesAdapter) {
        super(itemView);
        txt_item_modelo = (TextView) itemView.findViewById(R.id.txt_item_modelo);
        txt_item_color = (TextView) itemView.findViewById(R.id.txt_item_color);
        img_item_coche = (ImageView) itemView.findViewById(R.id.img_item_coche);
        //-----------------------------------------------------------------------------
        itemView.setOnClickListener(this);
        this.listaCochesAdapter = listaCochesAdapter;
    }

    @Override
    public void onClick(View v) {

    }
}
