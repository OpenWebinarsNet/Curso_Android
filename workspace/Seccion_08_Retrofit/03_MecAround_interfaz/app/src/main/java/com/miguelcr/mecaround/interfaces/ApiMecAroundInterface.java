package com.miguelcr.mecaround.interfaces;

import com.miguelcr.mecaround.responses.ResponseAverias;
import com.miguelcr.mecaround.responses.ResponseRegistro;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by miguelcampos on 29/10/17.
 */

public interface ApiMecAroundInterface {

    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseRegistro> doLogin(@Field("email") String e,
                                   @Field("password") String p);


    @GET("averia/lista")
    Call<ResponseAverias> getAverias(@Query("X-API-KEY") String key);
}
