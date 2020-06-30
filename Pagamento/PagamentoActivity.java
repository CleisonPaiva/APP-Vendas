package com.example.project2.Pagamento;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2.Centro.PrincipalActivity;
import com.example.project2.Repositorio_Dados.DBHelper;
import com.example.project2.R;

import java.util.Random;

public class PagamentoActivity extends AppCompatActivity {
    EditText id_pagamento,id_pedido,valor_pagamento;
    Button confirma;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        db = new DBHelper(this);//nao esquecerrrrrrrr

        Random rand = new Random();
        int number = rand.nextInt(11111) + 2323232;
        id_pagamento = (EditText) findViewById(R.id.pagamento);
        String mystring = String.valueOf(number);
        id_pagamento.setText(mystring);

        id_pedido = (EditText) findViewById(R.id.et_pedido_pagamento);
        valor_pagamento = (EditText) findViewById(R.id.valor_pagamento);
        final String id_pag = id_pagamento.getText().toString();

        confirma = (Button) findViewById(R.id.confirma);
        confirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p_pedido=id_pedido.getText().toString();
                String pagamento_valor=valor_pagamento.getText().toString();
                Toast.makeText(PagamentoActivity.this,p_pedido, Toast.LENGTH_SHORT).show();

                //TUDO OK
                String res = db.Validarpedido(p_pedido);

                Toast.makeText(PagamentoActivity.this, res, Toast.LENGTH_SHORT).show();
                if (res.equals("OK")) {
                    if (p_pedido.equals("")/*|| p_pedido.equals("")*/) {

                        Toast.makeText(PagamentoActivity.this, "Não encontrado", Toast.LENGTH_SHORT).show();
                    } else {

                        String getpreco = db.getPedidoBypreco(p_pedido);
                       double preco = Double.parseDouble(getpreco);



                        String totalfull = String.valueOf(preco);
                        valor_pagamento.setText( "R$ "+totalfull);

                        long ress = db.CriaPagamento(id_pag,p_pedido,pagamento_valor);

                        //Toast.makeText(NovoPedidoActivity.this, "tes" + p_pedido + p_cliente + p_user + p_total + p_data, Toast.LENGTH_SHORT).show();
                        if (ress > 0) {
                            Toast.makeText(PagamentoActivity.this, "Pagamento OK", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(PagamentoActivity.this, "Pagamento invalido" + ress, Toast.LENGTH_SHORT).show();
                        }
                    }


                } else {
                    Toast.makeText(PagamentoActivity.this, "Cliente Não Encontrado", Toast.LENGTH_SHORT).show();
                }






            }
        });
    }
    public void voltar(View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
}
