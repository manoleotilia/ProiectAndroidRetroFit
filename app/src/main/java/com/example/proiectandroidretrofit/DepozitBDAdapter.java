package com.example.proiectandroidretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DepozitBDAdapter extends ArrayAdapter {

    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<DepozitDB> lista_depozite_BD;
    public DepozitBDAdapter(@NonNull Context context, int resource, List<DepozitDB> lista_depozite_BD) {
        super(context, resource);
        this.layoutResource=resource;
        this.layoutInflater= LayoutInflater.from(context);
        this.lista_depozite_BD=lista_depozite_BD;
    }

    @Override
    public int getCount() {return lista_depozite_BD.size();}
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
        DepozitDB depozit_curent = lista_depozite_BD.get(position);
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
