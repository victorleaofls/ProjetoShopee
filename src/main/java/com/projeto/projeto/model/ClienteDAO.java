package com.projeto.projeto.model;

import javax.sql.DataSource;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAO {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    // Inserir novo cliente
    public void inserirCliente(Cliente cli) {
        String sql = "INSERT INTO cliente (nome, cpf, email, senha) VALUES (?, ?, ?, ?)";
        Object[] parametros = {
            cli.getNome(),
            cli.getCpf(),
            cli.getEmail(),
            cli.getSenha()
        };
        jdbc.update(sql, parametros);
    }

    // Buscar cliente para autenticação (login)
    public Cliente buscarPorEmailSenha(String email, String senha) {
        String sql = "SELECT * FROM cliente WHERE email = ? AND senha = ?";
        try {
            return jdbc.queryForObject(sql, new Object[]{email, senha}, (rs, rowNum) -> {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setSenha(rs.getString("senha"));
                return c;
            });
        } catch (EmptyResultDataAccessException e) {
            return null; // nenhum cliente encontrado com esse e-mail e senha
        }
    }
}
