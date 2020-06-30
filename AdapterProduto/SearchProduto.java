package com.example.project2.AdapterProduto;

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

import com.example.project2.Model.ProdutoModel;
import com.example.project2.Model.Show_Produtos;
import com.example.project2.Produto.NovoProdutoActivity;
import com.example.project2.R;

public class SearchProduto extends RecyclerView.Adapter<SearchProduto.SearchViewHolder> {

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView produt, unidade, estoque, valor, status,iden;
        public ImageButton excluir, modf;
        public Show_Produtos produto;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);


            produt = (TextView) itemView.findViewById(R.id.nameprod_card);
            unidade = (TextView) itemView.findViewById(R.id.unidade_card);
            estoque = (TextView) itemView.findViewById(R.id.estoque_card);
            valor = (TextView) itemView.findViewById(R.id.preco_card);
            status = (TextView) itemView.findViewById(R.id.status_card);
            iden = (TextView) itemView.findViewById(R.id.idprod_card);
            excluir = (ImageButton) itemView.findViewById(R.id.excluir_prod);

            excluir = (ImageButton) itemView.findViewById(R.id.excluir_prod);
            excluir.setOnClickListener(this);

            modf = (ImageButton) itemView.findViewById(R.id.mod);
            modf.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.excluir_prod) {
                modeloproduto.ApagarProduto(produto);
               // modeloproduto.show_produtos.remove(produto);
                //notify();
                notifyDataSetChanged();
            }
            if (v.getId() == R.id.mod){
                if(modeloproduto.show_produtos.size()>0){
                    Show_Produtos show_produtos=modeloproduto.show_produtos.get(getLayoutPosition());

                    Intent it=new Intent(context, NovoProdutoActivity.class);
                    it.putExtra("PRODUTO",produto);
                    ((AppCompatActivity)context).startActivity(it);
                }}
        }
    }
    private Context context;
    private ProdutoModel modeloproduto;

    public SearchProduto(Context context) {
        this.context = context;


        this.modeloproduto = new ProdutoModel(context);
        this.modeloproduto.carregarProduto();
    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.layout_produto, parent, false);
        return new SearchProduto.SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.produto = this.modeloproduto.show_produtos.get(position);

        holder.produt.setText(holder.produto.getNome_produto());
        holder.unidade.setText(holder.produto.getUnidade_produto());
        holder.estoque.setText(holder.produto.getEstoque_produto());
        holder.valor.setText(holder.produto.getPreco_produto());
        holder.status.setText(holder.produto.getStatus_produto());
        holder.iden.setText(holder.produto.getId_produto());
    }

    @Override
    public int getItemCount() {
        return  this.modeloproduto.show_produtos.size();
    }

    public void filtrar(String text) {
//        db.getFriendByName(text)
        if (text == null || text.isEmpty()){
            this.modeloproduto.carregarProduto();
        }else {
            this.modeloproduto.filtrarproduto(text);
        }
    }




}
