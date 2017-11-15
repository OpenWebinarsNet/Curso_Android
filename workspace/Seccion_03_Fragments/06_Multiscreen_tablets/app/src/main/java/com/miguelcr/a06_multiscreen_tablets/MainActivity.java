package com.miguelcr.a06_multiscreen_tablets;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LinearLayout layoutContenedor = (LinearLayout)findViewById(R.id.contenedor);

        if(layoutContenedor!=null) {
            // Tablet
            Toast.makeText(this, "Te encuentras en una tablet", Toast.LENGTH_SHORT).show();
        } else {
            // Móvil
            Toast.makeText(this, "Te encuentras en un móvil de mano", Toast.LENGTH_SHORT).show();

        }
    }
}
