package com.example.proiectandroidretrofit;

import java.io.Serializable;

public class Credit implements Serializable {

    private double suma_imprumutata;
    private String moneda;
    private int ani;
    private double suma_datorata;
    private int dobanda;

    public Credit()
    {

    }


    public double getSuma_imprumutata() {
        return suma_imprumutata;
    }

    public void setSuma_imprumutata(double suma_imprumutata) {
        this.suma_imprumutata = suma_imprumutata;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getAni() {
        return ani;
    }

    public void setAni(int ani) {
        this.ani = ani;
    }

    public double getSuma_datorata() {
        return suma_datorata;
    }

    public void setSuma_datorata(double suma_datorata) {
        this.suma_datorata = suma_datorata;
    }

    public int getDobanda() {
        return dobanda;
    }

    public void setDobanda(int dobanda) {
        this.dobanda = dobanda;
    }

    public Credit(int suma_imprumutata, String moneda, int ani, int suma_datorata, int dobanda) {
        this.suma_imprumutata = suma_imprumutata;
        this.moneda = moneda;
        this.ani = ani;
        this.suma_datorata = suma_datorata;
        this.dobanda=dobanda;


    }
}
