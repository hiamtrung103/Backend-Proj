package fi.projecttest.testProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .authorizeRequests()
	        //.antMatchers("/admin/**").hasRole("ADMIN")
	          .anyRequest().permitAll()
	          .and()
	      .formLogin()
	          .loginPage("/login")
	          .defaultSuccessUrl("/admin/info")
	          .permitAll()
	          .and()
	      .logout()
	          .permitAll()
	          .logoutSuccessUrl("/index");
	    }
	 
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("adminPrisma").password("{noop}password1").roles("ADMIN").and()
                .withUser("adminLidl").password("{noop}password2").roles("ADMIN").and();
    }
}
