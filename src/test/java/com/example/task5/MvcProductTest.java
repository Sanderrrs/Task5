package com.example.task5;

import com.example.task5.servicies.CreateAccountService;
import com.example.task5.servicies.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class MvcProductTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    ProductService productService;

    @MockBean
    CreateAccountService createAccountService;

    @Test
    public void instanceCreateIncorrectRequestTest() throws Exception{
        mockMvc.perform(post("/corporate-settlement-instance/create")
                        .contentType("application/json")
                        .content(Constants.incorrectInstanceRequest()))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void instanceCreateCorrectRequestTest() throws Exception {
        Mockito.when(this.productService.saveRequest(Mockito.any()))
                .thenReturn(Constants.correctProductResponse());

        mockMvc.perform(post("/corporate-settlement-instance/create")
                        .contentType("application/json")
                        .content(Constants.fromFile("correctProduct.json")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.instanceId").value(2))
                .andExpect(jsonPath("$.data.registerId[0]").value(2));
    }
}
