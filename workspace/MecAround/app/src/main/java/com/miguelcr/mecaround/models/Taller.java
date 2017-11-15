
package com.miguelcr.mecaround.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Taller {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("latlng")
    @Expose
    private String latlng;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("telefono")
    @Expose
    private String telefono;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Taller() {
    }

    /**
     * 
     * @param nombre
     * @param id
     * @param direccion
     * @param telefono
     * @param latlng
     */
    public Taller(String id, String nombre, String latlng, String direccion, String telefono) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.latlng = latlng;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Taller withId(String id) {
        this.id = id;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Taller withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getLatlng() {
        return latlng;
    }

    public void setLatlng(String latlng) {
        this.latlng = latlng;
    }

    public Taller withLatlng(String latlng) {
        this.latlng = latlng;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Taller withDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Taller withTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

}
