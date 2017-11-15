package com.miguelcr.a01_hellomaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        marker = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        // Talleres
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.390929,-5.994512))
                .title("Talleres Juan e Hijos")
                .snippet("Teléfono: 954112233")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.390521,-5.999415))
                .title("Mecánica González")
                .snippet("Teléfono: 954554433")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));

        Marker central = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.389515,-5.992613))
                .title("Talleres de electricidad del automóvil")
                .snippet("Teléfono: 954339988")
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(central.getPosition()));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.setOnMarkerClickListener(this);
        mMap.setOnMarkerDragListener(this);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, "Has hecho clic en marker: "+marker.getTitle(), Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        Toast.makeText(this, "Se comienza a arrastrar el marker: "+
                marker.getTitle()
                + "en lat,lon: "+marker.getPosition().latitude+","
                + marker.getPosition().longitude, Toast.LENGTH_SHORT).show();
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        Log.i("MARKER","MARKER: "+marker.getPosition().latitude+","
        +marker.getPosition().longitude);

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        Toast.makeText(this, "Se ha soltado el marker: "+
                marker.getTitle()
                + "en lat,lon: "+marker.getPosition().latitude+","
                + marker.getPosition().longitude,
                Toast.LENGTH_SHORT).show();
        marker.showInfoWindow();
    }
}
