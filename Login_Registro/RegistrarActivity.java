package com.example.project2.Login_Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project2.Repositorio_Dados.DBHelper;
import com.example.project2.R;

import java.util.Random;

public class RegistrarActivity extends AppCompatActivity {
    EditText et_username, et_pass1, et_pass2,et_ide;
    Button bt_registrar;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        db = new DBHelper(this);

        Random rand = new Random();
        int number = rand.nextInt(99) + 100;
        et_ide = (EditText) findViewById(R.id.et_id);
        String mystring = String.valueOf(number);
        et_ide.setText(mystring);



        et_username = (EditText) findViewById(R.id.et_reg_username);
        et_pass1 = (EditText) findViewById(R.id.et_reg_password);
        et_pass2 = (EditText) findViewById(R.id.et_reg_password2);

        bt_registrar = (Button) findViewById(R.id.bt_registernew);

        bt_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = et_username.getText().toString();
                String p1 = et_pass1.getText().toString();
                String p2 = et_pass2.getText().toString();
                String id = et_ide.getText().toString();

                if (et_username.getText().toString().equals("")) {
                    et_username.setError("Este campo é obrigatório");
                }

                else if (et_pass1.getText().toString().equals("") || et_pass2.getText().toString().equals("")) {
                    et_pass1.setError("Este campo é obrigatório");
                    et_pass2.setError("Este campo é obrigatório");
                    //Toast.makeText(RegistrarActivity.this, "Senha obrigatorio", Toast.LENGTH_SHORT).show();
                } else if (!et_pass1.getText().toString().equals(p2)/*!p1.equals(p2)*/) {
                   // Toast.makeText(RegistrarActivity.this, "As senhas não correspondem", Toast.LENGTH_SHORT).show();
                    et_pass2.setError("As senhas não correspondem");
                } else {

                    //tudo ok
                    long res = db.CriarUtilizador(username, p1,id);
                    if (res > 0) {
                        Toast.makeText(RegistrarActivity.this, "Registro OK", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegistrarActivity.this, "Registro Invalido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
