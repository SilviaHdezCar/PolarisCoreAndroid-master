package com.example.wposs_user.polariscoreandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Vector;

public class AdapterTerminal_asociada extends RecyclerView.Adapter<AdapterTerminal_asociada.ViewHolderTerminal> {

    private Vector<Terminal> listTerminal;
    private LayoutInflater inflador;

    public AdapterTerminal_asociada(Context c, Vector<Terminal> list) {
        this.listTerminal = list;
        this.inflador = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public ViewHolderTerminal onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.panel_terminal_asociada, null);

        return new ViewHolderTerminal(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderTerminal holder, int i) {
        Terminal ter = this.listTerminal.elementAt(i);
        holder.serial.setText( ter.getSerial());
        holder.marca.setText( ter.getMarca());
        holder.modelo.setText( ter.getModelo());
        holder.tecnologia.setText( ter.getTecnologia());
        holder.estado.setText(ter.getEstado());
        holder.fechaANS.setText(ter.getFechaLimite()+"");

    }

    @Override
    public int getItemCount() {
        return listTerminal.size();

    }

    public class ViewHolderTerminal extends RecyclerView.ViewHolder {

        TextView serial;
        TextView marca;
        TextView modelo;
        TextView tecnologia;
        TextView estado;
        TextView fechaANS;


        public ViewHolderTerminal(View v) {
            super(v);
            serial = (TextView) v.findViewById(R.id.serial_ter_asociada);
            marca = (TextView) v.findViewById(R.id.marca_ter_asociada);
            modelo = (TextView) v.findViewById(R.id.modelo_ter_asociada);
            tecnologia = (TextView) v.findViewById(R.id.tecno_ter_asociada);
            estado = (TextView) v.findViewById(R.id.estado_ter_asociada);
            fechaANS = (TextView) v.findViewById(R.id.fechal_ter_asociada);


        }


    }



}
