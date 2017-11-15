package com.miguelcr.realmcrud;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by miguelcampos on 29/10/17.
 */

public class AveriaDB extends RealmObject {
    @PrimaryKey
    private long id;
    private String titulo;
    private String descripcion;
    private String modelo;
    private String marca;

    public AveriaDB() {
        this.id = MyApp.AveriaID.incrementAndGet();
    }

    public AveriaDB(String titulo, String descripcion, String modelo, String marca) {
        this.id = MyApp.AveriaID.incrementAndGet();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.modelo = modelo;
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
