package com.projeto.projeto.model;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class ProdutoDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirProduto(Produto produto) {
        String sql = "INSERT INTO produto(nome, preco, cliente_id) VALUES (?, ?, ?)";
        jdbc.update(sql, produto.getNome(), produto.getPreco(), produto.getClienteId());
    }

    public List<Produto> listarPorCliente(int clienteId) {
        String sql = "SELECT * FROM produto WHERE cliente_id = ?";
        return jdbc.query(sql, new Object[] { clienteId }, (rs, rowNum) -> new Produto(rs.getInt("id"),
                rs.getString("nome"), rs.getDouble("preco"), rs.getInt("cliente_id")));
    }

    public Produto buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        return jdbc.queryForObject(sql, new Object[] { id }, (rs, rowNum) -> new Produto(rs.getInt("id"),
                rs.getString("nome"), rs.getDouble("preco"), rs.getInt("cliente_id")));
    }

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ? WHERE id = ? AND cliente_id = ?";
        jdbc.update(sql, produto.getNome(), produto.getPreco(), produto.getId(), produto.getClienteId());
    }

    public void deletarProduto(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        jdbc.update(sql, id);
    }

}