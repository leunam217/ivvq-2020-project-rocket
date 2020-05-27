package com.teamrocket.projetdevop.ivvqproject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        /*URI dbUri = new URI("postgres://trbbmncqbzuirq" +
                ":f9ab0cb034653f0eaa15052cb11347d040521f0665fb67eb7b02426470b74563@ec2-54-195-247-108.eu-west-1.compute.amazonaws.com:5432/d5nq4qb8hvrt7g");
        */

     String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?createDatabaseIfNotExist=true";


        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(dbUrl);

        return dataSource;
    }
}
