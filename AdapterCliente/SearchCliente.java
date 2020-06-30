package com.example.project2.AdapterCliente;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.Clientes.NovoClienteActivity;
import com.example.project2.Model.ClienteModel;
import com.example.project2.Model.Show_Clientes;
import com.example.project2.R;
import com.example.project2.Repositorio_Dados.DBHelper;


public class SearchCliente extends RecyclerView.Adapter<SearchCliente.SearchViewHolder> {

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, email, tipo, cpf, ident;
        public ImageButton excluir, modf;
        public Show_Clientes cliente;
        public DBHelper db;


        public SearchViewHolder(@NonNull View itemView, final Context context) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name_card);
            email = (TextView) itemView.findViewById(R.id.email_card);
            tipo = (TextView) itemView.findViewById(R.id.tipo_card);
            cpf = (TextView) itemView.findViewById(R.id.cpf_card);
            ident = (TextView) itemView.findViewById(R.id.ident_card);
            excluir = (ImageButton) itemView.findViewById(R.id.butexcluir);

            excluir = (ImageButton) itemView.findViewById(R.id.butexcluir);
            excluir.setOnClickListener(this);
            modf = (ImageButton) itemView.findViewById(R.id.buteditar);
            modf.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.butexcluir) {
               // modeloCliente.showClientes.remove(cliente);
               modeloCliente.ApagarCliente(cliente);
                notifyDataSetChanged();


            }
            if (v.getId() == R.id.buteditar) {
                if (modeloCliente.showClientes.size() > 0) {
                    Show_Clientes show_clientes = modeloCliente.showClientes.get(getLayoutPosition());

                    Intent it = new Intent(context, NovoClienteActivity.class);
                    it.putExtra("CLIENTE", cliente);
                    ((AppCompatActivity) context).startActivity(it);
                }
            }
        }
    }

    private Context context;
    //public List<Show_Clientes> showClientes;
    private ClienteModel modeloCliente;

    public SearchCliente(Context context/*, List<Show_Clientes> showClientes*/) {
        this.context = context;
        //this.showClientes = showClientes;
        this.modeloCliente = new ClienteModel(context);
        this.modeloCliente.carregarClientes();
    }




    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_cliente, parent, false);
        return new SearchViewHolder(itemView, context);

    }


    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.cliente = this.modeloCliente.showClientes.get(position);

        holder.name.setText(holder.cliente.getNome());
        holder.email.setText(holder.cliente.getEmail());
        holder.tipo.setText(holder.cliente.getTipo());
        holder.cpf.setText(holder.cliente.getCpf());
        holder.ident.setText(holder.cliente.getId());
        // Show_Clientes cliente = new Show_Clientes();
        //holder.excluir.setOnClickListener(new eventoexcluir(showClientes));


    }


    @Override
    public int getItemCount() {
        return this.modeloCliente.showClientes.size();
    }


    public void filtar(String text) {
//        db.getFriendByName(text)
        if (text == null || text.isEmpty()) {
            this.modeloCliente.carregarClientes();
        } else {
            this.modeloCliente.filtrarClientes(text);
        }
    }
}
