package com.example.wposs_user.polariscoreandroid.Comun;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLOutput;

public class Messages {


    public static void packMsgLogin() {
        packHttpData();
        packHttpHeader();

        Global.outputData = (Global.httpHeaderBuffer + "\r\n\r\n" + Global.httpDataBuffer).getBytes();

        Global.outputLen = Global.outputData.length;
        //Utils.dumpMemory(Global.outputData, Global.outputLen);

    }
    public static void packUpdatePass() {
       packTramaCambioClave();
       packHttpDataActPass();

        Global.outputData = (Global.headUpdate + "\r\n\r\n" + Global.bodyUpdate).getBytes();
        Global.outputLen = Global.outputData.length;
        //Utils.dumpMemory(Global.outputData, Global.outputLen);

    }





    public static void packHttpData() {
        //comienza a armar la trama
        Global.httpDataBuffer = "{\"user_email\": \"<CORREO>\",\"user_password\": \"<PASSWORD>\",\"gethash\": \"true\"}";//se arma la trama

        Global.httpDataBuffer = Global.httpDataBuffer.replace("<CORREO>", Global.correo);
        Global.httpDataBuffer = Global.httpDataBuffer.replace("<PASSWORD>", Global.password);
        //fn


    }


    public static void packHttpHeader() {
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


    public static boolean unPackMsgLogin(Context c) {

        String tramaCompleta = "";


        int indice = 0;

        Global.inputData = Global.httpDataBuffer.getBytes();

        tramaCompleta = uninterpret_ASCII(Global.inputData, indice, Global.inputData.length);//se convierte arreglo de bytes a string

        int tramaNecesitada = tramaCompleta.indexOf("}");

        String trama = tramaCompleta.substring(0, tramaNecesitada + 1);//ESTA ES LA TRAMA QUE ENVIA EL SERVIDOR, ES LA QUE SE VA A DESEMPAQUETAR

        String[] lineastrama = trama.split(",");

        JSONObject jsonObject =null;
        try {
            jsonObject = new JSONObject(tramaCompleta);

            Global.MESSAGE=jsonObject.get("message").toString();
            Log.i("MESSAGE:    ",""+Global.MESSAGE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (!Global.MESSAGE.equalsIgnoreCase("success")) {
            try {
                Global.mensaje=jsonObject.get("description").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }else {
            Global.mensaje="inicio de sesion correcto";
            Global.TOKEN = lineastrama[0].substring(10, lineastrama[0].length() - 1);
            Global.MESSAGE = lineastrama[1].substring(11, lineastrama[1].length() - 1);
            Global.ROL = lineastrama[2].substring(9, lineastrama[2].length() - 1);
            Global.LOGIN = lineastrama[3].substring(9, lineastrama[3].length() - 1);
            Global.ID = lineastrama[4].substring(6, lineastrama[4].length() - 1);
            Global.STATUS = lineastrama[5].substring(10, lineastrama[5].length() - 1);
            Global.POSITION = lineastrama[6].substring(12, lineastrama[6].length() - 1);
            Global.CODE = lineastrama[7].substring(8, lineastrama[7].length() - 1);
            return true;
        }

    }


    public static void packTramaCambioClave( ){
        String tramaCompleta="";

        //Head

   Global.headUpdate= "PUT " + Global.WEB_SERVICE + " HTTP/1.1" + "\r\n";;
        Global.headUpdate= "Content-Type: application/json" +"\r\n";;
        Global.headUpdate= "Authenticator:"+Global.TOKEN + "\r\n";;
        Global.headUpdate= Global.INITIAL_IP;
        Global.headUpdate= ":"+Global.INITIAL_PORT+ "\r\n";
        Global.headUpdate= "Content-Length: "+ Global.headUpdate.length();


   }

    public static void packHttpDataActPass(){

        //armo el body
        Global.bodyUpdate= "{\"user_identification\": \"<CORREO>\",\"user_password\": \"<PASSWORD>\"}";//se arma la trama

        Global.bodyUpdate = Global.httpDataBuffer.replace("<CORREO>", Global.ID);
        Global.bodyUpdate = Global.httpDataBuffer.replace("<PASSWORD>", Global.claveNueva);
        //fn


    }


 /*   public static void messageGuardar(int mensaje, String informacion, int tiempo) {
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

            default: break;
        }

        //if(tiempo!=null)
        //    Global.TimeMensaje=(tiempo);

    }*/

    /**
     * Este metodo convierte un array de Bytes a un objeto tipo String
     *
     * @retorna la cadena tipo String
     */
    public static String uninterpret_ASCII(byte[] rawData, int offset, int length) {
        char[] ret = new char[length];
        for (int i = 0; i < length; i++) {
            ret[i] = (char) rawData[offset + i];
        }
        return new String(ret);
    }


    private static void GuardarMensaje(String mensaje) {


        Global.mensaje += mensaje + "\n";


    }


}
