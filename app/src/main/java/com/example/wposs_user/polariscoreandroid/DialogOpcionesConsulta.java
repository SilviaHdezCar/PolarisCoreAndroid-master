package com.example.wposs_user.polariscoreandroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DialogOpcionesConsulta extends DialogFragment {



/*


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view =inflater.inflate(R.layout.dialogcambiarclave, null);
        claveActual = view.findViewById(R.id.dialog_clave_actual);
        clavenueva = view.findViewById(R.id.dialog_clave_nueva);
        claveConfirmarClave = view.findViewById(R.id.dialog_clave_confirmar);

        builder
                .setTitle("Seleccione el tipo de consulta")
                .setView(view)

                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String actual=claveActual.getText().toString();
                        String nueva=clavenueva.getText().toString();
                        String confirmacion=claveConfirmarClave.getText().toString();

                        final String msj=validarClave(actual, nueva,confirmacion);
                        Toast.makeText(getContext(), msj, Toast.LENGTH_LONG).show();

                        if(!msj.equalsIgnoreCase("Actualización exitosa")){

                        }else{
                            Toast.makeText(getContext(), msj, Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                            return;
                    }
                });
        return builder.create();
    }*/







}
