package br.com.uniftec.todoapp.service;

import java.util.List;

import br.com.uniftec.todoapp.model.Atividade;
import br.com.uniftec.todoapp.model.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface utilizada pelo retrofit para realizar a chamada para o webservice
 */

public interface ToDoService {

    @GET("/todos")
    public Call<List<Atividade>>carregarAtividades();

    @GET("/todos")
    public Call<Response<List<Atividade>>> carregarTodas();

    @POST("/todos")
    public Call<Atividade>criarAtividade(@Body Atividade atividade);

    @POST("/todos")
    public Call<Response<Atividade>>criarAtividadeTeste(@Body Atividade atividade);

    @POST("/todos/{idatualizar}")
    public Call<Atividade>atualizarAtividade(@Path("idatualizar") int id, @Body Atividade atividade);

}
