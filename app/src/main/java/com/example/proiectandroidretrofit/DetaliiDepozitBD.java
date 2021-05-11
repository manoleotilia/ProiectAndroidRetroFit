package com.example.proiectandroidretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class DetaliiDepozitBD extends AppCompatActivity {
    int pozitie;
    List<DepozitDB> lista_depozite_bd;
    TextView nume;
    TextView valoare;
    TextView luni;
    TextView dobanda;
    UserDataBase userDataBase;
    Button buttonSalvare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_depozit_bd);


        pozitie= getIntent().getIntExtra("pozitie_depozit_BD",-1);
        userDataBase=UserDataBase.getInstance(getApplicationContext());
        lista_depozite_bd= userDataBase.depozitDao().getAllDepozit(LoginActivity.user.getId());

        nume=(TextView)findViewById(R.id.textViewDepNume);
        valoare=(TextView)findViewById(R.id.textViewDepVal);
        luni=(TextView)findViewById(R.id.textViewDurLuni);
        dobanda=(TextView)findViewById(R.id.textViewDob);
        buttonSalvare=(Button)findViewById(R.id.btnSalvareFisier);

        nume.setText("Depozitul: "+String.valueOf(lista_depozite_bd.get(pozitie).getDepozit_id()));
        valoare.setText(String.valueOf(lista_depozite_bd.get(pozitie).getValoare_depozit())+lista_depozite_bd.get(pozitie).getMoneda());
        luni.setText(String.valueOf(lista_depozite_bd.get(pozitie).getLuni())+" luni");
        dobanda.setText(String.valueOf(lista_depozite_bd.get(pozitie).getDobanda())+"%");

        buttonSalvare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = lista_depozite_bd.get(pozitie).toString();
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("fisier1",MODE_PRIVATE);
                    fos.write(text.getBytes());
                    Toast.makeText(DetaliiDepozitBD.this, "Saved to" + getFilesDir()+ "/" + "fisier1", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(fos !=null)
                    {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }
}
