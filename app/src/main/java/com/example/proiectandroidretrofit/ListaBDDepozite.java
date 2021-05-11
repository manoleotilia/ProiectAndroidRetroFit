package com.example.proiectandroidretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ListaBDDepozite extends AppCompatActivity {
ListView listView;
UserDataBase userDataBase;
List<DepozitDB > lista_depozite_bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bddepozite);

        listView = (ListView)findViewById(R.id.listView_BD_Depozite);
        userDataBase = UserDataBase.getInstance(getApplicationContext());
        lista_depozite_bd = userDataBase.depozitDao().getAllDepozit(LoginActivity.user.getId());
        DepozitBDAdapter depozitBDAdapter = new DepozitBDAdapter(ListaBDDepozite.this,R.layout.lista_credite,lista_depozite_bd);
        listView.setAdapter(depozitBDAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaBDDepozite.this,DetaliiDepozitBD.class);
                intent.putExtra("pozitie_depozit_BD",position);

                startActivity(intent);
            }
        });


    }
}
