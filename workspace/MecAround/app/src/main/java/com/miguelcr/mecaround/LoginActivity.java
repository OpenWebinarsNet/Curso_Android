package com.miguelcr.mecaround;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.miguelcr.mecaround.commons.Constantes;
import com.miguelcr.mecaround.interfaces.MecAroundApiInterface;
import com.miguelcr.mecaround.models.ResponseAuthLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText editTextEmail, editTextPass;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the toolbar
        getSupportActionBar().hide();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPass = findViewById(R.id.editTextPassword);

        // Obtengo las preferencias
        sharedPref = getSharedPreferences(getString(R.string.preferencias_mecaround_file), Context.MODE_PRIVATE);

        // Averiguo si la variables de preferencias isLogin es true para saber si
        // el usuario está logueado. En ese caso redirijo a la pantalla de inicio
        if(sharedPref.getBoolean(getString(R.string.preferencias_islogin),false)) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }

    }

    public void doLogin(View view) {
        final String email = editTextEmail.getText().toString();
        String pass = editTextPass.getText().toString();

        if(!email.isEmpty() && !pass.isEmpty()) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constantes.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            MecAroundApiInterface service = retrofit.create(MecAroundApiInterface.class);


            Call<ResponseAuthLogin> serverResponse = service.doLogin(email, pass);


            serverResponse.enqueue(new Callback<ResponseAuthLogin>() {
                @Override
                public void onResponse(Call<ResponseAuthLogin> call, Response<ResponseAuthLogin> response) {
                    if (response.code() == 200) {
                        String serverKey = response.body().getKey();
                        String nombre = response.body().getNombre();

                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);


                        // Guardar los datos del login en Preferencias, a modo de variables de sesión
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(getString(R.string.preferencias_email), email);
                        editor.putString(getString(R.string.preferencias_nombre),nombre);
                        editor.putString(getString(R.string.preferencias_server_key),serverKey);
                        editor.putBoolean(getString(R.string.preferencias_islogin), true);
                        editor.commit();

                    } else {
                        Toast.makeText(LoginActivity.this, "Email and/or password are not ok", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseAuthLogin> call, Throwable t) {

                }
            });

        }
    }
}
