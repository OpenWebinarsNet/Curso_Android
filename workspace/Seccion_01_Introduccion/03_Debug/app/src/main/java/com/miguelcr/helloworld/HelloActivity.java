package com.miguelcr.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HelloActivity extends AppCompatActivity {
    private int i;
    private String metodo = "Hola";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        Log.i("TAG cicloVida ","Ciclovida: onCreate");
        metodo = "onCreate";
        i = 0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG cicloVida ","Ciclovida: onStart");
        metodo = "onStart";
        i++;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG cicloVida ","Ciclovida: onResume");
        i++;
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG cicloVida ","Ciclovida: onPause");
        i++;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG cicloVida ","Ciclovida: onStop");
        i--;
    }
}
