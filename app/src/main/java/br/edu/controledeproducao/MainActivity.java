package br.edu.controledeproducao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //ESTOU USANDO UM ArrayList POR QUE A INTENSÃO É SER POSSÍVEL CADASTRAR NOVAS OBRAS
    private static ArrayList<String> listaObras = new ArrayList<String>();
    private Spinner spinnerObra;
    private EditText editTextNome;
    private RadioGroup radioGroupTipo;

    private Pecas listaPecas;


    public ArrayList<String> getListaObras() {
        return listaObras;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerObra = findViewById(R.id.spinnerObra);
        editTextNome = findViewById(R.id.editTextNomePeca);
        radioGroupTipo = findViewById(R.id.radioGroupTipo);
        listaObras.add("");
        listaObras.add("Obra 1");
        listaObras.add("Obra 2");
        listaObras.add("Obra 3");

        popularSpinnerObra();
    }

    public void limpar(View view){
        radioGroupTipo.clearCheck();
        editTextNome.setText("");
        spinnerObra.setSelection(0);
    }

    public void cadastrar(View view){

        Pecas novaPeca = new Pecas();

        int botaoSelecionado = radioGroupTipo.getCheckedRadioButtonId();

        novaPeca.setObra(spinnerObra.getSelectedItem().toString());

        novaPeca.setNome(editTextNome.getText().toString());

        if(botaoSelecionado == R.id.radioButtonViga){
            novaPeca.setTipo(getString(R.string.viga));
        } else {
            if(botaoSelecionado == R.id.radioButtonPilar){
                novaPeca.setTipo(getString(R.string.pilar));
            } else {
                if(botaoSelecionado == R.id.radioButtonEscada){
                    novaPeca.setTipo(getString(R.string.escada));
                }
            }
        }

        String mensagem = "Obra: "+novaPeca.getObra()+
                          "\nNome: "+novaPeca.getNome()+
                          "\nTipo: "+novaPeca.getTipo();

        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();

    }

    public void adicionaObra(String novaObra){
        listaObras.add(novaObra);
    }

    public void popularSpinnerObra(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaObras);

        spinnerObra.setAdapter(adapter);
    }

    public static void adicionaObraSpineer(String novaObra){
        listaObras.add(novaObra);
    }

    public void abreCadastroObra(View view){

        Intent intent = new Intent(this, CadastroObraActivity.class);

        startActivity(intent);

    }

}