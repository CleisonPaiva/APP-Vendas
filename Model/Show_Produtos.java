package com.example.project2.Model;

import java.io.Serializable;

public class Show_Produtos implements Serializable {
    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getUnidade_produto() {
        return unidade_produto;
    }

    public void setUnidade_produto(String unidade_produto) {
        this.unidade_produto = unidade_produto;
    }

    public String getEstoque_produto() {
        return estoque_produto;
    }

    public void setEstoque_produto(String estoque_produto) {
        this.estoque_produto = estoque_produto;
    }

    public String getPreco_produto() {
        return preco_produto;
    }

    public void setPreco_produto(String preco_produto) {
        this.preco_produto = preco_produto;
    }

    public String getStatus_produto() {
        return status_produto;
    }

    public void setStatus_produto(String status_produto) {
        this.status_produto = status_produto;
    }

    public String getId_produto() {
        return id_produto;
    }

    public void setId_produto(String id_produto) {
        this.id_produto = id_produto;
    }

    public String nome_produto;
    public String unidade_produto;
    public String estoque_produto;
    public String preco_produto;
    public String status_produto;
    public String id_produto;



    public Show_Produtos(String nome_produto, String unidade_produto, String estoque_produto, String preco_produto, String status_produto, String id_produto) {
        this.nome_produto = nome_produto;
        this.unidade_produto = unidade_produto;
        this.estoque_produto = estoque_produto;
        this.preco_produto = preco_produto;
        this.status_produto = status_produto;
        this.id_produto = id_produto;
    }



    public Show_Produtos() {

    }






}
