package cavalcante.gouvea.lista.activity; // Define o pacote da atividade

// Importações necessárias para a atividade
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import cavalcante.gouvea.lista.R;
import cavalcante.gouvea.lista.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    // Constante para identificar a requisição de seleção de foto
    static int PHOTO_PICKER_REQUEST = 1;

    // Uri para armazenar a foto selecionada
    Uri photoSelected = null;

    // Método chamado quando a atividade recebe um resultado de outra atividade
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Verifica se o requestCode é o mesmo da requisição de seleção de foto e se o resultado foi OK
        if (requestCode == PHOTO_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            // Armazena a Uri da foto selecionada
            photoSelected = data.getData();

            // Referência à ImageView para exibir a foto selecionada
            ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview);

            // Define a Uri da foto selecionada na ImageView
            imvPhotoPreview.setImageURI(photoSelected);
        }
    }

    // Método chamado quando a atividade é criada
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class);

        Uri selectPhotoLocation = vm.getSelectPhotoLocation(); // Corrige para chamar getSelectPhotoLocation()
        if(selectPhotoLocation != null) {
            ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview);
            imvPhotoPreview.setImageURI(selectPhotoLocation);
        }

        // Referência ao ImageButton para selecionar a foto
        ImageButton imgCI = findViewById(R.id.imbCI);

        // Define um listener para o ImageButton
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria uma intenção para abrir o seletor de documentos para imagens
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                photoPickerIntent.setType("image/*");

                // Inicia a atividade e espera um resultado
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });

        // Referência ao botão para adicionar um novo item
        Button btnAddItem = findViewById(R.id.btnAddItem);

        // Define um listener para o botão de adicionar item
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica se uma foto foi selecionada
                if (photoSelected == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                // Referência ao campo de texto para o título
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();

                // Verifica se o título foi preenchido
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um Título!", Toast.LENGTH_LONG).show();
                    return;
                }

                // Referência ao campo de texto para a descrição
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();

                // Verifica se a descrição foi preenchida
                if (description.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição!", Toast.LENGTH_LONG).show();
                    return;
                }

                // Cria uma intenção para enviar os dados de volta
                Intent i = new Intent();
                i.setData(photoSelected);
                i.putExtra("title", title);
                i.putExtra("description", description);

                // Define o resultado como OK e envia a intenção
                setResult(Activity.RESULT_OK, i);

                // Finaliza a atividade
                finish();
            }
        });
    }
}
