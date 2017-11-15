package com.miguelcr.mecaround.interfaces;

import com.miguelcr.mecaround.models.ResponseAuthLogin;
import com.miguelcr.mecaround.models.ResponseAverias;
import com.miguelcr.mecaround.models.ResponseDatamasterData;
import com.miguelcr.mecaround.models.TalleresResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by miguelcampos on 29/10/17.
 */

public interface MecAroundApiInterface {

    @FormUrlEncoded
    @GET("datamaster/data")
    Call<ResponseDatamasterData> getDataMaster();

    @FormUrlEncoded
    @POST("auth/login")
    Call<ResponseAuthLogin> doLogin(
            @Field("email") String email,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("averia/nueva")
    Call<ResponseAuthLogin> nuevaAveria(
            @Field("titulo") String titulo,
            @Field("descripcion") String descripcion,
            @Field("marca") long marca,
            @Field("modelo") String modelo);

    @GET("averia/lista")
    Call<ResponseAverias> getAverias(
            @Query("X-API-KEY") String serverKey
    );

    @GET("taller/lista")
    Call<TalleresResponse> getTalleres(
            @Query("X-API-KEY") String serverKey
    );


}
