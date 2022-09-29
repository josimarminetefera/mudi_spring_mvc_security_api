package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@GetMapping
	@RequestMapping("/login")
	public String login() {
		System.out.println("----------------- LoginController /login");
		// login não faz nada só redireciona para a página
		return "login";
	}
}
