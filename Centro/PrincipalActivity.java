package com.example.project2.Centro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project2.Clientes.clienteActivity;
import com.example.project2.Pagamento.PagamentoActivity;
import com.example.project2.Pedido.PedidoActivity;
import com.example.project2.Produto.ProdutoActivity;
import com.example.project2.R;
import com.example.project2.Sincroniza.SincActivity;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void bt_cliente(View view) {
        Intent intent = new Intent(this, clienteActivity.class);
        startActivity(intent);
    }

    public void bt_produto(View view) {
        Intent intent = new Intent(this, ProdutoActivity.class);
        startActivity(intent);
    }
    public void bt_pedido(View view) {
        Intent intent = new Intent(this, PedidoActivity.class);
        startActivity(intent);
    }
    public void bt_pay(View view) {
        Intent intent = new Intent(this, PagamentoActivity.class);
        startActivity(intent);
    }
    public void bt_sinc(View view) {
        Intent intent = new Intent(this, SincActivity.class);
        startActivity(intent);
    }


}