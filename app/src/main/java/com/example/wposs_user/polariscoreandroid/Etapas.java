package com.example.wposs_user.polariscoreandroid;

import java.util.Date;

public class Etapas {

    Terminal terminal;
    private Date fecha;
    private int contador=1;

    public Etapas(Terminal terminal, Date fecha) {
        this.terminal = terminal;
        this.fecha = fecha;
        this.contador=contador++;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
