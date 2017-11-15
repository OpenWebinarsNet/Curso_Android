package com.miguelcr.mecaround;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.miguelcr.mecaround.commons.Constantes;
import com.miguelcr.mecaround.interfaces.ApiMecAroundInterface;
import com.miguelcr.mecaround.responses.ResponseRegistro;

import java.net.HttpURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    ImageView logotipo;
    EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logotipo = (ImageView) findViewById(R.id.imageViewLogo);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPass);

        RequestOptions cropOptions = new RequestOptions().fitCenter();

        Glide.with(this)
                .load("http://miguelcamposrivera.com/logo_mecaround.png")
                .apply(cropOptions)
                .into(logotipo);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferencias_mecaround_file), Context.MODE_PRIVATE);
        boolean isLogin = sharedPref.getBoolean(getString(R.string.preferencias_islogin),false);

        if(isLogin) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
        }
    }

    public void doLogin(View view) {
        String email = editTextEmail.getText().toString();
        String pass = editTextPassword.getText().toString();

        if(!email.isEmpty() && !pass.isEmpty()) {


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constantes.API_BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiMecAroundInterface apiService = retrofit.create(ApiMecAroundInterface.class);

            Call<ResponseRegistro> peticionLogin = apiService.doLogin(email,pass);

            peticionLogin.enqueue(new Callback<ResponseRegistro>() {
                @Override
                public void onResponse(Call<ResponseRegistro> call, Response<ResponseRegistro> response) {
                    if(response.code()== HttpURLConnection.HTTP_OK) {

                        String emailRespuesta = response.body().getEmail();
                        String nombreRespuesta = response.body().getNombre();
                        String keyRespuesta = response.body().getKey();

                        // Guardar los datos del login
                        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferencias_mecaround_file),Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(getString(R.string.preferencias_email), emailRespuesta);
                        editor.putString(getString(R.string.preferencias_nombre), nombreRespuesta);
                        editor.putString(getString(R.string.preferencias_key), keyRespuesta);
                        editor.putBoolean(getString(R.string.preferencias_islogin), true);
                        editor.commit();

                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuario y/o contraseña incorrecto", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseRegistro> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Error en la llamada", Toast.LENGTH_SHORT).show();
                }
            });



        } else {
            Toast.makeText(this, "Email y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }

    }
}
