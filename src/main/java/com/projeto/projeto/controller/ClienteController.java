package com.projeto.projeto.controller;

import com.projeto.projeto.model.Cliente;
import com.projeto.projeto.model.ClienteService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/usuario")
    public String perfilUsuario(Model model, HttpSession session) {
        Cliente cliente = (Cliente) session.getAttribute("usuarioLogado");
        if (cliente == null) {
            return "redirect:/login";
        }

        model.addAttribute("cliente", cliente);
        return "perfil-usuario"; // nova view personalizada
    }

    @GetMapping("/usuario/cadastro")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastro"; // view do formulário de cadastro
    }

    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute Cliente cliente) {
        clienteService.inserirCliente(cliente);
        return "redirect:/login"; // Correto: volta para login após cadastro
    }

}
