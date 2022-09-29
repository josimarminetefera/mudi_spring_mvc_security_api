package br.com.alura.mvc.mudi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration // classe de configuração do spring
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// configuração de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("----------------- WebSecurityConfig Adapter configure");
		// http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
		http.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.formLogin(form -> form.loginPage("/login").permitAll())
			.logout(logout -> logout.logoutUrl("/logout"));
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("joao").password("joao").roles("ADM").build();

		return new InMemoryUserDetailsManager(user);
	}
}
