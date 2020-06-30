package com.example.project2.Model;

import android.content.Context;

import com.example.project2.Repositorio_Dados.DBHelper;

import java.util.List;

public class PedidoModel {

    public List<Show_Pedido> show_pedidos;
    private Context context;

    public PedidoModel(Context context){
        this.context = context;
    }
    public void carregarpedido(){
        DBHelper db = new DBHelper(context);
        show_pedidos=db.getPedidos();
    }

    public void filtrarpedido(String pedido){
        DBHelper db = new DBHelper(context);
        show_pedidos =db.getPedidoByName(pedido);
    }
    public void ApagarPedido(Show_Pedido show_pedidos) {
        DBHelper db = new DBHelper(context);
        db.deletePedido(show_pedidos.p_pedido);
        //show_pedidos.remove(cliente);

    }

}
