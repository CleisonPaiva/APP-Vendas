package com.example.project2.Model;

import java.io.Serializable;

public class Show_Clientes implements Serializable {


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String nome,cpf,tipo,email,id;

    public Show_Clientes(String ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String getUltimaAlteracao() {
        return ultimaAlteracao;
    }

    public void setUltimaAlteracao(String ultimaAlteracao) {
        this.ultimaAlteracao = ultimaAlteracao;
    }

    public String ultimaAlteracao;


    public Show_Clientes(String id, String nome, String cpf, String tipo, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.tipo = tipo;
        this.email = email;
    }
    public Show_Clientes(){

    }

    @Override
    public String toString() {
        return "Show_Clientes{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", tipo='" + tipo + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", ultimaAlteracao='" + ultimaAlteracao + '\'' +
                '}';
    }
}
