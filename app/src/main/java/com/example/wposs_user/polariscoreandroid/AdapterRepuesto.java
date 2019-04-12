package com.example.wposs_user.polariscoreandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterRepuesto extends RecyclerView.Adapter<AdapterRepuesto.ViewHolderRepuesto>{


    ArrayList<Repuesto> rep ;


    public AdapterRepuesto(ArrayList<Terminal> ter)
    {
        this.rep = rep;
    }


    @Override
    public AdapterRepuesto.ViewHolderRepuesto onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.panel_repuesto,null,false);

        return new AdapterRepuesto.ViewHolderRepuesto(v);
    }


    public void onBindViewHolder(AdapterRepuesto.ViewHolderRepuesto holderDatos, int i) {
        holderDatos.asignarRepuesto(rep.get(i));

    }

    @Override
    public int getItemCount() {
        return rep.size();
    }

    public class ViewHolderRepuesto extends RecyclerView.ViewHolder {

        TextView codigo;
        TextView nombre;
        TextView estado;
        TextView cantidad;
        TextView titulo;



        public ViewHolderRepuesto(View v) {
            super(v);
            codigo = (TextView) v.findViewById(R.id.cod_rep);
            nombre = (EditText) v.findViewById(R.id.nom_rep);
            estado = (EditText) v.findViewById(R.id.estado_rep);
            cantidad = (EditText) v.findViewById(R.id.cant_rep);
            titulo = (EditText) v.findViewById(R.id.titulo_rep);
        }



        public void asignarRepuesto(Repuesto r) {

            int i = 0;


            for (Repuesto repu : rep) {


                codigo.setText(r.getCodigo());
               nombre.setText(r.getNombre());
                estado.setText(r.getEstado());
                cantidad.setText(String.valueOf(r.getCantidad()));



                if(repu.getCodigo().equals(codigo.getText().toString())) {
                    int x = rep.indexOf(repu)+1;
                    titulo.setText("-----------------------------------REPUESTO " + x+"-----------------------------------");
                }

            }
        }
    }

}
