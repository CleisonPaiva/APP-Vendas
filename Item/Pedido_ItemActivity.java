package com.example.project2.Item;

//import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Model.Show_Item;
import com.example.project2.Repositorio_Dados.DBHelper;
import com.example.project2.Pedido.NovoPedidoActivity;
import com.example.project2.Pedido.PedidoActivity;
import com.example.project2.R;

import java.util.Random;

public class Pedido_ItemActivity extends AppCompatActivity {
    EditText id_item, produto, qtd_item, valor_item, id_geral;

    Button salvar_item;
    DBHelper db;
    // NovoPedidoActivity novo;aqui
    // public List<Show_Produtos> prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_item);

        db = new DBHelper(this);

//         Random rando = new Random();
//        int numbero = rando.nextInt(9999) + 11111;
//        id = (TextView) findViewById(R.id.idgeral);
//        String mystring = String.valueOf(numbero);
//        id.setText(mystring);

        Random rand = new Random();
        int number = rand.nextInt(9999) + 22222;
        id_item = (EditText) findViewById(R.id.et_id_item);
        String mystringg = String.valueOf(number);
        id_item.setText(mystringg);

        produto = (EditText) findViewById(R.id.et_id_produto);
        qtd_item = (EditText) findViewById(R.id.et_quantidade);
        valor_item = (EditText) findViewById(R.id.et_vltotal);
        id_geral = (EditText) findViewById(R.id.id_geral);
        final String id_ite = id_item.getText().toString();
        // final String idg = id.getText().toString();
        qtd_item.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // Calcular o Total
//                String totalfull = String.valueOf(retorna());
//                valor_item.setText("R$ " + totalfull);
            }
        });
        salvar_item = (Button) findViewById(R.id.bt_salvaitem);
        salvar_item.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String p_produto = produto.getText().toString();
                String p_qtd = qtd_item.getText().toString();
                String p_valor = valor_item.getText().toString();
                String p_id = id_geral.getText().toString();
                // String p_produto=produto.getText().toString();

                //TUDO OK
                String res = db.Validarproduto(p_produto);
                if (res.equals("OK")) {
                    if (p_produto.equals("")) {

                        Toast.makeText(Pedido_ItemActivity.this, "Não encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        // String teste=db.getidpedido_item();
                        // id.setText(teste);
                        // Toast.makeText(Pedido_ItemActivity.this, "here"+teste, Toast.LENGTH_SHORT).show();


                        String getpreco = db.getProdutosBypreco(p_produto);
                        double preco = Double.parseDouble(getpreco);
                        double quantidade = Double.parseDouble(p_qtd);
                        double resultado = preco * quantidade;

                        String totalfull = String.valueOf(retorna());
                        valor_item.setText("R$ " + totalfull);


                        // Toast.makeText(Pedido_ItemActivity.this, "preco" + resultado, Toast.LENGTH_SHORT).show();
                        long ress = db.CriaItem(p_id, id_ite, p_produto, p_qtd, totalfull,data());

                        //Toast.makeText(NovoPedidoActivity.this, "tes" + p_pedido + p_cliente + p_user + p_total + p_data, Toast.LENGTH_SHORT).show();
                        if (ress > 0) {
                            Toast.makeText(Pedido_ItemActivity.this, "Registro OK", Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            Toast.makeText(Pedido_ItemActivity.this, "Registro invalido" + ress, Toast.LENGTH_SHORT).show();
                        }
                    }


                } else {
                    Toast.makeText(Pedido_ItemActivity.this, "Produto Não Encontrado", Toast.LENGTH_SHORT).show();
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
    private void verificaparametro() {
        Bundle bundle = getIntent().getExtras();
        if ((bundle != null) && (bundle.containsKey("ITEM"))) {
            Show_Item show_item = (Show_Item) bundle.getSerializable("ITEM");
            produto.setText(show_item.item);
            qtd_item.setText(show_item.quantidade_item);
            valor_item.setText(show_item.valor_item);
            id_geral.setText(show_item.id_item);

        }
    }

    public double retorna() {
        String p_produto = produto.getText().toString();
        String p_qtd = qtd_item.getText().toString();
        String getpreco = db.getProdutosBypreco(p_produto);
        double preco = Double.parseDouble(getpreco);
        double quantidade = Double.parseDouble(p_qtd);
        double resultado = preco * quantidade;
        return  resultado;
    }

    public void voltar(View view) {
        Intent intent = new Intent(this, PedidoActivity.class);
        startActivity(intent);
    }

    public void item(View view) {
        Intent intent = new Intent(this, NovoPedidoActivity.class);
        startActivity(intent);
    }
}
