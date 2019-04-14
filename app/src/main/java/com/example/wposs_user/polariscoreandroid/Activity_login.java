package com.example.wposs_user.polariscoreandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_login extends AppCompatActivity {

    private EditText txtCorreo;
    private EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtCorreo=(EditText)findViewById(R.id.txtCorreo);
        txtPass=(EditText)findViewById(R.id.txtPass);

    }

    public void iniciarSesion(View view){
        String correo=this.txtCorreo.getText().toString();
        String pass=this.txtPass.getText().toString();

        if(correo.isEmpty()&&pass.isEmpty()){
            Toast.makeText(this, "Ingrese correo y contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        if(correo.isEmpty()){
            Toast.makeText(this, "Debe ingresar el correo", Toast.LENGTH_SHORT).show();
            return;
        }if (pass.isEmpty()){
            Toast.makeText(this, "Ingrese la contraseña", Toast.LENGTH_SHORT).show();
            return;
        }else{
            new TaskLogin().execute();
        }

    }


    /*******************************************************************************
     Clase       : TaskLogin
     Description : Realiza la transacción de los parámetros del Login
     *******************************************************************************/

    class TaskLogin extends AsyncTask<String, Void, Boolean>{
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





        }
    }






    public void menu(View v){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void actu_clave(View v){

        Intent i = new Intent(this, Actualizar_clave.class);
        startActivity(i);
        finish();
    }




}
