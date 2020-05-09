package com.teamrocket.projetdevop.ivvqproject.security;

import com.teamrocket.projetdevop.ivvqproject.security.jws.JsonWebEntryPoint;
import com.teamrocket.projetdevop.ivvqproject.security.jws.JsonWebFilter;
import com.teamrocket.projetdevop.ivvqproject.security.jws.JsonWebProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@DependsOn("passwordEncoder")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    JsonWebFilter jwtFilter;

    @Autowired
    private JsonWebEntryPoint accessDenyHandler;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    @Qualifier("dataSource")
    DataSource dataSource;

    @Value("${users-query}")
    private String usersQuery;

    @Value("${roles-query}")
    private String rolesQuery;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.cors().and().csrf().disable()
                .authorizeRequests()

                .antMatchers("/profile/**").authenticated()
                .antMatchers("/cart/**").access("hasAnyRole('CUSTOMER')")
                .antMatchers("/order/finish/**").access("hasAnyRole('SELLER')")
                .antMatchers("/order/**").authenticated()
                .antMatchers("/profiles/**").authenticated()
                .antMatchers("/seller/product/new").access("hasAnyRole('SELLER')")
                .antMatchers("/seller/**/delete").access("hasAnyRole( 'SELLER')")
                .antMatchers("/seller/**").access("hasAnyRole('SELLER')")
                .anyRequest().permitAll()

                .and()
                .exceptionHandling().authenticationEntryPoint(accessDenyHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}