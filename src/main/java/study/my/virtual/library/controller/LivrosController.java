package study.my.virtual.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import study.my.virtual.library.livro.DadosCadastroLivro;
import study.my.virtual.library.livro.DadosListagemLivro;
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
	
	@GetMapping
	public List<DadosListagemLivro> listar() {
		return repository.findAll().stream().map(DadosListagemLivro::new).toList(); //convertendo lista de livros para lista de dados listagem livro
	}

}
