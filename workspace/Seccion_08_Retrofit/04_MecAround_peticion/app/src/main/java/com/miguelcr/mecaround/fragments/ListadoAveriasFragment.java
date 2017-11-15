package com.miguelcr.mecaround.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.miguelcr.mecaround.adapters.MyAveriaRecyclerViewAdapter;
import com.miguelcr.mecaround.commons.Constantes;
import com.miguelcr.mecaround.interfaces.ApiMecAroundInterface;
import com.miguelcr.mecaround.interfaces.OnAveriaInteractionListener;
import com.miguelcr.mecaround.R;
import com.miguelcr.mecaround.responses.Averia;
import com.miguelcr.mecaround.responses.ResponseAverias;
import com.miguelcr.mecaround.responses.ResponseRegistro;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoAveriasFragment extends Fragment {
    OnAveriaInteractionListener mListener;
    List<Averia> averiaList;
    RecyclerView recyclerView;
    Context ctx;
    String apiKey;

    public ListadoAveriasFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = getActivity();

        SharedPreferences sharedPref = ctx.getSharedPreferences(getString(R.string.preferencias_mecaround_file), Context.MODE_PRIVATE);
        apiKey = sharedPref.getString(getString(R.string.preferencias_key),"");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_averia_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            // Lista de averias
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constantes.API_BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiMecAroundInterface apiService = retrofit.create(ApiMecAroundInterface.class);

            Call<ResponseAverias> peticionAverias = apiService.getAverias(apiKey);

            peticionAverias.enqueue(new Callback<ResponseAverias>() {
                @Override
                public void onResponse(Call<ResponseAverias> call, Response<ResponseAverias> response) {

                    if(response.code()== HttpURLConnection.HTTP_OK) {
                        averiaList = response.body().getAverias();
                        recyclerView.setAdapter(new MyAveriaRecyclerViewAdapter(ctx, averiaList, mListener));
                    } else {
                        Toast.makeText(ctx, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<ResponseAverias> call, Throwable t) {

                }
            });

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAveriaInteractionListener) {
            mListener = (OnAveriaInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnAveriaInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
