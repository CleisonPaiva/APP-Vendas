package com.example.project2.Repositorio_Dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.format.DateFormat;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;

import com.example.project2.Model.Show_Clientes;
import com.example.project2.Model.Show_Item;
import com.example.project2.Model.Show_Pedido;
import com.example.project2.Model.Show_Produtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static int versao = 1;
    private static String nome = "DB.db";

    public DBHelper(@Nullable Context context) {
        super(context, nome, null, versao);
    }

    //Cria tabelas
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tabela de usuarios do sistema
        String str = ("CREATE TABLE Utilizador (username TEXT PRIMARY KEY, password TEXT,identificador TEXT);");
        db.execSQL(str);

        //Tabela para armazenar clientes
        String str1 = ("CREATE TABLE Clientes(nome TEXT PRIMARY KEY, cpf_cnpj TEXT,tipo TEXT,email TEXT,identificador TEXT,criado_alterado TEXT,excluido default null )");
        db.execSQL(str1);

        //Tabela para armazenar produtos
        String str2 = ("CREATE TABLE Produtos(nome TEXT PRIMARY KEY, unidade TEXT,estoque TEXT,preco TEXT,status TEXT,identificador TEXT,criado_alterado TEXT,excluido default null);");
        db.execSQL(str2);

        //Tabela para armazenar pedidos
        String str3 = ("CREATE TABLE Pedido(pedido TEXT PRIMARY KEY, cliente TEXT,vendedor TEXT,total TEXT,data TEXT,criado_alterado TEXT,excluido default null);");
        db.execSQL(str3);

        //Tabela para armazenar itens
        String str4 = ("CREATE TABLE Pedido_Item(item TEXT PRIMARY KEY, produto TEXT,quantidade TEXT,valor TEXT,identificador TEXT,criado_alterado TEXT,excluido default null);");
        db.execSQL(str4);

        //Tabela para armazenar pagamentos
        String str5 = ("CREATE TABLE Pagamento(id_pagamento TEXT PRIMARY KEY, id_pedido TEXT,valor TEXT);");
        db.execSQL(str5);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Utilizador;");
        db.execSQL("DROP TABLE IF EXISTS Clientes;");
        db.execSQL("DROP TABLE IF EXISTS Produtos;");
        db.execSQL("DROP TABLE IF EXISTS Pedido;");
        db.execSQL("DROP TABLE IF EXISTS Pedido_Item;");
        db.execSQL("DROP TABLE IF EXISTS Pagamento;");

        onCreate(db);
    }

    //Monta o login para o usuario,salvando na tabela Utilizador
    public long CriarUtilizador(String username, String password, String id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        cv.put("identificador", id);
        //cv.put("identificador".ti);

        long result = db.insert("Utilizador", null, cv);

        return result;
    }

    //Realiza a validação do login,verificando se existe no banco
    public String ValidarLogin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Utilizador WHERE username=? AND password=?", new String[]{username, password});
        if (c.getCount() > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }

    //Realiza a validação do cleinte,verificando se existe no banco
    public String Validarcliente(String cliente) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Clientes WHERE nome=? ", new String[]{cliente});
        if (c.getCount() > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }

    // Realiza a validação do pedido,verificando se existe no banco
    public String Validarpedido(String pedido) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Pedido WHERE pedido=? ", new String[]{pedido});
        if (c.getCount() > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }

    //Realiza a validação do produto,verificando se existe no banco
    public String Validarproduto(String produto) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Produtos WHERE nome=? ", new String[]{produto});
        if (c.getCount() > 0) {
            return "OK";
        } else {
            return "ERRO";
        }
    }

    //Monta a tabela para um novo cliente,salvando na tabela Clientes
    public long CriaClientes(String nomecliente, String cpf, String tipo, String email, String ident,String data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", nomecliente);
        cv.put("cpf_cnpj", cpf);
        cv.put("tipo", tipo);
        cv.put("email", email);
        cv.put("identificador", ident);
        cv.put("criado_alterado", data);
        //cv.put("alterado", c);



        long result = 0;
        Cursor c = db.rawQuery("select 1 from Clientes where identificador = " + ident, null);
        if (c.moveToNext()) {

             result = db.update("Clientes", cv ,"identificador ="+ident ,null );

        } else {
            result = db.insert("Clientes", null, cv);
        }
        return result;
    }

    //Monta a tabela para um novo produto,salvando na tabela Produtos
    public long CriaProdutos(String nomeproduto, String unidade, String estoque, String preco, String status, String identify,String data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", nomeproduto);
        cv.put("unidade", unidade);
        cv.put("estoque", estoque);
        cv.put("preco", preco);
        cv.put("status", status);
        cv.put("identificador", identify);
        cv.put("criado_alterado", data);
        //cv.put("criado_alterado", data);

       /* long result = db.insert("Produtos", null, cv);
       return result;*/
        long result = 0;
        Cursor c = db.rawQuery("select 1 from Produtos where identificador = " + identify, null);
        if (c.moveToNext()) {
            result = db.update("Produtos", cv, "identificador =" + identify, null);
        } else {
            result = db.insert("Produtos", null, cv);
        }
        return result;

    }

    //Monta a tabela para um novo pedido,salvando na tabela Pedido
    public long CriaPedido(String p_pedido, String p_cliente, String p_vendedor, String p_total, String p_data,String date) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("pedido", p_pedido);
        cv.put("cliente", p_cliente);
        cv.put("vendedor", p_vendedor);
        cv.put("total", p_total);
        cv.put("data", p_data);
        cv.put("criado_alterado", date);
        /*long result = db.insert("Pedido", null, cv);
        return result;*/
        long result = 0;
        Cursor c = db.rawQuery("select 1 from Pedido where pedido = " + p_pedido, null);
        if (c.moveToNext()) {
            result = db.update("Pedido", cv, "pedido =" + p_pedido, null);
        } else {
            result = db.insert("Pedido", null, cv);
        }
        return result;
    }

    //Monta a tabela para um novo item,salvando na tabela Pedido_Item
    public long CriaItem(String id, String p_item, String p_produto, String p_qtd, String p_valor,String data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();//cv.put("Item", item);
        cv.put("identificador", id);
        cv.put("item", p_item);
        cv.put("produto", p_produto);
        cv.put("quantidade", p_qtd);
        cv.put("valor", p_valor);
        cv.put("criado_alterado", data);
        /*long result = db.insert("Pedido_Item", null, cv);
        return result;*/
        long result = 0;
        Cursor c = db.rawQuery("select 1 from Pedido_Item where identificador = " + id, null);
        if (c.moveToNext()) {
            result = db.update("Pedido_Item", cv, "identificador =" + id, null);
        } else {
            result = db.insert("Pedido_Item", null, cv);
        }
        return result;
    }


    //Monta a tabela para um novo pagamento,salvando na tabela Pagamento
    public long CriaPagamento(String id_pay, String id_pedido, String valor) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();//cv.put("Item", item);
        cv.put("id_pagamento", id_pay);
        cv.put("id_pedido", id_pedido);
        cv.put("valor", valor);

        long result = db.insert("Pagamento", null, cv);
        return result;

    }

    //Busca o preço de acordo com o produto no parametro
    public String getProdutosBypreco(String produto) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT preco FROM Produtos WHERE nome=? ", new String[]{produto});
        if (c.moveToFirst()) {
            String preco = c.getString(0);
            return preco;
        } else {
            return "ERRO";
        }

    }

    //Busca o valo total do pedido,de acordo com o pedido
    public String getPedidoBypreco(String pedido) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT total FROM Pedido WHERE pedido=? ", new String[]{pedido});
        if (c.moveToFirst()) {
            String total = c.getString(0);
            return total;
        } else {
            return "ERRO";
        }

    }

    //Busca o valor total de um item de acordo com seu identificador
    public String getprecototal(String id) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT sum(valor) FROM Pedido_Item WHERE identificador=? ", new String[]{id});
        if (c.moveToFirst()) {
            String preco = c.getString(0);
            return preco;
        } else {
            return "ERRO";
        }

    }

    public void deleteCliente(String get_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Clientes WHERE identificador=?", new String[]{get_ID});
    }

    public void deleteProduto(String get_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Produtos WHERE identificador=?", new String[]{get_ID});
    }

    public void deletePedido(String get_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Pedido WHERE pedido=?", new String[]{get_ID});
    }

    public void deleteItem(String get_ID) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Pedido_Item WHERE identificador=?", new String[]{get_ID});
    }


    /***---------------------------CLIENTES------------------------***/

    //Busca todos os clientes
    public List<Show_Clientes> getClientes() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"nome", "cpf_cnpj", "tipo", "email", "identificador"};
        String tableName = "Clientes";  //8:50
        // String tableName="Clientes.db";  //8:50


        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<Show_Clientes> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Show_Clientes showClientes = new Show_Clientes();
                showClientes.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                showClientes.setCpf(cursor.getString(cursor.getColumnIndex("cpf_cnpj")));
                showClientes.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                showClientes.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                showClientes.setId(cursor.getString(cursor.getColumnIndex("identificador")));
                result.add(showClientes);
            } while (cursor.moveToNext());
        }
        return result;
    }

    //Busca o cliente por nome
    public List<String> getNames() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"nome"};
        String tableName = "Clientes";  //8:50
        // String tableName="Clientes.db";  //8:50


        qb.setTables(tableName);//here
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                result.add(cursor.getString(cursor.getColumnIndex("nome")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    //Busca o cliente por nome passado no parametro
    public List<Show_Clientes> getClienteByName(String name) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"nome", "cpf_cnpj", "tipo", "email", "identificador"};
        String tableName = "Clientes";  //8:50
        // String tableName="Clientes.db";  //8:50


        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "nome LIKE ?", new String[]{"%" + name + "%"}, null, null, null);
        List<Show_Clientes> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                Show_Clientes showClientes = new Show_Clientes();
                showClientes.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                showClientes.setCpf(cursor.getString(cursor.getColumnIndex("cpf_cnpj")));
                showClientes.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                showClientes.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                showClientes.setId(cursor.getString(cursor.getColumnIndex("identificador")));

                result.add(showClientes);
            } while (cursor.moveToNext());
        }
        return result;
    }


    /***---------------------------PRODUTOS------------------------***/

    //Busca todos os produtos
    public List<Show_Produtos> getProdutos() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"nome", "unidade", "estoque", "preco", "status", "identificador"};
        String tableName = "Produtos";  //8:50


        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<Show_Produtos> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Show_Produtos show_Produtos = new Show_Produtos();
                show_Produtos.setNome_produto(cursor.getString(cursor.getColumnIndex("nome")));
                show_Produtos.setUnidade_produto(cursor.getString(cursor.getColumnIndex("unidade")));
                show_Produtos.setEstoque_produto(cursor.getString(cursor.getColumnIndex("estoque")));
                show_Produtos.setPreco_produto(cursor.getString(cursor.getColumnIndex("preco")));
                show_Produtos.setStatus_produto(cursor.getString(cursor.getColumnIndex("status")));
                show_Produtos.setId_produto(cursor.getString(cursor.getColumnIndex("identificador")));
                result.add(show_Produtos);
            } while (cursor.moveToNext());
        }
        return result;
    }

    //Busca o produtos por nome
    public List<String> getNames_Produtos() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"nome"};
        String tableName = "Produtos";  //8:50
        // String tableName="Clientes.db";  //8:50


        qb.setTables(tableName);//here
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                result.add(cursor.getString(cursor.getColumnIndex("nome")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    //Busca o produto pelo nome passado no parametro
    public List<Show_Produtos> getProdutosByName(String name) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"nome", "unidade", "estoque", "preco", "status", "identificador"};
        String tableName = "Produtos";  //8:50
        // String tableName="Clientes.db";  //8:50


        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "nome LIKE ?", new String[]{"%" + name + "%"}, null, null, null);
        List<Show_Produtos> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                Show_Produtos show_Produtos = new Show_Produtos();
                show_Produtos.setNome_produto(cursor.getString(cursor.getColumnIndex("nome")));
                show_Produtos.setUnidade_produto(cursor.getString(cursor.getColumnIndex("unidade")));
                show_Produtos.setEstoque_produto(cursor.getString(cursor.getColumnIndex("estoque")));
                show_Produtos.setPreco_produto(cursor.getString(cursor.getColumnIndex("preco")));
                show_Produtos.setStatus_produto(cursor.getString(cursor.getColumnIndex("status")));
                show_Produtos.setId_produto(cursor.getString(cursor.getColumnIndex("identificador")));

                result.add(show_Produtos);
            } while (cursor.moveToNext());
        }
        return result;
    }

    /***---------------------------PEDIDOS------------------------***/

    //Busca todos os pedidos
    public List<Show_Pedido> getPedidos() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"pedido", "cliente", "vendedor", "total", "data"};
        String tableName = "Pedido";  //8:50
