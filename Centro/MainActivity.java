package com.example.project2.Centro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project2.Login_Registro.LoginActivity;
import com.example.project2.Login_Registro.RegistrarActivity;
import com.example.project2.R;

public class MainActivity extends AppCompatActivity {
    Button bt_entrar, bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_entrar = (Button) findViewById(R.id.bt_entrar);
        bt_register = (Button) findViewById(R.id.bt_register);

        bt_entrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistrarActivity.class);
                startActivity(i);

            }
        });
    }
}