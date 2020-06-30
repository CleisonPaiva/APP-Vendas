package com.example.project2.Pedido;

//import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.project2.Model.Show_Pedido;
import com.example.project2.Repositorio_Dados.DBHelper;
import com.example.project2.Item.Pedido_ItemActivity;
import com.example.project2.R;

public class NovoPedidoActivity extends AppCompatActivity {
    EditText id_cliente, id_user, total_pedido, data;
    TextView id_pedido;
    DBHelper db;
    Button adiciona, salva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        db = new DBHelper(this);

//        Random rand = new Random();
//        int number = rand.nextInt(9999) + 11111;
//        id_pedido = (TextView) findViewById(R.id.tv_pedido);
//        String mystring = String.valueOf(number);
//        id_pedido.setText(mystring);

        id_cliente = (EditText) findViewById(R.id.et_id_cliente);
        id_user = (EditText) findViewById(R.id.et_id_user);
        total_pedido = (EditText) findViewById(R.id.et_total);
        data = (EditText) findViewById(R.id.data);
        id_pedido = (EditText) findViewById(R.id.id_pedido);
        // final String p_id = id_pedido.getText().toString();


        salva = (Button) findViewById(R.id.bt_salva_pedido);

        salva.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String p_pedido = id_pedido.getText().toString();
                String p_cliente = id_cliente.getText().toString();
                String p_user = id_user.getText().toString();
                String p_total = total_pedido.getText().toString();
                String p_data = data.getText().toString();

                //TUDO OK
                String res = db.Validarcliente(p_cliente);
                if (res.equals("OK")) {
                    if (id_cliente.getText().toString().equals("")/*p_cliente.equals("")|| p_pedido.equals("")*/) {
                        id_cliente.setError("Nome Obrigatorio");
                    }
                    if (id_pedido.getText().toString().equals("")/*p_cliente.equals("")|| p_pedido.equals("")*/) {
                        id_pedido.setError("Identicador Obrigatorio");
                    }
                    if (id_user.getText().toString().equals("")/*p_cliente.equals("")|| p_pedido.equals("")*/) {
                        id_user.setError("Nome Obrigatorio");
                    }
                    if (total_pedido.getText().toString().equals("")/*p_cliente.equals("")|| p_pedido.equals("")*/) {
                        total_pedido.setError("Valor Obrigatorio");
                    }
                    if (data.getText().toString().equals("")/*p_cliente.equals("")|| p_pedido.equals("")*/) {
                        data.setError("Data Obrigatorio");
                    }

                 else {
                    String getpreco = db.getprecototal(p_pedido);
//                        double preco = Double.parseDouble(getpreco);
//
//                        double quantidade = Double.parseDouble(p_qtd);
//                        double resultado = preco * quantidade;
//
//                        String totalfull = String.valueOf(resultado);
                    total_pedido.setText("R$ " + getpreco);

                    long ress = db.CriaPedido(p_pedido, p_cliente, p_user, getpreco, p_data,data()/*, id*/);

                    //Toast.makeText(NovoPedidoActivity.this, "tes" + p_pedido + p_cliente + p_user + p_total + p_data, Toast.LENGTH_SHORT).show();
                    if (ress > 0) {
                        Toast.makeText(NovoPedidoActivity.this, "Registro OK", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(NovoPedidoActivity.this, "Registro invalido" + ress, Toast.LENGTH_SHORT).show();
                    }
                }


            } else

            {
                Toast.makeText(NovoPedidoActivity.this, "Cliente Não Encontrado", Toast.LENGTH_SHORT).show();
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
        if ((bundle!=null ) && (bundle.containsKey("PEDIDO"))){
            Show_Pedido show_pedido=(Show_Pedido)bundle.getSerializable("PEDIDO");
            id_cliente.setText(show_pedido.p_cliente);
            id_user.setText(show_pedido.p_user);
            total_pedido.setText(show_pedido.p_total);
            data.setText(show_pedido.p_data);
            id_pedido.setText(show_pedido.p_pedido);
        }
    }



    public void voltar(View view) {
        Intent intent = new Intent(this, Pedido_ItemActivity.class);
        startActivity(intent);

    }


}
