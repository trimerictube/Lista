package cavalcante.gouvea.lista.activity;

import static cavalcante.gouvea.lista.R.id.fabAddNewItem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cavalcante.gouvea.lista.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddItem = findViewById(fabAddNewItem);
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent i = new Intent(MainActivity.this,NewItemActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

    }
}