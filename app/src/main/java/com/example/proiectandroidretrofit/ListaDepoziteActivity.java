package com.example.proiectandroidretrofit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaDepoziteActivity extends AppCompatActivity {
private ListView listView;
private ArrayList<Depozit> lista_depozite;
private static final int CONVERTOR_DEPOZITE=6;
private Intent intent;
private Intent data2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_depozite);
        listView=(ListView)findViewById(R.id.listView_Depozite);
        lista_depozite=(ArrayList<Depozit>)getIntent().getSerializableExtra("lista_depozite");
        DepozitAdapter depozitAdapter= new DepozitAdapter(ListaDepoziteActivity.this,R.layout.lista_credite,lista_depozite);
        listView.setAdapter(depozitAdapter);


        intent = new Intent(ListaDepoziteActivity.this,ConvertorDepozite.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("pozitie_depozit",position);
                intent.putExtra("lista_depozite_catre_convertor",lista_depozite);
                startActivityForResult(intent,CONVERTOR_DEPOZITE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CONVERTOR_DEPOZITE && resultCode==RESULT_OK){
            lista_depozite=(ArrayList<Depozit>)data.getSerializableExtra("lista_depozite_de_la_convertor");
            DepozitAdapter depozitAdapter = new DepozitAdapter(ListaDepoziteActivity.this,R.layout.lista_credite,lista_depozite);
            listView.setAdapter(depozitAdapter);
            data2 = new Intent();
            data2.putExtra("lista_noua_depozite",lista_depozite);
            setResult(RESULT_OK,data2);

        }
    }
}
