package com.example.proiectandroidretrofit;

import java.io.Serializable;

public class Depozit implements Serializable {

    private double valoare_depozit;
    private String moneda;
    private int luni;
    private double suma_incasata;
    private int dobanda;


    public Depozit(double valoare_depozit, String moneda, int luni, double suma_incasata, int dobanda) {
        this.valoare_depozit = valoare_depozit;
        this.moneda = moneda;
        this.luni = luni;
        this.suma_incasata = suma_incasata;
        this.dobanda = dobanda;

    }

    public double getValoare_depozit() {
        return valoare_depozit;
    }

    public void setValoare_depozit(double valoare_depozit) {
        this.valoare_depozit = valoare_depozit;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getLuni() {
        return luni;
    }

    public void setLuni(int luni) {
        this.luni = luni;
    }

    public double getSuma_incasata() {
        return suma_incasata;
    }

    public void setSuma_incasata(double suma_incasata) {
        this.suma_incasata = suma_incasata;
    }

    public int getDobanda() {
        return dobanda;
    }

    public void setDobanda(int dobanda) {
        this.dobanda = dobanda;
    }
}
