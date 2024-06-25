package cavalcante.gouvea.lista.activity; // Define o pacote da atividade

// Importações necessárias para a atividade
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import cavalcante.gouvea.lista.R;
import cavalcante.gouvea.lista.model.NewActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1; // Constante para identificar a requisição do seletor de fotos

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Método chamado quando a atividade é criada
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item); // Define o layout da atividade

        NewActivityViewModel vm = new ViewModelProvider(this).get(NewActivityViewModel.class);
        // Obtém o ViewModel referente a NewItemActivity

        Uri selectPhotoLocation = vm.getSelectPhotoLocation();
        // Obtém o endereço URI guardado no ViewModel

        if (selectPhotoLocation != null) { // Caso o endereço não seja nulo
            ImageView imvFotoPreview = findViewById(R.id.imvPhotoPreview);
            // Obtém a referência ao ImageView
            imvFotoPreview.setImageURI(selectPhotoLocation);
            // Define a imagem no ImageView da tela
        }

        ImageButton imgCI = findViewById(R.id.imbCI); // Obtém a referência ao botão de escolher imagens
        imgCI.setOnClickListener(new View.OnClickListener() { // Define um listener para o botão
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                // Intenção para escolher a foto
                photoPickerIntent.setType("image/*");
                // Define o tipo de arquivo como imagem
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
                // Inicia a atividade do seletor de fotos e espera um resultado
            }
        });

        Button btnAddItem = findViewById(R.id.btnAddItem); // Obtém a referência ao botão de adicionar item
        btnAddItem.setOnClickListener(new View.OnClickListener() { // Define um listener para o botão
            @Override
            public void onClick(View v) { // Função chamada quando o botão é clicado

                NewActivityViewModel vm = new ViewModelProvider(NewItemActivity.this).get(NewActivityViewModel.class);
                // Obtém o ViewModel

                Uri photoSelected = vm.getSelectPhotoLocation();
                // Obtém o URI da foto selecionada
                if (photoSelected == null) { // Verifica se a foto é válida
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle); // Obtém a referência ao campo de texto do título
                String title = etTitle.getText().toString(); // Obtém o texto do campo de título

                if (title.isEmpty()) { // Verifica se o título é válido
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etDesc = findViewById(R.id.etDesc); // Obtém a referência ao campo de texto da descrição
                String desc = etDesc.getText().toString(); // Obtém o texto do campo de descrição

                if (desc.isEmpty()) { // Verifica se a descrição é válida
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent(); // Cria uma nova intenção
                i.setData(photoSelected); // Adiciona a foto na intenção
                i.putExtra("title", title); // Adiciona o título na intenção
                i.putExtra("description", desc); // Adiciona a descrição na intenção
                setResult(Activity.RESULT_OK, i); // Define o resultado como OK e adiciona a intenção
                finish(); // Finaliza a atividade
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_PICKER_REQUEST) { // Verifica se a requisição é do seletor de fotos
            if (resultCode == Activity.RESULT_OK) { // Verifica se o resultado foi OK
                Uri photoSelected = data.getData(); // Obtém o URI da foto selecionada
                ImageView imvFotoPreview = findViewById(R.id.imvPhotoPreview); // Obtém a referência ao ImageView
                imvFotoPreview.setImageURI(photoSelected); // Define a imagem no ImageView
                NewActivityViewModel vm = new ViewModelProvider(this).get(NewActivityViewModel.class);
                // Obtém o ViewModel
                vm.setSelectPhotoLocation(photoSelected);
                // Guarda o URI da imagem escolhida no ViewModel
            }
        }
    }
}
