package cavalcante.gouvea.lista.model;

public class MainActivityViewModel extends ViewModel{
    List<MyItem> itens = new ArrayList<>();

    public List<MyItem> getItens() {
        return itens;
    }
}
