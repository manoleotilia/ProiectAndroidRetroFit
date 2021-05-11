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

public class CreditActivity extends AppCompatActivity {

    private Credit credit;
    private Button btnCalculeaza;
    private Button btnCreareCredit;
    private EditText editTextSuma;
    private EditText editTextDobanda;
    private EditText editTextAni;
    private TextView textViewSumaDatorata;
    private int suma_datorata;
    private int suma_imprumutata;
    private int ani;
    private int dobanda;
    private String moneda;
    Intent data;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        data = new Intent();
        final Spinner spinner = findViewById(R.id.spinnerCredit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.monede, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(1);

        btnCalculeaza = (Button) findViewById(R.id.btnCalculeazaCredit);
        btnCreareCredit = (Button) findViewById(R.id.btnCreareCredit);
        editTextSuma = (EditText) findViewById(R.id.editTextSuma);
        editTextAni = (EditText) findViewById(R.id.editTextAni);
        editTextDobanda = (EditText) findViewById(R.id.editTextDobanda);
        textViewSumaDatorata = (TextView) findViewById(R.id.tVSumaDatorata);
        btnCalculeaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ani = Integer.parseInt(editTextAni.getText().toString());
                dobanda = Integer.parseInt(editTextDobanda.getText().toString());
                suma_imprumutata = Integer.parseInt(editTextSuma.getText().toString());

                suma_datorata = suma_imprumutata + (suma_imprumutata * dobanda / 100 * ani);
                moneda=spinner.getSelectedItem().toString();

                textViewSumaDatorata.setText(String.valueOf(suma_datorata)+" "+moneda);


            }
        });


        btnCreareCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moneda = spinner.getSelectedItem().toString();
                ani = Integer.parseInt(editTextAni.getText().toString());
                dobanda = Integer.parseInt(editTextDobanda.getText().toString());
                suma_imprumutata = Integer.parseInt(editTextSuma.getText().toString());

                suma_datorata = suma_imprumutata + (suma_imprumutata * dobanda / 100 * ani);
                credit = new Credit(suma_imprumutata, moneda, ani, suma_datorata, dobanda);

                data.putExtra(getString(R.string.credit_putExtra), credit);
                setResult(RESULT_OK, data);
                finish();

            }
        });


    }


}
