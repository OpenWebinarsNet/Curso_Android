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
import com.miguelcr.mecaround.R;
import com.miguelcr.mecaround.adapters.MyAveriaRecyclerViewAdapter;
import com.miguelcr.mecaround.adapters.MyTallerRecyclerViewAdapter;
import com.miguelcr.mecaround.commons.Constantes;
import com.miguelcr.mecaround.interfaces.MecAroundApiInterface;
import com.miguelcr.mecaround.interfaces.OnTallerInteractionListener;
import com.miguelcr.mecaround.models.Averia;
import com.miguelcr.mecaround.models.ResponseAverias;
import com.miguelcr.mecaround.models.Taller;
import com.miguelcr.mecaround.models.TalleresResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.net.HttpURLConnection.HTTP_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListaTalleresFragment extends Fragment {
    OnTallerInteractionListener mListener;
    List<Taller> tallerList;
    SharedPreferences sharedPrefs;
    String serverKey;
    RecyclerView recyclerView;

    public ListaTalleresFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefs = getActivity().getSharedPreferences(getString(R.string.preferencias_mecaround_file), Context.MODE_PRIVATE);
        serverKey = sharedPrefs.getString(getString(R.string.preferencias_server_key),"");    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_talleres, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            // Lista de talleres
            tallerList = new ArrayList<>();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constantes.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            MecAroundApiInterface service = retrofit.create(MecAroundApiInterface.class);

            Call<TalleresResponse> serverResponse = service.getTalleres(serverKey);

            serverResponse.enqueue(new Callback<TalleresResponse>() {
                @Override
                public void onResponse(Call<TalleresResponse> call, Response<TalleresResponse> response) {
                    if (response.code() == HTTP_OK) {
                        tallerList = response.body().getTalleres();
                        recyclerView.setAdapter(new MyTallerRecyclerViewAdapter(getActivity(), tallerList, mListener));
                    }
                }

                @Override
                public void onFailure(Call<TalleresResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error al cargar el listado de talleres", Toast.LENGTH_SHORT).show();
                }
            });

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTallerInteractionListener) {
            mListener = (OnTallerInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnTallerInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



}
