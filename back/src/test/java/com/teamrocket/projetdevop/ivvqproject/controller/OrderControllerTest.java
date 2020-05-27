package com.teamrocket.projetdevop.ivvqproject.controller;
/**
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamrocket.projetdevop.ivvqproject.domain.Order;
import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.service.OrderService;
import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import com.teamrocket.projetdevop.ivvqproject.utils.AbstractRestControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
public class OrderControllerTest extends AbstractRestControllerTest {

    @MockBean
    OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;


    private List<Order> orderList;


    @BeforeEach
    public void setUp() {
        this.orderList = new ArrayList<>();
        this.orderList.add(new Order());
    }

    @Test
    void orderHistoric() throws Exception {

        given(orderService.findAll()).willReturn(orderList);
        getMockMvc().perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(orderList.size())));
    }





}
**/