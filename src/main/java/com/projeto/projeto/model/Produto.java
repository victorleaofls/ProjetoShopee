package com.projeto.projeto.model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int clienteId;

    public Produto() {}

    public Produto(int id, String nome, double preco, int clienteId) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.clienteId = clienteId;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
    
    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
