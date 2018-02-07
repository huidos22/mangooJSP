package com.huidos.mangooo.configuration;
import java.util.Collection;

/**
 * This class is 
 * 
 * @author <A HREF="mailto:[huidos22@gmail.com]">Juan Carlos Rivera</A>
 * @version Revision: 1.0 Date: 2016/12/07
 **/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.BeansEndpoint;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.endpoint.InfoEndpoint;
import org.springframework.boot.actuate.endpoint.RequestMappingEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.EndpointHandlerMapping;
import org.springframework.boot.actuate.endpoint.mvc.EndpointMvcAdapter;
import org.springframework.boot.actuate.endpoint.mvc.HealthMvcEndpoint;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class MangoooSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private MangooUserDetailService mangooUserDetailService;
	@Bean  
	   @Autowired  
	   //Define the HandlerMapping similar to RequestHandlerMapping to expose the endpoint  
	   public EndpointHandlerMapping endpointHandlerMapping(  
	     Collection<? extends MvcEndpoint> endpoints  
	   ){  
	     return new EndpointHandlerMapping(endpoints);  
	   }  

	   @Bean  
	   @Autowired  
	   //define the HealthPoint endpoint  
	   public HealthMvcEndpoint healthMvcEndpoint(HealthEndpoint delegate){  
	     return new HealthMvcEndpoint(delegate, false);  
	   }  

	   @Bean  
	   @Autowired  
	   //define the Info endpoint  
	   public EndpointMvcAdapter infoMvcEndPoint(InfoEndpoint delegate){  
	      return new EndpointMvcAdapter(delegate);  
	   }  

	   @Bean  
	   @Autowired  
	   //define the beans endpoint  
	   public EndpointMvcAdapter beansEndPoint(BeansEndpoint delegate){  
	     return new EndpointMvcAdapter(delegate);  
	   }  

	   @Bean  
	   @Autowired  
	   //define the mappings endpoint  
	   public EndpointMvcAdapter requestMappingEndPoint(  
	     RequestMappingEndpoint delegate  
	   ){  
	     return new EndpointMvcAdapter(delegate);  
	  }  
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		// authenticationMgr.inMemoryAuthentication().withUser("pollo").password("123").authorities("ROLE_USER");
		// authenticationMgr.authenticationProvider(new
		// AdminSecurityAuthenticationProvider());
		authenticationMgr.userDetailsService(mangooUserDetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().maximumSessions(2);
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		http.sessionManagement().invalidSessionUrl("/invalidSession");

		http.authorizeRequests().antMatchers("/homePage").access("hasRole('ROLE_USER')").and().formLogin()
				.loginPage("/loginPage").defaultSuccessUrl("/homePage").failureUrl("/loginPage?error")
				.usernameParameter("username").passwordParameter("password").and().logout()
				.logoutSuccessUrl("/loginPage?logout");

	}
}
