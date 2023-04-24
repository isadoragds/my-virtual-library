package study.my.virtual.library.livro;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "livros")
@Entity(name = "Livro")

//Anotacoes Lombok para substituir os códigos padroes java
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Livro {
	
	public Livro(DadosCadastroLivro dados) {
		this.titulo = dados.titulo();
		this.autor = dados.autor();
		this.paginas = dados.paginas();
		this.genero = dados.genero();
		this.localizacao = dados.localizacao();
		this.status = dados.status();
	}

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

}
