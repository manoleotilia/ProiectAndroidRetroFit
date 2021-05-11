package com.example.proiectandroidretrofit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ListaBDUsers extends AppCompatActivity {

    UserDataBase userDataBase;
    ListView listView;
    List<User> userList;
    private static final int Cod=10;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        listView = (ListView)findViewById(R.id.listViewUsers);
        userDataBase = UserDataBase.getInstance(getApplicationContext());
        userList = userDataBase.userDao().getAllUsers();
        UserAdapter userAdapter = new UserAdapter(ListaBDUsers.this,R.layout.user,userList);
        listView.setAdapter(userAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_bdusers);
        listView = (ListView)findViewById(R.id.listViewUsers);
        userDataBase = UserDataBase.getInstance(getApplicationContext());
        userList = userDataBase.userDao().getAllUsers();
        UserAdapter userAdapter = new UserAdapter(ListaBDUsers.this,R.layout.user,userList);
        listView.setAdapter(userAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaBDUsers.this,EditUser.class);
                intent.putExtra("position_user",position);
                startActivityForResult(intent,Cod);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       switch(item.getItemId()){
           case R.id.menuItemSalvare: {
               String text="";
               for( int i=0;i<userList.size();i++) {
                text =text + userList.get(i).toString()+ "\n";
               }
                FileOutputStream fos = null;
               try {
                   fos = openFileOutput("fisier1", MODE_PRIVATE);
                   fos.write(text.getBytes());
                   Toast.makeText(ListaBDUsers.this, "Saved to" + getFilesDir() + "/" + "fisier2", Toast.LENGTH_SHORT).show();

               } catch (FileNotFoundException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               } finally {
                   if (fos != null) {
                       try {
                           fos.close();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               }
           }
       }

        return super.onOptionsItemSelected(item);
    }
}
