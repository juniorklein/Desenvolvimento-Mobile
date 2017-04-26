package br.com.uniftec.todoapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import br.com.uniftec.todoapp.R;
import br.com.uniftec.todoapp.ds.AtividadeDataSource;
import br.com.uniftec.todoapp.model.Atividade;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button adicionarButton;
    private ListView atividadesListView;

    private ListaAtividadesAdapter listaAtividadesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gerarMocks();

        adicionarButton = (Button) findViewById(R.id.activity_main_adicionar_button);
        atividadesListView = (ListView)findViewById(R.id.activity_main_atividades_list_view);

        adicionarButton.setOnClickListener(this);

        listaAtividadesAdapter = new ListaAtividadesAdapter(this, AtividadeDataSource.getInstance().consultar(null));
        atividadesListView.setAdapter(listaAtividadesAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view == adicionarButton){
            adicionarClick();
        }
    }

    private void adicionarClick(){

    }

    private void gerarMocks(){
        Atividade atividade = new Atividade();
        atividade.setTitulo("Titulo 1");
        atividade.setNota("Nota 1");
        AtividadeDataSource.getInstance().adicionar(atividade);

        atividade = new Atividade();
        atividade.setTitulo("Titulo 2");
        atividade.setNota("Nota 2");
        AtividadeDataSource.getInstance().adicionar(atividade);

        atividade = new Atividade();
        atividade.setTitulo("Titulo 3");
        atividade.setNota("Nota 3");
        AtividadeDataSource.getInstance().adicionar(atividade);
    }
}
