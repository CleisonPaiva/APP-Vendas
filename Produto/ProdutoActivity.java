package com.example.project2.Produto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project2.Centro.PrincipalActivity;
import com.example.project2.R;

public class ProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
    }
    public void bt_novoproduto(View view) {
        Intent intent = new Intent(this, NovoProdutoActivity.class);
        startActivity(intent);
    }
    public void bt_buscaproduto(View view) {
        Intent intent = new Intent(this, BuscarProdutoActivity.class);
        startActivity(intent);
    }
    public void bt_voltar(View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
}
