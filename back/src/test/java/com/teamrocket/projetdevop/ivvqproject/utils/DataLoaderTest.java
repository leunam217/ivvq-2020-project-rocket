package com.teamrocket.projetdevop.ivvqproject.utils;

import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import com.teamrocket.projetdevop.ivvqproject.util.DataLoader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;



@ExtendWith(MockitoExtension.class)
public class DataLoaderTest {

    private DataLoader dataLoader;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        dataLoader = new DataLoader(productService);
    }

    @Test
    public void testDataLoaderIsAnApplicationRunner(){

        assertThat(dataLoader).isNotNull();
        // un DataLoader est un ApplicationRunner
        assertTrue(dataLoader instanceof ApplicationRunner);
    }

}
