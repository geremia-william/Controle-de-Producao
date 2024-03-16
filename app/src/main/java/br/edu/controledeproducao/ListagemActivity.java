package br.edu.controledeproducao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {

    private ListView listViewPecas;

    private PecaAdapter pecasAdapter;
    private static ArrayList<Pecas> listaPecas = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        listViewPecas = findViewById(R.id.listViewPecas);

        popularListView();

        listViewPecas.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Pecas peca= (Pecas) listViewPecas.getItemAtPosition(position);

                        String mensagem = getString(R.string.dados_da_peca_clicada) +
                                "\n" + getString(R.string.obra) + peca.getObra() +
                                "\n" + getString(R.string.nome_peca) + peca.getNome() +
                                "\n" + getString(R.string.id) + peca.getId() +
                                "\n" + getString(R.string.tipo) + peca.getTipo();

                        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
                    }
                }

        );


    }

    ActivityResultLauncher<Intent> launcherNovaPeca = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            //Isso será chamado quando alguém resolver um resultado
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //Avalia se a activity está realmente rescebendo algo
                    if (result.getResultCode() == Activity.RESULT_OK){

                        Intent intent = result.getData();

                        Bundle bundle = intent.getExtras();

                        if (bundle != null){

                            Pecas novapeca = new Pecas(
                                                        bundle.getString(MainActivity.NOME),
                                                        bundle.getInt(MainActivity.ID) ,
                                                        bundle.getString(MainActivity.TIPO) ,
                                                        (Obra) bundle.getSerializable(MainActivity.OBRA)
                                                      );

                            listaPecas.add(novapeca);
                            pecasAdapter.notifyDataSetChanged();
                        }

                    }
                }
            });

    private void popularListView() {

        /*String[] nomePecaList = getResources().getStringArray(R.array.nome_pecas_iniciais_list_view);
        int[] idList = getResources().getIntArray(R.array.id_iniciais_list_view);
        String[] tipoList = getResources().getStringArray(R.array.tipo_iniciais_list_view);
        String[] obraList = getResources().getStringArray(R.array.obra_iniciais_list_view);
        String[] clienteList = getResources().getStringArray(R.array.cliente_iniciais_list_view);

        for(int i = 0 ; i < nomePecaList.length; i++){
            listaPecas.add( new Pecas(nomePecaList[i],
                            idList[i],
                            tipoList[i],
                            new Obra(obraList[i], clienteList[i])
                    )
            );
        }*/

        pecasAdapter = new PecaAdapter(this, listaPecas);
        listViewPecas.setAdapter(pecasAdapter);

    }

    public void abrirSobre(View view){
        SobreActivity.abrirSobre(this);

    }

    public void abrirCadastro(View view){

        MainActivity.abrirCadastro(this, launcherNovaPeca);

    }
}