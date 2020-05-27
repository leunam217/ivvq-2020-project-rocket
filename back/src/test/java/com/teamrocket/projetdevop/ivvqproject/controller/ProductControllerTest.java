package com.teamrocket.projetdevop.ivvqproject.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamrocket.projetdevop.ivvqproject.domain.Product;
import com.teamrocket.projetdevop.ivvqproject.domain.User;
import com.teamrocket.projetdevop.ivvqproject.repositories.UserRepository;
import com.teamrocket.projetdevop.ivvqproject.security.JWT.JsonWebTokenFilter;
import com.teamrocket.projetdevop.ivvqproject.security.JWT.JsonWebTokenProvider;
import com.teamrocket.projetdevop.ivvqproject.security.config.SpringSecurityConfig;
import com.teamrocket.projetdevop.ivvqproject.service.ProductService;
import com.teamrocket.projetdevop.ivvqproject.service.impl.ProductServiceImpl;
import com.teamrocket.projetdevop.ivvqproject.utils.AbstractRestControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.shaded.org.apache.commons.codec.binary.Base64;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;


import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;


@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTest extends AbstractRestControllerTest{

    @MockBean
    ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;


    private List<Product> productList;

    @BeforeEach
    void setup() {


        this.productList = new ArrayList<>();
        this.productList.add(new Product("B001", "Rocket", new BigDecimal(123), 100,"desc"));
        this.productList.add(new Product("B002", "Rocket1", new BigDecimal(123), 100,"desc"));
        this.productList.add(new Product("B003", "Rocket2", new BigDecimal(123), 100,"desc"));

    }

    @Test
    void should_fetch_all_products() throws Exception {
        given(productService.findAll()).willReturn(productList);

        getMockMvc().perform(get("/product"))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.size()",is(productList.size())));
    }


    @Test
    void shouldFetchOneProductById() throws Exception {

        final String productId = "B001";
        final Product product = new Product("B001", "Rocket", new BigDecimal(123), 100,"desc");

        given(productService.findOne(productId)).willReturn(Optional.of(product).get());

        getMockMvc().perform(get("/product/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId", is(product.getProductId())))
                .andExpect(jsonPath("$.productName", is(product.getProductName())))
                .andExpect(jsonPath("$.productStock", is(product.getProductStock())))
                .andExpect(jsonPath("$.productDescription", is(product.getProductDescription())));
    }


    @Test
    void add_product() throws Exception {
        given(productService.save(any(Product.class))).willAnswer((invocation) -> invocation.getArgument(0));
        Product product = new Product("B01", "Rocket", new BigDecimal(123), 100,"desc");

        getMockMvc().perform(post("/seller/product/new")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());
    }

    @Test
    void edit_product() throws Exception {
        String productId = "B01";
        Product product = new Product("B01", "Rocket", new BigDecimal(123), 100,"desc");
        product.setProductStock(12);
        given(productService.update(product)).willAnswer((invocation) -> invocation.getArgument(0));
        getMockMvc().perform(put("/seller/product/{id}/edit", productId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());
    }


    @Test
    void delete_product() throws Exception {
        String productId = "B001";
        Product product = new Product("B01", "Rocket", new BigDecimal(123), 100,"desc");
        given(productService.findOne(productId)).willReturn(Optional.of(product).get());
        doNothing().when(productService).delete(product.getProductId());

        this.getMockMvc().perform(delete("/seller/product/{id}/delete", product.getProductId()))
                .andExpect(status().isOk());
    }



}
