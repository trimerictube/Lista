package cavalcante.gouvea.lista.model; // Define o pacote da classe

import androidx.lifecycle.ViewModel; // Importa a classe ViewModel do pacote androidx.lifecycle

import java.util.ArrayList; // Importa a classe ArrayList do pacote java.util
import java.util.List; // Importa a interface List do pacote java.util

// Declaração da classe MainActivityViewModel que estende ViewModel
public class MainActivityViewModel extends ViewModel {
    // Declaração de uma lista do tipo MyItem chamada itens e inicialização com um ArrayList vazio
    List<MyItem> itens = new ArrayList<>();

    // Método getter para obter a lista de itens
    public List<MyItem> getItens() {
        return itens;
    }
}
