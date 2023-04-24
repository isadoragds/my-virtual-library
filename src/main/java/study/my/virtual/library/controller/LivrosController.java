package study.my.virtual.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import study.my.virtual.library.livro.DadosAtualizacaoLivro;
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
	public Page<DadosListagemLivro> listar(@PageableDefault(size = 10, sort = {"autor"}) Pageable paginacao) {
		return repository.findAll(paginacao).map(DadosListagemLivro::new); //convertendo lista de livros para lista de dados listagem livro
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizacaoLivro dados) {
		var livros = repository.getReferenceById(dados.id());
		livros.atualizarInformacoes(dados);
	}

}
