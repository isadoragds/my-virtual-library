package study.my.virtual.library.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import study.my.virtual.library.domain.DadosAtualizacaoLivro;
import study.my.virtual.library.domain.DadosCadastroLivro;
import study.my.virtual.library.domain.DadosDetalhamentoLivro;
import study.my.virtual.library.domain.DadosListagemLivro;
import study.my.virtual.library.domain.Livro;
import study.my.virtual.library.domain.LivroRepository;
import study.my.virtual.library.domain.LivroService;

@RestController
@RequestMapping("livros")
public class LivrosController {
	
	@Autowired
	private LivroRepository repository;
	
	@Autowired
	private LivroService livroService;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLivro dados, UriComponentsBuilder uriBuilder) {
		var livros = new Livro(dados);
		repository.save(livros);
		
		var uri = uriBuilder.path("/livros/{id}").buildAndExpand(livros.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoLivro(livros));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemLivro>> listar(@PageableDefault(size = 10, sort = {"autor"}) Pageable paginacao) {
		var page = repository.findAll(paginacao).map(DadosListagemLivro::new); //convertendo lista de livros para lista de dados listagem livro
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoLivro dados) {
		var livros = repository.getReferenceById(dados.id());
		livros.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoLivro(livros));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var livro = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoLivro(livro));
	}
	

}
