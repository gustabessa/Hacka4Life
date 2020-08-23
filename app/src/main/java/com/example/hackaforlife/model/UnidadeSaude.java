package com.example.hackaforlife.model;

import android.database.Observable;

import androidx.lifecycle.LiveData;

public class UnidadeSaude extends Observable {
    private String endereco;
    private String nome;
    private String tipo_unidade;
    private String modeloVisto;
    private Long numero;
    private Long contato;

    public String getModeloVisto() {
        return modeloVisto;
    }

    public void setModeloVisto(String modeloVisto) {
        this.modeloVisto = modeloVisto;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo_unidade() {
        return tipo_unidade;
    }

    public void setTipo_unidade(String tipo_unidade) {
        this.tipo_unidade = tipo_unidade;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Long getContato() {
        return contato;
    }

    public void setContato(Long contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "UnidadeSaude{" +
                "endereco='" + endereco + '\'' +
                ", nome='" + nome + '\'' +
                ", tipo_unidade='" + tipo_unidade + '\'' +
                ", numero=" + numero +
                ", contato=" + contato +
                '}';
    }
}
