package com.miguelcr.mecaround.models;

import com.miguelcr.mecaround.app.MyApp;

import io.realm.RealmObject;

/**
 * Created by miguelcampos on 29/10/17.
 */

public class TallerDB extends RealmObject {

    private long id;
    private String nombre;
    private String telefono;
    private String direccion;

    public TallerDB() {
        this.id = MyApp.AveriaID.incrementAndGet();
    }

    public TallerDB(String nombre, String telefono, String direccion) {
        this.id = MyApp.AveriaID.incrementAndGet();
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
