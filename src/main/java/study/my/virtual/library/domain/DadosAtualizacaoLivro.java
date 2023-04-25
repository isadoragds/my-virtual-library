package study.my.virtual.library.domain;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoLivro(
		
		@NotNull
		Long id,  
		Localizacao localizacao, 
		Status status) {

}
