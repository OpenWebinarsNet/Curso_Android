package com.miguelcr.a04_checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroupAv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupAv = (RadioGroup) findViewById(R.id.radioGroupAverias);
    }

    public void tipoAveriaClicked(View view) {

        String tipo = "";

        // Sobre qué checkbox se ha hecho click
        switch (view.getId()) {
            case R.id.checkBoxChapa:
                tipo = "Chapa y pintura";
                break;
            case R.id.checkBoxCristal:
                tipo = "Cristal";
                break;
            case R.id.checkBoxMecanica:
                tipo = "Avería mecánica";
                break;
        }

        Toast.makeText(this, tipo, Toast.LENGTH_SHORT).show();
    }

    public void conocerOpcionMarcada(View view) {
        int idSeleccionado = radioGroupAv.getCheckedRadioButtonId();
        String tipo = "";
        if(idSeleccionado==R.id.checkBoxChapa) {
            tipo = "Has seleccionado Chapa y pintura";
        } else if (idSeleccionado==R.id.checkBoxCristal) {
            tipo = "Has seleccionado Cristal";
        } else {
            tipo = "Has seleccionado Avería mecánica";
        }

        Toast.makeText(this, tipo, Toast.LENGTH_SHORT).show();
    }
}
