package br.com.alura.mvc.mudi;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration // classe de configuração do spring
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	// configuração de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("----------------- WebSecurityConfig Adapter configure");
		// http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
		http.authorizeRequests() // obriga entrar autorizado
			.anyRequest() // qualquer request
			.authenticated() // tem que estar autorizado
			.and()
			.formLogin(form -> 
				form.loginPage("/login")
				.defaultSuccessUrl("/home", true)
				.permitAll() // login não precisa estar logado
			)
			.logout(logout -> 
				logout.logoutUrl("/logout")//deslogar o usuário
			);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("----------------- WebSecurityConfig AuthenticationManagerBuilder configure");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // o que gera a criptografia boa
		
		//Criar usuário inicial
		//UserDetails user = User.builder().username("joao").password(encoder.encode("joao")).roles("ADM").build();
		
		auth
		.jdbcAuthentication()
		.dataSource(dataSource)
		.passwordEncoder(encoder);
		//.withUser(user);
	}

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.withDefaultPasswordEncoder().username("joao").password("joao").roles("ADM").build();
//		return new InMemoryUserDetailsManager(user);
//	}
}
