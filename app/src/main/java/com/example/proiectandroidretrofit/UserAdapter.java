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

public class UserAdapter extends ArrayAdapter {

    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<User> userList;



    public UserAdapter(@NonNull Context context , int resource, List<User> userList)
    {
        super(context,resource);
        this.layoutResource=resource;
        this.layoutInflater=LayoutInflater.from(context);
        this.userList=userList;
    }


    @Override
    public int getCount() {return userList.size();}

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
        User user = userList.get(position);
        viewHolder.textViewId.setText(String.valueOf(user.getId())+".");
        viewHolder.textViewUsername.setText(String.valueOf(user.getName()));
        viewHolder.textViewPassword.setText(String.valueOf(user.getPassword()));

        return convertView;

    }
    private class ViewHolder{
        final TextView textViewId;
        final TextView textViewUsername;
        final TextView textViewPassword;

        ViewHolder(View v){
            this.textViewId=(TextView)v.findViewById(R.id.textViewId);
            this.textViewUsername=(TextView)v.findViewById(R.id.textViewUsername);
            this.textViewPassword= (TextView)v.findViewById(R.id.textViewPassword);
        }
    }
}
