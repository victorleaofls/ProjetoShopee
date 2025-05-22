package com.projeto.projeto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PaginaController {

    @GetMapping("")
    public String principal() {
        return "pagina";
    }

    @GetMapping("/pagina")
    public String pagina() {
        return "pagina";
    }

    @GetMapping("/api/produto")
    public String produtos() {
        return "produto";
    }
}
