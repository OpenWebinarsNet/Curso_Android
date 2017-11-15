
package com.miguelcr.mecaround.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseAuthLogin {

    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("email")
    @Expose
    private String email;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseAuthLogin() {
    }

    /**
     * 
     * @param nombre
     * @param email
     * @param key
     */
    public ResponseAuthLogin(String key, String nombre, String email) {
        super();
        this.key = key;
        this.nombre = nombre;
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ResponseAuthLogin withKey(String key) {
        this.key = key;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ResponseAuthLogin withNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ResponseAuthLogin withEmail(String email) {
        this.email = email;
        return this;
    }

}
