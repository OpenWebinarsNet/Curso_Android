package com.miguelcr.mecaround;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.miguelcr.mecaround.adapters.MyTallerRecyclerViewAdapter;
import com.miguelcr.mecaround.commons.Constantes;
import com.miguelcr.mecaround.interfaces.MecAroundApiInterface;
import com.miguelcr.mecaround.models.Taller;
import com.miguelcr.mecaround.models.TalleresResponse;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.net.HttpURLConnection.HTTP_OK;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String serverKey;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        sharedPrefs = getSharedPreferences(getString(R.string.preferencias_mecaround_file), Context.MODE_PRIVATE);
        serverKey = sharedPrefs.getString(getString(R.string.preferencias_server_key),"");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

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
                    // Add a marker in Sydney and move the camera
                    List<Taller> tallerList = response.body().getTalleres();

                    double latMedia = 0;
                    double lonMedia = 0;

                    for(Taller t : tallerList) {
                        String latLon = t.getLatlng();
                        String lat = latLon.split(",")[0];
                        String lon = latLon.split(",")[1];

                        LatLng posicionTaller = new LatLng(Double.parseDouble(lat), Double.parseDouble(lon));
                        mMap.addMarker(new MarkerOptions()
                                .position(posicionTaller)
                                .draggable(true)
                                .title(t.getNombre())
                                .snippet(t.getDireccion()+" - "+t.getTelefono())
                                .icon(bitmapDescriptorFromVector(MapsActivity.this,R.drawable.ic_marker_mecaround)));

                        latMedia += Double.parseDouble(lat);
                        lonMedia += Double.parseDouble(lon);
                    }


                    mMap.moveCamera(CameraUpdateFactory.newLatLng(
                            new LatLng(latMedia/tallerList.size(),lonMedia/tallerList.size())
                    ));

                }
            }

            @Override
            public void onFailure(Call<TalleresResponse> call, Throwable t) {
                Toast.makeText(MapsActivity.this, "Error al cargar el listado de talleres", Toast.LENGTH_SHORT).show();
            }
        });




    }

    // El siguiente método transforma una imagen vectorial que tengamos en el directorio drawable
    // por una imagen Bitmap
    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
