package com.projeto.projeto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.projeto.model.Produto;

@RestController
@RequestMapping("/produto")
public class ApiController {
    public List<Produto> produtos = new ArrayList<>();

    public ApiController(){
        produtos.add(new Produto ("Casaco", 40.0));
        produtos.add(new Produto ("Doritos", 15.0));
        produtos.add(new Produto ("Boné", 20.0));
    }

    @GetMapping
    public List<Produto> getProdutos(){
        return produtos;
    }
}
