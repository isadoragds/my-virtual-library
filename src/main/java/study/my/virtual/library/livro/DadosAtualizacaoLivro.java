package study.my.virtual.library.livro;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoLivro(
		
		@NotNull
		Long id,  
		Localizacao localizacao, 
		Status status) {

}
