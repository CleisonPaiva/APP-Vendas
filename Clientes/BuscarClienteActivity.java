package com.example.project2.Clientes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.project2.Repositorio_Dados.DBHelper;
import com.example.project2.R;
//import com.example.project2.AdapterCliente.SearchAdapter;
import com.example.project2.AdapterCliente.SearchCliente;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class BuscarClienteActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchCliente adapter;
    DBHelper db;
    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_cliente);

        recyclerView = (RecyclerView) findViewById(R.id.recicle_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_bar);

        db = new DBHelper(this);
        materialSearchBar.setHint("Procurar");
        materialSearchBar.setCardViewElevation(10);
       // loadSuggerstList();//here
        loadSuggestList();

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            List<String> suggest =new ArrayList<>();
            for(String search:suggestList){
                if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase())){
                    suggest.add(search);
                }
            }
            materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled){
                    adapter=new SearchCliente(getBaseContext()/*,db.getFriends()*/);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        adapter=new SearchCliente(this/*,db.getFriends()*/);
        recyclerView.setAdapter(adapter);

    }

    private void loadSuggestList() {

        suggestList=db.getNames();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    private void startSearch(String text) {
        adapter=new SearchCliente(this/*,db.getFriendByName(text)*/);
        adapter.filtar(text);
        recyclerView.setAdapter(adapter);
    }

    /*private void loadSuggerstList() {
        suggestList=db.getNames();//here
        materialSearchBar.setLastSuggestions(suggestList);
    }*/





}
