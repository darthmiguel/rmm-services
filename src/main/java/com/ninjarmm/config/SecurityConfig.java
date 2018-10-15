package com.ninjarmm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
    throws Exception{
    auth
      .jdbcAuthentication()
      .dataSource(dataSource)
      .passwordEncoder(new BCryptPasswordEncoder())
      .usersByUsernameQuery("select login, password, enabled from customer where login = ?")
      .authoritiesByUsernameQuery("select c.login as username, r.role_name as role from customer c, role r where c.id = r.customer_id and c.login = ?");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
      .authorizeRequests()
      .anyRequest().hasRole("USER")
      .and()
      .httpBasic();
  }

}
