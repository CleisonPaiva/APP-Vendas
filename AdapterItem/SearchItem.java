package com.example.project2.AdapterItem;

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

import com.example.project2.Item.Pedido_ItemActivity;
import com.example.project2.Model.ItemModel;
import com.example.project2.Model.Show_Item;
import com.example.project2.Pedido.NovoPedidoActivity;
import com.example.project2.R;

public class SearchItem extends RecyclerView.Adapter<SearchItem.SearchViewHolder> {
    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView n_pedido,p_item, p_produto, quantidade, valor;
        public ImageButton exclui, modf;
        public Show_Item item;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            n_pedido = (TextView) itemView.findViewById(R.id.pedido_item_card);
            p_item = (TextView) itemView.findViewById(R.id.item_card);
            p_produto = (TextView) itemView.findViewById(R.id.produto_item_card);
            quantidade = (TextView) itemView.findViewById(R.id.quantidade_item_card);
            valor = (TextView) itemView.findViewById(R.id.valor_item_card);
            exclui = (ImageButton) itemView.findViewById(R.id.butexcluir);

            exclui = (ImageButton) itemView.findViewById(R.id.butexcluir);
            exclui.setOnClickListener(this);
            modf = (ImageButton) itemView.findViewById(R.id.buteditar);
            modf.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.butexcluir) {
                if (v.getId() == R.id.butexcluir) {
                    itemmodelo.ApagarItem(item);
                    itemmodelo.showitem.remove(item);
                    //notify();
                    notifyDataSetChanged();
                }

            }
            if (v.getId() == R.id.buteditar){
                if(itemmodelo.showitem.size()>0){
                    Show_Item showitem=itemmodelo.showitem.get(getLayoutPosition());

                    Intent it=new Intent(context, Pedido_ItemActivity.class);
                    it.putExtra("ITEM",item);
                    ((AppCompatActivity)context).startActivity(it);
                }}

        }
    }
    private Context context;
    private ItemModel itemmodelo;

    public SearchItem(Context context) {
        this.context = context;
        this.itemmodelo = new ItemModel(context);
        this.itemmodelo.carregaritem();
    }



    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_exibir_item, parent, false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.item= this.itemmodelo.showitem.get(position);

        holder.n_pedido.setText(holder.item.getId_item());
        holder.p_item.setText(holder.item.getItem());
        holder.p_produto.setText(holder.item.getProduto_item());
        holder.quantidade.setText(holder.item.getQuantidade_item());
        holder.valor.setText(holder.item.getValor_item());
    }

    @Override
    public int getItemCount() {
        return this.itemmodelo.showitem.size();
    }
    public void filtrar(String text) {
//        db.getFriendByName(text)
        if (text == null || text.isEmpty()){
            this.itemmodelo.carregaritem();
        }else {
            this.itemmodelo.filtraritem(text);
        }
    }

}
