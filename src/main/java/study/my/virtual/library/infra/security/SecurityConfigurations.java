package study.my.virtual.library.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Bean //para devolver um obj para o spring, podendo injeta-lo em algum service
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http.csrf().disable() //proprio tolken faz a protecao contra ataque csrf, por isso a desitivacao no spring security
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
				.and().build();	//trocando a sessao de statfull para stateless -> API Rest, configuracao sera personalizada por url e o formulario sera feito no frontend
	}
	
	@Bean //ensinando o Spring como criar o objeto para injeta-lo, no caso, na classe AutenticacaoController
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
