package com.miguelcr.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);

        Log.i("TAG cicloVida ","Ciclovida: onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("TAG cicloVida ","Ciclovida: onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG cicloVida ","Ciclovida: onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("TAG cicloVida ","Ciclovida: onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("TAG cicloVida ","Ciclovida: onStop");
    }
}
