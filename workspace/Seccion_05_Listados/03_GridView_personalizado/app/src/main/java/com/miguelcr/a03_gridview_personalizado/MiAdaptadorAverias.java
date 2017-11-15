package com.miguelcr.a03_gridview_personalizado;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by miguelcampos on 27/10/17.
 */

class MiAdaptadorAverias extends ArrayAdapter<Averia> {
    Context ctx;
    int layoutTemplate;
    List<Averia> averiaList;


    public MiAdaptadorAverias(@NonNull Context context, @LayoutRes int resource, @NonNull List<Averia> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.layoutTemplate = resource;
        this.averiaList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(layoutTemplate, parent, false);

        // Obtener la información del elemento de la lista que estoy iterando en este momento
        Averia elementoActual = averiaList.get(position);

        // Rescatar los elementos de la IU de la template
        TextView textViewTitulo = (TextView) v.findViewById(R.id.textViewTitulo);
        TextView textViewPresupuestos = (TextView) v.findViewById(R.id.textViewPresupuesto);
        ImageView imageViewFoto = (ImageView) v.findViewById(R.id.imageViewFoto);

        // HAcer un set de la info del elemento Actual en los elementos de la IU
        textViewTitulo.setText(elementoActual.getTitulo());
        textViewPresupuestos.setText(String.valueOf(elementoActual.getNumeroPresupuestos()));

        if(!elementoActual.getUrlFoto().isEmpty()) {
            Glide.with(ctx)
                    .load(elementoActual.getUrlFoto())
                    .into(imageViewFoto);
        }

        return v;
    }

}
