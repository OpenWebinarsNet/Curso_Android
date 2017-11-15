package com.miguelcr.mecaround.interfaces;

/**
 * Created by miguelcampos on 27/10/17.
 */

public interface OnNuevaAveriaListener {
    public void onAveriaGuardarClickListener(String titulo, String descripcion, String modelo);
    public void onAveriaUpdateClickListener(long id, String titulo, String descripcion, String modelo);
}
