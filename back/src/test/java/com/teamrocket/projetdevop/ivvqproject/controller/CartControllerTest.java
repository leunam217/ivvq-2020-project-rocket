package com.teamrocket.projetdevop.ivvqproject.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.domain.ProductOrdered;
import com.teamrocket.projetdevop.ivvqproject.domain.ShoppingCart;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.service.ProductOrderedService;
import com.teamrocket.projetdevop.ivvqproject.service.ShoppingCartService;
import com.teamrocket.projetdevop.ivvqproject.service.UserService;
import com.teamrocket.projetdevop.ivvqproject.utils.AbstractRestControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
public class CartControllerTest extends AbstractRestControllerTest {

        @MockBean
        ShoppingCartService shoppingCartService;

        @MockBean
        UserService userService;

        @Mock
        PasswordEncoder passwordEncoder;

        @Autowired
        private ObjectMapper objectMapper;

        @MockBean
        ProductOrderedService productOrderedService;


        private List<ShoppingCart> carts;

      //Collection<ProductOrdered> productOrdereds;

            private User user;

        @BeforeEach
        void setup() {
         this.user = new User("bobemail.com",passwordEncoder.encode("secret"),"Bob","21345","Toulouse");
        this.carts = new ArrayList<>();
        this.carts.add(new ShoppingCart(user));

    }


   /** @Test
    void edit()  throws Exception
    {
        ShoppingCart cart = new ShoppingCart(user);
        String itemId = "B01";
        ProductOrdered productOrdered = new ProductOrdered();
        given(userService.findOne(user.getName())).willReturn(user);


       //productOrderedService.update("B01",3,user);
      // given(productOrderedService.findOne("B01",user)).willReturn(productOrdered);
        getMockMvc().perform(put("/cart/{itemId}",itemId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(cart)))
                .andExpect(status().isOk());
    }**/

}
