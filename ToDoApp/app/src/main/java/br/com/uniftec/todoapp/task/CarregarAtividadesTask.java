package br.com.uniftec.todoapp.task;

import android.os.AsyncTask;

import java.util.List;

import br.com.uniftec.todoapp.model.Atividade;
import br.com.uniftec.todoapp.service.ToDoService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Task responsável por transformar a chamada para o webservice em um processo assíncrono
 */

public class CarregarAtividadesTask extends AsyncTask<Void, Void, List<Atividade>> {

    private ToDoService service;
    private CarregarAtividadesTaskCallBack delegate;

    public CarregarAtividadesTask(CarregarAtividadesTaskCallBack delegate){

        // Configura o retrofit através da baseUrl e do conversor que irá transformar o resultado de JSON para Java
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://todo-backend-spring4-java8.herokuapp.com")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        // Solicita para que o Retrofit crie uma classe responsável pelo serviço
        this.service = retrofit.create(ToDoService.class);

        // Armazena o delegate para chamar informando sucesso ou falha
        this.delegate = delegate;
    }

    @Override
    // Método que é executado no Android através de uma Thread não principal
    protected List<Atividade> doInBackground(Void... voids) {
        // Cria a chamada para o método
        Call<List<Atividade>> carregarAtividadesCall = service.carregarAtividades();

        try{
            // Executa a chamada e pega o retorno para devolver para a task no método "onPostExecute"
            return carregarAtividadesCall.execute().body();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(List<Atividade> atividades) {
        if(atividades != null){
            delegate.onCarregarAtividadesSuccess(atividades);
        } else {
            delegate.onCarregarAtividadesFailure();
        }
    }

    public interface CarregarAtividadesTaskCallBack {

        public void onCarregarAtividadesSuccess(List<Atividade> atividades);
        public void onCarregarAtividadesFailure();

    }
}
