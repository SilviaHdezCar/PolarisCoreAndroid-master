package com.example.wposs_user.polariscoreandroid.Comun;

import java.net.Socket;

public class Global {


    public static String correo;
    public static String password;


    public static String primaryIP;
    public static int primaryPort;

    public static  String mensaje ="";

    public static Socket tcpSocket = null;


    public static  String    WEB_SERVICE          = "";

    public static final String    INITIAL_IP          = "100.25.214.91";
    public static final int    INITIAL_PORT           = 3000;

    public static final String    HTTP_HEADER1       = "Content-Type: application/json";
    public static final String    HTTP_HEADER2        ="Host: ";
    public static final String    HTTP_HEADER3        = "Content-Length: ";

    public static final int       SOCKET_TIMEOUT          = 20000;

    public static final int       TRANSACTION_OK          = 1;
    public static final int       ERR_OPEN_SOCKET         = -1;
    public static final int       ERR_WRITE_SOCKET        = -2;
    public static final int       ERR_TIMEOUT_SOCKET      = -3;
    public static final int       ERR_READ_SOCKET         = -4;
    public static final int       ERR_DATA_RECEIVED       = -5;

    public static final int       MAX_LEN_OUTPUTDATA	  = 2048;
    public static final int       MAX_LEN_INPUTDATA		  = 4096;

    public static String    httpDataBuffer       = "";
    public static String    httpHeaderBuffer     = "";

    public static int            inputLen;
    public static int            outputLen;

    public static byte[]         outputData               = new byte[MAX_LEN_OUTPUTDATA];
    public static byte[]         inputData                = new byte[MAX_LEN_INPUTDATA];


    public static boolean enSesion=false;
}
