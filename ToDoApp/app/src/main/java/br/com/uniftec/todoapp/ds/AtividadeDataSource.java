package br.com.uniftec.todoapp.ds;

import java.util.ArrayList;
import java.util.List;

import br.com.uniftec.todoapp.model.Atividade;

/**
 * Created by marioklein on 20/04/17.
 */

public class AtividadeDataSource {

    private static AtividadeDataSource instance;

    private List<Atividade> atividades;
    private int lastId;

    private AtividadeDataSource() {
        atividades = new ArrayList<>();
        lastId = 0;
    }

    public static AtividadeDataSource getInstance() {
        if (instance == null) {
            instance = new AtividadeDataSource();
        }
        return instance;
    }

    public Atividade buscar(int id) {
        for (Atividade atividade : atividades) {
            if (atividade.getId() == id) {
                return atividade;
            }
        }
        return null;
    }

    public void adicionar(Atividade atividade) {
        if (atividade.getId() > 0) {
            throw new RuntimeException("Não é possível adicionar uma atividade que já possua ID");
        }
        atividade.setId(++lastId);
        atividades.add(atividade);
    }

    public boolean remover(int id) {
        Atividade remover = null;
        for (Atividade atividade : atividades) {
            if (atividade.getId() == id) {
                remover = atividade;
                break;
            }
        }
        if (remover == null) {
            return false;
        } else {
            atividades.remove(remover);
            return true;
        }
    }

    public List<Atividade> consultar(Boolean concluidas) {

        if (concluidas == null) {
            return new ArrayList<>(atividades);
        }

        List<Atividade> filtered = new ArrayList<>();
        for (Atividade atividade : atividades) {
            if (atividade.isConcluida() == concluidas) {
                filtered.add(atividade);
            }
        }

        return filtered;
    }

    public void atualizar(Atividade atividade) {
        if (!remover(atividade.getId())) {
            throw new RuntimeException("Produto não encontrado para ser atualizado");
        }

        atividades.add(atividade);
    }
}
