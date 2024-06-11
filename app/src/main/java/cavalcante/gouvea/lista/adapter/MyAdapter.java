package cavalcante.gouvea.lista.adapter; // Define o pacote da classe

// Importações necessárias para o adaptador
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cavalcante.gouvea.lista.R;
import cavalcante.gouvea.lista.activity.MainActivity;
import cavalcante.gouvea.lista.model.MyItem;

// Classe MyAdapter que herda de RecyclerView.Adapter
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    MainActivity mainActivity; // Referência à atividade principal
    List<MyItem> itens; // Lista de itens a serem exibidos

    // Construtor do adaptador que recebe a atividade principal e a lista de itens
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    // Método chamado para criar um novo ViewHolder
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout do item da lista
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list, parent, false);
        // Retorna um novo MyViewHolder com a view inflada
        return new MyViewHolder(v);
    }

    // Método chamado para vincular os dados ao ViewHolder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Obtém o item da lista na posição especificada
        MyItem myItem = itens.get(position);
        // Obtém a view do ViewHolder
        View v = holder.itemView;
        // Encontra e define a imagem no ImageView
        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        imvPhoto.setImageBitmap(myItem.photo);
        // Encontra e define o título no TextView
        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);
        // Encontra e define a descrição no TextView
        TextView tvDesc = v.findViewById(R.id.tvDesc);
        tvDesc.setText(myItem.description);
    }

    // Método que retorna a contagem de itens na lista
    @Override
    public int getItemCount() {
        return itens.size();
    }

    // Classe interna MyViewHolder que estende RecyclerView.ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Construtor que recebe a view do item da lista
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
