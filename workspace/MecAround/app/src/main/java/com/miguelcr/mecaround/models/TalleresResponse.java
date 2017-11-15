
package com.miguelcr.mecaround.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TalleresResponse {

    @SerializedName("talleres")
    @Expose
    private List<Taller> talleres = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TalleresResponse() {
    }

    /**
     * 
     * @param talleres
     */
    public TalleresResponse(List<Taller> talleres) {
        super();
        this.talleres = talleres;
    }

    public List<Taller> getTalleres() {
        return talleres;
    }

    public void setTalleres(List<Taller> talleres) {
        this.talleres = talleres;
    }

    public TalleresResponse withTalleres(List<Taller> talleres) {
        this.talleres = talleres;
        return this;
    }

}
