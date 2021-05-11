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

public class DepozitAdapter extends ArrayAdapter {
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private ArrayList<Depozit> lista_Depozite;


    public DepozitAdapter(@NonNull Context context, int resource, ArrayList<Depozit> lista_Depozite) {
        super(context, resource);
        this.layoutResource=resource;
        this.layoutInflater=LayoutInflater.from(context);
        this.lista_Depozite=lista_Depozite;

    }


    @Override
    public int getCount() {
        return lista_Depozite.size();
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
        Depozit depozit_curent = lista_Depozite.get(position);
        viewHolder.textViewDepozit_Nr.setText("Depozitul "+ String.valueOf(position+1));
        viewHolder.textViewLuni.setText(String.valueOf(depozit_curent.getLuni()+" luni"));
        viewHolder.textViewSuma.setText(String.format("%.2f",depozit_curent.getSuma_incasata())+" "+depozit_curent.getMoneda());


        return convertView;
    }
    private class ViewHolder{
        final TextView textViewDepozit_Nr;
        final TextView textViewSuma;
        final TextView textViewLuni;

        ViewHolder(View v){
            this.textViewLuni=(TextView)v.findViewById(R.id.tV_lista_Ani);
            this.textViewDepozit_Nr=(TextView)v.findViewById(R.id.tV_lista_Credit_nr);
            this.textViewSuma= (TextView)v.findViewById(R.id.tV_lista_Suma_Datorata);
        }
    }
}
