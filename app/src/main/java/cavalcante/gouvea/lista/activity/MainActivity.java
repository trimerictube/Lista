package cavalcante.gouvea.lista.activity; // Define o pacote da classe

// Importações necessárias para a atividade
import androidx.appcompat.app.AppCompatActivity; // Importa a classe AppCompatActivity para suporte à ActionBar
import androidx.lifecycle.ViewModelProvider; // Importa a classe ViewModelProvider para usar ViewModels
import androidx.recyclerview.widget.DividerItemDecoration; // Importa a classe DividerItemDecoration para adicionar divisores no RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager; // Importa a classe LinearLayoutManager para gerenciar o layout do RecyclerView
import androidx.recyclerview.widget.RecyclerView; // Importa a classe RecyclerView

import android.app.Activity; // Importa a classe Activity
import android.content.Intent; // Importa a classe Intent para manipular intenções
import android.net.Uri; // Importa a classe Uri para manipular URIs
import android.os.Bundle; // Importa a classe Bundle para passar dados entre atividades
import android.view.View; // Importa a classe View

import com.google.android.material.floatingactionbutton.FloatingActionButton; // Importa a classe FloatingActionButton

import java.io.FileNotFoundException; // Importa a classe FileNotFoundException (não usada no código)
import java.util.ArrayList; // Importa a classe ArrayList para manipular listas
import java.util.List; // Importa a interface List

import cavalcante.gouvea.lista.R; // Importa o arquivo de recursos R
import cavalcante.gouvea.lista.adapter.MyAdapter; // Importa a classe MyAdapter
import cavalcante.gouvea.lista.model.MainActivityViewModel; // Importa a classe MainActivityViewModel
import cavalcante.gouvea.lista.model.MyItem; // Importa a classe MyItem
import cavalcante.gouvea.lista.util.Util; // Importa a classe Util (não usada no código)

public class MainActivity extends AppCompatActivity { // Define a classe MainActivity que estende AppCompatActivity

    // Constante para identificar a requisição de um novo item
    static int NEW_ITEM_REQUEST = 1;

    // Lista que armazena os itens
    List<MyItem> itens = new ArrayList<>();

    // Adaptador para o RecyclerView
    MyAdapter myAdapter;

    // Método chamado quando a atividade recebe um resultado de outra atividade
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verifica se o requestCode é o mesmo da requisição de novo item
        if (requestCode == NEW_ITEM_REQUEST) {
            // Verifica se o resultado foi OK
            if(resultCode == Activity.RESULT_OK) {
                // Cria um novo MyItem e preenche com os dados recebidos
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                Uri selectedPhotoURI = data.getData();

                // Obtém a instância do ViewModel
                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
                List<MyItem> itens = vm.getItens();

                // Adiciona o novo item à lista e notifica o adaptador
                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }

    // Método chamado quando a atividade é criada
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referência ao botão de ação flutuante para adicionar um novo item
        FloatingActionButton fabAddNewItem = findViewById(R.id.fabAddNewItem);

        // Define um listener para o botão de ação flutuante
        fabAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria uma intenção para abrir a NewItemActivity
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);

                // Inicia a atividade e espera um resultado
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        // Referência ao RecyclerView que mostrará os itens
        RecyclerView rvItens = findViewById(R.id.rvItens);

        // Obtém a instância do ViewModel
        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        List<MyItem> itens = vm.getItens();

        // Cria o adaptador passando o contexto e a lista de itens
        myAdapter = new MyAdapter(this, this.itens);

        // Define o adaptador no RecyclerView
        rvItens.setAdapter(myAdapter);

        // Define que o RecyclerView terá um tamanho fixo
        rvItens.setHasFixedSize(true);

        // Cria um LayoutManager para o RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        // Define o LayoutManager no RecyclerView
        rvItens.setLayoutManager(layoutManager);

        // Cria uma decoração de divisor para o RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);

        // Adiciona a decoração de divisor ao RecyclerView
        rvItens.addItemDecoration(dividerItemDecoration);
    }
}
