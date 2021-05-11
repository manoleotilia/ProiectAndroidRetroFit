package com.example.proiectandroidretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class DepozitActivity extends AppCompatActivity {

    private Depozit depozit;
    private TextView textViewSumaIncasata;
    private EditText editTextValoareDepozit;
    private EditText editTextDobandaDepozit;
    private EditText editTextLuni;
    private Button btnCalculeazaDepozit;
    private Button btnCreareDepozit;
    private String moneda;
    private int dobanda;
    private int luni;
    private int valoare_depozit;
    private int suma_incasata;
    private Intent data;
    UserDataBase userDataBase;
    DepozitDB depozitDB;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depozit);
        userDataBase = UserDataBase.getInstance(getApplicationContext());
        data=new Intent();
        final Spinner spinner = findViewById(R.id.spinnerDepozit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.monede, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(1);

        textViewSumaIncasata = (TextView) findViewById(R.id.tVSumaIncasata);
        editTextValoareDepozit = (EditText) findViewById(R.id.editTextValoareDepozit);
        editTextDobandaDepozit = (EditText) findViewById(R.id.editTextDobandaDepozit);
        editTextLuni = (EditText) findViewById(R.id.editTextLuni);
        btnCalculeazaDepozit = (Button) findViewById(R.id.btnCalculeazaDepozit);
        btnCreareDepozit = (Button) findViewById(R.id.btnCreareDepozit);

        btnCalculeazaDepozit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                valoare_depozit = Integer.parseInt(editTextValoareDepozit.getText().toString());
                dobanda = Integer.parseInt(editTextDobandaDepozit.getText().toString());
                luni = Integer.parseInt(editTextLuni.getText().toString());
                suma_incasata = valoare_depozit + (valoare_depozit * dobanda / 12 / 100 * luni);
                moneda = spinner.getSelectedItem().toString();
                textViewSumaIncasata.setText(String.valueOf(suma_incasata) + " " + moneda);
            }
        });

        btnCreareDepozit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valoare_depozit = Integer.parseInt(editTextValoareDepozit.getText().toString());
                dobanda = Integer.parseInt(editTextDobandaDepozit.getText().toString());
                luni = Integer.parseInt(editTextLuni.getText().toString());
                suma_incasata = valoare_depozit + (valoare_depozit * dobanda / 12 / 100 * luni);
                moneda = spinner.getSelectedItem().toString();

                depozit = new Depozit(valoare_depozit, moneda, luni, suma_incasata, dobanda);
                depozitDB = new DepozitDB();
                depozitDB.setUser_id(LoginActivity.user.getId());
                depozitDB.setSuma_incasata(depozit.getSuma_incasata());
                depozitDB.setLuni(depozit.getLuni());
                depozitDB.setDobanda(depozit.getDobanda());
                depozitDB.setMoneda(depozit.getMoneda());
                depozitDB.setValoare_depozit(depozit.getValoare_depozit());

               userDataBase.depozitDao().addDepozit(depozitDB);

                data.putExtra("depozit", depozit);
                setResult(RESULT_OK, data);
                finish();

            }
        });


    }
}
