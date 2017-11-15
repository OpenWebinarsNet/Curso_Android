
package com.miguelcr.mecaround.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseAverias {

    @SerializedName("averias")
    @Expose
    private List<Averia> averias = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseAverias() {
    }

    /**
     * 
     * @param averias
     */
    public ResponseAverias(List<Averia> averias) {
        super();
        this.averias = averias;
    }

    public List<Averia> getAverias() {
        return averias;
    }

    public void setAverias(List<Averia> averias) {
        this.averias = averias;
    }

    public ResponseAverias withAverias(List<Averia> averias) {
        this.averias = averias;
        return this;
    }

}
