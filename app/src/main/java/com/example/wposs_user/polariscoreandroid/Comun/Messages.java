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
        Global.httpDataBuffer = "{\"user_email\": \"<CORREO>\",\"user_password\": \"<PASSWORD>\",\"gethash\": \"true\"}" ;//se arma la trama

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


    public static boolean unPackMsgLogin(Context c) {


        String tramaCompleta="";


        int indice=0;

        Global.inputData=Global.httpDataBuffer.getBytes();

       tramaCompleta=uninterpret_ASCII(Global.inputData, indice,Global.inputData.length);//se convierte arreglo de bytes a string

        int tramaNecesitada=tramaCompleta.indexOf("}");

        String trama=tramaCompleta.substring(0,tramaNecesitada+1);//trama completa recibida

        String [] lineastrama=trama.split(",");

     //   IF()-MAL
        if(lineastrama.length<=2){

        }


//      int posToken= lineastrama[0].indexOf(",");
       Global.TOKEN=lineastrama[0].substring(10,lineastrama[0].length()-1);
       Global.MESSAGE=lineastrama[1].substring(11,lineastrama[1].length()-1);
       Global.ROL = lineastrama[2].substring(9,lineastrama[2].length()-1);
       Global.LOGIN = lineastrama[3].substring(9,lineastrama[3].length()-1);
       Global.ID = lineastrama[4].substring(6,lineastrama[4].length()-1);
       Global.STATUS = lineastrama[5].substring(10,lineastrama[5].length()-1);
       Global.POSITION = lineastrama[6].substring(12,lineastrama[6].length()-1);
       Global.CODE = lineastrama[7].substring(8,lineastrama[7].length()-1);



        return true;



        //IF

    }







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



    /** Este metodo convierte un array de Bytes a un objeto tipo String
     * @retorna la cadena tipo String
     */
    public static String uninterpret_ASCII(byte[] rawData, int offset, int length){
        char[] ret = new char[length];
        for (int i = 0; i < length; i++)
        {
            ret[i] = (char)rawData[offset + i];
        }
        return new String(ret);
    }


    private static void GuardarMensaje(String mensaje) {


        Global.mensaje+=mensaje+"\n";


    }



}
