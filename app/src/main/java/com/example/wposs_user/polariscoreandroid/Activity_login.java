package com.example.wposs_user.polariscoreandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wposs_user.polariscoreandroid.Comun.Global;
import com.example.wposs_user.polariscoreandroid.Comun.Messages;
import com.example.wposs_user.polariscoreandroid.Comun.Utils;
import com.example.wposs_user.polariscoreandroid.TCP.TCP;

public class Activity_login extends AppCompatActivity {

    private EditText txtCorreo;
    private EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtPass = (EditText) findViewById(R.id.txtPass);


    }

    public void iniciarSesion(View view) {
        String correo = this.txtCorreo.getText().toString();
        String pass = this.txtPass.getText().toString();

        if (correo.isEmpty() && pass.isEmpty()) {
            Toast.makeText(this, "Ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        if (correo.isEmpty()) {
            Toast.makeText(this, "Debe ingresar el correo", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty()) {
            Toast.makeText(this, "Ingrese la contraseña", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Global.WEB_SERVICE="/PolarisCore/Users/login";
            Global.primaryIP = Global.INITIAL_IP;
            Global.primaryPort = Global.INITIAL_PORT;

            Global.correo=correo;
            Global.password=pass;

         new TaskLogin().execute();//hacer la peticion

        /*    Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();*/
        }

    }


    /*******************************************************************************
     Clase       : TaskLogin
     Description : Realiza la transacción de los parámetros del Login
     *******************************************************************************/


    class TaskLogin extends AsyncTask<String, Void, Boolean> {
        ProgressDialog progressDialog;
        int trans = 0;


        /*******************************************************************************
         Método       : onPreExecute
         Description  : Se ejecuta antes de realizar el proceso, muestra una ventana con uin msj de espera
         *******************************************************************************/



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Activity_login.this, R.style.MyAlertDialogStyle);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Validando credenciales...");
            progressDialog.show();
        }


    /*******************************************************************************
     Método       : doInBackground
     Description  : Se ejecuta para realizar la transacción y verificar coenxión
     *******************************************************************************/
        @Override
        protected Boolean doInBackground(String... strings) {
            Messages.packMsgLogin();

            trans= TCP.transaction(Global.outputLen);

            // Verifica la transacción
            if ( trans == Global.TRANSACTION_OK)
                return true;
            else
                return false;
        }

        /*******************************************************************************
         Método       : onPostExecute
         Description  : Se ejecuta después de realizar el doInBackground
         *******************************************************************************/
        @Override
        protected void onPostExecute(Boolean value) {

            progressDialog.dismiss();
           Messages.unPackMsgLogin(Activity_login.this);
            /*if (value) {

              if ( Messages.unPackMsgLogin(Activity_login.this) ) {
                    Global.enSesion = true;

                    //validar si la clave es el mismo numero de cedula de
                   // Utils.GoToNextActivity(Activity_login.this, A.class, Global.StatusExit);


                } else {
                    // Si el login no es OK, manda mensaje de error
                    try{
                        Utils.GoToNextActivity(MainActivity.this, ErrorProgramaActivity.class, Global.StatusExit);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    // Limpia el login

                }

                limpiarLogin();
                // Si es falso, cierra el socket y vuelve a crearlo, si es verdadero el socket continua abierto
                TCP.disconnect();

            }
            else {
               switch (Utils.validateErrorsConexion(false,trans,MainActivity.this) ){

                    case 0:                                                                         // En caso de que continue = true y error data
                        break;

                    case 1:                                                                         // En caso de que continue = false y error data
                        break;

                    default:                                                                        // Errores de conexion
                        Global.MsgError = Global.MSG_ERR_CONEXION;
                        Global.StatusExit = false;
                        // Muestra la ventana de error
                        Utils.GoToNextActivity(MainActivity.this, ErrorProgramaActivity.class,false);
                        break;
                }
                limpiarLogin();
            }*/
        }


    }

    public void menu(View v) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void actu_clave(View v) {

        Intent i = new Intent(this, Actualizar_clave.class);
        startActivity(i);
        finish();
    }


}
