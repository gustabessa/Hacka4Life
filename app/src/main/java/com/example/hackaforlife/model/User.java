package com.example.hackaforlife.model;

public class User {
    private String login;
    private String senha;
    private String nomeUnidadeSaude;
    private String id;

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", nomeUnidadeSaude='" + nomeUnidadeSaude + '\'' +
                ", id=" + id +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNomeUnidadeSaude() {
        return nomeUnidadeSaude;
    }

    public void setNomeUnidadeSaude(String nomeUnidadeSaude) {
        this.nomeUnidadeSaude = nomeUnidadeSaude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