//    t("pedido", p_pedido);
//    cv.put("cliente", p_cliente);
//    cv.put("vendedor", p_vendedor);
//    cv.put("total", p_total);
//    cv.put("data", p_data);

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<Show_Pedido> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Show_Pedido show_pedido = new Show_Pedido();
                show_pedido.setP_pedido(cursor.getString(cursor.getColumnIndex("pedido")));
                show_pedido.setP_cliente(cursor.getString(cursor.getColumnIndex("cliente")));
                show_pedido.setP_user(cursor.getString(cursor.getColumnIndex("vendedor")));
                show_pedido.setP_total(cursor.getString(cursor.getColumnIndex("total")));
                show_pedido.setP_data(cursor.getString(cursor.getColumnIndex("data")));
                result.add(show_pedido);
            } while (cursor.moveToNext());
        }
        return result;
    }

    //Busca o pedidos por nome
    public List<String> getNames_Pedido() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"pedido"};
        String tableName = "Pedido";  //8:50
        // String tableName="Clientes.db";  //8:50


        qb.setTables(tableName);//here
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                result.add(cursor.getString(cursor.getColumnIndex("pedido")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    //Busca o pedidos pelo nome passado no parametro
    public List<Show_Pedido> getPedidoByName(String name) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"pedido", "cliente", "vendedor", "total", "data"};
        String tableName = "Produtos";  //8:50
        // String tableName="Clientes.db";  //8:50


        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "pedido LIKE ?", new String[]{"%" + name + "%"}, null, null, null);
        List<Show_Pedido> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                Show_Pedido show_pedido = new Show_Pedido();
                show_pedido.setP_pedido(cursor.getString(cursor.getColumnIndex("pedido")));
                show_pedido.setP_cliente(cursor.getString(cursor.getColumnIndex("cliente")));
                show_pedido.setP_user(cursor.getString(cursor.getColumnIndex("vendedor")));
                show_pedido.setP_total(cursor.getString(cursor.getColumnIndex("total")));
                show_pedido.setP_data(cursor.getString(cursor.getColumnIndex("data")));
                result.add(show_pedido);
            } while (cursor.moveToNext());
        }
        return result;
    }

    /***---------------------------ITENS------------------------***/

    //Busca todos os itens
    public List<Show_Item> getPedido_item() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"identificador", "item", "produto", "quantidade", "valor"};
        String tableName = "Pedido_Item";  //8:50
