
package com.miguelcr.mecaround.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMensaje {

    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseMensaje() {
    }

    /**
     * 
     * @param mensaje
     */
    public ResponseMensaje(String mensaje) {
        super();
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ResponseMensaje withMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

}
