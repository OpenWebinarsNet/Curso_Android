package com.miguelcr.a01_listview_simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lista;
    private List<String> talleresList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listViewTalleres);

        talleresList = new ArrayList<>();
        talleresList.add("Taller Juan e Hijos");
        talleresList.add("Chapa y Pintura González");
        talleresList.add("Norauto");
        talleresList.add("Midas");
        //...

        ArrayAdapter<String> adaptadorTalleres = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                talleresList
        );

        lista.setAdapter(adaptadorTalleres);

        // Evento click sobre cada elemento de la lista
        lista.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
        Toast.makeText(this, "Taller seleccionado: "+talleresList.get(posicion), Toast.LENGTH_SHORT).show();

        //view.animate().rotationY(360).setDuration(1000).start();
    }
}
