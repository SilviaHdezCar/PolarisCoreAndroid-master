package com.example.wposs_user.polariscoreandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ConsultaTerminalesSerial extends Fragment {

    private MainActivity obj;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //obj.buscarTerminalSerial();
        return inflater.inflate(R.layout.fragment_consultar_terminales_serial, container, false);

    }
}
