package br.com.uniftec.todoapp.service;

import java.util.List;

import br.com.uniftec.todoapp.model.Atividade;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Interface utilizada pelo retrofit para realizar a chamada para o webservice
 */

public interface ToDoService {

    @GET("/todos")
    public Call<List<Atividade>>carregarAtividades();

}
