package com.miguelcr.mecaround.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.miguelcr.mecaround.MapsActivity;
import com.miguelcr.mecaround.adapters.MyAveriaRecyclerViewAdapter;
import com.miguelcr.mecaround.R;
import com.miguelcr.mecaround.commons.Constantes;
import com.miguelcr.mecaround.interfaces.MecAroundApiInterface;
import com.miguelcr.mecaround.interfaces.OnAveriaInteractionListener;
import com.miguelcr.mecaround.models.Averia;
import com.miguelcr.mecaround.models.ResponseAuthLogin;
import com.miguelcr.mecaround.models.ResponseAverias;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.net.HttpURLConnection.HTTP_OK;

public class ListadoAveriasFragment extends Fragment {
    OnAveriaInteractionListener mListener;
    List<Averia> averiaList;
    SharedPreferences sharedPrefs;
    String serverKey;
    RecyclerView recyclerView;

    public ListadoAveriasFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefs = getActivity().getSharedPreferences(getString(R.string.preferencias_mecaround_file), Context.MODE_PRIVATE);
        serverKey = sharedPrefs.getString(getString(R.string.preferencias_server_key),"");
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
            averiaList = new ArrayList<>();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constantes.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MecAroundApiInterface service = retrofit.create(MecAroundApiInterface.class);

            Call<ResponseAverias> serverResponse = service.getAverias(serverKey);

            serverResponse.enqueue(new Callback<ResponseAverias>() {
                @Override
                public void onResponse(Call<ResponseAverias> call, Response<ResponseAverias> response) {
                    if (response.code() == HTTP_OK) {
                        averiaList = response.body().getAverias();
                        recyclerView.setAdapter(new MyAveriaRecyclerViewAdapter(getActivity(), averiaList, mListener));
                    }
                }

                @Override
                public void onFailure(Call<ResponseAverias> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error al cargar el listado de averías", Toast.LENGTH_SHORT).show();
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
