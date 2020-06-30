package com.example.project2.Sincroniza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.project2.AdapterCliente.SearchCliente;
import com.example.project2.Model.Show_Clientes;
import com.example.project2.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SincActivity extends AppCompatActivity implements TarefaInterface {
    private ArrayList<Show_Clientes> lista=new ArrayList<Show_Clientes>();
    RecyclerView recicle;
    SearchCliente adapter;
    private ArrayAdapter<Show_Clientes> dataadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinc);

        TarefaGet tarefaget= new TarefaGet();
        tarefaget.tarefaInterface=this;
        tarefaget.execute();


        recicle=findViewById(R.id.recicle);

        /*dataadapter=new ArrayAdapter<Show_Clientes>(this,android.R.layout.simple_list_item_1,lista);
        recicle.setAdapter(dataadapter);*/

        //recicle.setOnClickListener();


       /* adapter=  new SearchCliente(getBaseContext());
        recyclerView.setAdapter(adapter);*/

    }

    @Override
    public void carregarlistagem(String srtJson) {
        Log.i("TAG",srtJson);

        Toast.makeText(SincActivity.this, "Registro "+srtJson, Toast.LENGTH_SHORT).show();

        try {
            JSONObject jsonObj=new JSONObject(srtJson);
            JSONArray dados =jsonObj.getJSONArray("dados");
            for (int i =0;1<dados.length();i++){
                JSONObject c =dados.getJSONObject(i);
                Show_Clientes show_clientes=new Show_Clientes();
                show_clientes.id=c.getString("id");
                show_clientes.nome=c.getString("nome");
                show_clientes.cpf=c.getString("cpfCnpj");
                show_clientes.tipo=c.getString("tipo");
                show_clientes.email=c.getString("email");
              //  show_clientes.ultimaAlteracao=c.getString("ultimaAlteracao");
         lista.add(show_clientes);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       dataadapter.notifyDataSetChanged();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
