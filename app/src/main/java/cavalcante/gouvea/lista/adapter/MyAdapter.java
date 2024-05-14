package cavalcante.gouvea.lista.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cavalcante.gouvea.lista.activity.MainActivity;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(mainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInfalter.from(mainActivity);
        View v = infalter.infalter(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position);
        View v = holder.itemView;
        ImageView imvfoto = v.findViewById(R.id.imvfoto);
        imvfoto.setImageURI(myItem.foto);TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);
        TextView tvdesc = v.findViewById(R.id.tvdesc);
        tvdesc.setText(myItem.description);
    }

    @Override
    public int getItemCount() {
        return iten.size();
    }


}
