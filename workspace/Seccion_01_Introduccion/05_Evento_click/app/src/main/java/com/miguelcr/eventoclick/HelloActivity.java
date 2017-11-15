package com.miguelcr.eventoclick;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HelloActivity extends AppCompatActivity implements View.OnClickListener {
    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        texto = (TextView)findViewById(R.id.textoEvento);

        texto.setOnClickListener(this);

    }

    public void initSecActivity(View view) {
        Toast.makeText(this, "Has hecho click en el texto", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id==R.id.textoEvento) {
            Toast.makeText(HelloActivity.this, "Has hecho click con escuchador", Toast.LENGTH_LONG).show();
        } else {
            //...
        }
    }
}
