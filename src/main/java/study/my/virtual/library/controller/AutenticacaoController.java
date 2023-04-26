package study.my.virtual.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import study.my.virtual.library.domain.usuario.DadosAutenticacao;
import study.my.virtual.library.domain.usuario.Usuario;
import study.my.virtual.library.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
		var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authentication =  manager.authenticate(token);
		
		return ResponseEntity.ok(tokenService.gerarToken((Usuario) authentication.getPrincipal()));
	}
	
}
