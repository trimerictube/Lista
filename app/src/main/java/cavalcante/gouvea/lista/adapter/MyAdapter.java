package cavalcante.gouvea.lista.adapter;

import androidx.recyclerview.widget.RecyclerView;

import cavalcante.gouvea.lista.activity.MainActivity;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(mainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

}
