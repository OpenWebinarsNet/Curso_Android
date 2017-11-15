package com.miguelcr.mecaround;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.miguelcr.mecaround.fragments.ListaTalleresFragment;
import com.miguelcr.mecaround.fragments.ListadoAveriasFragment;
import com.miguelcr.mecaround.interfaces.OnAveriaInteractionListener;
import com.miguelcr.mecaround.interfaces.OnTallerInteractionListener;
import com.miguelcr.mecaround.models.Averia;
import com.miguelcr.mecaround.models.Taller;

public class MainActivity extends AppCompatActivity implements OnAveriaInteractionListener,
        OnTallerInteractionListener {
    String serverKey;
    SharedPreferences sharedPrefs;
    Menu menuOpciones;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment f = null;

            switch (item.getItemId()) {
                case R.id.navigation_averias:
                    f = new ListadoAveriasFragment();
                    menuOpciones.findItem(R.id.action_show_map).setVisible(false);
                    menuOpciones.findItem(R.id.action_nueva_averia).setVisible(true);
                    break;
                case R.id.navigation_talleres:
                    f = new ListaTalleresFragment();
                    menuOpciones.findItem(R.id.action_show_map).setVisible(true);
                    menuOpciones.findItem(R.id.action_nueva_averia).setVisible(false);
                    break;
                case R.id.navigation_perfil:

                    break;
            }

            if(f!=null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container,f)
                        .commit();

                return true;
            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_private);

        sharedPrefs = getSharedPreferences(getString(R.string.preferencias_mecaround_file), Context.MODE_PRIVATE);
        serverKey = sharedPrefs.getString(getString(R.string.preferencias_server_key),"");

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,new ListadoAveriasFragment())
                .commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_options_menu, menu);
        menuOpciones = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_show_map:
                Intent i = new Intent(this,MapsActivity.class);
                startActivity(i);
                return true;
            case R.id.action_logout:
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putBoolean(getString(R.string.preferencias_islogin), false);
                editor.putString(getString(R.string.preferencias_server_key), "");
                // Commit the edits!
                editor.commit();

                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAveriaClick(Averia averia) {

    }

    @Override
    public void onTallerClick(Taller taller) {
        Intent i = new Intent(this, DetalleTallerScrollingActivity.class);
        i.putExtra("taller",taller.getNombre());
        i.putExtra("direccion",taller.getDireccion());
        i.putExtra("telefono",taller.getTelefono());
        i.putExtra("latlon",taller.getLatlng());
        startActivity(i);
    }
}
