package com.projeto.projeto.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteDAO cdao;

    public void inserirCliente(Cliente cli) {
        cdao.inserirCliente(cli);
    }

    public Cliente autenticarCliente(String email, String senha) {
        return cdao.buscarPorEmailSenha(email, senha);
    }
}