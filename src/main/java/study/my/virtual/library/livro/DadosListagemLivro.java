package study.my.virtual.library.livro;

public record DadosListagemLivro(String titulo, String autor, Genero genero, Status status) {
	
	public DadosListagemLivro(Livro livros) {
		this(livros.getTitulo(), livros.getAutor(), livros.getGenero(), livros.getStatus());
	}

}
