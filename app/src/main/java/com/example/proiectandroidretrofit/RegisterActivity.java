package com.example.proiectandroidretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegisterAct;
    EditText editTextUsernameAct;
    EditText editTextPasswordAct;
    public static UserDataBase userDataBase;
    public static final String PREFERENCES_FILE_NAME = "CVPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegisterAct=(Button)findViewById(R.id.btnRegisterAct);
        editTextPasswordAct=(EditText)findViewById(R.id.editTextPasswordAct);
        editTextUsernameAct=(EditText)findViewById(R.id.editTextUsernameAct);
        userDataBase = Room.databaseBuilder(getApplicationContext(),UserDataBase.class,"userdb").allowMainThreadQueries().build();

        btnRegisterAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    if(editTextUsernameAct.getText().length()<4)
                {
                    Toast.makeText(RegisterActivity.this,"introduceti un username de cel putin 4 litere",Toast.LENGTH_LONG).show();
                }
                    else
                        if(editTextPasswordAct.getText().length()<1)
                    {
                        Toast.makeText(RegisterActivity.this,"introduceti o parola",Toast.LENGTH_LONG).show();
                    }
                        else
                    {
                        User user = new User();
                        user.setName(editTextUsernameAct.getText().toString());
                        user.setPassword(editTextPasswordAct.getText().toString());
                        userDataBase.userDao().addUser(user);

                        SharedPreferences settingsFile = getSharedPreferences(PREFERENCES_FILE_NAME, 0);
                        SharedPreferences.Editor myEditor = settingsFile.edit();
                        myEditor.putString("username",user.getName().toString());
                        myEditor.putString("password",user.getPassword().toString());
                        myEditor.apply();
                        setResult(RESULT_OK);


                        finish();
                    }

            }
        });


    }
}
