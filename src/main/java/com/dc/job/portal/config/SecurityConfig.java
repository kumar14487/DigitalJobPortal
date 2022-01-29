package com.dc.job.portal.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	 @Value("${actuator.username:null}")
     private String actuatorUsername;

     @Value("${actuator.password:null}")
     private String actuatorPassword;
     
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.antMatcher("/actuator/**").authorizeRequests().requestMatchers(EndpointRequest.to("info", "health"))
				.permitAll() // To bypass security for this Endpoint's
				.anyRequest().hasRole("ACTUATOR") // Any other endpoint
				.and().httpBasic().and().csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser(actuatorUsername)
				.password(encoder.encode(actuatorPassword)).roles("ACTUATOR");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.requestMatchers(new OrRequestMatcher(new OrRequestMatcher(
						EndpointRequest.to("info", "health", "httptrace"), new AntPathRequestMatcher("/v2/api-docs"),
						new AntPathRequestMatcher("/configuration/ui"),
						new AntPathRequestMatcher("/swagger-resources/**"),
						new AntPathRequestMatcher("/configuration/**"), new AntPathRequestMatcher("/swagger-ui.html**"),
						new AntPathRequestMatcher("/logout****"), new AntPathRequestMatcher("/webjars/**"),
						new AntPathRequestMatcher("/favicon.ico"))));
	}

}
