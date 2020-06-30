package com.example.project2.Produto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project2.Model.Show_Produtos;
import com.example.project2.Repositorio_Dados.DBHelper;
import com.example.project2.R;

import java.util.Random;

public class NovoProdutoActivity extends AppCompatActivity {
    EditText et_nomeproduto, et_unidade, et_estoque, et_preco, et_status, et_idprod;
    Button bt_salvaprod;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);

        db = new DBHelper(this);

        /*Random rand = new Random();
        int number = rand.nextInt(999) + 1100;
        et_idprod = (EditText) findViewById(R.id.et_idprod);
        String mystring = String.valueOf(number);
        et_idprod.setText(mystring);*/

        et_nomeproduto = (EditText) findViewById(R.id.et_nomeproduto);
        et_unidade = (EditText) findViewById(R.id.et_unidade);
        et_estoque = (EditText) findViewById(R.id.et_estoque);
        et_preco = (EditText) findViewById(R.id.et_preco);
        et_status = (EditText) findViewById(R.id.et_status);
        et_idprod = (EditText) findViewById(R.id.et_idprod);
        //final String p_id = et_idprod.getText().toString();

        bt_salvaprod = (Button) findViewById(R.id.bt_salvaprod);

        bt_salvaprod.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String p_nome = et_nomeproduto.getText().toString();
                String p_unidade = et_unidade.getText().toString();
                String p_estoque = et_estoque.getText().toString();
                String p_preco = et_preco.getText().toString();
                String p_status = et_status.getText().toString();
                String p_id = et_idprod.getText().toString();

                if (et_nomeproduto.getText().toString().equals("")/*p_nome.equals("")*/) {
                    et_nomeproduto.setError("Nome Obrigatorio");
                }
                if (et_unidade.getText().toString().equals("")/*p_nome.equals("")*/) {
                    et_unidade.setError("Unidade Obrigatorio");
                }
                if (et_estoque.getText().toString().equals("")/*p_nome.equals("")*/) {
                    et_estoque.setError("Estoque Obrigatorio");
                }
                if (et_preco.getText().toString().equals("")/*p_nome.equals("")*/) {
                    et_preco.setError("Preço Obrigatorio");
                }
                if (et_status.getText().toString().equals("")/*p_nome.equals("")*/) {
                    et_status.setError("Status Obrigatorio");

                } else {

                    //tudo ok
                    long ress = db.CriaProdutos(p_nome, p_unidade, p_estoque, p_preco, p_status, p_id,data());


                    if (ress > 0) {
                        Toast.makeText(NovoProdutoActivity.this, "Registro OK", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(NovoProdutoActivity.this, "Registro Invalido", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        verificaparametro();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String data(){
        String dataAtualFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(System.currentTimeMillis());

        //Seta valores da data
        String dataString = dataAtualFormatada.substring(0,10);

        //Seta valores do horário
        String horario = dataAtualFormatada.substring(11,19);
        return dataString +" "+ horario;
    }

    private  void verificaparametro(){
        Bundle bundle=getIntent().getExtras();
        if ((bundle!=null ) && (bundle.containsKey("PRODUTO"))){
            Show_Produtos show_produtos=(Show_Produtos)bundle.getSerializable("PRODUTO");
            et_nomeproduto.setText(show_produtos.nome_produto);
            et_idprod.setText(show_produtos.id_produto);
            et_unidade.setText(show_produtos.unidade_produto);
            et_estoque.setText(show_produtos.estoque_produto);
            et_preco.setText(show_produtos.preco_produto);
            et_status.setText(show_produtos.status_produto);
        }
    }

    public void voltar(View view) {
        Intent intent = new Intent(this, ProdutoActivity.class);
        startActivity(intent);
    }
}
