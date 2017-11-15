
package com.miguelcr.mecaround.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Marca {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("marca")
    @Expose
    private String marca;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Marca() {
    }

    /**
     * 
     * @param id
     * @param marca
     */
    public Marca(String id, String marca) {
        super();
        this.id = id;
        this.marca = marca;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Marca withId(String id) {
        this.id = id;
        return this;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Marca withMarca(String marca) {
        this.marca = marca;
        return this;
    }

}
