package br.edu.controledeproducao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String NOME = "NOME";
    public static final String OBRA = "Obra";
    public static final String ID = "ID";
    public static final String TIPO = "TIPO";

    private static ArrayList<Obra> listaObras = new ArrayList<>();
    private Spinner spinnerObra;
    private EditText editTextNome, editTextId;
    private RadioGroup radioGroupTipo;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerObra = findViewById(R.id.spinnerObra);
        editTextNome = findViewById(R.id.editTextNomePeca);
        editTextId = findViewById(R.id.editTextId);
        radioGroupTipo = findViewById(R.id.radioGroupTipo);
            popularSpinnerObra();

    }

    public void limpar(View view){
        radioGroupTipo.clearCheck();
        editTextNome.setText("");
        spinnerObra.setSelection(0);
        editTextId.setText("");

        Toast.makeText(this, R.string.todos_os_campos_foram_limpos,Toast.LENGTH_SHORT).show();
    }


    public void salvar(View view){

        Pecas novaPeca = new Pecas();
        boolean valida = true;

        String validNome = editTextNome.getText().toString();

        int botaoSelecionado = radioGroupTipo.getCheckedRadioButtonId();

        if(validNome.isEmpty()){
            Toast.makeText(this, R.string.o_campo_nome_n_o_pode_ser_vazio, Toast.LENGTH_SHORT).show();
            editTextNome.requestFocus();
            valida = false;
        } else{
            if(botaoSelecionado == -1){
                Toast.makeText(this, R.string.o_campo_tipo_nao_pode_ser_vazio, Toast.LENGTH_SHORT).show();
                valida = false;
            } else{
                try{
                    int validId = Integer.parseInt(editTextId.getText().toString());

                } catch (NumberFormatException e){
                    Toast.makeText(this, R.string.o_campo_id_deve_ser_um_numero_inteiro, Toast.LENGTH_SHORT).show();
                    editTextId.setText("");
                    editTextId.requestFocus();
                    valida = false;
                }
            }
        }

        if(valida) {

            String nome = editTextNome.getText().toString();
            int id = Integer.parseInt(editTextId.getText().toString());
            Obra obra = (Obra) spinnerObra.getSelectedItem();

            String tipo = "";

            if (botaoSelecionado == R.id.radioButtonViga) {
                tipo = getString(R.string.viga);
            } else {
                if (botaoSelecionado == R.id.radioButtonPilar) {
                    tipo = getString(R.string.pilar);
                } else {
                    if (botaoSelecionado == R.id.radioButtonEscada) {
                        tipo = getString(R.string.escada);
                    }
                }
            }

            Intent intent = new Intent();

            intent.putExtra(NOME , nome);
            intent.putExtra(ID , id);
            intent.putExtra(TIPO , tipo);
            intent.putExtra(OBRA , obra);

            setResult(Activity.RESULT_OK, intent);

            finish();

        }

    }

    public void popularSpinnerObra(){
        String[] nomesObraInicial =getResources().getStringArray(R.array.obras_iniciais_spinner);
        String[] clientesInicial =getResources().getStringArray(R.array.clientes_iniciais_spinner);

        for(int i = 0 ; i < nomesObraInicial.length; i++){

            listaObras.add(new Obra(nomesObraInicial[i],clientesInicial[i]));

        }
        ArrayAdapter<Obra> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaObras);

        spinnerObra.setAdapter(adapter);
    }

    public static void abrirCadastro(AppCompatActivity activity , ActivityResultLauncher<Intent> launcher){

        Intent intent = new Intent(activity, MainActivity.class);

        //Usa o Luacher para esperar a resposta da intent de cadastro que será aberta
        launcher.launch(intent);

    }

    public void cancelar(View view){

        setResult(Activity.RESULT_CANCELED);
        finish();

    }
}