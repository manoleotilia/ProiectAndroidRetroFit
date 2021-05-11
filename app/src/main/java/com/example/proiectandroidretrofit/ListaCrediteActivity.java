package com.example.proiectandroidretrofit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaCrediteActivity extends AppCompatActivity {
private ListView listView;
private ArrayList<Credit> lista_credite;
private static final int CONVERTOR_CREDITE=5;
private Intent intent;
private Intent data2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_credite);
        listView = (ListView) findViewById(R.id.listView_Credite);
        lista_credite= (ArrayList<Credit>) getIntent().getSerializableExtra("lista_credite");
        CreditAdapter creditAdapter = new CreditAdapter(ListaCrediteActivity.this,R.layout.lista_credite,lista_credite);
        listView.setAdapter(creditAdapter);
         intent = new Intent(ListaCrediteActivity.this,ConvertorCredite.class);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("pozitie_credit",position);
                intent.putExtra("lista_credite_catre_convertor",lista_credite);
                startActivityForResult(intent,CONVERTOR_CREDITE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONVERTOR_CREDITE && resultCode == RESULT_OK) {
            lista_credite=(ArrayList<Credit>)data.getSerializableExtra("lista_credite_de_la_convertor");
            CreditAdapter creditAdapter = new CreditAdapter(ListaCrediteActivity.this,R.layout.lista_credite,lista_credite);
            listView.setAdapter(creditAdapter);
            data2 = new Intent();
            data2.putExtra("lista_noua_credite",lista_credite);
            setResult(RESULT_OK,data2);

        }

    }
}
