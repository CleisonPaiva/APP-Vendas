package com.example.project2.AdapterPedido;

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

import com.example.project2.Model.PedidoModel;
import com.example.project2.Model.Show_Pedido;
import com.example.project2.Pedido.NovoPedidoActivity;
import com.example.project2.R;

public class SearchPedido extends RecyclerView.Adapter<SearchPedido.SearchViewHolder> {


    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView pedido,cliente, user, total, data;
        public ImageButton excluir, modf;
        public Show_Pedido p_pedido;


        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            pedido = (TextView) itemView.findViewById(R.id.pedido_card);
            cliente = (TextView) itemView.findViewById(R.id.cliente_card);
            user = (TextView) itemView.findViewById(R.id.user_card);
            total = (TextView) itemView.findViewById(R.id.total_card);
            data = (TextView) itemView.findViewById(R.id.data_card);
            excluir = (ImageButton) itemView.findViewById(R.id.butexcluir);

            excluir = (ImageButton) itemView.findViewById(R.id.butexcluir);
            excluir.setOnClickListener(this);

            modf = (ImageButton) itemView.findViewById(R.id.buteditar);
            modf.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.butexcluir) {
                if (v.getId() == R.id.butexcluir) {
                    modelopedido.ApagarPedido(p_pedido);
                    modelopedido.show_pedidos.remove(p_pedido);
                    //notify();
                    notifyDataSetChanged();
                }

            }
            if (v.getId() == R.id.buteditar){
                if(modelopedido.show_pedidos.size()>0){
                    Show_Pedido show_pedido=modelopedido.show_pedidos.get(getLayoutPosition());

                    Intent it=new Intent(context, NovoPedidoActivity.class);
                    it.putExtra("PEDIDO",p_pedido);
                    ((AppCompatActivity)context).startActivity(it);
                }}

        }
    }
    private Context context;
    private PedidoModel modelopedido;

    public SearchPedido(Context context) {
        this.context =context;
        this.modelopedido = new PedidoModel(context);
        this.modelopedido.carregarpedido();
    }





    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_pedido, parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.p_pedido= this.modelopedido.show_pedidos.get(position);

        holder.pedido.setText(holder.p_pedido.getP_pedido());
        holder.cliente.setText(holder.p_pedido.getP_cliente());
        holder.user.setText(holder.p_pedido.getP_user());
        holder.total.setText(holder.p_pedido.getP_total());
        holder.data.setText(holder.p_pedido.getP_data());
    }

    @Override
    public int getItemCount() {
        return this.modelopedido.show_pedidos.size();
    }
    public void filtrar(String text) {
//        db.getFriendByName(text)
        if (text == null || text.isEmpty()){
            this.modelopedido.carregarpedido();
        }else {
            this.modelopedido.filtrarpedido(text);
        }
    }


}
