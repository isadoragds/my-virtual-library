package study.my.virtual.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import study.my.virtual.library.livro.DadosCadastroLivro;
import study.my.virtual.library.livro.Livro;
import study.my.virtual.library.livro.LivroRepository;

@RestController
@RequestMapping("livros")
public class LivrosController {
	
	@Autowired
	private LivroRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroLivro dados) {
		repository.save(new Livro(dados));
	}

}
