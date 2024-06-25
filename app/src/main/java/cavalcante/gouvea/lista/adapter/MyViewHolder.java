package cavalcante.gouvea.lista.adapter; // Define o pacote da classe

import android.view.View; // Importa a classe View do pacote android.view

import androidx.annotation.NonNull; // Importa a anotação NonNull do pacote androidx.annotation
import androidx.recyclerview.widget.RecyclerView; // Importa a classe RecyclerView do pacote androidx.recyclerview.widget

// Declaração da classe MyViewHolder que estende RecyclerView.ViewHolder
public class MyViewHolder extends RecyclerView.ViewHolder {

    // Construtor da classe MyViewHolder que recebe um View como parâmetro
    public MyViewHolder(@NonNull View itemView) {
        // Chama o construtor da classe pai (RecyclerView.ViewHolder) passando o itemView
        super(itemView);
    }
}
