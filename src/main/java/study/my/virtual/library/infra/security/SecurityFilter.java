package study.my.virtual.library.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import study.my.virtual.library.domain.usuario.UsuarioRepository;

/**
 * Filter é um dos recursos que fazem parte da especificação de Servlets, a qual padroniza o tratamento de requisições e respostas em aplicações Web no Java. 
 * Ou seja, tal recurso não é específico do Spring, podendo assim ser utilizado em qualquer aplicação Java.

É um recurso muito útil para isolar códigos de infraestrutura da aplicação, como, por exemplo, segurança, logs e auditoria, 
para que tais códigos não sejam duplicados e misturados aos códigos relacionados às regras de negócio da aplicação.
 * **/


//Classe genérica - não é servico, interface, configuração etc
@Component 
public class SecurityFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository repository;
	
	//OncePerRequest -> classe garante que o filtro será executado uma unica vez por requisicao

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		//System.out.println("chamando filter");
		
		//recuperando o token
		var tokenJWT = recuperarToken(request);
		
		if (tokenJWT != null) {
			var subject = tokenService.getSubject(tokenJWT);
			var usuario = repository.findByLogin(subject);
			
			var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
			//System.out.println("logado");
		}
		
		//filterChain representa a cadeia de filtros da requisicao
		//para chamar os servicos e as proximas camadas da aplicacao, é necessario chamar o proximo filtro
		//se quiser interromper e nao chamar o prox, nao chama o do filter
		filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null) {
			return authorizationHeader.replaceAll("Bearer ", "");
		}
		
		return null;
	}
	
}
