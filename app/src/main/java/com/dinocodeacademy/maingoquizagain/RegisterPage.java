package com.dinocodeacademy.maingoquizagain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    private static final String TAG = "REG_ACTIVITY";
    Database helper;
    EditText name, pass;
    Button register;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register_layout);
        name = findViewById(R.id.usernameInputReg);
        pass = findViewById(R.id.passwordInputReg);
        register = findViewById(R.id.regButton);

        helper = new Database(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUsers();
                Intent paased = new Intent(RegisterPage.this,SplashScreen.class);
                startActivity(paased);
            }
        });

    }
    private void addUsers(){
        String t1 = name.getText().toString();
        String t2 = pass.getText().toString();
        long identity = helper.insertData(t1,t2);
        if(identity<0)
        {
            Toast.makeText(getApplicationContext(),"Scuccesfully registered",Toast.LENGTH_SHORT).show();
        } else
        {
            Toast.makeText(getApplicationContext(),"Failed to register",Toast.LENGTH_SHORT).show();
        }

    }
}
