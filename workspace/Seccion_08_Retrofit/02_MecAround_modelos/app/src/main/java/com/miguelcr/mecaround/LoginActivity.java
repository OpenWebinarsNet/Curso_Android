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

        if(email.equals("miguel@gmail.com") && pass.equals("1234")) {
            // Login correcto

            // Guardar los datos del login
            SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferencias_mecaround_file),Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.preferencias_email), email);
            editor.putBoolean(getString(R.string.preferencias_islogin), true);
            editor.commit();

            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);

        } else {
            Toast.makeText(this, "Email y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }

    }
}
