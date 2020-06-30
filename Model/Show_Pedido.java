package com.example.project2.Model;

import java.io.Serializable;

public class Show_Pedido implements Serializable {
    public String p_pedido;
    public String p_cliente;
    public String p_user;
    public String p_total;
    public String p_data;


    public Show_Pedido(String p_pedido, String p_cliente, String p_user, String p_total, String p_data) {
        this.p_pedido = p_pedido;
        this.p_cliente = p_cliente;
        this.p_user = p_user;
        this.p_total = p_total;
        this.p_data = p_data;

    }
    public Show_Pedido(){

    }

    public String getP_pedido() {
        return p_pedido;
    }

    public void setP_pedido(String p_pedido) {
        this.p_pedido = p_pedido;
    }

    public String getP_cliente() {
        return p_cliente;
    }

    public void setP_cliente(String p_cliente) {
        this.p_cliente = p_cliente;
    }

    public String getP_user() {
        return p_user;
    }

    public void setP_user(String p_user) {
        this.p_user = p_user;
    }

    public String getP_total() {
        return p_total;
    }

    public void setP_total(String p_total) {
        this.p_total = p_total;
    }

    public String getP_data() {
        return p_data;
    }

    public void setP_data(String p_data) {
        this.p_data = p_data;
    }







}
