package br.com.uniftec.todoapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.uniftec.todoapp.R;
import br.com.uniftec.todoapp.ds.AtividadeDataSource;
import br.com.uniftec.todoapp.model.Atividade;
import br.com.uniftec.todoapp.service.ToDoService;
import br.com.uniftec.todoapp.task.CarregarAtividadesTask;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by marioklein on 04/05/17.
 */

public class ToDoListFragment extends Fragment implements View.OnClickListener, CarregarAtividadesTask.CarregarAtividadesTaskCallBack{

    private Button adicionarButton;
    private ListView atividadesListView;

    private ProgressDialog progressDialog;

    private ListaAtividadesAdapter listaAtividadesAdapter;
    private List<Atividade> listaAtividades;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listaAtividades = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);

        adicionarButton = (Button) view.findViewById(R.id.fragment_to_do_list_adicionar_button);
        atividadesListView = (ListView) view.findViewById(R.id.fragment_to_do_list_atividades_list_view);

        adicionarButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Cria o adapter e configura o list view para mostrar o conteúdo do ArrayList armazenado em listaAtividades
        listaAtividadesAdapter = new ListaAtividadesAdapter(getActivity(), listaAtividades);
        atividadesListView.setAdapter(listaAtividadesAdapter);

        // Método que irá criar a task, executá-la e colocar um progress dialog em tela para indicar que o processo de carga está ocorrendo
        carregarAtividades();

    }

    @Override
    public void onClick(View view) {
        if(view == adicionarButton){
            adicionarClick();
        }
    }

    private void adicionarClick(){
        new AlertDialog.Builder(getActivity())
                .setTitle("Alerta")
                .setMessage("Mensagem do Dialog")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(ToDoListFragment.this.getActivity(), "Mensagem do Toast", Toast.LENGTH_LONG).show();
                    }
                })
                .create().show();
    }

    private void carregarAtividades(){
        // parâmetros método estático show(Context, Titulo, Menssagem, Indeterminado, Cancelável)
        progressDialog = ProgressDialog.show(getActivity(), "Aguarde", "Carregando as atividades, por favor aguarde.", true, false);
        // Método execute é responsável por criar a thread de background
        new CarregarAtividadesTask(this).execute();
    }

    @Override
    // Método de call back quando a task executou com sucesso
    public void onCarregarAtividadesSuccess(List<Atividade> atividades) {
        // Limpa o array list e adiciona todas as atividades que retornaram do webservice
        listaAtividades.clear();
        listaAtividades.addAll(atividades);

        // Informa ao adapter que o conteúdo do array list foi modificado, logo o ListView deve ser atualizado
        if(listaAtividadesAdapter != null){
            listaAtividadesAdapter.notifyDataSetChanged();
        }

        // Retira o progressDialog da tela
        progressDialog.dismiss();
    }

    @Override
    // Método de call back quando a task executou com falha
    public void onCarregarAtividadesFailure() {
        progressDialog.dismiss();
        Toast.makeText(getActivity(), "Ocorreu um erro inesperado ao carregar a lista de atividades", Toast.LENGTH_LONG).show();
    }
}
