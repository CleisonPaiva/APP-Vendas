package com.example.project2.Model;

import java.io.Serializable;

public class Show_Item implements Serializable {

    public String id_item,item,produto_item,quantidade_item,valor_item;


    public Show_Item(String id_item, String item, String produto_item, String quantidade_item, String valor_item) {
        this.id_item = id_item;
        this.item = item;
        this.produto_item = produto_item;
        this.quantidade_item = quantidade_item;
        this.valor_item = valor_item;
    }

    public Show_Item() {
    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getProduto_item() {
        return produto_item;
    }

    public void setProduto_item(String produto_item) {
        this.produto_item = produto_item;
    }

    public String getQuantidade_item() {
        return quantidade_item;
    }

    public void setQuantidade_item(String quantidade_item) {
        this.quantidade_item = quantidade_item;
    }

    public String getValor_item() {
        return valor_item;
    }

    public void setValor_item(String valor_item) {
        this.valor_item = valor_item;
    }


}