//        v.put("identificador", id);
//        cv.put("item", p_item);
//        cv.put("produto", p_produto);
//        cv.put("quantidade", p_qtd);
//        cv.put("valor", p_valor);
//        long result = db.insert("Pedido_Item",

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<Show_Item> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Show_Item show_item = new Show_Item();
                show_item.setId_item(cursor.getString(cursor.getColumnIndex("identificador")));
                show_item.setItem(cursor.getString(cursor.getColumnIndex("item")));
                show_item.setProduto_item(cursor.getString(cursor.getColumnIndex("produto")));
                show_item.setQuantidade_item(cursor.getString(cursor.getColumnIndex("quantidade")));
                show_item.setValor_item(cursor.getString(cursor.getColumnIndex("valor")));
                result.add(show_item);
            } while (cursor.moveToNext());
        }
        return result;
    }

    //Busca o item por nome
    public List<String> getNames_item() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"item"};
        String tableName = "Pedido_Item";  //8:50
        // String tableName="Clientes.db";  //8:50


        qb.setTables(tableName);//here
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        List<String> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                result.add(cursor.getString(cursor.getColumnIndex("item")));
            } while (cursor.moveToNext());
        }
        return result;
    }

    //Busca o item pelo nome passado no parametro
    public List<Show_Item> getitemByName(String name) {

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"identificador", "item", "produto", "quantidade", "valor"};
        String tableName = "Pedido_Item";  //8:50
        // String tableName="Clientes.db";  //8:50
        //  .put("identificador", id);
//        cv.put("item", p_item);
//        cv.put("produto", p_produto);
//        cv.put("quantidade", p_qtd);
//        cv.put("valor", p_valor);
//        long result = db.insert("Pedido_Item",


        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "item LIKE ?", new String[]{"%" + name + "%"}, null, null, null);
        List<Show_Item> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                Show_Item show_item = new Show_Item();
                show_item.setId_item(cursor.getString(cursor.getColumnIndex("identificador")));
                show_item.setItem(cursor.getString(cursor.getColumnIndex("item")));
                show_item.setProduto_item(cursor.getString(cursor.getColumnIndex("produto")));
                show_item.setQuantidade_item(cursor.getString(cursor.getColumnIndex("quantidade")));
                show_item.setValor_item(cursor.getString(cursor.getColumnIndex("valor")));
                result.add(show_item);
            } while (cursor.moveToNext());
        }
        return result;
    }

   /* public void deleteItem(List<Show_Clientes> get_ID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Clientes WHERE identificador='"+get_ID+"'");

    }*/


}


