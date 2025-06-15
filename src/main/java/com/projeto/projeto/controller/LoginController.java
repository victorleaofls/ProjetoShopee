package com.projeto.projeto.controller;

import com.projeto.projeto.model.Cliente;
import com.projeto.projeto.model.ClienteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private ClienteService clienteService;

    // Exibe a página de login
    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(value = "erro", required = false) String erro,
            org.springframework.ui.Model model) {
        if (erro != null) {
            model.addAttribute("msgErro", "Email ou senha inválidos!");
        }
        return "login"; // nome do template login.html
    }

    @PostMapping("/login")
    public String processarLogin(@RequestParam String email,
            @RequestParam String senha,
            HttpSession session,
            org.springframework.ui.Model model) {
        try {
            Cliente cliente = clienteService.autenticarCliente(email, senha);
            if (cliente != null) {
                session.setAttribute("usuarioLogado", cliente);
                return "redirect:/pagina";
            } else {
                return "redirect:/login?erro=true";
            }
        } catch (Exception e) {
            return "redirect:/login?erro=true";
        }
    }

    // Opcional: rota para logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

}