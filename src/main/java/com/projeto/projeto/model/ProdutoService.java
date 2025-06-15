package com.projeto.projeto.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoDAO pdao;

    public void inserirProduto(Produto p) {
        pdao.inserirProduto(p);
    }

    public List<Produto> listarPorCliente(int clienteId) {
        return pdao.listarPorCliente(clienteId);
    }

    public Produto buscarPorId(int id) {
        return pdao.buscarPorId(id);
    }

    public void atualizarProduto(Produto p) {
        pdao.atualizarProduto(p);
    }

    public void deletarProduto(int id) {
        pdao.deletarProduto(id);
    }

}