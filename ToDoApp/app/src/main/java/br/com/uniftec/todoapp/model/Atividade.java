package br.com.uniftec.todoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by marioklein on 20/04/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Atividade {

    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String titulo;
    @JsonProperty("url")
    private String nota;
    @JsonProperty("completed")
    private boolean concluida;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }
}
