package com.miguelcr.mecaround.localdb;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by miguelcampos on 29/10/17.
 */

public class MarcaDB extends RealmObject {

    @PrimaryKey
    private String id;
    private String nombre;

    public MarcaDB() {
    }

    public MarcaDB(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
