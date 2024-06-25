package cavalcante.gouvea.lista.model; // Define o pacote da classe

import android.net.Uri; // Importa a classe Uri para trabalhar com URIs

import androidx.lifecycle.ViewModel; // Importa a classe ViewModel do pacote androidx.lifecycle

// Declaração da classe NewActivityViewModel que estende ViewModel
public class NewActivityViewModel extends ViewModel {

    Uri selectPhotoLocation = null; // Declara um atributo Uri para armazenar o endereço URI da foto selecionada inicialmente como nulo

    public Uri getSelectPhotoLocation() { // Método getter para obter o endereço URI da foto selecionada
        return selectPhotoLocation; // Retorna o endereço URI da foto selecionada
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation) { // Método setter para definir o endereço URI da foto selecionada
        this.selectPhotoLocation = selectPhotoLocation; // Define o endereço URI da foto selecionada
    }
}
