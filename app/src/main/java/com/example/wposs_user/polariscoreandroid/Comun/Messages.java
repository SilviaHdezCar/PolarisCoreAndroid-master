package com.example.wposs_user.polariscoreandroid.Comun;

import android.content.Context;
import android.util.Log;

public class Messages {



    public static void packMsgLogin(){
        packHttpData();
        packHttpHeader();

        Global.outputData = (Global.httpHeaderBuffer + "\r\n\r\n" + Global.httpDataBuffer).getBytes();
        Log.d("Silvia",  "DATA**********"+Global.httpDataBuffer);
        Log.d("Silvia",  "HEADER**********"+Global.httpHeaderBuffer);
        Global.outputLen = Global.outputData.length;
        Utils.dumpMemory(Global.outputData, Global.outputLen);

    }

    public static void packHttpData(){
        //comienza a armar la trama
        Global.httpDataBuffer = "{\"user_email\" \"<CORREO>\",\"user_password\": \"<PASSWORD>\",\"gethash\": \"true\"}";//se arma la trama

        Global.httpDataBuffer = Global.httpDataBuffer.replace("<CORREO>", Global.correo);
        Global.httpDataBuffer = Global.httpDataBuffer.replace("<PASSWORD>", Global.password);
        //fn



    }

    public static void packHttpHeader(){
//cabecera
        int tam;
        Global.httpHeaderBuffer = "";
        Global.httpHeaderBuffer = "POST " + Global.WEB_SERVICE + " HTTP/1.1";
        Global.httpHeaderBuffer = Global.httpHeaderBuffer + "\r\n";
        Global.httpHeaderBuffer = Global.httpHeaderBuffer + Global.HTTP_HEADER1;
        Global.httpHeaderBuffer = Global.httpHeaderBuffer + "\r\n";
        Global.httpHeaderBuffer = Global.httpHeaderBuffer + Global.HTTP_HEADER2;
        Global.httpHeaderBuffer = Global.httpHeaderBuffer + Global.INITIAL_IP;
        Global.httpHeaderBuffer = Global.httpHeaderBuffer + ":";
        Global.httpHeaderBuffer = Global.httpHeaderBuffer + Global.INITIAL_PORT;
        Global.httpHeaderBuffer = Global.httpHeaderBuffer + "\r\n";
        Global.httpHeaderBuffer = Global.httpHeaderBuffer + Global.HTTP_HEADER3;
        Global.httpHeaderBuffer = Global.httpHeaderBuffer + Global.httpDataBuffer.length();

    }

/*
    public static boolean unPackMsgLogin(Context c) {


    }*/







    public static void messageGuardar(int mensaje, String informacion, int tiempo) {

        Global.mensaje="";
        switch(mensaje) {

            case 1:
                GuardarMensaje("No se establecio");
                GuardarMensaje("conexion GPRS.");
                GuardarMensaje("<Intente mas tarde>");
                break;

            case 2:
                GuardarMensaje("No se establecio");
                GuardarMensaje("comunicacion con el");
                GuardarMensaje("servidor. ");
                GuardarMensaje("<Intente mas tarde>");
                break;

            case 3:
                GuardarMensaje("Usuario o clave");
                GuardarMensaje("invalidos !!!");
                break;

                /*
            case 4:
                GuardarMensaje("Clave invalida !!!");
                break;
            case 5:
                GuardarMensaje("ERROR");
                GuardarMensaje("Problemas de HTTP");
                break;


            case 112:
                GuardarMensaje("ERROR");
                GuardarMensaje("CONEXION HTTP no es OK");
                break;
*/
            default: break;
        }


    }


    private static void GuardarMensaje(String mensaje) {


        Global.mensaje+=mensaje+"\n";


    }
}
