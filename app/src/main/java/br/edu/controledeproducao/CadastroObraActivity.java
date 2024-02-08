package br.edu.controledeproducao;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroObraActivity extends AppCompatActivity {

    public EditText editTextNomeObra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_obra);

        editTextNomeObra = findViewById(R.id.editTextNomeObra);
    }


    public void cadastrarObra(View view){
        String novaObra = editTextNomeObra.getText().toString();
        MainActivity.adicionaObraSpineer(novaObra);
        finish();
    }

}