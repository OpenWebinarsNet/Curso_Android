package com.miguelcr.a01_hellomaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    Marker marker;
    Circle circle;
    LatLng posicionAnterior;

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
        posicionAnterior = sydney;
        marker = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,10));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.setOnMapClickListener(this);
        mMap.setOnMapLongClickListener(this);

        // Definición círculo en Mapa
        CircleOptions circleOptions = new CircleOptions()
                .center(sydney)
                .fillColor(ContextCompat.getColor(this,R.color.colorAccent))
                .strokeColor(ContextCompat.getColor(this,R.color.colorPrimary))
                .strokeWidth(10)
                .radius(1000); // In meters

        // Get back the mutable Circle
        circle = mMap.addCircle(circleOptions);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(this, "Has hecho click en: "+latLng.latitude+","+latLng.longitude, Toast.LENGTH_SHORT).show();
        mMap.addMarker(new MarkerOptions()
        .position(latLng)
        .title("Nuevo marker"));

        // Instantiates a new Polyline object and adds points to define a rectangle
        PolylineOptions rectOptions = new PolylineOptions()
                .add(posicionAnterior)
                .add(latLng); // Closes the polyline.

        Polyline polyline = mMap.addPolyline(rectOptions);

        posicionAnterior = latLng;

        circle.setCenter(latLng);
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        Toast.makeText(this, "Has hecho LONG click en: "+latLng.latitude+","+latLng.longitude, Toast.LENGTH_SHORT).show();
    }
}
