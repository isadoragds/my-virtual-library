package study.my.virtual.library.livro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroLivro(
		
		@NotBlank
		String titulo, 
		
		@NotBlank
		String autor, 
		
		String paginas, 
		
		@NotNull
		Genero genero, 
		
		@NotNull
		Localizacao localizacao, 
		
		@NotNull
		Status status) {

}
