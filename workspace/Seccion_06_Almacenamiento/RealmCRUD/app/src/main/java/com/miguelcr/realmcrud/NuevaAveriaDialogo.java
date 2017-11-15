package com.miguelcr.realmcrud;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by miguelcampos on 27/10/17.
 */

public class NuevaAveriaDialogo extends DialogFragment {
    AlertDialog.Builder builder;
    OnNuevaAveriaListener mListener;
    View v;
    EditText editTextTitulo, editTextDescripcion;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.dialogo_nueva_averia, null);
        editTextTitulo = (EditText)v.findViewById(R.id.editText);
        editTextDescripcion = (EditText)v.findViewById(R.id.editText2);

        builder.setView(v);

        builder.setTitle("Nueva avería")
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), "Avería guardada", Toast.LENGTH_SHORT).show();

                        mListener.onAveriaGuardarClickListener(editTextTitulo.getText().toString(),editTextDescripcion.getText().toString());

                        dialog.dismiss();

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cancelar > cerrar el cuadro de diálogo
                        dialog.dismiss();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (OnNuevaAveriaListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement OnNuevaAveriaListener");
        }
    }
}
