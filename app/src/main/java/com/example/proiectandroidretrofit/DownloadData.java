package com.example.proiectandroidretrofit;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadData extends AsyncTask<String, Void, String> {

    ConversieInterfata conversieInterfata = null;

    public DownloadData(ConversieInterfata conversieInterfata) {
        this.conversieInterfata = conversieInterfata;
    }
    public DownloadData(){

    }


    public double getCurs_eur() {
        return ConvertorDepozite.curs_eur;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        // Toast.makeText(ConvertorCredite.this, s, Toast.LENGTH_SHORT).show();
        ParsareCurs parsareCurs = new ParsareCurs();
        parsareCurs.parsare(s);
        ConvertorDepozite.curs_eur = parsareCurs.getCurs_euro();

        if (conversieInterfata != null) {
            conversieInterfata.Conversie(ConvertorDepozite.curs_eur);
        }


    }

    @Override
    protected String doInBackground(String... strings) {

        String Curs_BNR = downloadXML(strings[0]);
        if (Curs_BNR == null) {

        }

        return Curs_BNR;
    }


    private String downloadXML(String urlPath) {

        StringBuilder xmlResult = new StringBuilder();
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int cod_raspuns = connection.getResponseCode();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            int caractereCitite;
            char[] inputBuffer = new char[500];
            while (true) {
                caractereCitite = reader.read(inputBuffer);
                if (caractereCitite < 0) {
                    break;
                }
                if (caractereCitite > 0) {
                    xmlResult.append(String.copyValueOf(inputBuffer, 0, caractereCitite));
                }
            }
            reader.close();
            return xmlResult.toString();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        } catch (SecurityException e) {
        }
        return null;

    }
}