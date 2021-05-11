package com.example.proiectandroidretrofit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    private Credit credit;
    private Depozit depozit;
    private Button btnCredit;
    private Button btnDepozit;
    private Button btnListaCredite;
    private Button btnListaDepozite;
    private Button btnAfisareBD;
    private Button btnAfisareUseri;
    private Button btnAdaugaCrediteFirebase;
    private Button btnSarbatori;
    private ArrayList<Credit> listaCredite;
    private ArrayList<Depozit> listaDepozite;
    FirebaseDatabase databaseListaCredite;
    DatabaseReference myRef;
    DepozitDBDao depozitDBDao;
    private static final int CREDIT_ACTIVITY_REQ_CODE = 1;
    private static final int DEPOZIT_ACTIVITY_REQ_CODE = 2;
    private static final int LISTA_CREDITE_ACTIVITY_REQ_CODE = 3;
    private static final int LISTA_DEPOZITE_ACTIVITY_REQ_CODE = 4;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         Log.d("TEST", getString(R.string.onactvresult));

        if (requestCode == CREDIT_ACTIVITY_REQ_CODE && resultCode == RESULT_OK) {
            credit = (Credit) data.getSerializableExtra("credit");
            listaCredite.add(credit);
        }

        if (requestCode == DEPOZIT_ACTIVITY_REQ_CODE && resultCode == RESULT_OK) {
            depozit = (Depozit) data.getSerializableExtra("depozit");
            listaDepozite.add(depozit);
        }
        if (requestCode == LISTA_CREDITE_ACTIVITY_REQ_CODE && resultCode == RESULT_OK) {
            listaCredite = (ArrayList<Credit>) data.getSerializableExtra("lista_noua_credite");
        }
        if (requestCode == LISTA_DEPOZITE_ACTIVITY_REQ_CODE && resultCode == RESULT_OK) {
            listaDepozite = (ArrayList<Depozit>) data.getSerializableExtra("lista_noua_depozite");
        }
        Log.d("TEST", "onActivityResult: terminare");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        listaCredite = new ArrayList<>();
        listaDepozite = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Credit proba_credit = new Credit(1000, getString(R.string.RON), 4, 1400, 5);
        Depozit proba_depozit = new Depozit(100, "RON", 12, 300, 20);
        listaCredite.add(proba_credit);
        listaDepozite.add(proba_depozit);
        btnCredit = (Button) findViewById(R.id.btnCredit);
        btnDepozit = (Button) findViewById(R.id.btnDepozit);
        btnListaCredite = (Button) findViewById(R.id.btnListaCredite);
        btnListaDepozite = (Button) findViewById(R.id.btnListaDepozite);
        btnAfisareBD = (Button) findViewById(R.id.btnAfisareBD);
        btnAfisareUseri = (Button) findViewById(R.id.btnAfisareUseri);
        btnSarbatori = (Button) findViewById(R.id.btnSarbatori);
        btnAdaugaCrediteFirebase = (Button) findViewById(R.id.btnAdaugaCrediteFirebase);
        final ArrayList<Credit> listaCrediteFirebase = new ArrayList<>();
        final ArrayList<String> listaCrediteFirebaseString = new ArrayList<>();

        btnSarbatori.setOnClickListener(v -> {
            Intent sarbatori_intent = new Intent(MainActivity.this, SarbatoriLegaleActivity.class);
            startActivity(sarbatori_intent);
        });


        btnCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent credit_intent = new Intent(MainActivity.this, CreditActivity.class);
                startActivityForResult(credit_intent, CREDIT_ACTIVITY_REQ_CODE);

            }
        });
        btnDepozit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent depozit_intent = new Intent(MainActivity.this, DepozitActivity.class);
                startActivityForResult(depozit_intent, DEPOZIT_ACTIVITY_REQ_CODE);
            }
        });

        btnListaDepozite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listaDepozite_intent = new Intent(MainActivity.this, ListaDepoziteActivity.class);
                listaDepozite_intent.putExtra("lista_depozite", listaDepozite);
                startActivityForResult(listaDepozite_intent, LISTA_DEPOZITE_ACTIVITY_REQ_CODE);
            }
        });

        btnListaCredite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listaCredite_intent = new Intent(MainActivity.this, ListaCrediteActivity.class);
                listaCredite_intent.putExtra("lista_credite", listaCredite);
                startActivityForResult(listaCredite_intent, LISTA_CREDITE_ACTIVITY_REQ_CODE);
            }
        });

        btnAfisareBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                depozitDBDao = UserDataBase.getInstance(getApplicationContext()).depozitDao();
//                List<DepozitDB>listaDepoziteBD = depozitDBDao.getAllDepozit(LoginActivity.user.getId());
//                String mesaj= "" ;
//                for( int i=0;i<listaDepoziteBD.size();i++)
//                {
//                    mesaj=mesaj+ " "+ String.valueOf(listaDepoziteBD.get(i).getValoare_depozit());
//                }
//
//                Toast.makeText(MainActivity.this, mesaj, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, ListaBDDepozite.class);
                startActivity(intent);

            }

        });
        btnAfisareUseri.setVisibility(View.INVISIBLE);
        btnAfisareUseri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaBDUsers.class);
                startActivity(intent);
            }
        });
        btnAdaugaCrediteFirebase.setVisibility(View.INVISIBLE);
        btnAdaugaCrediteFirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (int i = 0; i < listaCredite.size(); i++) {
                            myRef.child("Credit" + (i + 1)).setValue(listaCredite.get(i));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


    }


}
