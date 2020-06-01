package com.teamrocket.projetdevop.ivvqproject.controller;
/**
 * import com.fasterxml.jackson.databind.ObjectMapper; import
 * com.teamrocket.projetdevop.ivvqproject.domain.Order; import
 * com.teamrocket.projetdevop.ivvqproject.domain.Product; import
 * com.teamrocket.projetdevop.ivvqproject.service.OrderService; import
 * com.teamrocket.projetdevop.ivvqproject.service.ProductService; import
 * com.teamrocket.projetdevop.ivvqproject.utils.AbstractRestControllerTest;
 * import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.Mock;
 * import org.mockito.junit.jupiter.MockitoExtension; import
 * org.mockito.junit.jupiter.MockitoSettings; import
 * org.mockito.quality.Strictness; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 * import org.springframework.boot.test.mock.mockito.MockBean; import
 * org.springframework.security.core.Authentication; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority;
 * 
 * import java.math.BigDecimal; import java.util.ArrayList; import
 * java.util.List; import java.util.Optional;
 * 
 * import static org.hamcrest.CoreMatchers.is; import static
 * org.mockito.BDDMockito.given; import static org.mockito.Mockito.never; import
 * static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * 
 * @ExtendWith(MockitoExtension.class) @AutoConfigureMockMvc(addFilters = false)
 * @MockitoSettings(strictness = Strictness.LENIENT) public class
 *                             OrderControllerTest extends
 *                             AbstractRestControllerTest {
 * 
 * @MockBean OrderService orderService;
 * 
 * @Autowired private ObjectMapper objectMapper;
 * 
 * 
 *            private List<Order> orderList;
 * 
 * @MockBean private Order order;
 * 
 * @Mock Authentication authentication;
 * 
 * 
 * @BeforeEach public void setUp() { this.order = new Order(1L, "bob@email.com",
 *             "bob","1234","toulouse", new BigDecimal(10),"confirmé");
 *             this.orderList = new ArrayList<>(); this.orderList.add(new
 *             Order()); }
 * 
 * @Test void orderHistoric() throws Exception {
 * 
 *       given(authentication.getAuthorities().contains(new
 *       SimpleGrantedAuthority("ROLE_CUSTOMER"))).will(invocation ->
 *       invocation.getArgument(0));
 *       given(orderService.findByBuyerEmail(order.getBuyerEmail())).willReturn(orderList);
 *       getMockMvc().perform(get("/order")) .andExpect(status().isOk())
 *       .andExpect(jsonPath("$.size()",is(orderList.size()))); }
 * 
 * 
 *       }
 **/