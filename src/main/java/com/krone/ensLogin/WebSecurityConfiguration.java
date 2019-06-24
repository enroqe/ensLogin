package com.krone.ensLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.krone.ensLogin.service.UserDetailsServiceImpl;


@EnableOAuth2Sso
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	  @Autowired UserDetailsServiceImpl userDetailsService;
	  
	  @Bean public BCryptPasswordEncoder passwordEncoder() { BCryptPasswordEncoder
	  bCryptPasswordEncoder = new BCryptPasswordEncoder(); return
	  bCryptPasswordEncoder; }
	  
	  
	  @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception {
	  
	  // Setting Service to find User in the database. // And Setting
	  //PasswordEncoder
	  auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()
	  );
	  
	  }
	 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		/*
		 * http.csrf() .disable() .antMatcher("/**") .authorizeRequests()
		 * .antMatchers("/", "/login") .permitAll() .anyRequest() .authenticated();
		 */
     // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
		
		  // The pages does not require login
		  http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
		  
		  // /userInfo page requires login as ROLE_USER or ROLE_ADMIN. // If no login,
		  //it will redirect to /login page.
		  http.authorizeRequests().antMatchers("/userInfo").
		  access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
		  
		  // For ADMIN only.
		  http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')"
		  );
		  
		  // When the user has logged in as XX. // But access a page that requires role
		   // AccessDeniedException will be thrown.
		  http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		 
    }
}
