package com.example.wposs_user.polariscoreandroid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class Tab_terminal extends Fragment{

    ArrayList<Terminal> ter ;
    RecyclerView recycler;
    AdapterTerminal adt;


/*
    public View onCreate(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState)
    {

       View v = inflater.inflate(R.layout.fragment_tab_ter, container, false);
        recycler = (RecyclerView) v.findViewById(R.id.rvTerminal);
        ter = new ArrayList<Terminal>();
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Terminal t = new Terminal("AB1444", "SONY", "2014  ", "SIM", "DISP", null);
        ter.add(t);
        adt = new AdapterTerminal(ter);
        recycler.setAdapter(adt);




        return  v;

    }*/




}
