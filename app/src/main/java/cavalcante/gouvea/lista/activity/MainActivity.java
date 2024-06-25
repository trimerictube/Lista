package cavalcante.gouvea.lista.activity;

// Importações necessárias para a atividade
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cavalcante.gouvea.lista.R;
import cavalcante.gouvea.lista.adapter.MyAdapter;
import cavalcante.gouvea.lista.model.MainActivityViewModel;
import cavalcante.gouvea.lista.model.MyItem;
import cavalcante.gouvea.lista.util.Util;

// Define a classe MainActivity que herda de AppCompatActivity
public class MainActivity extends AppCompatActivity {

    // Constante para identificar a requisição de um novo item
    static int NEW_ITEM_REQUEST = 1;

    // Lista que armazena os itens
    List<MyItem> itens = new ArrayList<>();

    // Adaptador para o RecyclerView
    MyAdapter myAdapter;

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

        // Obtém o ViewModel
        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        List<MyItem> itens = vm.getItens();

        // Cria o adaptador passando o contexto e a lista de itens
        myAdapter = new MyAdapter(this, itens);
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

    // Método chamado quando a atividade recebe um resultado de outra atividade
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verifica se o requestCode é o mesmo da requisição de novo item
        if (requestCode == NEW_ITEM_REQUEST) {
            // Verifica se o resultado foi OK
            if (resultCode == Activity.RESULT_OK) {
                // Cria um novo MyItem e preenche com os dados recebidos
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title"); // define o titulo
                myItem.description = data.getStringExtra("description"); // define a descrição
                Uri selectedPhotoUri = data.getData(); // obtém o URI da foto selecionada

                try {
                    // Converte o URI da foto selecionada para Bitmap
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoUri, 100, 100);
                    myItem.photo = photo; // define a foto do item
                } catch (FileNotFoundException e) {
                    e.printStackTrace(); // imprime a pilha de erro em caso de exceção
                }

                // Obtém o ViewModel
                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
                // Obtém a lista de itens do ViewModel
                List<MyItem> itens = vm.getItens();

                // Adiciona o novo item à lista
                itens.add(myItem);
                // Notifica o adaptador que um novo item foi inserido
                myAdapter.notifyItemInserted(itens.size() - 1);
            }
        }
    }
}
