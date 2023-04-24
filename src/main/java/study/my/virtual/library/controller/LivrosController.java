package study.my.virtual.library.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.my.virtual.library.livro.DadosCadastroLivro;

@RestController
@RequestMapping("livros")
public class LivrosController {
	
	@PostMapping
	public void cadastrar(@RequestBody DadosCadastroLivro dados) {
		System.out.println(dados);
	}

}
