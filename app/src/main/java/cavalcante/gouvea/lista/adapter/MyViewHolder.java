package cavalcante.gouvea.lista.adapter; // Define o pacote da classe

import android.view.View; // Importa a classe View do pacote android.view

import androidx.annotation.NonNull; // Importa a anotação NonNull do pacote androidx.annotation
import androidx.recyclerview.widget.RecyclerView; // Importa a classe RecyclerView do pacote androidx.recyclerview.widget

// Declaração da classe MyViewHolder que estende RecyclerView.ViewHolder
public class MyViewHolder extends RecyclerView.ViewHolder {
    public MyViewHolder(@NonNull View itemView){
        super(itemView);

    }
}
