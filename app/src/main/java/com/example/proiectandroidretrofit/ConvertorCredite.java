package com.example.proiectandroidretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ConvertorCredite extends AppCompatActivity {

    private TextView textViewCredit;
    private TextView textViewSuma;
    private ArrayList<Credit> lista_credite;
    private Button buttonConversieEURO;
    private Button buttonConversieRON;
    private String cursURL = "https://www.bnr.ro/nbrfxrates.xml";
    private String cursCachedUrl = "INVALID";
    private int pozitie;
    private double curs_eur;


    private ConversieInterfata conversieInterfata = new ConversieInterfata() {
        @Override
        public void ConversieRon(double curs_eur) {

            lista_credite.get(pozitie).setMoneda("RON");
            double suma_datorata_eur = lista_credite.get(pozitie).getSuma_datorata();
            double suma_imprumutata_eur = lista_credite.get(pozitie).getSuma_imprumutata();
            lista_credite.get(pozitie).setSuma_datorata(suma_datorata_eur * curs_eur);
            lista_credite.get(pozitie).setSuma_imprumutata(suma_imprumutata_eur * curs_eur);
            textViewSuma.setText(String.format("%.2f", (suma_datorata_eur * curs_eur)) + " " + lista_credite.get(pozitie).getMoneda());
            data.putExtra("lista_credite_convertor", lista_credite);
            setResult(RESULT_OK, data);

        }

        @Override
        public void ConversieEur(double curs_eur) {
            lista_credite.get(pozitie).setMoneda("EUR");
            double suma_datorata_ron = lista_credite.get(pozitie).getSuma_datorata();
            double suma_imprumutata_ron = lista_credite.get(pozitie).getSuma_imprumutata();
            lista_credite.get(pozitie).setSuma_datorata(suma_datorata_ron / curs_eur);
            lista_credite.get(pozitie).setSuma_imprumutata(suma_imprumutata_ron / curs_eur);
            textViewSuma.setText(String.format("%.2f", (suma_datorata_ron / curs_eur)) + " " + lista_credite.get(pozitie).getMoneda());
            data.putExtra("lista_credite_convertor", lista_credite);
            setResult(RESULT_OK, data);
        }

        @Override
        public void Conversie(double curs_eur) {

            if (lista_credite.get(pozitie).getMoneda().equalsIgnoreCase("eur")) {
                ConversieRon(curs_eur);
            } else if (lista_credite.get(pozitie).getMoneda().equalsIgnoreCase("ron")) {
                ConversieEur(curs_eur);
            }
        }
    };

    private Intent data;
    private boolean buton_euro_apasat = false;
    private boolean buton_ron_apasat = false;
    private DownloadData downloadData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor_credite);
        data = new Intent();
        lista_credite = (ArrayList<Credit>) getIntent().getSerializableExtra("lista_credite_catre_convertor");


        pozitie = getIntent().getIntExtra("pozitie_credit", -1);
        textViewCredit = (TextView) findViewById(R.id.tVCredit_Convertor);
        textViewSuma = (TextView) findViewById(R.id.tVSuma_Convertor);
        buttonConversieEURO = (Button) findViewById(R.id.btnConversieEuro);
        buttonConversieRON = (Button) findViewById(R.id.btnConversieRon);

        textViewCredit.setText("Creditul" + " " + String.valueOf(pozitie + 1));
        textViewSuma.setText(String.format("%.2f", lista_credite.get(pozitie).getSuma_datorata()) + " " + lista_credite.get(pozitie).getMoneda());

        buttonConversieEURO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton_euro_apasat = true;
                buton_ron_apasat = false;
                downloadUrl("https://www.bnr.ro/nbrfxrates.xml");
                data.putExtra("lista_credite_de_la_convertor", lista_credite);
                setResult(RESULT_OK, data);

            }
        });
        buttonConversieRON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buton_ron_apasat = true;
                buton_euro_apasat = false;
                downloadUrl("https://www.bnr.ro/nbrfxrates.xml");
                data.putExtra("lista_credite_de_la_convertor", lista_credite);
                setResult(RESULT_OK, data);


            }
        });

    }

    private void downloadUrl(String cursURL) {
        if ((lista_credite.get(pozitie).getMoneda().equalsIgnoreCase("ron") && buton_ron_apasat == false) ||
                (lista_credite.get(pozitie).getMoneda().equalsIgnoreCase("eur") && buton_euro_apasat == false)) {
            if (!cursURL.equals(cursCachedUrl)) {
                downloadData = new DownloadData(conversieInterfata);
                downloadData.execute(cursURL);

                cursCachedUrl = cursURL;
            } else {
                curs_eur = downloadData.getCurs_eur();
                conversieInterfata.Conversie(curs_eur);
                Toast.makeText(this, "Download realizat deja.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "conversie ilogica", Toast.LENGTH_SHORT).show();
        }

    }


//    private void ConversieRon(double curs_eur) {
//        lista_credite.get(pozitie).setMoneda("RON");
//        double suma_datorata_eur = lista_credite.get(pozitie).getSuma_datorata();
//        double suma_imprumutata_eur = lista_credite.get(pozitie).getSuma_imprumutata();
//        lista_credite.get(pozitie).setSuma_datorata(suma_datorata_eur * curs_eur);
//        lista_credite.get(pozitie).setSuma_imprumutata(suma_imprumutata_eur * curs_eur);
//        textViewSuma.setText(String.format("%.2f", (suma_datorata_eur * curs_eur)) + " " + lista_credite.get(pozitie).getMoneda());
//        data.putExtra("lista_credite_convertor", lista_credite);
//        setResult(RESULT_OK, data);
//    }
//
//    private void ConversieEur(double curs_eur) {
//        lista_credite.get(pozitie).setMoneda("EUR");
//        double suma_datorata_ron = lista_credite.get(pozitie).getSuma_datorata();
//        double suma_imprumutata_ron = lista_credite.get(pozitie).getSuma_imprumutata();
//        lista_credite.get(pozitie).setSuma_datorata(suma_datorata_ron / curs_eur);
//        lista_credite.get(pozitie).setSuma_imprumutata(suma_imprumutata_ron / curs_eur);
//        textViewSuma.setText(String.format("%.2f", (suma_datorata_ron / curs_eur)) + " " + lista_credite.get(pozitie).getMoneda());
//        data.putExtra("lista_credite_convertor", lista_credite);
//        setResult(RESULT_OK, data);
//    }
//
//    private void ConversieInterfata(double curs_eur) {
//
//            if (lista_credite.get(pozitie).getMoneda().equalsIgnoreCase("eur")) {
//                ConversieRon(curs_eur);
//            } else if(lista_credite.get(pozitie).getMoneda().equalsIgnoreCase("ron")){
//                ConversieEur(curs_eur);
//            }
//
//    }


//    private class DownloadData extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            // Toast.makeText(ConvertorCredite.this, s, Toast.LENGTH_SHORT).show();
//            ParsareCurs parsareCurs = new ParsareCurs();
//            parsareCurs.parsare(s);
//            curs_eur = parsareCurs.getCurs_euro();
//            ConversieInterfata(curs_eur);
//
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//
//            String Curs_BNR = downloadXML(strings[0]);
//            if (Curs_BNR == null) {
//            }
//
//            return Curs_BNR;
//        }
//
//
//        private String downloadXML(String urlPath) {
//
//            StringBuilder xmlResult = new StringBuilder();
//            try {
//                URL url = new URL(urlPath);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                int cod_raspuns = connection.getResponseCode();
//
//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//                int caractereCitite;
//                char[] inputBuffer = new char[500];
//                while (true) {
//                    caractereCitite = reader.read(inputBuffer);
//                    if (caractereCitite < 0) {
//                        break;
//                    }
//                    if (caractereCitite > 0) {
//                        xmlResult.append(String.copyValueOf(inputBuffer, 0, caractereCitite));
//                    }
//                }
//                reader.close();
//                return xmlResult.toString();
//            } catch (MalformedURLException e) {
//            } catch (IOException e) {
//            } catch (SecurityException e) {
//            }
//            return null;
//
//        }
//    }


}
