package com.miguelcr.a04_fragmentos_comunicacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnControlesFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.contenedor,new ControlesFragment())
                .commit();
    }

    @Override
    public void botonColorClicked(String color) {
        Toast.makeText(this, "Estoy en el activity, color recibido: "+color, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void botonTextoClicked(String texto) {
        Toast.makeText(this, "Estoy en el activity, texto recibido: "+texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mensaje(String texto) {

    }
}
