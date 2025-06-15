package com.projeto.projeto.controller;

import com.projeto.projeto.model.Produto;
import com.projeto.projeto.model.ProdutoService;
import com.projeto.projeto.model.Cliente;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/produto")
public String listarProdutos(Model model, HttpSession session) {
    Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
    if (cliente != null) {
        try {
            model.addAttribute("produtos", produtoService.listarPorCliente(cliente.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("produtos", List.of());
        }
    } else {
        model.addAttribute("produtos", List.of());
    }
    model.addAttribute("usuarioLogado", cliente);
    return "produtos";
}


    @GetMapping("/produto/novo")
    public String novoProdutoForm(Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        if (cliente == null) {
            return "redirect:/login";
        }
        model.addAttribute("produto", new Produto());
        return "form-produto";
    }

    @PostMapping("/produto/salvar")
    public String salvarProduto(@ModelAttribute Produto produto, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        if (cliente == null) {
            return "redirect:/login";
        }
        produto.setClienteId(cliente.getId());
        produtoService.inserirProduto(produto);
        return "redirect:/produto";
    }

    @GetMapping("/produto/editar/{id}")
    public String editarProduto(@PathVariable int id, Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        if (cliente == null) {
            return "redirect:/login";
        }
        Produto produto = produtoService.buscarPorId(id);
        
        if (produto == null || produto.getClienteId() != cliente.getId()) {
            return "redirect:/produto";
        }
        model.addAttribute("produto", produto);
        return "form-produto";
    }

    @PostMapping("/produto/atualizar")
public String atualizarProduto(@ModelAttribute Produto produto, HttpSession session) {
    Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");

    if (cliente == null) {
        return "redirect:/login";
    }

    Produto existente = produtoService.buscarPorId(produto.getId());
    if (existente == null || existente.getClienteId() != cliente.getId()) {
        return "redirect:/produto";
    }
    produto.setClienteId(cliente.getId());

    produtoService.atualizarProduto(produto);
    return "redirect:/produto";
}


    @GetMapping("/produto/deletar/{id}")
    public String deletarProduto(@PathVariable int id, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        if (cliente == null) {
            return "redirect:/login";
        }
        Produto produto = produtoService.buscarPorId(id);
        if (produto != null && produto.getClienteId() == cliente.getId()) {
            produtoService.deletarProduto(id);
        }
        return "redirect:/produto";
    }
}
