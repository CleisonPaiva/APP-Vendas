package com.example.project2.Model;

import android.content.Context;

import com.example.project2.Repositorio_Dados.DBHelper;

import java.util.List;

public class ClienteModel {

    public List<Show_Clientes> showClientes;

    private Context context;

    public ClienteModel(Context context) {
        this.context = context;
    }


    public void carregarClientes() {
        DBHelper db = new DBHelper(context);
        showClientes = db.getClientes();
    }

    public void filtrarClientes(String nome) {
        DBHelper db = new DBHelper(context);
        showClientes = db.getClienteByName(nome);
    }

    public void ApagarCliente(Show_Clientes cliente) {
        DBHelper db = new DBHelper(context);
        db.deleteCliente(cliente.id);
        showClientes.remove(cliente);

    }

    public Show_Clientes getClienteById(int id) {
//        DBHelper db = new DBHelper(context);
//        return db.getFriendByID(id);
        return null;
    }

    public void salvarCliente(Show_Clientes cliente) {
//        DBHelper db = new DBHelper(context);
//        db.gravarCliente(cliente);
    }
}
