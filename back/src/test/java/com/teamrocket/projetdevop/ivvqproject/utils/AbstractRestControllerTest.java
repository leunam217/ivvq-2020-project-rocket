package com.teamrocket.projetdevop.ivvqproject.utils;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = "databaseHeroku=false")
@AutoConfigureMockMvc
public abstract class AbstractRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {

		SecurityContextHolder.clearContext();
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}

}
