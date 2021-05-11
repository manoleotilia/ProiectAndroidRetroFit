package com.example.proiectandroidretrofit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "depozite")
@ForeignKey(entity = User.class,parentColumns = "id",childColumns = "user_id")
public class DepozitDB {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "depozit_id")
    private int depozit_id;
    @ColumnInfo(name = "user_id")
    private int user_id;
    @ColumnInfo(name = "valoare_depozit")
    private double valoare_depozit;
    @ColumnInfo(name = "moneda")
    private String moneda;
    @ColumnInfo(name = "luni")
    private int luni;
    @ColumnInfo(name = "suma_incasata")
    private double suma_incasata;
    @ColumnInfo(name = "dobanda")
    private int dobanda;

    public int getDepozit_id() {
        return depozit_id;
    }

    public int getCredit_id() {
        return depozit_id;
    }

    public void setDepozit_id(int depozit_id) {
        this.depozit_id = depozit_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public String toString()
    {
        return  String.valueOf(depozit_id)+ " "+ String.valueOf(valoare_depozit)+ " "+
                moneda + String.valueOf(luni) + " "+ String.valueOf(dobanda);
    }

}
