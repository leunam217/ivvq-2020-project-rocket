package com.teamrocket.projetdevop.ivvqproject.security.config;

import com.teamrocket.projetdevop.ivvqproject.security.JWT.JsonWebTokenEntryPoint;
import com.teamrocket.projetdevop.ivvqproject.security.JWT.JsonWebTokenFilter;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@DependsOn("passwordEncoder")
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    JsonWebTokenFilter jsonWebTokenFilter;
    @Autowired
    private JsonWebTokenEntryPoint accessDenyHandler;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

               //.antMatchers("/profile/**").authenticated()
                .antMatchers("/cart/**").access("hasRole('ROLE_CUSTOMER')")
                .antMatchers("/order/finish/**").access("hasRole('ROLE_SELLER')")
                .antMatchers("/order/**").authenticated()
                //.antMatchers("/profiles/**").authenticated()
                .antMatchers("/seller/product/new").access("hasRole('ROLE_SELLER')")
                .antMatchers("/seller/**/delete").access("hasRole('ROLE_SELLER')")
                .antMatchers("/seller/**").access("hasRole('ROLE_SELLER')")
                .anyRequest().permitAll()

                .and()
                .exceptionHandling().authenticationEntryPoint(accessDenyHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jsonWebTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
