package study.my.virtual.library.domain;

public record DadosDetalhamentoLivro(Long id, String titulo, String autor, String paginas, Genero genero, Localizacao localizacao, Status status) {
	
	public DadosDetalhamentoLivro(Livro livro) {
		this(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getPaginas(), livro.getGenero(), livro.getLocalizacao(), livro.getStatus());
	}
}
