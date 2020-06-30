package com.example.project2.Pedido;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.project2.Centro.PrincipalActivity;
import com.example.project2.Item.ExibirItemActivity;
import com.example.project2.Item.Pedido_ItemActivity;
import com.example.project2.R;


public class PedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
    }
    public void bt_newpedido(View view) {
        Intent intent = new Intent(this, Pedido_ItemActivity.class);
        startActivity(intent);
    }
    public void bt_buscapedido(View view) {
        Intent intent = new Intent(this, BuscaPedidoActivity.class);
        startActivity(intent);
    }
     public void bt_item(View view) {
        Intent intent = new Intent(this, ExibirItemActivity.class);
        startActivity(intent);
    }
    public void butt_voltar(View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
}
