package com.example.wposs_user.polariscoreandroid;

import java.util.Date;

public class Terminal {


    private String serial;
    private String marca;
    private String modelo;
    private String tecnologia;
    private String estado;
    private String sellos;
    private String mk;
    private Date fechaCompra;
    private Date fechaInicioGarantía;
    private Date fechaRegistro;
    private Date fechaLimite;
    private  int periodoGarantía;
    private Usuario usuario;
    private String ubicacion;



    public Terminal(String serial, String marca, String modelo, String tecnologia, String estado, Date fechaLimite) {
        this.serial = serial;
        this.marca = marca;
        this.modelo = modelo;
        this.tecnologia = tecnologia;
        this.estado = estado;
        this.fechaLimite = fechaLimite;
    }



    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaLimite() {
        return fechaLimite;
    }

    public String getSellos() {
        return sellos;
    }

    public void setSellos(String sellos) {
        this.sellos = sellos;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaInicioGarantía() {
        return fechaInicioGarantía;
    }

    public void setFechaInicioGarantía(Date fechaInicioGarantía) {
        this.fechaInicioGarantía = fechaInicioGarantía;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getPeriodoGarantía() {
        return periodoGarantía;
    }

    public void setPeriodoGarantía(int periodoGarantía) {
        this.periodoGarantía = periodoGarantía;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setFechaLimite(Date fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
}
