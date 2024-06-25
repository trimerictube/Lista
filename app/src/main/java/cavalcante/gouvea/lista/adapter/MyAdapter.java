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
    MainActivity mainActivity; // Referência para a MainActivity
    List<MyItem> itens; // Lista de itens a serem exibidos

    // Construtor da classe MyAdapter
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity; // Inicializa a referência para a MainActivity
        this.itens = itens; // Inicializa a lista de itens
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflador para ler o arquivo XML de layout do item e criar os elementos de interface
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        // Usa o inflador para criar os elementos de interface referentes a um item e guarda em um objeto do tipo View
        View v = inflater.inflate(R.layout.item_list, parent, false);
        // Retorna um novo MyViewHolder contendo o objeto do tipo View (v)
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Obtém o item que será usado para preencher a UI
        MyItem myItem = itens.get(position);
        // Obtém o objeto do tipo View que está guardado dentro do ViewHolder
        View v = holder.itemView;

        // Obtém a referência ao ImageView e define a imagem do item
        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        imvPhoto.setImageBitmap(myItem.photo);

        // Obtém a referência ao TextView do título e define o título do item
        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        // Obtém a referência ao TextView da descrição e define a descrição do item
        TextView tvDesc = v.findViewById(R.id.tvDesc);
        tvDesc.setText(myItem.description);

        // Nas linhas acima, preenchemos a UI com os dados do item,
        // observando que chamamos o método findViewById dentro do objeto View (v)
    }

    @Override
    public int getItemCount() {
        // Retorna a quantidade de itens na lista
        return itens.size();
    }

    // Classe interna MyViewHolder que herda de RecyclerView.ViewHolder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // Construtor da classe MyViewHolder
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
