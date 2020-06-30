package com.example.project2.Clientes;

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

import com.example.project2.Model.Show_Clientes;
import com.example.project2.Repositorio_Dados.DBHelper;
import com.example.project2.R;

import java.sql.Timestamp;
import java.util.Random;

public class NovoClienteActivity extends AppCompatActivity {
    EditText et_nomecliente, et_cpf, et_tipo, et_email, et_ide;
    Button bt_salvar;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_cliente);

        db = new DBHelper(this);

       /* Random rand = new Random();
        int number = rand.nextInt(999) + 1100;
        et_ide = (EditText) findViewById(R.id.et_id);
        String mystring = String.valueOf(number);
        et_ide.setText(mystring);
*/
        et_nomecliente = (EditText) findViewById(R.id.et_nomecliente);
        et_cpf = (EditText) findViewById(R.id.et_cpf);
        et_tipo = (EditText) findViewById(R.id.et_tipo);
        et_email = (EditText) findViewById(R.id.et_email);
        et_ide = (EditText) findViewById(R.id.et_id);
       // final String id = et_ide.getText().toString();

        bt_salvar = (Button) findViewById(R.id.bt_salvar);

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String n_cliente = et_nomecliente.getText().toString();
                String n_cpf = et_cpf.getText().toString();
                String n_tipo = et_tipo.getText().toString();
                String n_email = et_email.getText().toString();
                String n_id = et_ide.getText().toString();

                if (et_nomecliente.getText().toString().equals("")) {
                    et_nomecliente.setError("Nome Obrigatorio");
                }
                if (et_cpf.getText().toString().equals("")) {
                    et_cpf.setError("CPF/CNPJ Obrigatorio");
                }
                if (et_tipo.getText().toString().equals("")) {
                    et_tipo.setError("Tipo Obrigatorio");
//                    et_username.getText().toString().equals("")/*username.equals("")*/) {
//                        et_username.setError("Nome Obrigatorio");
                    //  Toast.makeText(NovoClienteActivity.this, "Não encontrado", Toast.LENGTH_SHORT).show();
                } else {

                    //tudo ok
                    long ress = db.CriaClientes(n_cliente, n_cpf, n_tipo, n_email, n_id,dataa());

                    //Toast.makeText(NovoClienteActivity.this, "tes" + n_cliente + n_cpf + n_tipo + n_email, Toast.LENGTH_SHORT).show();
                    if (ress > 0) {
                        Toast.makeText(NovoClienteActivity.this, "Registro OK", Toast.LENGTH_SHORT).show();
                        db.close();
                        finish();
                    } else {
                        Toast.makeText(NovoClienteActivity.this, "Registro Invalido" , Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        verificaparametro();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String dataa(){
        String dataAtualFormatada = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                .format(System.currentTimeMillis());

        //Seta valores da data
        String dataString = dataAtualFormatada.substring(0,10);

        //Seta valores do horário
        String horario = dataAtualFormatada.substring(11,19);
        return dataString +" "+ horario;
    }
    private  void verificaparametro(){
        Bundle bundle=getIntent().getExtras();
        if ((bundle!=null ) && (bundle.containsKey("CLIENTE"))){
            Show_Clientes show_clientes=(Show_Clientes)bundle.getSerializable("CLIENTE");
            et_nomecliente.setText(show_clientes.nome);
            et_ide.setText(show_clientes.id);
            et_cpf.setText(show_clientes.cpf);
            et_email.setText(show_clientes.email);
            et_tipo.setText(show_clientes.tipo);
        }
    }

    public void voltar(View view) {
        Intent intent = new Intent(this, clienteActivity.class);
        startActivity(intent);
    }
}

