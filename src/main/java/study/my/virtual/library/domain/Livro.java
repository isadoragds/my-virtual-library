package study.my.virtual.library.domain;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Table(name = "livros")
@Entity(name = "Livro")

//Anotacoes Lombok para substituir os códigos padroes java
public class Livro {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String autor;
	private String paginas;
	
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@Enumerated(EnumType.STRING)
	private Localizacao localizacao;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Livro() {
	}
	
	public Livro(DadosCadastroLivro dados) {
		this.titulo = dados.titulo();
		this.autor = dados.autor();
		this.paginas = dados.paginas();
		this.genero = dados.genero();
		this.localizacao = dados.localizacao();
		this.status = dados.status();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Livro(Long id, String titulo, String autor, String paginas, Genero genero, Localizacao localizacao,
			Status status) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.genero = genero;
		this.localizacao = localizacao;
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}

	public void atualizarInformacoes(@Valid DadosAtualizacaoLivro dados) {
		if (dados.localizacao() != null) {
			this.localizacao = dados.localizacao();			
		}
		if (dados.status() != null) {
			this.status = dados.status();			
		}
	}
	
	


}
