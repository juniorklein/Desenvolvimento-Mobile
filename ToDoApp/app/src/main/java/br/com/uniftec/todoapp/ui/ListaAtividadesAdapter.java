package br.com.uniftec.todoapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.uniftec.todoapp.R;
import br.com.uniftec.todoapp.model.Atividade;

/**
 * Created by marioklein on 20/04/17.
 */

public class ListaAtividadesAdapter extends ArrayAdapter<Atividade> {

    private Context context;

    public ListaAtividadesAdapter(Context context, List<Atividade> atividades){
        super(context, 0, atividades);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View linhaView = convertView;
        if(linhaView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            linhaView = inflater.inflate(R.layout.linha_lista_atividades, parent, false);
        }

        CheckBox concluidaCheckBox = (CheckBox) linhaView.findViewById(R.id.linha_lista_atividades_concluida_check_box);
        TextView tituloTextView = (TextView) linhaView.findViewById(R.id.linha_lista_atividades_titulo_text_view);

        Atividade atividade = getItem(position);

        tituloTextView.setText(atividade.getTitulo());
        concluidaCheckBox.setChecked(atividade.isConcluida());

        return linhaView;
    }
}
