package com.example.proiectandroidretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConvertorDepozite extends AppCompatActivity {
    private TextView textViewDepozit;
    private TextView textViewSumaDepozit;
    private TextView textViewConversie;
    private Button buttonConversieEURO;
    private Button buttonConversieRON;
    private Button buttonConvertTo;
    private EditText editTextConvertTo;
    private ArrayList<Depozit> lista_depozite;
    private String cursURL = "https://www.bnr.ro/nbrfxrates.xml";
    private String cursCachedUrl = "INVALID";
    private int pozitie;
    public static double curs_eur;
    private Intent data;
    private boolean buton_euro_apasat = false;
    private boolean buton_ron_apasat = false;
    private DownloadData downloadData;
    private ConversieInterfata conversieInterfata = null;
//    new ConversieInterfata() {
//        @Override
//        public void ConversieRon(double curs_eur) {
//            lista_depozite.get(pozitie).setMoneda("RON");
//            double suma_incasata_eur = lista_depozite.get(pozitie).getSuma_incasata();
//            double valoare_depozit_eur = lista_depozite.get(pozitie).getValoare_depozit();
//            lista_depozite.get(pozitie).setSuma_incasata(suma_incasata_eur * curs_eur);
//            lista_depozite.get(pozitie).setValoare_depozit(valoare_depozit_eur * curs_eur);
//            textViewSumaDepozit.setText(String.format("%.2f", (suma_incasata_eur * curs_eur)) + " " + lista_depozite.get(pozitie).getMoneda());
//            data.putExtra("lista_credite_convertor", lista_depozite);
//            setResult(RESULT_OK, data);
//        }
//
//        @Override
//        public void ConversieEur(double curs_eur) {
//            lista_depozite.get(pozitie).setMoneda("EUR");
//            double suma_incasata_ron = lista_depozite.get(pozitie).getSuma_incasata();
//            double valoare_depozit_ron = lista_depozite.get(pozitie).getValoare_depozit();
//            lista_depozite.get(pozitie).setSuma_incasata(suma_incasata_ron / curs_eur);
//            lista_depozite.get(pozitie).setValoare_depozit(valoare_depozit_ron / curs_eur);
//            textViewSumaDepozit.setText(String.format("%.2f", (suma_incasata_ron / curs_eur)) + " " + lista_depozite.get(pozitie).getMoneda());
//            data.putExtra("lista_credite_convertor", lista_depozite);
//            setResult(RESULT_OK, data);
//        }
//
//        @Override
//        public void Conversie(double curs_eur) {
//            if (lista_depozite.get(pozitie).getMoneda().equalsIgnoreCase("eur")) {
//                ConversieRon(curs_eur);
//            } else if (lista_depozite.get(pozitie).getMoneda().equalsIgnoreCase("ron")) {
//                ConversieEur(curs_eur);
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor_depozite);
        data = new Intent();
        lista_depozite = (ArrayList<Depozit>) getIntent().getSerializableExtra("lista_depozite_catre_convertor");

        pozitie = getIntent().getIntExtra("pozitie_depozit", -1);
        downloadUrl("https://www.bnr.ro/nbrfxrates.xml");
        textViewDepozit = (TextView) findViewById(R.id.tVDepozit_Convertor);
        textViewSumaDepozit = (TextView) findViewById(R.id.tVSumaDepozit_Convertor);
        textViewConversie = (TextView) findViewById(R.id.textViewConversie);
        buttonConversieEURO = (Button) findViewById(R.id.btnConversieEuro_Depozit);
        buttonConversieRON = (Button) findViewById(R.id.btnConversieRon_Depozit);
        buttonConvertTo = (Button) findViewById(R.id.btnConverTo);
        editTextConvertTo= (EditText) findViewById(R.id.editTextConvertTo);
        textViewDepozit.setText("Depozitul" + " " + String.valueOf(pozitie + 1));
        textViewSumaDepozit.setText(String.format("%.2f", lista_depozite.get(pozitie).getSuma_incasata()) + " " + lista_depozite.get(pozitie).getMoneda());
        buttonConversieEURO.setVisibility(View.INVISIBLE);
        buttonConversieRON.setVisibility(View.INVISIBLE);

//        buttonConversieEURO.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                buton_euro_apasat = true;
//                buton_ron_apasat = false;
//                downloadUrl("https://www.bnr.ro/nbrfxrates.xml");
//                data.putExtra("lista_depozite_de_la_convertor", lista_depozite);
//                setResult(RESULT_OK, data);
//
//            }
//        });

        buttonConvertTo.setOnClickListener(v -> {
            GetDataService service = ExchangeRateClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<ExchangeRates> call = service.latest("7f30e94e231f48989de394259d384855",editTextConvertTo.getText().toString());
            call.enqueue(new Callback<ExchangeRates>() {
                @Override
                public void onResponse( Call<ExchangeRates> call, Response<ExchangeRates> response) {
                    double conversie =  lista_depozite.get(pozitie).getSuma_incasata() * response.body().getRates().get(editTextConvertTo.getText().toString());
                    if(!lista_depozite.get(pozitie).getMoneda().equalsIgnoreCase("eur")) {
                        conversie /= curs_eur;
                    }
                    textViewConversie.setText(String.format("%.2f", conversie));
                }

                @Override
                public void onFailure(Call<ExchangeRates> call, Throwable t) {
                    Toast.makeText(ConvertorDepozite.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        buttonConversieEURO.setOnClickListener(v -> {
//            GetDataService service = ExchangeRateClientInstance.getRetrofitInstance().create(GetDataService.class);
//            Call<ExchangeRates> call = service.latest("7f30e94e231f48989de394259d384855");
//            call.enqueue(new Callback<ExchangeRates>() {
//                @Override
//                public void onResponse( Call<ExchangeRates> call, Response<ExchangeRates> response) {
//                    Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                public void onFailure(Call<ExchangeRates> call, Throwable t) {
//                    Toast.makeText(ConvertorDepozite.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//                }
//            });

            GetDataService service = PublicHolidaysClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<List<PublicHolidays>> call = service.getAllPublicHolidays("2020","RO");
            call.enqueue(new Callback<List<PublicHolidays>>() {
                @Override
                public void onResponse(Call<List<PublicHolidays>> call, Response<List<PublicHolidays>> response) {
                    Toast.makeText(getApplicationContext(),response.body().toString(),Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<List<PublicHolidays>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();

                }
            });
        });
        buttonConversieRON.setOnClickListener(v -> {
            buton_ron_apasat = true;
            buton_euro_apasat = false;
            downloadUrl("https://www.bnr.ro/nbrfxrates.xml");
            data.putExtra("lista_depozite_de_la_convertor", lista_depozite);
            setResult(RESULT_OK, data);


        });

    }

    private void downloadUrl(String cursURL) {
        if ((lista_depozite.get(pozitie).getMoneda().equalsIgnoreCase("ron") && buton_ron_apasat == false) ||
                (lista_depozite.get(pozitie).getMoneda().equalsIgnoreCase("eur") && buton_euro_apasat == false)) {
            if (!cursURL.equals(cursCachedUrl)) {
                downloadData = new DownloadData(conversieInterfata);
                downloadData.execute(cursURL);

                cursCachedUrl = cursURL;
            } else {
                curs_eur = downloadData.getCurs_eur();
                //conversieInterfata.Conversie(curs_eur);
            }
        }
    }
}

