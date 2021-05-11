package com.example.proiectandroidretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class EditUser extends AppCompatActivity {
    EditText username;
    EditText password;
    Button buttonUpdate;
    Button buttonDelete;
    UserDataBase userDataBase;
    UserDao userDao;
    List<User> userList;
    User user;
    int pozitie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        userDataBase=UserDataBase.getInstance(getApplicationContext());

        username= (EditText)findViewById(R.id.editTextUserNedit);
        password=(EditText)findViewById(R.id.editTextPassedit);
        buttonUpdate=(Button)findViewById(R.id.btnUpdate);
        buttonDelete=(Button)findViewById(R.id.btnDelete);
           pozitie = getIntent().getIntExtra("position_user",-1);
         userList=userDataBase.userDao().getAllUsers();
         user = userList.get(pozitie);
        username.setText(String.valueOf(user.getName()));
        password.setText(String.valueOf(user.getPassword()));

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setPassword(password.getText().toString());
                user.setName(username.getText().toString());

                userDataBase.userDao().updateUser(user);
                Intent data= new Intent();
                setResult(RESULT_OK);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDataBase.userDao().deleteUser(user);
            }
        });


    }
}
