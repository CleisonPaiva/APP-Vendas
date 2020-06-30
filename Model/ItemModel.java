package com.example.project2.Model;

import android.content.Context;

import com.example.project2.Repositorio_Dados.DBHelper;

import java.util.List;

public class ItemModel {
    public List<Show_Item> showitem;
    private Context context;
    public ItemModel(Context context) {
        this.context = context;
    }
    public void carregaritem(){
        DBHelper db = new DBHelper(context);
        showitem = db.getPedido_item();
    }

    public void filtraritem(String nome){
        DBHelper db = new DBHelper(context);
        showitem = db.getitemByName(nome);
    }
    public void ApagarItem(Show_Item item) {
        DBHelper db = new DBHelper(context);
        db.deleteItem(item.id_item);
        showitem.remove(item);

    }


}
