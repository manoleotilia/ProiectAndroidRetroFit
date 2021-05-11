package com.example.proiectandroidretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CreditAdapter extends ArrayAdapter {
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private ArrayList<Credit> lista_Credite;
    public CreditAdapter(@NonNull Context context, int resource, ArrayList<Credit> lista_Credite) {
        super(context, resource);

        this.layoutResource=resource;
        this.layoutInflater=LayoutInflater.from(context);
        this.lista_Credite=lista_Credite;
    }

    @Override
    public int getCount() {
        return lista_Credite.size();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            convertView=layoutInflater.inflate(layoutResource,parent,false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        Credit credit_curent = lista_Credite.get(position);
        viewHolder.textViewCredit_Nr.setText("Creditul "+String.valueOf(position+1));
        viewHolder.textViewSuma.setText(String.format("%.2f",credit_curent.getSuma_datorata())+" "+credit_curent.getMoneda());
        viewHolder.textViewAni.setText(String.valueOf(credit_curent.getAni())+ " ani");
        return convertView;
    }


    private class ViewHolder{
        final TextView textViewCredit_Nr;
        final TextView textViewSuma;
        final TextView textViewAni;

        ViewHolder(View v){
            this.textViewAni=(TextView)v.findViewById(R.id.tV_lista_Ani);
            this.textViewCredit_Nr=(TextView)v.findViewById(R.id.tV_lista_Credit_nr);
            this.textViewSuma= (TextView)v.findViewById(R.id.tV_lista_Suma_Datorata);
        }
    }
}
