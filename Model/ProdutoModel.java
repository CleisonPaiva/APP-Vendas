package com.example.project2.Model;

import android.content.Context;

import com.example.project2.Repositorio_Dados.DBHelper;

import java.util.List;

public class ProdutoModel {
    public List<Show_Produtos> show_produtos;

    public ProdutoModel(Context context) {
        this.context = context;
    }

    private Context context;

    public void carregarProduto(){
        DBHelper db = new DBHelper(context);
        show_produtos = db.getProdutos();
    }
   // filtrarproduto
    public void filtrarproduto(String nome){
        DBHelper db = new DBHelper(context);
        show_produtos = db.getProdutosByName(nome);
    }
    public void ApagarProduto(Show_Produtos prduto) {
        DBHelper db = new DBHelper(context);
        db.deleteProduto(prduto.id_produto);
       // showClientes.remove(cliente);

    }

}
