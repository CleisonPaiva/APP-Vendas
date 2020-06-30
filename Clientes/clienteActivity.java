package com.example.project2.Clientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project2.Centro.PrincipalActivity;
import com.example.project2.R;

public class clienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
    }

    public void bt_newcliente(View view) {
        Intent intent = new Intent(this, NovoClienteActivity.class);
        startActivity(intent);
    }
    public void bt_buscacliente(View view) {
        Intent intent = new Intent(this, BuscarClienteActivity.class);
        startActivity(intent);
    }
    public void but_voltar(View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
}
