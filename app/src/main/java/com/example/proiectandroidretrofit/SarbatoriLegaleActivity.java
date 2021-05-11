package com.example.proiectandroidretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SarbatoriLegaleActivity extends AppCompatActivity {
    ListView listView;
    Button buttonSarbatoriLegale;
    EditText anSarbatoare;
    EditText codulTarii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarbatori_legale);
        buttonSarbatoriLegale = (Button) findViewById(R.id.btnSarbatoriLegale);
        anSarbatoare = (EditText) findViewById(R.id.editTextAnSarbatori);
        codulTarii = (EditText) findViewById(R.id.editTextCodulTarii);
        listView = (ListView) findViewById(R.id.listViewSarbatori);


        buttonSarbatoriLegale.setOnClickListener( v-> {
            GetDataService service = PublicHolidaysClientInstance.getRetrofitInstance().create(GetDataService.class);

            Call<List<PublicHolidays>> call = service.getAllPublicHolidays(anSarbatoare.getText().toString(),codulTarii.getText().toString());
            call.enqueue(new Callback<List<PublicHolidays>>() {
                @Override
                public void onResponse(Call<List<PublicHolidays>> call, Response<List<PublicHolidays>> response) {
                    ArrayAdapter adapter = new ArrayAdapter<PublicHolidays>( SarbatoriLegaleActivity.this , android.R.layout.simple_list_item_1, response.body());
                    listView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<PublicHolidays>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();

                }
            });
        });

    }
}