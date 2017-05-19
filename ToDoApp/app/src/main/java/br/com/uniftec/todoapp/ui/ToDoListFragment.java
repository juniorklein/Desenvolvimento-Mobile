package br.com.uniftec.todoapp.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import br.com.uniftec.todoapp.R;
import br.com.uniftec.todoapp.ds.AtividadeDataSource;

/**
 * Created by marioklein on 04/05/17.
 */

public class ToDoListFragment extends Fragment implements View.OnClickListener {

    private Button adicionarButton;
    private ListView atividadesListView;

    private ListaAtividadesAdapter listaAtividadesAdapter;

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
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        listaAtividadesAdapter = new ListaAtividadesAdapter(activity, AtividadeDataSource.getInstance().consultar(null));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        atividadesListView.setAdapter(listaAtividadesAdapter);

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
}
