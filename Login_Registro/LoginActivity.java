package com.example.project2.Login_Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project2.Centro.PrincipalActivity;
import com.example.project2.Repositorio_Dados.DBHelper;
import com.example.project2.R;

public class LoginActivity extends AppCompatActivity {
    EditText et_username, et_password;
    Button bt_login;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHelper(this);

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        bt_login = (Button) findViewById(R.id.bt_login);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if (et_username.getText().toString().equals("admin") && et_password.getText().toString().equals("admin")) {

                    Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
                    startActivity(i);

                }
                if (et_username.getText().toString().equals("")/*username.equals("")*/) {
                    et_username.setError("Nome Obrigatorio");

                } else if (et_password.getText().toString().equals("")/*password.equals("")*/) {
                    et_password.setError("Senha Obrigatoria");

                } else {

                    //TUDO OK
                    String res = db.ValidarLogin(username, password);
                    if (res.equals("OK")) {
                        Intent i = new Intent(LoginActivity.this, PrincipalActivity.class);
                        startActivity(i);
                        Toast.makeText(LoginActivity.this, "Login Autorizado", Toast.LENGTH_SHORT).show();


                    } else {
                        if (et_username.getText().toString().equals("admin") && et_password.getText().toString().equals("admin")) {
                            Toast.makeText(LoginActivity.this, "Login Autorizado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Negado", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
