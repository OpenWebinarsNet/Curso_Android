package com.miguelcr.a06_intent_explicita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciarActivity(View view) {
        // Escribiremos el código necesario para iniciar el
        // activity DatosActivity >>> intent explícito

        Intent intentDatos = new Intent(this,DatosActivity.class);

        intentDatos.putExtra("numero",5);
        intentDatos.putExtra("nombre","Miguel");

        startActivity(intentDatos);
    }
}
