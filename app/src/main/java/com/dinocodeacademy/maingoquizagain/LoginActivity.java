package com.dinocodeacademy.maingoquizagain;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText inputUsername;
    EditText inputPassword;
    Database helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        inputUsername = findViewById(R.id.usernameInput);
        inputPassword = findViewById(R.id.passwordInput);
        final String defaultUs = "Oana";
        helper = new Database(this);


        Button login_btn = findViewById(R.id.loginButton);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputUsername.getText().toString();
                if(helper.login(inputUsername.getText().toString(), inputPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Acces granted", Toast.LENGTH_SHORT).show();
                    Intent paased = new Intent(LoginActivity.this,SplashScreen.class);
                    startActivity(paased);
                }else{
                    Toast.makeText(getApplicationContext(), "Rerigster Needed", Toast.LENGTH_SHORT).show();
                    Intent register = new Intent(LoginActivity.this,RegisterPage.class);
                    startActivity(register);
                }
            }
        });
    }
}


